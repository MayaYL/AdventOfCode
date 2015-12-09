package com.mayaliu.codeadvent.day4;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 4 puzzle, December 2015. See 
 * http://adventofcode.com/day/4
 */
public class AdventCoinMine {

	public static final String USAGE = String.format("Usage:%njava AdventCoinMine [input]/[file location]");
	
	/**
	 * @param args
	 *   The string or file path containing Santa's directions.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 *   
	 * @throws NoSuchAlgorithmException 
	 *   Thrown if there is an error with the MD5 hash call.
	 */
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String root = CommonUtils.processInput(args, "AdventCoinMine");
    	
		// TODO: actually make the nubmer of zeros configurable.
        int answer = findInteger(6, root);
        System.out.format("The number to append for %d zeros is %d", 6, answer);
	}
	
	private static int findInteger(int length, String root) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        boolean found = false;
        int integer = 0;
        
        while (!found) {
        	String newRoot = root + Integer.toString(integer);
        	md.update(newRoot.getBytes());
            
            byte byteData[] = md.digest();
     
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
            	String digit = Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1);
            	sb.append(digit);
            	if (!digit.contains("0")) {
            		break;
            	}
	             if (sb.length() >= length) {
	            	 if (sb.subSequence(0, length).toString().equals("000000")) {
	            		 found = true;
	            		 return integer;
	            	 }
	            	 break;
	             }
            }
            
            integer++;
        }
        
        return -1;
	}
}