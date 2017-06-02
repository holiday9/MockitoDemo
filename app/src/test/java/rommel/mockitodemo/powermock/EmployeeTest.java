package rommel.mockitodemo.powermock;

import android.text.TextUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;

/**
 * Created by yuan on 2017/6/2.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class EmployeeTest {

    @Before
    public void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                CharSequence str = (CharSequence) invocationOnMock.getArguments()[0];
                if (str == null || str.length() == 0)
                    return true;
                else
                    return false;
            }
        });
    }

    @Test
    public void testEmptyName() {
        Employee employee = new Employee("");
        Assert.assertFalse(employee.isNameValid());
    }

    @Test
    public void testValidName() {
        Employee employee = new Employee("Peter");
        Assert.assertTrue(employee.isNameValid());
    }
}
