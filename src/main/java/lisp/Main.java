package lisp;

import java.io.*;
/*
 * Student: Jeremy Mah
 * ID: 10053908
 * Class: 331
 * assignment 2
 * Instructor: Mike Jacobson
 * TA: Eduard Pelchat
 * tut: M/W 11am
 */

/*
 * driver class for assignement 2. takes the command line args only. returns none
 */
public class Main
{
	private static int CAPACITY = 100;
	
	/**
	 * Prints a message describing proper usage with respect to required command
	 * line parameters and exits.
	 */
	private static void usage()
	{
		System.out.println("invalid inputs. Usage: A2Q5 <type> <filename>");
		System.out.println("where <type> is 0 or 1 and <filename> contains text inputs");
		System.exit(1);
	}

	/*
	 * Student: Jeremy Mah ID: 10053908 Class: 331 assignment 2 Instructor: Mike
	 * Jacobson TA: Eduard Pelchat tut: M/W 11am
	 */
	/**
	 * examines arguments, reads from file line-by-line and creates a new
	 * instance of A2Q5b to evaluate each line. handles it's own file errors and
	 * exceptions thrown by other classes
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		ExpressionEvaluator lispExpression = null;
		double result = 0.0;
		if (args.length != 2)
		{
			usage();
			System.exit(1);
		}

		int type = Integer.parseInt(args[0]);
		String fileName = args[1];
		BufferedReader br = null;
		lispExpression = new ExpressionEvaluator();

		try
		{
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String expression = br.readLine();
			while (expression != null)
			{
				try
				{
					BoundedStack<String> stack = getStack(type);
					populateStack(stack, expression);
					
					result = lispExpression.startExpression(stack);
					System.out.println(result);
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
				lispExpression.cleanStack();
				expression = br.readLine();
			}
		}
		catch (Exception e)
		{
			System.out.println("Invalid Expression");
		}
		finally
		{
			try
			{
				br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	
	private static void populateStack(BoundedStack<String> stack, String expression)
	{
		StackPopulator stackPopulator = new StackPopulator(stack);
		stackPopulator.pushExpressionIntoStack(expression);
	}

	private static BoundedStack<String> getStack(int type)
	{
		if (type == 0) {
			return new ArrayStack<String>(CAPACITY);
		} else {
			return new LinkedListStack<String>(CAPACITY);
		}
	}
}
