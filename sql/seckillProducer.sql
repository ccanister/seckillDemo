--秒杀存储过程
DELIMITER $$	
--定义存储过程
--参数：in输入out输出
CREATE PROCEDURE `seckill`.`execute_seckill`(
		in v_seckill_id bigint,in v_phone bigint,
		in v_kill_time datetime,out r_result int)
	Begin
		DECLARE insert_count int DEFAULT 0;
		start TRANSACTION;
		insert ignore into seckill_sucess
		(seckill_id,user_phone,create_time)
		values (v_seckill_id,v_phone,v_kill_time);
		SELECT row_count() into insert_count;
		IF (insert_count = 0) THEN
			ROLLBACK;
			set r_result = -1;
		ELSEIF(insert_count <0) THEN
			ROLLBACK;
			set r_result = -2;
		ELSE
			UPDATE seckill
			set number = number - 1
			where seckill_id = v_seckill_id
			and end_time > v_kill_time
			and start_time < v_kill_time
			and number > 0;
			SELECT row_count() into insert_count;
			IF (insert_count = 0) THEN
				ROLLBACK;
				set r_result = -1;
			ELSEIF(insert_count <0) THEN
				ROLLBACK;
				set r_result = -2;
			ELSE
				COMMIT;
				set r_result = 1;
			END IF;
		END IF;
	END
$$

DELIMITER ;
set @r_result=-3;

call execute_seckill(1003,15857699267,now(),@r_result);

select r_result;
