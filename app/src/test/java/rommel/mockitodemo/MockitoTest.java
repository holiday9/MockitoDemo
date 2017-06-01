package rommel.mockitodemo;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by yuan on 2017/3/27.
 */

public class MockitoTest {
    @Test
    public void testBehaviourVerify() {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test(expected = RuntimeException.class)
    public void testStubingWithReturnValue() {
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                System.out.println(invocation.getMethod().getName());
                return null;
            }
        }).when(mockedList).clear();

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void testStubingWithVoidValue() {
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                System.out.println(invocation.getMethod().getName());
                return null;
            }
        }).when(mockedList).clear();

        mockedList.clear();
    }

    @Test
    public void testArgMatcher() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenReturn("element");

        mockedList.get(999);

        verify(mockedList).get(anyInt());

    }

    @Test
    public void testVerifyInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");
        singleMock.add("was added first");

        //create an inOrder verifier for a single mock
        InOrder inOrder1 = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder1.verify(singleMock).add("was added first");
        inOrder1.verify(singleMock).add("was added second");
        inOrder1.verify(singleMock).add("was added first");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder2 = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    @Test
    public void testNoInteractionBetweenMocks() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);
        //using mocks - only mockOne is interacted
        mockOne.add("one");
        mockOne.add("extra interaction");

        //ordinary verification
        verify(mockOne).add("one");
        verify(mockOne).add("extra interaction");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);

        verifyNoMoreInteractions(mockOne);
    }

    @Test
    public void testCustomReturnWithAnwser() {
        List mock = mock(List.class);
        when(mock.get(anyInt())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + args;
            }
        });

        //the following prints "called with arguments: foo"
        System.out.println(mock.get(0));
    }

    @Test
    public void testMockToReal() {
        Three mockThree = mock(Three.class);
        Five mockFive = mock(Five.class);

        when(mockFive.value()).thenReturn(5);
        when(mockThree.value()).thenReturn(3);

        Cal cal = new Cal(mockThree, mockFive);
        Assert.assertEquals(8, cal.cal());
    }

    @Test
    public void testArguCapter() {
        List mock = mock(List.class);

        mock.add(new Person("John"));

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        verify(mock).add(argument.capture());
        Assert.assertEquals("John", argument.getValue().getName());
    }
}






























