package com.mayaliu.codeadvent.day13;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.collections4.iterators.PermutationIterator;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 13 puzzle, December 2015. See 
 * http://adventofcode.com/day/13
 */
public class SeatingArrangement {
	private static HashMap<String, HashMap<String, Integer>> guests = new HashMap<String, HashMap<String, Integer>>();
	
	/**
	 * @param args
	 *   The string or file path containing the list of guests.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String guestList = CommonUtils.processInput(args, "SeatingArrangement");
			
		String[] guestInformation = guestList.split("\\.(\n)*");
		for (String info : guestInformation) {
			String[] infoParts = info.split(" ");
			String name = infoParts[0];
			int multiplier = infoParts[2].equals("gain") ? 1 : -1;
			int happiness = multiplier * Integer.parseInt(infoParts[3]);
			String neighborName = infoParts[infoParts.length - 1];
			
			HashMap<String, Integer> neighbors = guests.get(name);
			if (neighbors == null) {
				neighbors = new HashMap<String, Integer>();
			}
			
			neighbors.put(neighborName, happiness);
			guests.put(name, neighbors);
		}
		
		// Part 2: add myself.
		for (HashMap<String, Integer> neighbor : guests.values()) {
			neighbor.put("Maya", 0);
		}
		HashMap<String, Integer> neighbors = new HashMap<String, Integer>();
		for (String guest : guests.keySet()) {
			neighbors.put(guest, 0);
		}
		guests.put("Maya", neighbors);
		
		int maxHappiness = run();
		
		System.out.format("The max net happiness is %d.", maxHappiness);
	}
	
	/**
	 * Find the maximum happiness.
	 * 
	 * Brute-force: iterate through all permutations and add together and update
	 * the current highest happiness.
	 * 
	 * @return
	 *   The maximum happiness level.
	 */
	private static int run() {
		PermutationIterator<String> it = new PermutationIterator<String>(guests.keySet());
		int maxHappiness = Integer.MIN_VALUE;
		
		while (it.hasNext()) {
			int totalHappiness = 0;
			ArrayList<String> sequence = (ArrayList<String>) it.next();
			
			// Handle the first guest separately - it has no previous neighbor (
			// in a linear sense).
			String firstGuest = sequence.get(0);
			totalHappiness += guests.get(firstGuest).get(sequence.get(1));
			
			for (int i = 1; i < sequence.size() - 1; i++) {
				String guest = sequence.get(i);
				totalHappiness += guests.get(guest).get(sequence.get(i + 1));
				totalHappiness += guests.get(guest).get(sequence.get(i - 1));
			}
			
			// Handle the last guest separately - it has no next neighbor (in a
			// linear sense).
			String lastGuest = sequence.get(sequence.size() - 1);
			totalHappiness += guests.get(lastGuest).get(sequence.get(sequence.size() - 2));
			totalHappiness += guests.get(lastGuest).get(firstGuest);
			totalHappiness += guests.get(firstGuest).get(lastGuest);
			
			maxHappiness = totalHappiness > maxHappiness ? totalHappiness : maxHappiness;
		}
		
		return maxHappiness;
	}
	
}