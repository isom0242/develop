package com.example.iterator;

interface Iterator {
	public boolean hasNext();
	public Object next();
}

interface AggregateIterator {
	public Iterator iterator();
}