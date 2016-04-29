package com.example.iterator;

interface Iterator<E> {
	boolean hasNext();
	E next();
	void remove();
}

interface Iterable<T> {
	Iterator<T> iterator();
}