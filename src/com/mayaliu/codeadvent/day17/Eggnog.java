package com.mayaliu.codeadvent.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.iterators.PermutationIterator;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 17 puzzle, December 2015. See 
 * http://adventofcode.com/day/17
 */
public class Eggnog {
	public static final int TOTAL_EGGNOG = 150;
	
	/**
	 * @param args
	 *   The string or file path containing the list of Aunt Sues.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String containerData = CommonUtils.processInput(args, "EggNog");
		String[] containerStringArray = containerData.split("\n");
		int numContainers = containerStringArray.length;
		// Keep track of how many ways there are to use numbers of bottles.
		HashMap<Integer, Integer> waysToUseContainers = new HashMap<Integer, Integer>();
		
		ArrayList<Integer> containerList = new ArrayList<Integer>();
		for (String s : containerStringArray) {
			containerList.add(Integer.valueOf(s));
		}
		
		Collections.sort(containerList);
		Collections.reverse(containerList);
		
		// This is a binary list. Each container can be either used (1) or not 
		// used (0). Generate a list of n bits (where n is the number of 
		// containers) and test each combination.
		int numCombinations = 0;
		int[] selectionArray = new int[numContainers];
		
		do {
			int sum = 0;
			for (int i = 0; i < numContainers; i++) {
				sum += selectionArray[i] * containerList.get(i).intValue();
				if (sum > TOTAL_EGGNOG) {
					break;
				}
			}
			if (sum == TOTAL_EGGNOG) {
				int containersUsed = countContainersInUse(selectionArray);
				Integer key = containersUsed;
				Integer numWays = waysToUseContainers.get(key);
				
				if (numWays == null) {
					numWays =  new Integer(0);
				}
				
				waysToUseContainers.put(key, 1 + numWays);
				numCombinations++;
			}
			
			selectionArray = increment(selectionArray, numContainers - 1);
		} while (!isAtMax(selectionArray));
		
		// Sort to find the least number of containers used.
		ArrayList<Integer> keys = new ArrayList<Integer>(waysToUseContainers.keySet());
		Collections.sort(keys);
		Integer lowestNumOfContainers = keys.get(0);
		int numWays = waysToUseContainers.get(keys.get(0));
		
		System.out.format("There are %d valid combinations.\n", numCombinations);
		System.out.format("There are %d valid ways to use %s containers.\n", numWays, lowestNumOfContainers);
		
	}
	
	/**
	 * Increment the array as if it were a binary number.
	 * 
	 * @param selectionArray
	 *   The array to increment.
	 * @param digit
	 *   The digit to increment.
	 *   
	 * @return
	 *   The array after increment.
	 */
	private static int[] increment(int[] selectionArray, int digit) {
		int lastDigit = selectionArray[digit];
		if (lastDigit == 0) {
			selectionArray[digit] = 1;
			return selectionArray;
		}
		
		selectionArray[digit] = 0;
		return increment(selectionArray, --digit);
	}
	
	/**
	 * Returns whether the array consists of all 1s.
	 * 
	 * A binary string of all 1s is at its maximum value.
	 *  
	 * @param selectionArray
	 *   The array to test.
	 *   
	 * @return
	 *   Whether the array consists of all 1s.
	 */
	private static boolean isAtMax(int[] selectionArray) {
		for (int i : selectionArray) {
			if (i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Count the number of 1s in an array, indicating containers in use.
	 * 
	 * @param selectionArray
	 *   The array to test.
	 *   
	 * @return
	 *   The number of containers in use.
	 */
	private static int countContainersInUse(int[] selectionArray) {
		int sum = 0;
		for (int i : selectionArray) {
			sum += i;
		}
		
		return sum;
	}
}