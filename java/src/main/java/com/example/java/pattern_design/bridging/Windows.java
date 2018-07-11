package com.example.java.pattern_design.bridging;

/**
 * 实现类接口扩展
 */
public class Windows implements Platform {
    @Override
    public void play(String type) {
        System.out.println(this.getClass().getName() + ": " + type);
    }

    @Override
    public void pause() {

    }

    @Override
    public void previous() {

    }


    @Override
    public void next() {

    }

    @Override
    public void stop() {

    }
}