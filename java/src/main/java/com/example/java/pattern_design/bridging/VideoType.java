/**
 * 抽象类
 */
public abstract class VideoType {
    private Platform mPlatform;

    public void setPlatform(Platform platform) {
        this.mPlatform = platform;
    }

    public Platform getPlatform() {
        return mPlatform;
    }

    //在实现类接口中声明下列接口方法
    public abstract void play();
    public abstract void pause();
    public abstract void prev();
    public abstract void next();
    public abstract void stop();
}