/*
 * Student: Jeremy Mah
 * ID: 10053908
 * Class: 331
 * assignment 2
 * Instructor: Mike Jacobson
 * TA: Eduard Pelchat
 * tut: M/W 11am
 */
public class A2Q5b {
	private  int capacity = 100;
	private  StringBuffer buf = new StringBuffer();
	private  boolean appendState = false;
	private  BoundedStack<String> stack;
	private  int depth = 0;
	
	/**
	 * handles any exceptions thrown by called methods
	 * @param 1 or 0 depending on type of stack implementation
	 * @param lisp expression
	 */
	/**
	 * method made solely for the purpose of having a return method that can be used to test
	 * @param type integer 0 or 1 for the implementation of the stack
	 * @param expression the lisp expression
	 * @return value of expression of type double
	 */
	public double startExpression(int type, String expression){
		double value = 0.0;
			
		if(expression.length() == 0){
			throw new InvalidExpressionException("Invalid Expression2");
		}
		if	(type == 0){
			stack = new A2Q3<String>(capacity);
		}
		else{
			stack = new A2Q4<String>(capacity);
		}
		
		for (int i = expression.length()-1; i >= 0; i--){	//all initial pushing is done inside parseInput
			parseInput(expression.charAt(i));
			if(Mode.debugStack==true)
				System.out.println("parsed character: " + expression.charAt(i));
		}
		if	(stack.pop().contentEquals("(")){
			depth = 0;
			value = evaluate();
		}
		else
				throw new InvalidExpressionException("Invalid Expression3");
		return value;
	}
	
/**
 * This method scans the string start to finish one character at a time.
 * if there is two number characters or negative sign next to a number character they are joined into a single string
 * values are pushed onto a stack which is implemented by an array of strings
 * @param a single character from the list expression
 * @throws InvalidExpressionException if unexpected characters found (ie alphabet) 
 */
	public void parseInput(char c){
		if	(Mode.debugStack==true)
			System.out.println("parsing: "+c);
		switch(c){
		case '(':
		case ')':
			if	(buf.length() > 0){
				buf.reverse();
				stack.push(buf.toString());
				buf.delete(0,buf.length());
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
			if	(buf.length() > 0){
				buf.reverse();
				stack.push(buf.toString());
				buf.delete(0, buf.length());
			}
			appendState = false;
			break;
		default:
			throw new InvalidExpressionException("Invalid Expression4");
		}
		return;
	}

/**
 * recursive evaluation method; called whenever opening bracket is popped
 * must be called after encountering an opening bracket and will evaluate the contents 
 * until the corresponding closing bracket given a valid equation
 * Will call itself again when encountering another opening bracket before a closing bracket.
 * @return returns a boolean value whether the expression is a valid Lisp expression.
 * @throws InvalidExpressionException if some expression is not entirely bound by a single pair of brackets. ie. '(+ 1 2)5'.
 * 		will always throw an exception if a close bracket is not on stack when returning to this method
 */
	public double evaluate(){
		depth += 1;
		String str=null;
		double value = 0.0;
		while (stack.size() > 1 && stack.top().contentEquals(")") == false){
			str = stack.pop();
			if	(str.contentEquals("(")){
				value = evaluate();
			}
			else if	(stack.size() > 0 && str.contentEquals(")") == false)
				value = doOperator(str);
		}
		if (stack.isEmpty() == true)
			throw new InvalidExpressionException("Invalid Expression4.5");
		stack.pop();
		if(Mode.debugEval)
			System.out.println("stack size= " +stack.size());
		if(depth == 1 && stack.isEmpty()==false)
			throw new InvalidExpressionException("Invalid Expression5");
		depth -= 1;
		return value;
	}

/**
 * Analyzes the operation to be performed and calls doOperands
 * @param one character long string containing the operator to perform
 * @return the value calculated from the operand until closing bracket is on top of stack
 **/	
	public double doOperator(String str){
		double value = 0.0;
		if (Mode.debugEval)
			System.out.println("String passed to doOperator is: "+str);
		if	(str.contentEquals("("))
			evaluate();
		
		else if	(str.contentEquals("+")){
			value = doOperands('+');
		}
		else if	(str.contentEquals("*")){
			value = doOperands('*');
		}
		else if (str.contentEquals("-")){
			value = doOperands('-');
		}
		else if (str.contentEquals("/")){
			value = doOperands('/');
		}
		else if (str.contentEquals(")")){
			return 0.0;
		}
		else{
			throw new InvalidExpressionException("Invalid Expression5.5");
		}
		return value;
	}
	
/**
 * given an operator, performs the operand with values pulled off the stack until closing bracket is on top.
 * Also keeps track of how many terms have gone by to properly evaluate lisp expressions.
 * @param char operator expected to be one of + - * /
 * @return (double) the value of the evaluated segment until a closing bracket is on top of the stack
 * @throws InvalidExpressionException if operator is - or / and closing bracket is on top of the stack
 * or if after being called another operator is on top of the stack 
 */
	public double doOperands(char operator){
		int terms = 0;
		double value = 0.0;
		while (stack.size() > 0){
			terms++;
			

			if (stack.top().contentEquals(")")){
				if	(terms==1 && operator=='*')
					value = 1.0;
				else if(terms==1 && (operator=='/' || operator=='-')){
					throw new InvalidExpressionException("Invalid Expression6");
				}
				if (Mode.debugEval)
					System.out.println("value at this point = "+value);
				return value;
			}
			
			String str = stack.pop();
			
			if	(str.contentEquals("("))
				str = Double.toString(evaluate());
			else if (str.contentEquals("+") || str.contentEquals("*") || str.contentEquals("-") || str.contentEquals("/")){
				throw new InvalidExpressionException("Invalid Expression");
			}
			
			switch(operator){
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
				if (terms == 1){
					if (stack.top().contentEquals(")"))
						value =  -Double.parseDouble(str);
					else
						value = Double.parseDouble(str);
				}
				else{
					value -= Double.parseDouble(str);
				}
				break;
			case '/':
				if (terms == 1){
					if (stack.top().contentEquals(")"))
						value = 1 / Double.parseDouble(str);
					else
						value = Double.parseDouble(str);
				}
				else{
					value /= Double.parseDouble(str);
				}
				break;
			}
		}
		return 0.0;
	}
	
	/*
	 * for whatever reason sometimes it is desired to empty the stack and start over
	 * this method does just that
	 */
	public void cleanStack(){
		while (stack.size() > 0){
			stack.pop();
		}
	}
}