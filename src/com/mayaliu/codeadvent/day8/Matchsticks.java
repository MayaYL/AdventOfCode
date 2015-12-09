package com.mayaliu.codeadvent.day8;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 8 puzzle, December 2015. See 
 * http://adventofcode.com/day/8
 */
public class Matchsticks {
	
	public static final String USAGE = String.format("Usage:%njava Matchsticks [input]/[file location]");
	
	/**
	 * @param args
	 *   The string or file path containing the list of instructions.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String stringList = CommonUtils.processInput(args, "Matchsticks");
		
		String[] strings = stringList.split("\n");
		int originalLength = 0;
		int decodedLength = 0;
		int encodedLength = 0;
		
		for (String s : strings) {
			originalLength += s.length();
			
			String encoded = StringEscapeUtils.escapeJava(s);
			// Add 2 for the enclosing quotes to add.
			encodedLength += encoded.length() + 2;
			
			// Replace hex with unicode representations, because Java. 
			// (StringEscapeUtils.unescapeJava does not recognize \x notation.)		
			Pattern p = Pattern.compile("\\\\x[0-9a-fA-F]{2}");
			Matcher m = p.matcher(s);
			while (m.find()) {
				String hex = m.group();
				String replacement = hex.replaceAll("\\\\x", "\\\\u00");
				s = s.replace(hex, replacement);
			}
			
			// Take out the enclosing quotes before unescaping.
			String escaped = StringEscapeUtils.unescapeJava(s.substring(1, s.length()-1));
			decodedLength += escaped.length();
		}
		
		System.out.format("The difference in length between the original and the decoded strings is %d.\n", originalLength - decodedLength);
		System.out.format("The difference in length between the original and the encoded strings is %d.\n", encodedLength - originalLength);
	}
	
}
