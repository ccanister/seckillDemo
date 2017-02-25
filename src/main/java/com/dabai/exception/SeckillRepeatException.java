package com.dabai.exception;

/**
 * 重复秒杀异常
 * Created by 人杰 on 2017/2/14.
 */
public class SeckillRepeatException extends SeckillException{
    public SeckillRepeatException(String message) {
        super(message);
    }

    public SeckillRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
