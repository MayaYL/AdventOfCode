package com.mayaliu.codeadvent.day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for part 2 of adventofcode's Day 5 puzzle, December 2015. See 
 * http://adventofcode.com/day/5
 */
public class NaughtyOrNicePart2 {
	
	public static final String USAGE = String.format("Usage:%njava NaughtyOrNice [input]/[file location]");
	
	/**
	 * @param args
	 *   The string or file path containing the list of words.
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
		
		String wordList = input;
		
		// If input is a file, use its contents as the word list string.
		boolean isFile = CommonUtils.isFile(input);
		if (isFile) {
			wordList = CommonUtils.getDataFromFile(input);
		}
		
		String[] words = wordList.split("\n");
		int numNiceWords = 0;
		
		for (String word : words) {
			word = StringUtils.lowerCase(word);
			
			// Pairs of letters, at least twice.
			int numVowels = 0;
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