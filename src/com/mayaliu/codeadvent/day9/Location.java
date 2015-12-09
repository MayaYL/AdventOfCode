package com.mayaliu.codeadvent.day9;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Location implements Comparable<Location>{
	private String name;
	private int distanceFromSource;
	private PriorityQueue<Location> destinations;
	private boolean visited;
	
	public Location(String name) {
		this.name = name;
		this.distanceFromSource =0;
		this.destinations = new PriorityQueue<Location>();
		this.visited = false;
	}
	
	public void addDestination(Location l, int distance) {
		l.setDistanceFromSource(distance);
		this.destinations.add(l);
	}
	
	public int getDistanceFromSource() {
		return this.distanceFromSource;
	}
	
	public void setDistanceFromSource(int dist) {
		this.distanceFromSource = dist;
	}
	
	public String getName() {
		return this.name;
	}
	
//	public void visit() {
//		this.visited = true;
//	}
//	
//	public boolean isVisited() {
//		return this.visited;
//	}
	
	public PriorityQueue<Location> getDestinations() {
		return this.getDestinations();
	}

	@Override
	public int compareTo(Location o) {
		if (this.getDistanceFromSource() < o.getDistanceFromSource()) {
			return -1;
		}
		return 1;
	}
}
