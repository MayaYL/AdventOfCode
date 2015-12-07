package com.mayaliu.codeadvent.day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.StatUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 7 puzzle, December 2015. See 
 * http://adventofcode.com/day/7
 */
public class Assembly {
	
	public static final String USAGE = String.format("Usage:%njava Assembly [input]/[file location]");
	
	private static WireMap wireMap;
	private static ArrayList<String> solvableInputs;
	
	/**
	 * @param args
	 *   The string or file path containing the list of instructions.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static void main(String[] args) throws IOException {
		// Print usage if there are no arguments, or if the "help" option is 
		// specified.
		if (args.length != 1) {
			CommonUtils.printUsage(USAGE);
		}
		
		String input = args[0];
		
		if (input == "--help" || input == "-h") {
			CommonUtils.printUsage(USAGE);
		}
		
		// Input validation.
		if (StringUtils.isEmpty(input)) {
			CommonUtils.printUsage(USAGE);
		}
		
		String instructionLines = input;
		
		// If input is a file, use its contents as the word list string.
		boolean isFile = CommonUtils.isFile(input);
		if (isFile) {
			instructionLines = CommonUtils.getDataFromFile(input);
		}
		
		String[] instructions = instructionLines.split("\n");
		
		wireMap = new WireMap();
		solvableInputs = new ArrayList<String>();
		parseInstructions(instructions);
		

	
		System.out.println(wireMap.toString());
		System.out.format("The final value of wire a is %d.", 1);
	}
	
	private static void parseInstructions(String[] instructions) {
		for (String instruction : instructions) {
			// Parse out the wire to set the value for.
			String[] inputAndOutput = instruction.split("->");
			String outputWire = inputAndOutput[1];
			
			// Parse out the inputs.
			String[] inputWires = inputAndOutput[0].split(" ");
			String inputWire1 = "";
			String inputWire2 = "";
			
			switch (inputWires.length) {
			case 1:
				// Simple assignment, or starting input (the first wire to have
				// a numeric value assigned to it).
				inputWire1 = inputWires[0];
				if (StringUtils.isNumeric(inputWire1)) {
					solvableInputs.add(instruction);
				}
				wireMap.addInstruction(inputWire1, instruction);
				break;
			case 2:
				// NOT operator.
				inputWire1 = inputWires[1];
				wireMap.addInstruction(inputWire1, instruction);
				break;
			case 3:
				// Two inputs- variables and/or values.
				inputWire1 = inputWires[0];
				if (!StringUtils.isNumeric(inputWire1)) {
					wireMap.addInstruction(inputWire1, instruction);
				}
				inputWire2 = inputWires[2];
				if (!StringUtils.isNumeric(inputWire2)) {
					wireMap.addInstruction(inputWire2, instruction);
				}
				break;
			}
		}
	}
	
	private static int run() throws NumberFormatException, Exception {
		// Start from solvableInputs...
		while (!wireMap.isEmpty()) {
			for (String input : solvableInputs) {
				Wire currentWire = wireMap.get(input);
				if (StringUtils.isNumeric(input)) {
					String[] inputAndOutput = input.split("->");
					String outputWireName = inputAndOutput[1];
					Wire outputWire = wireMap.get(outputWireName);
					outputWire.setValue(Integer.parseInt(input));
				}
			}
		}
		
		return 1;
	}

	private static class Wire {
		private String name;
		private ArrayList<String> instructions;
		private int value;
		private boolean isSettled;
		
		public Wire(String name) {
			this.name = name;
			this.instructions = new ArrayList<String>();
			this.isSettled = false;
			this.value = 0;
		}
		
		public void setValue(int val) throws Exception {
			if (!this.isSettled) {
				this.isSettled = true;
				this.value = val;
			}
			else {
				String message = String.format("Value has already been set for %s.", this.name);
				throw new Exception(message);
			}
		}
		
		public int getValue() {
			return this.value;
		}
		
		public void addInstruction(String inst) {
			this.instructions.add(inst);
		}
		
		public void removeInstruction(String inst) {
			this.instructions.remove(inst);
		}
		
		public ArrayList<String> getRemainingInstructions() {
			return this.instructions;
		}
		
		public boolean isSettled() {
			return this.isSettled;
		}
		
		@Override
		public String toString() {
			return String.format("{%s, %s}\n", this.name, this.instructions.toString());
		}
	}
	
	private static class Connection {
		private String connectionString;
		private ArrayList<String> inputNames;
		private String outputName;
		private String operator;
		
		public Connection(String connection) {
			
		}
	}
	
	private static class WireMap extends HashMap<String, Wire> {
		public void addInstruction(String key, String val) {
			Wire w = this.get(key);
			
			if (w == null) {
				w = new Wire(key);
			}
			w.addInstruction(val);
			
			this.put(key, w);
		}
	}
}
