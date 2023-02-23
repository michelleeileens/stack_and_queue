import java.util.ArrayList;
import java.util.Collections;

public class MyStack<T> implements StackInterface<T> {
	
	private int size;
	private ArrayList<T> stack;
	int items = 0;
	
	public MyStack(){
		size = 100;
		stack = new ArrayList<T> (size);
	}
	
	public MyStack(int size) {
		this.size =size;
		stack = new ArrayList<T> (size);
	}

	@Override
	public boolean isEmpty() {
		if (items == 0)
			return true;
		return false;
	}

	@Override
	public boolean isFull() {
		if (items == size)
			return true;
		return false;
	}

	@Override
	public T pop() throws StackUnderflowException {
		T poppedElement = null;
		if(items == 0) 
			throw new StackUnderflowException();
		else {
			poppedElement = stack.get(items - 1);
			stack.remove(items - 1);
			items--;
			return poppedElement;
		}
	}

	@Override
	public T top() throws StackUnderflowException {
		if(items == 0)
			throw new StackUnderflowException();
		return stack.get(items-1);
	}

	@Override
	public int size() {
		return items;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if(items >= size) 
			throw new StackOverflowException();
		else {
			stack.add(e);
			items++;
			return true;
		}

	}
	
	@Override
	public String toString() {
		String stackString = "";
		for (T i : stack ) 
			stackString += i;
		return stackString;
	}
	
	@Override
	public String toString(String delimiter) {
		String stackString="";
		for (int i = 0; i < stack.size(); i++ ) {
			if ( i != stack.size() - 1)
				stackString += stack.get(i)+delimiter;
			else
				stackString += stack.get(i);
		}
		return stackString;
	}
	
	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> listCopy = new ArrayList<T>(list);
		stack.addAll(listCopy);
		items = stack.size();	
	}

}