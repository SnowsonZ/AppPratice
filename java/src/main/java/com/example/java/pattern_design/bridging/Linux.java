/**
 * 实现类接口扩展
 */
public class Linux implements Platform {
    public void play(String type) {
        System.out.println(this.getClass().getName() + ": " + type);
    }

    // ...省略其他方法
    public void pause() {

    }

    public void prev() {

    }

    public void next() {

    }

    public void stop() {

    }
}