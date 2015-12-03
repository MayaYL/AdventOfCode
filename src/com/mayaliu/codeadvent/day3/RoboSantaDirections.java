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
 * Solution for part 2 of adventofcode's Day 3 puzzle, December 2015. See 
 * http://adventofcode.com/day/3
 */
public class RoboSantaDirections {
	
	public static final String USAGE = String.format("Usage:%njava RoboSantaDirections [input]/[file location]");
	
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
		int santaOldX = 0; 
		int santaOldY = 0;
		int roboSantaOldX = 0;
		int roboSantaOldY = 0;
		
		houseCoordinates.add(0, santaOldX);
		houseCoordinates.add(1, santaOldY);
		visited.add(houseCoordinates);
		
		for (int i = 0; i < instructionChars.length; i++) {
			char instruction = instructionChars[i];
			houseCoordinates = new ArrayList<Integer>(2);
			int oldX, oldY;
			
			// Santa moves on the odd-numbered instructions; RoboSanta moves on
			// the even-numbered instructions.
			if (i%2 == 1) {
				oldX = santaOldX;
				oldY = santaOldY;
			}
			else{
				oldX = roboSantaOldX;
				oldY = roboSantaOldY;
			}
			
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
			
			// Update the Santas' coordinates.
			if (i%2 == 1) {
				santaOldX = oldX;
				santaOldY = oldY;
			}
			else{
				roboSantaOldX = oldX;
				roboSantaOldY = oldY;
			}
		}
		
		System.out.format("The two Santas visited %d unique houses.", visited.size());
	}

}
