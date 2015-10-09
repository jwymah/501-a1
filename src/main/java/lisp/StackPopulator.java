package lisp;

import java.util.EmptyStackException;

public class StackPopulator
{
	private StringBuffer buf = new StringBuffer();
	private boolean appendState = false;
	private BoundedStack<String> stack;

	StackPopulator(BoundedStack<String> stack)
	{
		this.stack = stack;
	}

	public void pushExpressionIntoStack(String expression) throws InvalidExpressionException, EmptyStackException
	{
		if (expression.length() == 0)
		{
			throw new EmptyStackException();
		}
		
		for (int i = expression.length() - 1; i >= 0; i--)
		{
			parseChar(expression.charAt(i));
			if (Mode.debugStack == true)
				System.out.println("parsed character: " + expression.charAt(i));
		}
		if (buf.length() > 0)
		{
			stack.push(buf.toString()); // to make sure the very first character
										// is always pushed
			buf.delete(0, buf.length());
		}
	}

	/**
	 * This method scans the string start to finish one character at a time. if
	 * there is two number characters or negative sign next to a number
	 * character they are joined into a single string values are pushed onto a
	 * stack which is implemented by an array of strings
	 * 
	 * @param a
	 *            single character from the list expression
	 * @throws InvalidExpressionException
	 *             if unexpected characters found (ie alphabet)
	 */
	private void parseChar(char c) throws InvalidExpressionException
	{
		if (Mode.debugStack == true)
			System.out.println("parsing: " + c);
		switch (c)
		{
			case '(':
			case ')':
				if (buf.length() > 0)
				{
					buf.reverse();
					stack.push(buf.toString());
					buf.delete(0, buf.length());
				}
				stack.push(Character.toString(c));
				appendState = false;
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				buf.append(c);
				appendState = true;
				break;
			case '-':
				if (appendState == true)
					buf.append(c);
				else
					stack.push(Character.toString(c));

				break;
			case '+':
			case '*':
			case '/':
				stack.push(Character.toString(c));
				appendState = false;
				break;
			case ' ':
				if (buf.length() > 0)
				{
					buf.reverse();
					stack.push(buf.toString());
					buf.delete(0, buf.length());
				}
				appendState = false;
				break;
			default:
				throw new InvalidExpressionException("Unknown character '" + c + "'.");
		}
		return;
	}
}
