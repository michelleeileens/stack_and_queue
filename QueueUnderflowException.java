
public class QueueUnderflowException extends Exception {
	public QueueUnderflowException() {
		super("Dequeue method called on an empty queue");
	}
}
