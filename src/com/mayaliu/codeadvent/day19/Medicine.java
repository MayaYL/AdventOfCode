package com.mayaliu.codeadvent.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 19 puzzle, December 2015. See 
 * http://adventofcode.com/day/19
 */
public class Medicine {
	
	/**
	 * @param args
	 *   The string or file path containing the light data.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String input = CommonUtils.processInput(args, "Medicine");
		HashMap<String, ArrayList<String>> substitutions = new HashMap<String, ArrayList<String>>();
		HashSet<String> distinctMolecules = new HashSet<String>();
		
		String[] inputs = input.split("(\n)+");
		// The very last line is the medicine to make.
		String medicine = inputs[inputs.length - 1];
		for (int i = 0; i < inputs.length - 1; i++) {
			String sub = inputs[i];
			String[] parts = sub.split(" => ");
			
			ArrayList<String> val = substitutions.get(parts[0]);
			if (val == null) {
				val = new ArrayList<String>();
			}
			
			val.add(parts[1]);
			substitutions.put(parts[0], val);
		}
		
		// Parse the input and look for the substitutatble elements.
		for (String key : substitutions.keySet()) {
			String medicineCopy = medicine;
			Pattern keyPattern = Pattern.compile(key);
			Matcher keyMatcher = keyPattern.matcher(medicine);
			
			while (keyMatcher.find()) {
//				System.out.println(keyMatcher.group());
//				System.out.println(totalMolecules);
				for (String val : substitutions.get(key)) {
					StringBuilder sb = new StringBuilder();
//					System.out.println("Before key "+key+": "+medicineCopy);
					keyMatcher.start();
					sb.append(medicine.substring(0, keyMatcher.start()));
					sb.append(val);
					sb.append(medicine.substring(keyMatcher.end()));
					System.out.println("After: "+sb.toString());
					distinctMolecules.add(sb.toString());
				}
			}
		}
		
		System.out.format("There are %d total possible molecules that can be created.\n", distinctMolecules.size());
	}
}