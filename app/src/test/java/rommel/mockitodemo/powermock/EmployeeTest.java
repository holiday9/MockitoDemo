package rommel.mockitodemo.powermock;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yuan on 2017/6/2.
 */

public class EmployeeTest {

    @Test
    public void testEmptyName() {
        Employee employee = new Employee("");
        Assert.assertFalse(employee.isNameValid());
    }
}
