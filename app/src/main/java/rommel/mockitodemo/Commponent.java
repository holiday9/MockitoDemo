package rommel.mockitodemo;

/**
 * Created by yuan on 2017/3/29.
 */

public class Commponent {
    public void switchFunc() {
        if (isToggleA()) {
            toggleA();
        } else {
            toggleB();
        }
    }

    public void toggleA() {
        System.out.println("-------------toggleA");
    }

    public void toggleB() {
        System.out.println("-------------toggleA");
    }

    private boolean isToggleA() {
        return true;
    }
}
