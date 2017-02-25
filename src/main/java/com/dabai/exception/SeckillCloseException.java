package com.dabai.exception;

/**
 * 秒杀关闭异常
 * Created by 人杰 on 2017/2/14.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillCloseException(String message) {
        super(message);
    }
}
