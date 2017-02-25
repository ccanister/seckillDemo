--数据库初始化脚本

--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建数据库秒杀表
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL COMMENT "商品名称",
`number` int NOT NULL COMMENT "商品库存",
`start_time` datetime NOT NULL COMMENT "秒杀开始时间",
`end_time` datetime NOT NULL COMMENT "秒杀结束时间",
`create_time` datetime NOT NULL COMMENT "创建时间",
PRIMARY KEY (seckill_id),
key index_start_time(start_time),
key index_end_time(end_time),
key index_create_time(create_time),
)ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8 COMMENT '秒杀库存表';

--初始化数据库表
insert into
	seckill(name,number,start_time,end_time)
values
	('1000元秒杀魅族pro6',100,'2017-2-10 00:00:00','2017-2-10 00:10:00'),
	('2000元秒杀小米5',100,'2017-2-10 00:00:00','2017-2-10 00:10:00'),
	('3000元秒杀ipad air3',100,'2017-2-10 00:00:00','2017-2-10 00:10:00'),
	('4000元秒杀iphone7',100,'2017-2-10 00:00:00','2017-2-10 00:10:00');