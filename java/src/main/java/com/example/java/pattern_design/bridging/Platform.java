package com.example.java.pattern_design.bridging;

/**
 * 实现类接口定义
 */
public interface Platform {
    void play(String type);

    void pause();

    void previous();

    void next();

    void stop();
}