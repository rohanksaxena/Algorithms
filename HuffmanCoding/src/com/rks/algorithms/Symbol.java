package com.rks.algorithms;

public class Symbol {

	private String key;
	private int weight;
	private int level;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void printSymbol(){
		System.out.println(this.getKey()+" : "+this.getWeight()+"\tat level: "+this.getLevel());
	}
}
