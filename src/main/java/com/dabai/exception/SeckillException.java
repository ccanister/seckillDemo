package com.dabai.exception;

/**
 * 秒杀相关业务异常
 * Created by 人杰 on 2017/2/14.
 */
public class SeckillException extends Exception{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
