package com.dabai.dto;

import com.dabai.entity.SeckillSuccess;
import com.dabai.enums.SeckillStateEnum;

/**
 * 封装秒杀后结果
 * Created by 人杰 on 2017/2/14.
 */
public class SeckillExecution {
    //
    private long seckillId;
    //秒杀状态
    private int state;
    //秒杀标识
    private  String stateInfo;
    //秒杀成功对象
    private SeckillSuccess seckillSuccess;
    //秒杀成功构造器
    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SeckillSuccess seckillSuccess) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.seckillSuccess = seckillSuccess;
    }
    //秒杀失败构造器

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillSuccess=" + seckillSuccess +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillSuccess getSeckillSuccess() {
        return seckillSuccess;
    }

    public void setSeckillSuccess(SeckillSuccess seckillSuccess) {
        this.seckillSuccess = seckillSuccess;
    }
}
