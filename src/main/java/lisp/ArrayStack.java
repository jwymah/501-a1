package lisp;

import java.util.EmptyStackException;

public class ArrayStack<T> extends BoundedStack<T>{
	private T[] stack;
	private int top = -1;
	
	@SuppressWarnings("unchecked")
	public	ArrayStack (int newCapacity){
		super(newCapacity);
		stack = (T[]) new String[newCapacity];
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
