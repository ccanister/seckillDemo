--秒杀成功明细表
--用户登录成功认证相关信息
CREATE TABLE seckill_sucess(
	`seckill_id` bigint NOT NULL COMMENT "商品库存id",
	`user_phone` bigint NOT NULL COMMENT "用户手机号",
	`state` int NOT NULL COMMENT '状态表示：-1无效 0成功 1已付款 2已下单',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY(seckill_id,user_phone), /*联合主键*/
	key index_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';