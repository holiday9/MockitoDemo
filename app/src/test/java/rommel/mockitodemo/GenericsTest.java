package rommel.mockitodemo;

import org.junit.Assert;
import org.junit.Test;

import rommel.mockitodemo.generics.Generics;
import rommel.mockitodemo.generics.Stu;

/**
 * Created by yuan on 2017/6/5.
 */

public class GenericsTest {
    @Test
    public void shouldReturnNormalStu() throws InstantiationException, IllegalAccessException {
        Object stuObj = Generics.getObject(Stu.class);

        Assert.assertTrue(stuObj instanceof Stu);
        Stu stu = (Stu) stuObj;
        Assert.assertEquals("xiaoming", stu.name);
    }
}
