import java.util.ArrayList;
import java.util.Collections;

public class MyQueue<T> implements QueueInterface<T> {

	private int size;
	private ArrayList<T> queue;
	int front, end, items = 0;
	
	public MyQueue(){
		size = 100;
		queue = new ArrayList<T> (size);
	}
	
	public MyQueue(int size) {
		this.size = size;
		queue=new ArrayList<T> (size);
	}
	
	@Override
	public boolean isEmpty() {
		if (items!=0)
			return false;
		return true;
	}

	@Override
	public boolean isFull() {
		if (items == size)
			return true;
		return false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T dequeueElement;
		if (items == 0) 
			throw new QueueUnderflowException();
		else {
			dequeueElement=queue.get(front);
			front++;
			items--;
		}
		return dequeueElement;
	}

	@Override
	public int size() {
		return items;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (items == size) 
			throw new QueueOverflowException();
		else {
			queue.add(end,e);
			end++;
			items++;
			return true;
		}
	}
	
	@Override
	public String toString() {
		String queueString = "";
		for (T i:queue )
			queueString += i;
		return queueString;
	}
	
	@Override
	public String toString(String delimiter) {
		String queueString="";
		for (int i = 0; i < queue.size(); i++ ){
			if (i != queue.size() - 1)
				queueString += queue.get(i)+delimiter;
			else 
				queueString += queue.get(i);
		}
		return queueString;
	}

	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> queueCopy = new ArrayList<T>(list);
		queue.addAll(queueCopy);
		items = queue.size();
	}

}

