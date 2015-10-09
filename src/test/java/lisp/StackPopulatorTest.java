package lisp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackPopulatorTest
{
	StackPopulator stackPopulator;
	private final int CAPACITY = 100;
	private BoundedStack<String> stack;

	@Before
	public void setup()
	{
		stack = new LinkedListStack<String>(CAPACITY);
		stackPopulator = new StackPopulator(stack);
	}

	@Test
	public void test_pushExpressionIntoStack_empty()
	{
		stackPopulator.pushExpressionIntoStack("");
		assertTrue(stack.isEmpty());
	}

	@Test
	public void test_pushExpressionIntoStack_exactCapacity()
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < CAPACITY; i++)
		{
			builder.append(i);
			builder.append(' ');
		}
		stackPopulator.pushExpressionIntoStack(builder.toString());
		assertEquals(builder.toString().replaceAll(" ", ""), retrieveStringOffStack());
	}

	@Test
	public void test_pushExpressionIntoStack_validExpression()
	{
		String input = "(- 2 3 1)";
		String expected = "(-231)";
		stackPopulator.pushExpressionIntoStack(input);
		String result = retrieveStringOffStack();
		assertEquals(expected, result);
	}

	@Test(expected = InvalidExpressionException.class)
	public void test_pushExpressionIntoStack_invalidCharacter()
	{
		StringBuilder builder = new StringBuilder();
		builder.append('x');
		stackPopulator.pushExpressionIntoStack(builder.toString());
	}

	@Test (expected = FullStackException.class)
	public void test_pushExpressionIntoStack_tooLarge()
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < CAPACITY + 1; i++)
		{
			builder.append(i);
			builder.append(' ');
		}
		stackPopulator.pushExpressionIntoStack(builder.toString());
	}

	private String retrieveStringOffStack()
	{
		StringBuilder builder = new StringBuilder();
		while (!stack.isEmpty())
		{
			builder.append(stack.pop());
		}
		return builder.toString();
	}
}
