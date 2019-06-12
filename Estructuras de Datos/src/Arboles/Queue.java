package Arboles;

public interface Queue<T> {
 public void enqueue(T e) throws FullQueueException;
 public T dequeue() throws EmptyQueueException;
 public T front() throws EmptyQueueException;
 public boolean isEmpty();
 public int size();
}
