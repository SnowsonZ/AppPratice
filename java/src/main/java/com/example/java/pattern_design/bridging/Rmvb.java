package com.example.java.pattern_design.bridging;

/**
 * 扩充抽象类
 */
public class Rmvb extends VideoType {
    @Override
    public void play() {
        Platform platform = getPlatform();
        if (platform != null) {
            platform.play(this.getClass().getName());
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void prev() {

    }

    @Override
    public void next() {

    }

    @Override
    public void stop() {

    }

}