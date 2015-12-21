package com.mayaliu.codeadvent.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 16 puzzle, December 2015. See 
 * http://adventofcode.com/day/16
 */
public class SueFinder {
	
	/**
	 * @param args
	 *   The string or file path containing the list of Aunt Sues.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String sueData = CommonUtils.processInput(args, "SueFinder");
		String[] sueList = sueData.split("\n");
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		expectedValues.put("children", 3);
		expectedValues.put("cats", 7);
		expectedValues.put("samoyeds", 2);
		expectedValues.put("pomeranians", 3);
		expectedValues.put("akitas", 0);
		expectedValues.put("vizslas", 0);
		expectedValues.put("goldfish", 5);
		expectedValues.put("trees", 3);
		expectedValues.put("cars", 2);
		expectedValues.put("perfumes", 1);
		
		for (String sue : sueList) {
			// Find the ID. The very first number in the line will be the ID.
			Pattern idPattern = Pattern.compile("[0-9]+");
			Matcher idMatcher = idPattern.matcher(sue);
			idMatcher.find();
			int sueNumber = Integer.parseInt(idMatcher.group());
			
			// Find each of the possible properties.
			// Pattern: "goldfish: 9," which is (string): (int)
			Pattern propertyPattern = Pattern.compile("[a-z]+: [0-9]+");
			Matcher propertyMatcher = propertyPattern.matcher(sue);
			boolean matched = false;
			while (propertyMatcher.find()) {
				String data = propertyMatcher.group();
				String[] dataParts = data.split(": ");
				String propertyName = dataParts[0];
				int propertyValue = Integer.parseInt(dataParts[1]);
				int expectedValue = Integer.valueOf(expectedValues.get(propertyName));
				
				// Check for the ranged values.
				if (propertyName.equals("cats") || propertyName.equals("trees")) {
					if (propertyValue <= expectedValue) {
						matched = false;
						break;
					}
				}
				else if (propertyName.equals("pomeranians") || propertyName.equals("goldfish")) {
					if (propertyValue >= expectedValue) {
						matched = false;
						break;
					}
				}
				// Not a ranged value.
				else {
					if (expectedValue != propertyValue) {
						matched = false;
						break;
					}
					else {
						matched = true;
					}
				}
			}
			
			if (matched) {
				System.out.format("Sue number %d sent the package.\n", sueNumber);
				break;
			}
		}
	}
}