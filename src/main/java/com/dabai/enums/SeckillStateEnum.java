package com.dabai.enums;

/**
 * 使用枚举表述常量数据字段
 * Created by 人杰 on 2017/2/14.
 */
public enum SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"秒杀重复"),
    INNER_ERROR(-2,"系统错误"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;

    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index){
        for(SeckillStateEnum state: values() ){
            if(state.getState() == index){
                return state;
            }
        }

        return null;
    }
}
