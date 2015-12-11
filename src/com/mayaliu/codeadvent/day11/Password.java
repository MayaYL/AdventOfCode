package com.mayaliu.codeadvent.day11;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple class to store data about passwords.
 * 
 * @author maya.liu
 */
public class Password {
	private String password;
	private static final int REQUIRED_LENGTH = 8;
	
	public Password(String s) {
		this.password = s;
		this.isValid();
	}
	
	/**
	 * Checks the validity of the given password.
	 * 
	 * Fastest validity checks are performed first, so the conditions are not
	 * checked in order.
	 *   
	 * @return
	 *   Whether the password is valid.
	 */
	public boolean isValid() {
		String password = this.password;
		
		// Base condition: passwords must be REQUIRED_LENGTH characters long.
		if (password.length() != REQUIRED_LENGTH) {
			return false;
		}
		
		// Condition 2: passwords may not contain the letters i, o, or l.
		if (password.contains("i") || password.contains("o") || password.contains("l")) {
			return false;
		}
		
		// Condition 1: passwords must include one increasing straight of at 
		// least three letters.
		boolean containsIncreasingSequence = false;
		for (int i = 0; i < REQUIRED_LENGTH - 3; i++) {
			if (password.charAt(i) == password.charAt(i + 1) - 1) {
				if (password.charAt(i + 1) == password.charAt(i + 2) - 1) {
					containsIncreasingSequence = true;
					break;
				}
				// We know that the 2nd and 3rd chars do not fit the criteria so 
				// there is no need to check them again.
				i += 2;
			}
		}
		if (!containsIncreasingSequence) {
			return false;
		}
		
		// Condition 3: Passwords must contain at least two different, 
		// non-overlapping pairs of letters.
		Pattern doublePattern = Pattern.compile("([a-z])\\1");
		Matcher doubleMatcher = doublePattern.matcher(password);
		HashSet<String> uniqueDoubles = new HashSet<String>();
		while (doubleMatcher.find() && uniqueDoubles.size() < 2) {
			uniqueDoubles.add(doubleMatcher.group());
		}
		if (uniqueDoubles.size() < 2) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Increment the password by one.
	 */
	public void increment() {
		char[] passwordArray = this.password.toCharArray();
		this.increment(passwordArray, this.REQUIRED_LENGTH - 1);
	}
	
	/**
	 * Recursively attempts to increment the password digits.
	 * 
	 * @param passwordArray
	 *   The char array of the current password.
	 * @param digit
	 *   The digit to try and increment.
	 */
	private void increment(char[] passwordArray, int digit) {
		char lastChar = passwordArray[digit];
		if (lastChar != 'z') {
			passwordArray[digit] = ++lastChar;
			this.password = new String(passwordArray);
			return;
		}
		
		passwordArray[digit] = 'a';
		increment(passwordArray, --digit);
	}
	
	/**
	 * Returns the password string.
	 */
	public String getPassword() {
		return this.password;
	}
}
