package stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    private Stack stack;

    @Before
    public void setUp() {
        stack = BoundedStack.Make(2);
    }

    @Test
    public void newlyCreateStack_ShouldEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }

    @Test
    public void AfterOnePush_StackSizeShouldBeOne() {
        stack.push(1);
        assertEquals(1, stack.getSize());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void AfterOnePushAndOnePop_StackSizeShouldBeEmpty() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void WhenPushedPastLimit_StackOverflows() {
        stack.push(1);
        stack.push(1);
        stack.push(1);
    }

    @Test(expected = BoundedStack.Underflow.class)
    public void WhenEmptyStackIsPopped_ShouldThrowUnderFlow() {
        stack.pop();
    }

    @Test
    public void WhenOneIsPushed_OneIsPopped() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void WhenOneAndTwoArePushed_TwoAndOnePopped() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test(expected = BoundedStack.IllegalCapacity.class)
    public void whenCreatingStackWithNegativeSize_ShouldThrowIllegalCapacity() {
        BoundedStack.Make(-1);
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void whenCreatingStackWithZeroCapacity_AnyPushShouldOverflow() {
        stack = BoundedStack.Make(0);
        stack.push(1);
    }

    @Test
    public void whenOneIsPushed_OneIsOnTop() {
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test(expected = Stack.Empty.class)
    public void WhenStackIsEmpty_TopThrowsEmpty() {
        stack.top();
    }

    @Test(expected = Stack.Empty.class)
    public void WithZeroCapacityStack_topThrowsEmpty() {
        stack = BoundedStack.Make(0);
        stack.top();
    }

    @Test
    public void GivenStackWithOneTwoPushed_FindOneAndTwo() {
        stack.push(1);
        stack.push(2);
        int oneIndex = stack.find(1);
        int twoIndex = stack.find(2);
        assertEquals(1,oneIndex);
        assertEquals(0,twoIndex);
    }

    @Test
    public void GivenStackWithNo2_Find2ShouldReturnNull(){
        assertNull(stack.find(2));
    }
}

