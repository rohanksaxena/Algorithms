package com.rks.algorithms;

public class Item {
	
	private int value;
	private int size;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public void printItem(){
		System.out.println("Value: "+this.getValue()+"\tSize: "+this.getSize());
	}
	
}
