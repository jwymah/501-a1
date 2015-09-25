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
public class A2Q5 {
	
	/**
	 *  Prints a message describing proper usage with respect to required
	 *  command line parameters and exits.
	 */
	private static void usage() {
				System.out.println("invalid inputs. Usage: A2Q5 <type> <filename>");
				System.exit(1);
		}
		/*
		 * Student: Jeremy Mah
		 * ID: 10053908
		 * Class: 331
		 * assignment 2
		 * Instructor: Mike Jacobson
		 * TA: Eduard Pelchat
		 * tut: M/W 11am
		 */
/**
 * examines arguments, reads from file line-by-line and creates a new instance of A2Q5b to evaluate each line.
 * handles it's own file errors and exceptions thrown by other classes
 * @param args
 */
	public static void main(String args[]){
		A2Q5b lispExpression = null;
		double result = 0.0;
		if (args.length != 2){
			usage();
			System.exit(1);
		}
		
		int type = Integer.parseInt(args[0]);
		String fileName = args[1];

		try
		{
		       FileReader fr = new FileReader(fileName);
		       BufferedReader br = new BufferedReader(fr);
		       String temp = br.readLine ();
		       while (temp != null)
		       {
		    	   try{
		    	   lispExpression = new A2Q5b();
		    	   result = lispExpression.startExpression(type, temp);
		    	   System.out.println(result);
		    	   }catch(Exception e){
		    		   System.out.println("Invalid Expression");
		    	   }
		    	   if (lispExpression!=null)
		    		   lispExpression.cleanStack();
		    	   temp = br.readLine ();
		       }
		}catch(Exception e){
			System.out.println("Invalid Expression");
		}
	}
}