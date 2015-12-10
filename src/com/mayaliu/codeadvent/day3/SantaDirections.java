package com.mayaliu.codeadvent.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * Solution for adventofcode's Day 3 puzzle, December 2015. See 
 * http://adventofcode.com/day/3
 * 
 * @author MayaYL
 */
public class SantaDirections {
		
	/**
	 * @param args
	 *   The string or file path containing Santa's directions.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static void main(String[] args) throws IOException {
		String instructions = CommonUtils.processInput(args, "SantaDirections");

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
