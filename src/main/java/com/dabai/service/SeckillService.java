package com.dabai.service;

import com.dabai.dto.Exposer;
import com.dabai.dto.SeckillExecution;
import com.dabai.entity.Seckill;
import com.dabai.exception.SeckillCloseException;
import com.dabai.exception.SeckillException;
import com.dabai.exception.SeckillRepeatException;

import java.util.List;

/**
 * Created by 人杰 on 2017/2/14.
 */
public interface SeckillService {

    /**
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开始时输出秒杀地址
     *否则输出系统时间和秒杀开始时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param md5
     * @param userPhone
     */
    SeckillExecution executeSeckill(long seckillId, String md5, long userPhone)
            throws SeckillException,SeckillRepeatException,SeckillCloseException;
    
    /**
     * @param seckillId
     * @param md5
     * @param userPhone
     * @return SeckillExecution
     */
    SeckillExecution executeSeckillProcedure(long seckillId, String md5, long userPhone);
}
