package com.alibaba.common.lifecycle;

/**
 * 对应的lifecycle控制接口
 * 
 * @author agapple 2013-9-12 下午2:19:56
 */
public interface YuGongLifeCycle {

    public void start();

    public void stop();

    /**
     * 异常stop的机制
     * 
     * @param why
     * @param e
     */
    public void abort(String why, Throwable e);

    public boolean isStart();

    public boolean isStop();

}
