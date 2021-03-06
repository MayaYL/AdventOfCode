package com.mayaliu.codeadvent.day2;

import java.io.IOException;
import java.util.Arrays;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * Solution for adventofcode's Day 2 puzzle, December 2015. See 
 * http://adventofcode.com/day/2
 * 
 * @author MayaYL
 */
public class WrappingPaperCalculator {
	
	/**
	 * @param args
	 *   The string or file path containing package dimensions.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static void main(String[] args) throws IOException {
		String dimensionsList = CommonUtils.processInput(args, "WrappingPaperCalculator");
		
		String[] dimensions = dimensionsList.split("\\s+");
		
		int totalArea = 0;
		int totalRibbonLength = 0;
		
		for (String dimension : dimensions) {
			String[] sidesAsStrings = dimension.split("x|X");
			if (sidesAsStrings.length != 3) {
				System.err.format("Incorrect number of sides given: %s.%n", dimension);
				System.exit(1);
			}
			else {
				int[] sides = new int[3];
				for (int i = 0; i < 3; i++) {
					sides[i] = Integer.parseInt(sidesAsStrings[i]);
				}
				Arrays.sort(sides);
				
				totalArea += getArea(sides[0], sides[1], sides[2]);
				totalRibbonLength += getRibbonLength(sides[0], sides[1], sides[2]);
			}
		}
		
		System.out.format("The elves will need %d square feet of wrapping paper.%n", totalArea);
		System.out.format("The elves will need %d feet of ribbon.%n", totalRibbonLength);
	}

	/**
	 * Calculates the area of wrapping paper needed for the given dimensions.
	 * 
	 * This method assumes that the three dimensions are sorted in increasing 
	 * order and that the length and width are the shortest dimensions, so that
	 * it can add the extra slack area correctly.
	 * 
	 * @param length
	 *   The length of the package.
	 * @param width
	 *   The width of the package.
	 * @param height
	 *   The height of the package.
	 *   
	 * @return
	 *   The area of wrapping paper needed for the package.
	 */
	private static int getArea(int length, int width, int height) {
		int area = 3 * (length * width) + 2 * (length * height) + 2 * (width * height);
		return area;
	}

	/**
	 * Calculates the length of ribbon needed for the given dimensions.
	 * 
	 * This method assumes that the three dimensions are sorted in increasing 
	 * order and that the length and width are the shortest dimensions.
	 * 
	 * @param length
	 *   The length of the package.
	 * @param width
	 *   The width of the package.
	 * @param height
	 *   The height of the package.
	 *   
	 * @return
	 *   The length of ribbon needed for the package.
	 */
	private static int getRibbonLength(int length, int width, int height) {
		int totalLength = 2 * (length + width) + (length * width * height);
		return totalLength;
	}
	
}
