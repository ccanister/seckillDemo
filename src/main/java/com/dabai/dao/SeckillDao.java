package com.dabai.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dabai.entity.Seckill;

/**
 * Created by 人杰 on 2017/2/13.
 */
public interface SeckillDao  {

    /**
     *减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);


    /**
     * 根据id查询秒杀对象
     * @param secksillId
     * @return
     */
    Seckill queryById(long secksillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
    
    /**
     * 利用存储过程秒杀
     * @param paramMap
     * @return
     */
    void killByProcedure(Map<String,Object> paramMap);
}
