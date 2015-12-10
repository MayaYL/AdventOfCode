package com.mayaliu.codeadvent.day9;

/**
 * A simple class to store data about Destinations.
 * 
 * @author maya.liu
 */
public class Destination implements Comparable<Destination>{
	private String name;
	private int distance;
	private boolean shortest;
	
	public Destination(String name, boolean shortest) {
		this.name = name;
		this.distance =0;
		this.shortest = shortest;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public void setDistance(int dist) {
		this.distance = dist;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isShortest() {
		return this.shortest;
	}
	
	@Override
	public int compareTo(Destination o) {
		if (this.getDistance() < o.getDistance()) {
			return shortest? -1 : 1;
		}
		return shortest? 1 : -1;
	}
}
