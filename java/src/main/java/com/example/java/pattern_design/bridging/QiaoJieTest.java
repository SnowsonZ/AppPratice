public class QiaoJieTest {
    public static void main(String[] args) {
        Platform platformOne = new Windows();
        VideoType typeOne = new MP4();
        typeOne.setPlatform(platformOne);

        Platform platformTwo = new Linux();
        VideoType typeTwo = new Rmvb();
        typeTwo.setPlatform(platformTwo);

        // play
        typeOne.play();
        typeTwo.play();
    }
}