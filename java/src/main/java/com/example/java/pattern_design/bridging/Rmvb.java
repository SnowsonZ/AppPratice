/**
 * 扩充抽象类
 */
public class Rmvb extends VideoType {
    public void play() {
        Platform platform = getPlatform();
        if (platform != null) {
            platform.play(this.getClass().getName());
        }
    }

    public void pause() {

    }

    public void prev() {

    }

    public void next() {

    }

    public void stop() {

    }
}