package com.rks.algorithms;

public class Job{
	
	private int weight;
	private int length;
	private int cost;
	private int startTime;
	private int completionTime;
	private int weightedCompletionTime;
	
	public int getWeightedCompletionTime() {
		return weightedCompletionTime;
	}

	public void setWeightedCompletionTime(int weightedCompletionTime) {
		this.weightedCompletionTime = weightedCompletionTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void objectiveFunction(){
		this.setCost(weight-length);
	}
	
	public void CompletionTime(int startTime){
		this.setCompletionTime(startTime+this.cost);
	}
}
