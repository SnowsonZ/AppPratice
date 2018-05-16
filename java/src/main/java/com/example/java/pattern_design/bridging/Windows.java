package com.example.java.pattern_design.bridging;

/**
 * 实现类接口扩展
 */
public class Windows implements Platform {
    public void play(String type) {
        System.out.println(this.getClass().getName() + ": " + type);
    }

    public void pause() {

    }

    @Override
    public void previous() {

    }


    public void next() {

    }

    public void stop() {

    }
}