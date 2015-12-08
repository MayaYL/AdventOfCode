package com.mayaliu.codeadvent.day7;

import java.util.ArrayList;

public class Connection {
	private String connectionString;
	private ArrayList<String> inputNames;
	private String outputName;
	private String operator;
	
	public Connection(String connection) {
		this.connectionString = connection;
		inputNames = new ArrayList<String>();
		
		// Parse out the wire to set the value for.
		String[] inputAndOutput = connection.split("->");
		outputName = inputAndOutput[1].trim();
		
		// Parse out the inputs.
		String[] inputWires = inputAndOutput[0].trim().split(" ");
		String inputWire1 = "";
		String inputWire2 = "";
		
		switch (inputWires.length) {
		case 1:
			// Simple assignment, or starting input (the first wire to have
			// a numeric value assigned to it).
			inputNames.add(inputWires[0].trim());
			operator = null;
			break;
		case 2:
			// NOT operator.
			inputNames.add(inputWires[1].trim());
			operator = "NOT";
			break;
		case 3:
			// Two inputs- variables and/or values.
			inputNames.add(inputWires[0].trim());
			operator = inputWires[1].trim();
			inputNames.add(inputWires[2].trim());
			break;
		}
	}
	
	public ArrayList<String> getInputNames() {
		return this.inputNames;
	}
	
	public String getOutputName() {
		return this.outputName;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public String getConnectionString() {
		return this.connectionString;
	}
	
	@Override
	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("inputs: ");
//		sb.append(inputNames.toString());
//		sb.append("\nOperator: ");
//		sb.append(operator);
//		sb.append("\nOutput: ");
//		sb.append(outputName);
//		sb.append("\n");
//		return sb.toString();
		return this.connectionString;
	}
	
}