package rommel.mockitodemo;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by yuan on 2017/3/29.
 */

public class CommponentTest {
    @Test
    public void testExcuteFuncBody() {
        Commponent mockCommponent = mock(Commponent.class);

        mockCommponent.switchFunc();

        // mock object don't execute func , so verify toggleB will broken.
//        verify(mockCommponent).toggleB();
        verify(mockCommponent).switchFunc();
    }
}
