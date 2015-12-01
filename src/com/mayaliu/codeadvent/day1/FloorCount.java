package com.mayaliu.codeadvent.day1;

import java.io.File;

/**
 * 
 */

/**
 * @author maya.liu
 *
 */
public class FloorCount {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			// Print out usage.
			System.out.format("Usage:%njava FloorCount [input]/[file location]%n");
			System.exit(0);
		}
		
		String input = args[0];
		
		// Input validation.
		if (input == null) {
			System.err.println("Invalid input received.");
		}
		
		// If input is a file.
		boolean isFile = new File(input).isFile();
		if (isFile) {
			File file = new File(input);
			
		}
		
		// If input is in the command-line.
		
		// Parse and calculate floors.
		int floor = 0;
		int firstIndexOfBasement;
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
			
			if (floor <0) {
				System.out.format("Santa first went to the basement at index %d.%n", i+1);
				System.exit(0);
			}
		}
		
		System.out.format("Santa is on floor %d.%n", floor);
	}

}
