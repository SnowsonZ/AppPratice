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