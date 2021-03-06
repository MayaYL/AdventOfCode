package com.mayaliu.codeadvent.day5;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * Solution for part 2 of adventofcode's Day 5 puzzle, December 2015. See 
 * http://adventofcode.com/day/5
 * 
 * @author MayaYL
 */
public class NaughtyOrNicePart2 {
	
	/**
	 * @param args
	 *   The string or file path containing the list of words.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static void main(String[] args) throws IOException {
		String wordList = CommonUtils.processInput(args, "NaughtyOrNicePart2");
		
		String[] words = wordList.split("\n");
		int numNiceWords = 0;
		
		for (String word : words) {
			word = StringUtils.lowerCase(word);
			
			// Pairs of letters, at least twice.
			Pattern pairRegex = Pattern.compile("([a-z][a-z]).*\\1");
			Matcher pairMatcher = pairRegex.matcher(word);
			if (!pairMatcher.find()) {
				continue;
			}
			
			// xyx pattern.
			Pattern altRegex = Pattern.compile("([a-z])([a-z])\\1");
			Matcher altMatcher = altRegex.matcher(word);
			if (!altMatcher.find()) {
				continue;
			}
			
			numNiceWords++;
		}

		System.out.format("There are %d nice words.", numNiceWords);
	}

}
