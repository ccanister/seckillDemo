package com.dabai.entity;

import java.util.Date;

/**
 *
 * Created by 人杰 on 2017/2/13.
 */
public class SeckillSuccess {
    private long seckillId;

    private long userPhone;

    private short state;

    private Date creatTime;

    //多对一
    private Seckill seckill;

    @Override
    public String toString() {
        return "SeckillSuccess{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", creatTime=" + creatTime +
                ", seckill=" + seckill +
                '}';
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
