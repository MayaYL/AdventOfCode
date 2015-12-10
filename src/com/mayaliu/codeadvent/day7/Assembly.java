package com.mayaliu.codeadvent.day7;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.math.NumberUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * Solution for adventofcode's Day 7 puzzle, December 2015. See 
 * http://adventofcode.com/day/7
 * 
 * @author MayaYL
 */
public class Assembly {
	
	private static WireMap wireMap;
	private static ArrayList<Connection> solvableConnections;
	
	/**
	 * @param args
	 *   The string or file path containing the list of instructions.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String instructionLines = CommonUtils.processInput(args, "Assembly");
		
		String[] instructions = instructionLines.split("\n");
		
		wireMap = new WireMap();
		solvableConnections = new ArrayList<Connection>();
		parseInstructions(instructions);
		run();
	
		ArrayList<String> keys = new ArrayList<String>(wireMap.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			System.out.println(wireMap.get(key).getName() + " is set to " + wireMap.get(key).getValue());
		}
	}
	
	private static void parseInstructions(String[] instructions) {
		for (String instruction : instructions) {			
			Connection c = new Connection(instruction);
			ArrayList<String> inputNames = c.getInputNames();
			if (inputNames.size() == 1) {
				if (NumberUtils.isNumber(inputNames.get(0))) {
					solvableConnections.add(c);
				}
				else {
					wireMap.addInstruction(inputNames.get(0), c);
				}
			}
			else {
				for (int i = 0; i < inputNames.size(); i++) {
					String inputName = inputNames.get(i);
					if (NumberUtils.isNumber(inputName)) {
						continue;
					}
					wireMap.addInstruction(inputName, c);
				}
			}
		}
	}
	
	private static void run() throws NumberFormatException, Exception {
		// Start from solvableInputs...
		while (!solvableConnections.isEmpty()) {
			for (int i = 0; i < solvableConnections.size(); i++) {
				Connection c = solvableConnections.get(i);
				Wire output = wireMap.get(c.getOutputName());
				if (output == null) {
					wireMap.put(c.getOutputName(), new Wire(c.getOutputName()));
					output = wireMap.get(c.getOutputName());
				}
				ArrayList<String> currentInputs = c.getInputNames();
				switch (currentInputs.size()) {
				case 1:
					// One value, with or without a NOT operator.
					short value = 0;
					String variableName = currentInputs.get(0);
					if (NumberUtils.isNumber(variableName)) {
						value = Short.parseShort(variableName); 
					}
					else {
						if (wireMap.get(variableName).isSettled()) {
							value = wireMap.get(variableName).getValue();
						}
						else {
							continue;
						}
					}
					if (c.getOperator() == null) {
						output.setValue(value);
					}
					else {
						output.setValue((short) (~value));
					}
					break;
				case 2:
					// Two values and an operator
					short value1 = 0;
					short value2 = 0;
					String variableName1 = currentInputs.get(0);
					String variableName2 = currentInputs.get(1);
					output = wireMap.get(c.getOutputName());
					if (NumberUtils.isNumber(variableName1)) {
						value1 = Short.parseShort(variableName1); 
					}
					else {
						if (wireMap.get(variableName1).isSettled()) {
							value1 = wireMap.get(variableName1).getValue();
						}
						else {
							continue;
						}
					}
					if (NumberUtils.isNumber(variableName2)) {
						value2 = Short.parseShort(variableName2); 
					}
					else {
						if (wireMap.get(variableName2).isSettled()) {
							value2 = wireMap.get(variableName2).getValue();
						}
						else {
							continue;
						}
					}
					switch (c.getOperator()) {
					case "AND":
						output.setValue((short) (value1 & value2));
						break;
					case "OR":
						output.setValue((short) (value1 | value2));
						break;
					case "LSHIFT":
						output.setValue((short) (value1 << value2));
						break;
					case "RSHIFT":
						output.setValue((short)((char)value1 >>> (char)value2));
						break;						
					}
					break;
				}
				
				output.removeConnection(c);
				for (String currentInput : currentInputs) {
					if (!NumberUtils.isNumber(currentInput)) {
						Wire in = wireMap.get(currentInput);
						in.removeConnection(c);
						wireMap.put(currentInput, in);
					}
				}
				solvableConnections.removeAll(Collections.singleton(c));
				solvableConnections.addAll(output.getRemainingConnections().values());
				wireMap.put(c.getOutputName(), output);
			}
		}
	}

}
