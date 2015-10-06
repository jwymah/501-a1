package lisp;

/**
 * Interface for question two.
 * 
 * @author Jeremy mah
 * @param <T>
 *
 */
public abstract class BoundedStack<T>
{
	protected int size;
	private int capacity;

	public BoundedStack(int capacity)
	{
		size = 0;
		this.capacity = capacity;
	}
	
	/**
	 * @returns the size of the stack. ie. set elements in the array.
	 */
	public int size() {
		return size;
	}
	/**
	 * @returns the maximum size of the stack. ie. size of the array.
	 */
	public int capacity() {
		return capacity;
	}
	/**
	 * checks to see whether the size==capacity.
	 * 
	 * @returns true if size==capacity, false otherwise.
	 */
	public boolean isFull() {
		if (size == capacity)
			return true;
		return false;
	}

	/**
	 * Tests whether the stack is empty.
	 *
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}
	/**
	 * Pushes the object x onto the top of the stack.
	 *
	 * @param x
	 *            object to be pushed onto the stack.
	 * @throws FullStackException
	 *             if the stack is full.
	 */
	public abstract void push(T x);

	/**
	 * Returns the object at the top of the stack.
	 *
	 * @return reference to the item at the top of the stack
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public abstract T top();

	/**
	 * Removes and returns the object at the top of the stack.
	 *
	 * @return reference to the item at the top of the stack
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public abstract T pop();
}
