package com.rks.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class JobScheduling{
	
	private static int jobSize;
	private static int currentTime;
	private static long sumOfWeightedCompletionTimes;
	
	public static void main(String[] args) {
		ArrayList<Job> jobs = readFile("jobs.txt");
		System.out.println("before sorting:"); 
		Collections.sort(jobs, new JobComparator());
		System.out.println("after sorting:");
		calculateCompletionTimes(jobs);
		System.out.println("Answer: "+sumOfWeightedCompletionTimes);
	}

	public static ArrayList<Job> readFile(String fileName){
		ArrayList<Job> jobs = new ArrayList<>();
		try {
			Scanner in = new Scanner(new File(fileName));
			jobSize = in.nextInt();
			System.out.println(jobSize);
			while(in.hasNextLine()){
					Job job = new Job();
					job.setWeight(in.nextInt());
					job.setLength(in.nextInt());
					job.objectiveFunction();
					jobs.add(job);					
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return jobs;		
	}	
	
	public static void calculateCompletionTimes(ArrayList<Job> jobs){
		for(Job j: jobs){
			j.setStartTime(currentTime);
			j.setCompletionTime(j.getStartTime()+j.getLength());
			currentTime += j.getLength();
			j.setWeightedCompletionTime(j.getCompletionTime()*j.getWeight());
			sumOfWeightedCompletionTimes += j.getWeightedCompletionTime();
 		}
	}
}
 