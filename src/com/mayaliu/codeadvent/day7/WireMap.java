package com.mayaliu.codeadvent.day7;

import java.util.HashMap;

public class WireMap extends HashMap<String, Wire> {
	private static final long serialVersionUID = 1L;

	public void addInstruction(String key, Connection val) {
		Wire w = this.get(key);
		
		if (w == null) {
			w = new Wire(key);
		}
		w.addConnection(val);
		
		this.put(key, w);
	}
}
