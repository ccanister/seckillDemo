package com.dabai.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 人杰 on 2017/2/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillSuccessDaoTest {

    @Resource
    private SeckillSuccessDao seckillSuccessDao;

    @Test
    public void insertSeckillSuccess() throws Exception {
        long seckillId = 1000L;
        long userPhone = 18883994321L;
        System.out.println(seckillSuccessDao.insertSeckillSuccess(seckillId,userPhone));
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long seckillId = 1000L;
        long userPhone = 18883994321L;
        System.out.println(seckillSuccessDao.queryByIdWithSeckill(seckillId,userPhone));
    }

}