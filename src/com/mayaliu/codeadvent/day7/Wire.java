package com.mayaliu.codeadvent.day7;

import java.util.HashMap;

public class Wire {
	private String name;
	private HashMap<String, Connection> connections;
	private short value;
	private boolean isSettled;
	
	public Wire(String name) {
		this.name = name;
		this.connections = new HashMap<String, Connection>();
		this.isSettled = false;
		this.value = 0;
	}
	
	public void setValue(short val) throws Exception {
		if (!this.isSettled) {
			this.isSettled = true;
			this.value = val;
//			System.out.println("****" + this.name + " is now " + val);
		}
		else {
			String message = String.format("Value has already been set for %s.", this.name);
			throw new Exception(message);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public short getValue() {
		return this.value;
	}
	
	public void addConnection(Connection c) {
		this.connections.put(c.getConnectionString(), c);
	}
	
	public void removeConnection(Connection c) {
		this.connections.remove(c.getConnectionString());
	}
	
	public HashMap<String, Connection> getRemainingConnections() {
		return this.connections;
	}
	
	public boolean isSettled() {
		return this.isSettled;
	}
	
	@Override
	public String toString() {
		return String.format("{%s, %s}\n", this.name, this.connections.keySet().toString());
	}
	
}