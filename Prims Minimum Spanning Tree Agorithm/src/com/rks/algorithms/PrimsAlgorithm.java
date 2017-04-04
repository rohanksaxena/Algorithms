package com.rks.algorithms;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class PrimsAlgorithm {

	private static Vector<Edge> edgeList;
	private static Vector<Integer> coveredNodes;
	private static Vector<Integer> remainingNodes;
	private static int vertexCount;
	private static int edgeCount;

	public static void main(String[] args) {
		long minimumSpanningTreeCost;
		edgeList = readFile("input.txt");
		minimumSpanningTreeCost = primsAlgo();
		System.out.println("Minimum Spanning Tree Cost :" + minimumSpanningTreeCost);
	}

	public static long primsAlgo(){
		long cost=0;
		int minLength = 10000;
		Edge edge = new Edge();;
		remainingNodes = new Vector<Integer>(vertexCount);
		coveredNodes = new Vector<Integer>(vertexCount);
		for(int i=2;i<=vertexCount;i++)
			remainingNodes.add(i);
		coveredNodes.add(1);
		while(remainingNodes.size()>0){	
			for(Edge e: edgeList){
				if (coveredNodes.contains(e.getStartNode())&&coveredNodes.contains(e.getEndNode())){
					continue;
				}
				else if(coveredNodes.contains(e.getStartNode())||coveredNodes.contains(e.getEndNode()))
				{
					if (e.getWeight()<minLength){
						minLength = e.getWeight();
						edge.setStartNode(e.getStartNode());
						edge.setEndNode(e.getEndNode());
						edge.setWeight(e.getWeight());
					}
				}						
			}
			
			addToCoveredNodes(edge);
			removeFromRemainingNodes(edge);
			cost += edge.getWeight();
			minLength = 10000;
		}
		return cost;
	}

	public static void addToCoveredNodes(Edge e){
		if(coveredNodes.contains(e.getStartNode())){			
			coveredNodes.add(e.getEndNode());
		}
		else if(coveredNodes.contains(e.getEndNode())){
			coveredNodes.add(e.getStartNode());
		}
	}
	
	public static void removeFromRemainingNodes(Edge e){
		if(remainingNodes.contains(e.getStartNode())){
			remainingNodes.remove(remainingNodes.indexOf(e.getStartNode()));
		}
		else if(remainingNodes.contains(e.getEndNode())){
			remainingNodes.remove(remainingNodes.indexOf(e.getEndNode()));
		}		
	}
	
	public static Vector<Edge> readFile(String fileName) {
		Vector<Edge> edges = new Vector<Edge>();
		try {
			Scanner in = new Scanner(new File(fileName));
			vertexCount = in.nextInt();
			System.out.println("vertexCount: " + vertexCount);
			edgeCount = in.nextInt();
			System.out.println("edgeCount: " + edgeCount);
			while (in.hasNextLine()) {
				Edge e = new Edge();
				e.setStartNode(in.nextInt());
				e.setEndNode(in.nextInt());
				e.setWeight(in.nextInt());
				edges.add(e);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edges;
	}

}
