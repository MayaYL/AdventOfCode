package com.mayaliu.codeadvent.day10;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 10 puzzle, December 2015. See 
 * http://adventofcode.com/day/10
 */
public class LookSay {

	/**
	 * @param args
	 *   The string or file path containing the initial string.
	 *   
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String initial = CommonUtils.processInput(args, "LookSay");
		// The number of iterations to play.
		int iterations = 50;
		
		for (int j = 0; j < iterations; j++) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < initial.length();) {
				char current = initial.charAt(i++);
				int repeat = 1;
				while (i < initial.length() && initial.charAt(i) == current) {
					repeat++;
					i++;
				}
				
				sb.append(repeat);
				sb.append(current);
			}
			initial = sb.toString();
		}
		
		System.out.println(initial.length());
	}
	
}