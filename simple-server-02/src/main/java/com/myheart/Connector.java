package com.myheart;

/**
 * @author yangxin
 * @date 2018/12/11
 */
public interface Connector extends Runnable {

    /**
     * 初始化连接器
     */
    void initialize();

    /**
     * 开启一个连接器线程
     */
    void start();
}
