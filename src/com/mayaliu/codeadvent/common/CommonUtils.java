package com.mayaliu.codeadvent.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MayaYL
 * 
 * A utility class handling common tasks such as reading from files. 
 */
public class CommonUtils {
	
	public static final String USAGE_PATTERN = "Usage:%njava Matchsticks [input]/[file location]\n";
	

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
	
	/**
	 * Prints the usage for the given command name- usually, the class name.
	 * 
	 * Usage will the in the format given by CommonUtils.USAGE_PATTERN.
	 * 
	 * @param commandName
	 *   The name of the command to run.
	 */
	public static void printUsage(String commandName) {
		System.out.format(CommonUtils.USAGE_PATTERN, commandName);
		System.exit(1);
	}
	
	/**
	 * Processes the input given the command-line args and the command name.
	 * @param args
	 *   The command-line arguments the calling method received.
	 * @param commandName
	 *   The command name for the calling method.
	 *   
	 * @return
	 *   The processed input string.
	 *   
	 * @throws IOException
	 *   Thrown if a File operation failed in some way.
	 */
	public static String processInput(String[] args, String commandName) throws IOException {
		// Print usage if there are no arguments, or if the "help" option is 
		// specified.
		if (args.length != 1) {
			CommonUtils.printUsage(commandName);
		}
		
		String input = args[0];
		String ret = input;
		
		if (StringUtils.isEmpty(input) || input == "--help" || input == "-h") {
			CommonUtils.printUsage(commandName);
		}
		
		// If input is a file, use its contents as the input value.
		boolean isFile = CommonUtils.isFile(input);
		if (isFile) {
			ret = CommonUtils.getDataFromFile(input);
		}
		
		return ret;
	}
}
