package com.dabai.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.dabai.dao.SeckillDao;
import com.dabai.dao.SeckillSuccessDao;
import com.dabai.dao.cache.RedisDao;
import com.dabai.dto.Exposer;
import com.dabai.dto.SeckillExecution;
import com.dabai.entity.Seckill;
import com.dabai.entity.SeckillSuccess;
import com.dabai.enums.SeckillStateEnum;
import com.dabai.exception.SeckillCloseException;
import com.dabai.exception.SeckillException;
import com.dabai.exception.SeckillRepeatException;
import com.dabai.service.SeckillService;

/**
 * Created by 人杰 on 2017/2/14.
 */
// @Component:@Service.@Dao,@Contorller
@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired // @Resource
	private SeckillDao seckillDao;
	@Autowired
	private SeckillSuccessDao seckillSuccessDao;
	@Autowired
	private RedisDao redisDao;
	// md5盐值，用来混淆
	private String salt = "r23fbweiblgveqbwgioq;ehg";

	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 10);
	}

	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		// 优化点:缓存优化
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) {
			seckill = this.getById(seckillId);
			if (null == seckill) {
				return new Exposer(false, seckillId);
			} else {
				redisDao.putSeckill(seckill);
			}
		}
		long startTime = seckill.getStartTime().getTime();
		long endTime = seckill.getEndTime().getTime();
		// 系统当前时间
		long nowTime = new Date().getTime();
		if (nowTime < startTime || nowTime > endTime) {
			return new Exposer(false, seckillId, nowTime, startTime, endTime);
		}
		String md5 = this.getMD5(seckillId);
		return new Exposer(true, md5, seckillId);

	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	public SeckillExecution executeSeckill(long seckillId, String md5, long userPhone)
			throws SeckillException, SeckillRepeatException, SeckillCloseException {
		if (null == md5 || !this.getMD5(seckillId).equals(md5)) {
			throw new SecurityException("seckill data rewrite");
		}
		// 执行秒杀逻辑，减库存+记录购买行为
		Date nowTime = new Date(); // 有问题，当前时间的误差
		int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
		// 一旦发生错误，spring会帮助我们回滚
		try {
			if (updateCount <= 0) {
				// 没有更新到记录
				throw new SeckillCloseException("seckill is closed");
			} else {
				// 记录购买行为
				int insertCount = seckillSuccessDao.insertSeckillSuccess(seckillId, userPhone);
				// 唯一:seckillId,userPhone
				if (insertCount <= 0) {
					// 重复购买
					throw new SeckillRepeatException("seckill Repeated");
				} else {
					// 秒杀成功
					SeckillSuccess seckillSuccess = seckillSuccessDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, seckillSuccess);
				}
			}
		} catch (SeckillRepeatException e1) {
			throw e1;
		} catch (SeckillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 将编译时异常转换成运行时异常
			throw new SeckillException("seckill inner exception" + e.getMessage());
		}
	}

	@Override
	public SeckillExecution executeSeckillProcedure(long seckillId, String md5, long userPhone) {
		if (null == md5 || !this.getMD5(seckillId).equals(md5)) {
			throw new SecurityException("seckill data rewrite");
		}
		Date nowTime = new Date(); // 有问题，当前时间的误差
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("seckillId", seckillId);
		paramsMap.put("userPhone", userPhone);
		paramsMap.put("killTime", nowTime);
		paramsMap.put("result", null);
		seckillDao.killByProcedure(paramsMap);
		int result = MapUtils.getInteger(paramsMap, "result", -2);
		if (result == 1) {
			SeckillSuccess ss = seckillSuccessDao.queryByIdWithSeckill(seckillId, userPhone);
			return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,ss);
		} else {
			return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
		}
	}
}
