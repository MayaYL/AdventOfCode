package com.mayaliu.codeadvent.day13;

public class Guest implements Comparable<Guest> {
	private String name;
	private int numNeighbors;
	
	/**
	 * The happiness level for the guest sitting next to the current one.
	 */
	private int happiness;
	
	public Guest(String name, int happiness) {
		this.name = name;
		this.happiness = happiness;
	}
	
	public int getNumNeighbors() {
		return this.numNeighbors;
	}
	
	public void addNeighbor() {
		this.numNeighbors++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	@Override
	public int compareTo(Guest o) {
		if (o.getHappiness() > this.happiness) {
			return 1;
		}
		return -1;
	}
	
	
}
