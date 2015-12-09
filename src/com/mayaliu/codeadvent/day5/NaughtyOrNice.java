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
 * Solution for adventofcode's Day 5 puzzle, December 2015. See 
 * http://adventofcode.com/day/5
 */
public class NaughtyOrNice {
	
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
		String wordList = CommonUtils.processInput(args, "NaughtyOrNice");
		
		String[] words = wordList.split("\n");
		int numNiceWords = 0;
		
		for (String word : words) {
			word = StringUtils.lowerCase(word);
			
			// Three vowels.
			int numVowels = 0;
			Pattern vowelRegex = Pattern.compile("[aeiou]");
			Matcher vowelMatcher = vowelRegex.matcher(word);
			while (vowelMatcher.find() && numVowels < 3) {
				numVowels++;
			}
			
			if (numVowels < 3) {
				continue;
			}
			
			// Double letters.
			Pattern doubleRegex = Pattern.compile("([a-z])\\1");
			Matcher doubleMatcher = doubleRegex.matcher(word);
			if (!doubleMatcher.find()) {
				continue;
			}
			
			// Forbidden substrings.
			if (word.contains("ab") || word.contains("cd") 
					|| word.contains("pq") || word.contains("xy")) {
				continue;
			}
			
			numNiceWords++;
		}

		System.out.format("There are %d nice words.", numNiceWords);
	}

}
