package lisp;

/*
 * Student: Jeremy Mah
 * ID: 10053908
 * Class: 331
 * assignment 2
 * Instructor: Mike Jacobson
 * TA: Eduard Pelchat
 * tut: M/W 11am
 */
public class ExpressionEvaluator {
	private int capacity = 100;
	private BoundedStack<String> stack;
	private int depth = 0;
	
	/**
	 * method made solely for the purpose of having a return method that can be
	 * used to test
	 * 
	 * @param type
	 *            integer 0 or 1 for the implementation of the stack
	 * @param expression
	 *            the lisp expression
	 * @return value of expression of type double
	 */
	public double startExpression(int type, String expression) {
		double value = 0.0;

		if (expression.length() == 0) {
			throw new InvalidExpressionException("Empty expression.");
		}
		if (type == 0) {
			stack = new ArrayStack<String>(capacity);
		} else {
			stack = new LinkedListStack<String>(capacity);
		}
		StackPopulator stackPopulator = new StackPopulator(stack);
		stackPopulator.pushExpressionIntoStack(expression);
		value = evaluateExpressionOnStack(value);
		return value;
	}

	private double evaluateExpressionOnStack(double value)
	{
		if (stack.pop().contentEquals("(")) {
			depth = 0;
			value = evaluateSubExpression();
		} else
			throw new InvalidExpressionException("Does not start with '('.");
		return value;
	}

	/**
	 * recursive evaluation method; called whenever opening bracket is popped
	 * must be called after encountering an opening bracket and will evaluate
	 * the contents until the corresponding closing bracket given a valid
	 * equation Will call itself again when encountering another opening bracket
	 * before a closing bracket.
	 * 
	 * @return returns a boolean value whether the expression is a valid Lisp
	 *         expression.
	 * @throws InvalidExpressionException
	 *             if some expression is not entirely bound by a single pair of
	 *             brackets. ie. '(+ 1 2)5'. will always throw an exception if a
	 *             close bracket is not on stack when returning to this method
	 */
	public double evaluateSubExpression() {
		depth += 1;
		String str = null;
		double value = 0.0;
		while (stack.size() > 1 && stack.top().contentEquals(")") == false) {
			str = stack.pop();
			if (str.contentEquals("(")) {
				value = evaluateSubExpression();
			} else if (stack.size() > 0 && str.contentEquals(")") == false)
				value = doOperator(str);
		}
		if (stack.isEmpty() == true)
			throw new InvalidExpressionException("Expression is not properly bound by parenthesis.");
		stack.pop();
		if (Mode.debugEval)
			System.out.println("stack size= " + stack.size());
		if (depth == 1 && stack.isEmpty() == false)
			throw new InvalidExpressionException("Final character in expression is not ')'.");
		depth -= 1;
		return value;
	}

	/**
	 * Analyzes the operation to be performed and calls doOperands
	 * 
	 * @param one
	 *            character long string containing the operator to perform
	 * @return the value calculated from the operand until closing bracket is on
	 *         top of stack
	 **/
	public double doOperator(String str) {
		double value = 0.0;
		if (Mode.debugEval)
			System.out.println("String passed to doOperator is: " + str);
		if (str.contentEquals("("))
			evaluateSubExpression();

		else if (str.contentEquals("+")) {
			value = doOperands('+');
		} else if (str.contentEquals("*")) {
			value = doOperands('*');
		} else if (str.contentEquals("-")) {
			value = doOperands('-');
		} else if (str.contentEquals("/")) {
			value = doOperands('/');
		} else if (str.contentEquals(")")) {
			return 0.0;
		} else {
			throw new InvalidExpressionException("Unknown operator found '" + str + "'.");
		}
		return value;
	}

	/**
	 * given an operator, performs the operand with values pulled off the stack
	 * until closing bracket is on top. Also keeps track of how many terms have
	 * gone by to properly evaluate lisp expressions.
	 * 
	 * @param char
	 *            operator expected to be one of + - * /
	 * @return (double) the value of the evaluated segment until a closing
	 *         bracket is on top of the stack
	 * @throws InvalidExpressionException
	 *             if operator is - or / and closing bracket is on top of the
	 *             stack or if after being called another operator is on top of
	 *             the stack
	 */
	public double doOperands(char operator) {
		int terms = 0;
		double value = 0.0;
		while (stack.size() > 0) {
			terms++;

			if (stack.top().contentEquals(")")) {
				if (terms == 1 && operator == '*')
					value = 1.0;
				else if (terms == 1 && (operator == '/' || operator == '-')) {
					throw new InvalidExpressionException(
							"Expression starts with an unsupported starting operator '" + operator + "'.");
				}
				return value;
			}

			String str = stack.pop();

			if (str.contentEquals("("))
				str = Double.toString(evaluateSubExpression());
			else if (str.contentEquals("+") || str.contentEquals("*") || str.contentEquals("-")
					|| str.contentEquals("/")) {
				throw new InvalidExpressionException("Expression contains operators before parenthesis.");
			}

			value = doOperator(operator, terms, value, str);
		}
		return 0.0;
	}

	private double doOperator(char operator, int terms, double value, String str)
	{
		switch (operator) {
		case '+':
			if (terms == 1)
				value = 0.0;
			value += Double.parseDouble(str);
			break;
		case '*':
			if (terms == 1)
				value = 1.0;
			value *= Double.parseDouble(str);
			break;
		case '-':
			if (terms == 1) {
				if (stack.top().contentEquals(")"))
					value = -Double.parseDouble(str);
				else
					value = Double.parseDouble(str);
			} else {
				value -= Double.parseDouble(str);
			}
			break;
		case '/':
			if (terms == 1) {
				if (stack.top().contentEquals(")"))
					value = 1 / Double.parseDouble(str);
				else
					value = Double.parseDouble(str);
			} else {
				value /= Double.parseDouble(str);
			}
			break;
		}
		return value;
	}

	/*
	 * for whatever reason sometimes it is desired to empty the stack and start
	 * over this method does just that
	 */
	public void cleanStack() {
		while (stack.size() > 0) {
			stack.pop();
		}
	}
}