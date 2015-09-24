import org.junit.*;
import static org.junit.Assert.*;
/*
 * Student: Jeremy Mah
 * ID: 10053908
 * Class: 331
 * assignment 2
 * Instructor: Mike Jacobson
 * TA: Eduard Pelchat
 * tut: M/W 11am
 */


/*junit tests for assignment 2
 * 
 * these tests are quite complete because the algorithm used to evaluate the expressions is recursive,
 * and since every smaller case is considered, any combination of these cases will yield the same result
 * 
 */
public class A2Q6 {
	private A2Q5b stackA;
	private A2Q5b stackL;
	private final int ARRAY = 0;
	private final int LL = 1;
	
	@Before
	public void init() {
	stackA = new A2Q5b();
	stackL = new A2Q5b();
	}
	/**
	 * begin black box tests for assignment 2 question 5.
	 * note: all methods to be tested are in A2Q5b, as A2Q5 is just the driver that takes command line args
	 */
	
	
	/**
	 * test case 1: tests empty string as the expression
	 * expected: InvalidExpressionException
	 * purpose: to test is empty expressions are real expessions
	 */
	
 @Test (expected = InvalidExpressionException.class)
 public void testNullA(){
    String expression = "";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
 }
 @Test (expected = InvalidExpressionException.class)
 public void testNullL(){
     String expression = "";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
 }
 
 /*
  * test case 2: tests expression with only brackets
  * Expected: 0.0
  * purpose: the next closest thing to nothing is nothing wrapped up like a gift
  * 
  * note: could not find whether this was a valid expression, so i assumed that it IS and evaluates to zero.
  * it would be an easy fix to change it to an invalid expression
  */
 @Test
 public void emptyExpressionA(){
	String expression = "()";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	assertEquals(0.0, stackA.startExpression(ARRAY, expression), 1e-16);
 }
 @Test
 public void emptyExpressionL(){
     String expression = "()";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(0.0, stackL.startExpression(LL, expression), 1e-16);
 }
 
 
 /*
  * test case 3: minimal operands, + and *
  * expected: 0.0 for + and 1.0 for *
  */
 @Test
 public void minimalExpressionPlusA(){
	String expression = "(+)";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	assertEquals(0.0, stackA.startExpression(LL, expression), 1e-16);
 }
 @Test
 public void minimalExpressionPlusL(){
     String expression = "(+)";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(0.0, stackL.startExpression(LL, expression), 1e-16);
}

 @Test
 public void minimalExpressionMulA(){
	String expression = "(*)";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	assertEquals(1.0, stackA.startExpression(LL, expression), 1e-16);
 }
 @Test
 public void minimalExpressionMulL(){
     String expression = "(*)";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(1.0, stackL.startExpression(LL, expression), 1e-16);
}
 
 
 /*
  * test case 4: illegal size for operators - and /
  * expected: InvalidExpressionException
  * purpose: these operators require at least one operand. This tests that.
  * 
  */
 @Test (expected = InvalidExpressionException.class)
 public void illegalExpressionSubA(){
	String expression = "(-)";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
 }
 @Test (expected = InvalidExpressionException.class)
 public void illegalExpressionSubL(){
     String expression = "(-)";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}

 @Test (expected = InvalidExpressionException.class)
 public void illegalExpressionDivA(){
	String expression = "(/)";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
 }
 @Test (expected = InvalidExpressionException.class)
 public void illegalExpressionDivL(){
     String expression = "(/)";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}
 
 
 /*
  * test case 5: minimal operands for - and /
  * expected: -5 and 0.2
  * purpose: test the lower bound of what is required for these operators
  * 
  */
 @Test
 public void minimalExpressionSubA(){
	String expression = "(- 5)";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	assertEquals(-5.0, stackA.startExpression(ARRAY, expression), 1e-16);
 }
 @Test
 public void minimalExpressionSubL(){
     String expression = "(- 5)";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(-5.0, stackL.startExpression(LL, expression), 1e-16);
}

 @Test
 public void minimalExpressionDivA(){
	String expression = "(/ 5)";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	assertEquals(0.2, stackA.startExpression(ARRAY, expression), 1e-16);
 }
 @Test
 public void minimalExpressionDivL(){
     String expression = "(/ 5)";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(0.2, stackL.startExpression(LL, expression), 1e-16);
}
 
 /*
  * test case 6: typical cases with nested expressions
  * Expected: 3.1415926534674368 and 16.5 respectively
  * purpose: test codes ability to evaluate robust expressions with many combinations
  * 
  */
@Test
 public void typicalA1(){
	 String expression ="(+ 3 (/ (+ 7 (/ (+ 9 6 (/ (+ 1 (/ (+ (* 4 (+ 1 (* 8 9))) (/ (+ 1 1)))))))))))";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	assertEquals(3.1415926534674368, stackA.startExpression(ARRAY, expression), 1e-16);
 }
@Test
public void typicalL1(){
	 String expression ="(+ 3 (/ (+ 7 (/ (+ 9 6 (/ (+ 1 (/ (+ (* 4 (+ 1 (* 8 9))) (/ (+ 1 1)))))))))))";
	System.out.println("\nTesting evalute(String) with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(3.1415926534674368, stackL.startExpression(LL, expression), 1e-16);
}
@Test
public void typicalA2(){
	 String expression ="(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 )))";
	System.out.println("\nTesting evalute(String) with: type=array, string= " + "'"+expression+"'");
	assertEquals(16.5, stackA.startExpression(ARRAY, expression), 1e-16);
}
@Test
public void typicalL2(){
	 String expression ="(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1 )))";
	System.out.println("\nTesting evalute(String) with: type=LL, string= " + "'"+expression+"'");
	assertEquals(16.5, stackL.startExpression(LL, expression), 1e-16);
}

/*
 * test case 7: invalid consecutive operands
 * Expected: InvalidExpressionException
 * purpose: ensure that an operator doesn't try to operate on another operator
 */
@Test (expected = InvalidExpressionException.class)
public void invalidOperandsA(){
	String expression = "(+ - 3345)";
	System.out.println("\nTesting evaluate() with: type=array, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
}
@Test (expected = InvalidExpressionException.class)
public void invalidOperandsL(){
	String expression = "(+ - 3345)";
	System.out.println("\nTesting evaluate() with: type=linkedList, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}

/*
 * test case 8: arbitrary and negative integers
 * Expected: -10000.0 and -88888 respectively
 * purpose: this functionality was added in. so best make sure it works
 */
@Test
public void arbitraryIntsA1(){
	String expression = "(+ -5666 -4334)";
	System.out.println("\nTesting evaluate() with: type=array, string= " + "'"+expression+"'");
	assertEquals(-10000.0, stackA.startExpression(ARRAY,expression), 1e-16);
}
@Test
public void arbitraryIntsL1(){
	String expression = "(+ -5666 -4334)";
	System.out.println("\nTesting evaluate() with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(-10000.0, stackL.startExpression(LL,expression), 1e-16);
}
@Test
public void arbitraryIntsA2(){
	String expression = "(- -99999 -11111)";
	System.out.println("\nTesting evaluate() with: type=array, string= " + "'"+expression+"'");
	assertEquals(-88888.0, stackA.startExpression(ARRAY,expression), 1e-16);
}
@Test
public void arbitraryIntsL2(){
	String expression = "(- -99999 -11111)";
	System.out.println("\nTesting evaluate() with: type=linkedList, string= " + "'"+expression+"'");
	assertEquals(-88888.0, stackL.startExpression(LL,expression), 1e-16);
}

/*
 * test case 9: if operands come after numbers
 * expected: InvalidExpressionException
 * purpose: see what would happen if an operator came after a number. similar to consecutive operands
 * in that it doesn't want to see operators try to operate on other operators
 */
@Test (expected = InvalidExpressionException.class)
public void illegalOrderA(){
	String expression = "(+ -5666 -4334+)";
	System.out.println("\nTesting evaluate() with: type=array, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
}
@Test (expected = InvalidExpressionException.class)
public void illegalOrderL(){
	String expression = "(+ -5666 -4334+)";
	System.out.println("\nTesting evalute(LL) with: type=linkedList, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}

/*
 * test case 10: mismatched brackets
 * expected: InvalidExpressionException
 * purpose: dealing with computers, expressions should be concrete and brackets MUST be matched
 * 
 */
@Test (expected = InvalidExpressionException.class)
public void illegalBracketsA1(){
	String expression = "((+ 3 3))))";
	System.out.println("\nTesting evaluate() with: type=array, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
}
@Test (expected = InvalidExpressionException.class)
public void illegalBracketsL1(){
	String expression = "((+ 3 3))))";
	System.out.println("\nTesting evaluate() with: type=LL, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}

@Test (expected = InvalidExpressionException.class)
public void illegalBracketsA2(){
	String expression = "(+ 3(+ 3(+3))";
	System.out.println("\nTesting evaluate() with: type=array, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
}
@Test (expected = InvalidExpressionException.class)
public void illegalBracketsL2(){
	String expression = "(+ 3(+ 3(+3))";
	System.out.println("\nTesting evaluate() with: type=LL, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}

/*
 * tests to see what would happen with a normal everyday math expression where number comes first
 * expected: InvalidExpressionException
 * purpose: likely to happen, a person will input an expression with normal language, and lisp should not accept
 */
@Test (expected = InvalidExpressionException.class)
public void nonLispA(){
	String expression = "(4 + 5)";
	System.out.println("\nTesting evaluate() with: type=array, string= " + "'"+expression+"'");
	assertEquals(9.0, stackA.startExpression(ARRAY,expression), 1e-16);
}@Test (expected = InvalidExpressionException.class)
public void nonLispL(){
	String expression = "(4 + 5)";
	System.out.println("\nTesting evaluate() with: type=LL, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}

/*
 * test expressions that are too large for the bounded stack
 * expected: FullStackException (this will vary depending on capacity, may have
 * a stack large enough to hold this and it wont throw, as this is >100 elements but not infinite)
 * purpose: make sure we don't push more than the stack can hold, and with some machines/languages, keep security
 */
@Test (expected = FullStackException.class)
public void fullStackA(){
String expression = "(+ 1 1 1 1 1 1 1  1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 ";
	System.out.println("\nTesting evaluate() with: type=LL, string= " + "'"+expression+"'");
	stackA.startExpression(ARRAY, expression);
}
@Test (expected = FullStackException.class)
public void fullStackL(){
String expression = "(+ 1 1 1 1 1 1 1  1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 ";
	System.out.println("\nTesting evaluate() with: type=LL, string= " + "'"+expression+"'");
	stackL.startExpression(LL, expression);
}
}
