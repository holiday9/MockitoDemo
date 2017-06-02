package rommel.mockitodemo.powermock;

import android.text.TextUtils;

/**
 * Created by yuan on 2017/6/1.
 */

public class Employee {
    public String name = "";

    public Employee() {

    }

    public Employee(String name) {
        this.name = name;
    }

    public boolean isNameValid() {
        return !TextUtils.isEmpty(name);
    }
}
