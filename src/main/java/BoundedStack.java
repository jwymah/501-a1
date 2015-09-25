package main.java;

/**
 * Interface for question two.
 * 
 * @author Jeremy mah
 * @param <T>
 *
 */
public interface BoundedStack<T>
{
	/**
	 * @returns the size of the stack. ie. set elements in the array.
	 */
	public int size();

	/**
	 * @returns the maximum size of the stack. ie. size of the array.
	 */
	public int capacity();

	/**
	 * checks to see whether the size==capacity.
	 * 
	 * @returns true if size==capacity, false otherwise.
	 */
	public boolean isFull();

	/**
	 * Pushes the object x onto the top of the stack.
	 *
	 * @param x
	 *            object to be pushed onto the stack.
	 * @throws FullStackException
	 *             if the stack is full.
	 */
	public void push(T x);

	/**
	 * Tests whether the stack is empty.
	 *
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Returns the object at the top of the stack.
	 *
	 * @return reference to the item at the top of the stack
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public T top();

	/**
	 * Removes and returns the object at the top of the stack.
	 *
	 * @return reference to the item at the top of the stack
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public T pop();
}
