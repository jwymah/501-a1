package main.java;
/**
 * The cpsc331Stack interface represents the Stack ADT as described
 * in the CPSC 331 lectures during the Winter 2007 term.
 *
 * @author  Mike Jacobson
 * @version 1.0, Oct 6, 2008
 */
public interface cpsc331Stack<T> {

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Pushes the object x onto the top of the stack.
     *
     * @param x object to be pushed onto the stack.
     */
    public void push(T x);

    /**
     * Returns the object at the top of the stack.
     *
     * @return reference to the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T top();

    /**
     * Removes and returns the object at the top of the stack.
     *
     * @return reference to the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop();
}