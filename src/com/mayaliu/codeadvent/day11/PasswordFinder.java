package com.mayaliu.codeadvent.day11;

import java.io.IOException;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * Solution for adventofcode's Day 11 puzzle, December 2015. See 
 * http://adventofcode.com/day/11
 * 
 * @author MayaYL
 */
public class PasswordFinder {
	
	/**
	 * @param args
	 *   The string or file path containing the original password.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static void main(String[] args) throws IOException {
		Password password = new Password(CommonUtils.processInput(args, "PasswordFinder"));
		// Increment the password first in case it is currently valid.
		password.increment();
		
		while (!password.isValid()) {
			password.increment();
		}
		
		System.out.format("The next valid password is %s\n", password.getPassword());
	}
	
}
	