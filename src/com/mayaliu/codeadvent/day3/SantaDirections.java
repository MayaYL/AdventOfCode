package com.mayaliu.codeadvent.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 3 puzzle, December 2015. See 
 * http://adventofcode.com/day/3
 */
public class SantaDirections {
	
	public static final String USAGE = String.format("Usage:%njava SantaDirections [input]/[file location]");
	
	/**
	 * @param args
	 *   The string or file path containing Santa's directions.
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
		
		String instructions = input;
		
		// If input is a file, use its contents as the instructions string.
		boolean isFile = CommonUtils.isFile(input);
		if (isFile) {
			instructions = CommonUtils.getDataFromFile(input);
		}

		HashSet<List<Integer>> visited = new HashSet<List<Integer>>();
		char[] instructionChars = instructions.toCharArray();
		ArrayList<Integer> houseCoordinates = new ArrayList<Integer>(2);
		int oldX = 0;
		int oldY = 0;
		houseCoordinates.add(0, oldX);
		houseCoordinates.add(1, oldY);
		visited.add(houseCoordinates);
		
		for (char instruction : instructionChars) {
			houseCoordinates = new ArrayList<Integer>(2);
			
			if (instruction == '^') {
				houseCoordinates.add(0, oldX);
				houseCoordinates.add(1, ++oldY);
			}
			else if (instruction == 'v') {
				houseCoordinates.add(0, oldX);
				houseCoordinates.add(1, --oldY);
			}
			else if (instruction == '<') {
				houseCoordinates.add(0, --oldX);
				houseCoordinates.add(1, oldY);
			}
			else if (instruction == '>') {
				houseCoordinates.add(0, ++oldX);
				houseCoordinates.add(1, oldY);
			}
			else {
				System.err.format("Invalid character %s encountered.%n", instruction);
				System.exit(1);
			}
			
			visited.add(houseCoordinates);
		}
		
		System.out.format("Santa visited %d unique houses.", visited.size());
	}

}
