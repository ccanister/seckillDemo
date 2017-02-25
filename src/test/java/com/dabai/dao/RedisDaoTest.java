package com.dabai.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dabai.dao.cache.RedisDao;
import com.dabai.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
	private long seckillId = 1001L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SeckillDao seckillDao;
	@Test
	public void testSeckill() {
		Seckill seckill = redisDao.getSeckill(seckillId);
		System.out.println(seckill.toString());
		if (seckill == null) {
			seckill = seckillDao.queryById(seckillId);
			String result = redisDao.putSeckill(seckill);
			logger.info(result);
			seckill = redisDao.getSeckill(seckillId);
			logger.info(seckill.toString());
		}
	}

}
