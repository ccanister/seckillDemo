package com.dabai.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dabai.entity.Seckill;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final JedisPool jedisPool;
	
	public RedisDao(String ip,int port){
		jedisPool = new JedisPool(ip,port);
	}
	
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	
	public Seckill getSeckill(long seckillId){
			Jedis jedis = jedisPool.getResource();
			String key = "seckill:" + seckillId;
			//没有实现内部序列化操作
			//get->byte[]->反序列化->Object(SeckillId)
			//采用自定义序列化方式
			//protostuff:pojo
			byte[] bytes = jedis.get(key.getBytes());
			if(bytes != null){
				//获得空对象
				Seckill seckill = schema.newMessage();	
				//反序列化
				ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
				return seckill;
			}
			jedis.close();
			return null;
	}

	public String putSeckill(Seckill seckill){
		//set Object -> 序列化 -> byte[]
		Jedis jedis = jedisPool.getResource();
		String key = "seckill:" + seckill.getSeckillId();
		byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, 
				LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));//缓存器
		//超时缓存
		int timeout = 60 * 60;
		String result = jedis.setex(key.getBytes(),timeout,bytes);
		jedis.close();
		return result;
	}
}
