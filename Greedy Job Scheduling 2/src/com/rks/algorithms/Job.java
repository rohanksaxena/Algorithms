package com.rks.algorithms;

public class Job{
	
	private double weight;
	private double length;
	private double cost;
	private double startTime;
	private double completionTime;
	private double weightedCompletionTime;
	
	public double getWeightedCompletionTime() {
		return weightedCompletionTime;
	}

	public void setWeightedCompletionTime(double weightedCompletionTime) {
		this.weightedCompletionTime = weightedCompletionTime;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	
	public double getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(double completionTime) {
		this.completionTime = completionTime;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void objectiveFunction(){
		this.setCost(weight/length);
	}
	
	public void CompletionTime(double startTime){
		this.setCompletionTime(startTime+this.cost);
	}
}
