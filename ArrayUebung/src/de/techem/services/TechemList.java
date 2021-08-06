package de.techem.services;

import java.util.Optional;

public interface TechemList<T> {
	
	void append(T t);
	Optional<T> get();
	boolean update(T t);
	boolean remove();
	
	
	boolean movePrevious();
	boolean moveNext();
	
	boolean isEmpty();
	boolean isEndOfList();
	boolean isBeginnOfList();
	
	default boolean removeAll() {
		if(isEmpty()) return false;
		while(remove()) {}
		return true;
	}
	
	default boolean moveFirst() {
		if(isEmpty()) return false;
		while(movePrevious()) {}
		return true;
	}
	default boolean moveLast() {
		if(isEmpty()) return false;
		while(moveNext()) {}
		return true;
	}
	
	
	

}
