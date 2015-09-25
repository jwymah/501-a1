package lisp;
import java.util.EmptyStackException;
/*
 * Student: Jeremy Mah
 * ID: 10053908
 * Class: 331
 * assignment 2
 * Instructor: Mike Jacobson
 * TA: Eduard Pelchat
 * tut: M/W 11am
 */
public class LinkedListStack<T> implements BoundedStack<T>{
	private int size;
	private int capacity;
	private ListNode head;

	public LinkedListStack (int newCapacity){
		capacity = newCapacity;
	}
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T top() throws EmptyStackException{
		if (isEmpty())
				throw new EmptyStackException();
		String temp = head.getData();
		return (T) temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pop() throws EmptyStackException{
		if (size <= 0)
				throw new EmptyStackException();
		
		String temp = head.getData();
		if	(size == 1)
			head = null;
		else
			head = head.getNext();
		
		size--;
		if	(Mode.debugStack == true)
			System.out.println("popped: "+temp);
		return (T) temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public boolean isFull() {
		if (size == capacity)
			return true;
		return false;
	}

	@Override
	public void push(T x) throws FullStackException{
		if (isFull())
			throw new FullStackException("Stack is full. Cannot push anymore");
		ListNode temp = new ListNode((String) x);
		temp.setNext(head);
		size++;
		if	(Mode.debugStack == true)
			System.out.println("pushed: "+x);
		head = temp;
	}

}
