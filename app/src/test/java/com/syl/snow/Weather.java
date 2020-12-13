package com.syl.snow;

/**
 * @author Bright
 * @date 2020/12/13 21:07
 * @describe
 * Java中有些类会在方法中直接抛出异常，例如ThreadLocal，按理说，抛出异常，如果这些方法还被调用，那么就会抛出异常，程序就会终止运行。
 * 我特意写了这么一个类测试了一下，果然如此。
 */
public class Weather {
    private int first ;
    private String name;
    private boolean isStart;

    public Weather() {
        throw new RuntimeException("this is a  test Exception");
    }
}
