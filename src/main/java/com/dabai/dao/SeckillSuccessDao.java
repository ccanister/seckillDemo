package com.dabai.dao;

import com.dabai.entity.SeckillSuccess;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 人杰 on 2017/2/13.
 */
public interface SeckillSuccessDao {

    /**
     * 插入秒杀明细表
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSeckillSuccess(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);

    /**
     * 根据id查询SuccessKilled斌携带秒杀对象实体
     * @param seckillId
     * @return
     */
    SeckillSuccess queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
