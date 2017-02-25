package com.dabai.service.Impl;

import com.dabai.dto.Exposer;
import com.dabai.dto.SeckillExecution;
import com.dabai.entity.Seckill;
import com.dabai.exception.SeckillCloseException;
import com.dabai.exception.SeckillRepeatException;
import com.dabai.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 人杰 on 2017/2/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                       "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("hh");
        logger.info("seckillList={}",seckillList);
    }

    @Test
    public void getById() throws Exception {
        logger.info("test");
    }

    @Test
    public void testSeckillLogic() throws Exception {
        long seckillId = 1001L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        System.out.println(exposer);
        if (exposer.isExposed()) {
            long userPhone = 18883995760L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, md5, userPhone);
                logger.info(seckillExecution.toString());
            } catch (SeckillCloseException e) {

            } catch (SeckillRepeatException e2) {

            }
        }
    }

    @Test 
	public void testExecuteSeckillProcedure() throws Exception {
		long seckillId = 1004L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		System.out.println(exposer);
		if (exposer.isExposed()) {
			long userPhone = 18883995768L;
			String md5 = exposer.getMd5();
			SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, md5, userPhone);
			logger.info(seckillExecution.toString());
		}

	}


}