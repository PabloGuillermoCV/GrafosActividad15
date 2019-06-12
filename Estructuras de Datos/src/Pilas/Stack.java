package Pilas;

public interface Stack <T>{
 public void push(T elemento);
 public T pop() throws EmptyStackException;
 public int size();
 public boolean isEmpty();
 public T top() throws EmptyStackException;
}
