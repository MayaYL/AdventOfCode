package com.mayaliu.codeadvent.day1;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 1 puzzle, December 2015. See 
 * http://adventofcode.com/day/1.
 * 
 * This class keeps track of where Santa is based on an input string consisting 
 * of "(" and ")". Each "(" indicates one flight up, and each ")" indicates one
 * flight down. Both command-line and file arguments are accepted. The output 
 * indicates both where Santa is, as well as the first time Santa went into the
 * basement.
 *
 */
public class FloorCount {

	/**
	 * @param args
	 *   The string or file path containing Santa's instructions.
	 */
	public static void main(String[] args) {
		// Print usage if there are no arguments, or if the "help" option is 
		// specified.
		if (args.length != 1) {
			printUsage();
		}
		
		String input = args[0];
		
		if (input == "--help" || input == "-h") {
			printUsage();
		}
		
		// Input validation.
		// TODO: use apache common
		if (input == null || input.length() == 0) {
			System.err.println("The input cannot be empty. Please provide either a string or a file name.");
			System.exit(1);
		}
		
		String instructions;
		
		// If input is a file.
		boolean isFile = new File(input).isFile();
		if (isFile) {
//			File file = new File(input);
//			FileInputStream inputStream = new FileInputStream(input);
//			try {
//				instructions = IOUtils.toString(inputStream);
//			} finally {
//			    inputStream.close();
//			}
		}
		else {
			instructions = input;
		}
		
		// Parse and calculate floors.
		int floor = 0;
		int firstIndexOfBasement = -1;
		char[] inputArray = input.toCharArray();
		
		for (int i = 0; i<inputArray.length; i++) {
			char currentChar = inputArray[i];
			
			if (currentChar == '(') {
				floor++;
			}
			else if (currentChar == ')') {
				floor --;
			}
			else {
				System.err.format("Invalid character %s encountered.%n", currentChar);
			}
			
			if (floor < 0 && firstIndexOfBasement == -1) {
				firstIndexOfBasement = i + 1;
			}
		}
		
		System.out.format("Santa is on floor %d.%n", floor);
		System.out.format("Santa first went to the basement at index %d.%n", firstIndexOfBasement);
	}
	
	/**
	 * Prints out the usage and exits with an error.
	 */
	private static void printUsage() {
		// Print out usage.
		System.out.format("Usage:%njava FloorCount [input]/[file location]%n");
		System.exit(1);
	}

}
