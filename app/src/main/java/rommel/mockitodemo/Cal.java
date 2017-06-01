package rommel.mockitodemo;

/**
 * Created by yuan on 2017/3/30.
 */

public class Cal {
    private final Five five;
    private final Three three;

    public Cal(Three three, Five five) {
        this.five = five;
        this.three = three;
    }

    public int cal() {
        return three.value() + five.value();
    }
}
