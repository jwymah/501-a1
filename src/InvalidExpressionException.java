/*
 * /*
 * Student: Jeremy Mah
 * ID: 10053908
 * Class: 331
 * assignment 2
 * Instructor: Mike Jacobson
 * TA: Eduard Pelchat
 * tut: M/W 11am
 */


/*new exception class for assignment2
 */
public class InvalidExpressionException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  /**
	  *
	  * @param message The message
	  */
	  InvalidExpressionException(String Message) {
	    super(Message);
	  }
	  
}
