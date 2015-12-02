package com.mayaliu.codeadvent.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

/**
 * @author MayaYL
 * 
 * A utility class handling common tasks such as reading from files. 
 */
public class CommonUtils {

	/**
	 * Returns whether the given path name points to a valid file.
	 * 
	 * @param path
	 *   The file path to check.
	 *   
	 * @return
	 *   Whether the given path name points to a valid file.
	 */
	public static boolean isFile(String path) {
		boolean isFile = new File(path).isFile();
		return isFile;
	}

	/**
	 * Reads data from a file.
	 * 
	 * Assumes that the caller has already verified that the file path is valid
	 * by calling isFile().
	 * 
	 * @param path
	 *   The file path to read.
	 *   
	 * @return
	 *   The data read from the file.
	 *   
	 * @throws IOException
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static String getDataFromFile(String path) throws IOException {
		String data = "";
		FileInputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(path);
			data = IOUtils.toString(inputStream).trim();
			inputStream.close();
		} catch (IOException e) {
			System.err.format("An error has occurred while processing file %s.%n", path);
			if (inputStream != null) {
			    inputStream.close();
			}
			System.exit(1);
		}
		
		return data;
	}
	
	public static void printUsage(String usage) {
		System.out.println(usage);
		System.exit(1);
	}
}
