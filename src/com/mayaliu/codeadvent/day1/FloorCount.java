package com.mayaliu.codeadvent.day1;

import java.io.IOException;

import com.mayaliu.codeadvent.common.CommonUtils;

/** 
 * Solution for adventofcode's Day 1 puzzle, December 2015. See 
 * http://adventofcode.com/day/1.
 * 
 * This class keeps track of where Santa is based on an input string consisting 
 * of "(" and ")". Each "(" indicates one flight up, and each ")" indicates one
 * flight down. Both command-line and file arguments are accepted. The output 
 * indicates both where Santa is, as well as the first time Santa went into the
 * basement.
 *
 * @author MayaYL
 */
public class FloorCount {
	
	/**
	 * @param args
	 *   The string or file path containing Santa's instructions.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static void main(String[] args) throws IOException {
		String instructions = CommonUtils.processInput(args, "Matchsticks");
		
		// Parse and calculate floors.
		int floor = 0;
		int firstIndexOfBasement = -1;
		char[] instructionsArray = instructions.toCharArray();
		
		for (int i = 0; i<instructionsArray.length; i++) {
			char currentChar = instructionsArray[i];
			
			if (currentChar == '(') {
				floor++;
			}
			else if (currentChar == ')') {
				floor --;
			}
			else {
				System.err.format("Invalid character %s encountered.%n", currentChar);
				System.exit(1);
			}
			
			if (floor < 0 && firstIndexOfBasement == -1) {
				firstIndexOfBasement = i + 1;
			}
		}
		
		System.out.format("Santa is on floor %d.%n", floor);
		System.out.format("Santa first went to the basement at index %d.%n", firstIndexOfBasement);
	}

}
