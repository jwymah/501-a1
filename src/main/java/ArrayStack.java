package main.java;
import java.util.EmptyStackException;

public class ArrayStack<T> implements BoundedStack<T>{
	private T[] stack;
	private int size = 0;
	private int capacity = 0;
	private int top = -1;
	
	@SuppressWarnings("unchecked")
	public	ArrayStack (int newCapacity){
		stack = (T[]) new String[newCapacity];
		capacity = newCapacity;
	}
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@Override
	public T top() throws EmptyStackException{
		if (isEmpty()){
			throw new EmptyStackException ();
		}
		T temp = stack[top];
		return temp;
	}

	@Override
	public T pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException ();
		T temp = stack[top];
		top--;
		size--;
		
		if	(Mode.debugStack==true)
			System.out.println("popped " + temp);
		return temp;
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
		top++;
		stack[top] = x;
		size++;
		
		if	(Mode.debugStack==true)
			System.out.println("pushed " + x);
		return;
	}
}
