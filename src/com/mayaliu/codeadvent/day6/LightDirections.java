package com.mayaliu.codeadvent.day6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.StatUtils;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 6 puzzle, December 2015. See 
 * http://adventofcode.com/day/6
 */
public class LightDirections {
	
	public static final String USAGE = String.format("Usage:%njava LightDirections [input]/[file location]");
	
	/**
	 * @param args
	 *   The string or file path containing the list of directions.
	 *   
	 * @throws IOException 
	 *   Thrown if a file is provided and there were problems with opening or 
	 *   reading it.
	 */
	public static void main(String[] args) throws IOException {
		String instructionLines = CommonUtils.processInput(args, "LightDirections");
		
		double[][] lights = new double[1000][1000];
		String[] rawInstructions = instructionLines.split("\n");
		
		for (String inst : rawInstructions) {
			Pattern pattern = Pattern.compile("[0-9]{1,3},[0-9]{1,3}");
			Matcher matcher = pattern.matcher(inst);
			ArrayList<Coordinate> toAndFrom = new ArrayList<Coordinate>();
			
			while (matcher.find()) {
				String coordinatePair = matcher.group();
				String[] coordinatePairArray = coordinatePair.split(",");
				Coordinate c = new Coordinate(Integer.parseInt(coordinatePairArray[0]), Integer.parseInt(coordinatePairArray[1]));
				toAndFrom.add(c);
			}
			
			int fromX = toAndFrom.get(0).getX();
			int fromY = toAndFrom.get(0).getY();
			int toX = toAndFrom.get(1).getX();
			int toY = toAndFrom.get(1).getY();
			
			for (int i = fromX; i <= toX; i++) {
				for (int j = fromY; j <= toY; j++) {
					int currentVal = (int) lights[i][j];
					int newVal = 0;
							
					if (inst.contains("toggle")) {
						if (currentVal == 0) {
							newVal = 1;
						}
						else {
							newVal = 0;
						}
					}
					else if (inst.contains("turn on")) {
						newVal = 1;
					}
					else if (inst.contains("turn off")) {
						newVal = 0;
					}
					
					lights[i][j] = (double) newVal;
				}
			}
				
		}

		int totalLights = 0;
		
		for (int i = 0; i < 1000; i++) {
			totalLights += StatUtils.sum(lights[i]);
		}
		
		System.out.format("There are %d lights on.", totalLights);
	}

	private static class Coordinate {
		private int x;
		private int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return this.x;
		}
		
		public int getY() {
			return this.y;
		}
	}
}
