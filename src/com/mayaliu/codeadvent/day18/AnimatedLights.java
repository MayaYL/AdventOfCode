package com.mayaliu.codeadvent.day18;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 18 puzzle, December 2015. See 
 * http://adventofcode.com/day/18
 */
public class AnimatedLights {
	public static final int GRID_SIZE = 100;
	public static final int PADDED_GRID_SIZE = GRID_SIZE + 2;
	public static final int ITERATIONS = 100;
	
	/**
	 * @param args
	 *   The string or file path containing the light data.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String initialLightValues = CommonUtils.processInput(args, "AnimatedLights");
		String[] values = initialLightValues.split("\n");
		char[][] grid = new char[PADDED_GRID_SIZE][PADDED_GRID_SIZE];
		
		for (int i = 1; i <= GRID_SIZE; i++) {
			String value = values[i-1];
			char[] singleValues = value.toCharArray();
			for (int j = 1; j <= GRID_SIZE ; j++) {
				char c = singleValues[j-1];
				grid[i][j] = c;
			}
		}
		
		// Set the paddings to 0.
		for (int i = 0; i < PADDED_GRID_SIZE; i++) {
			grid[0][i] = '.';
			grid[PADDED_GRID_SIZE - 1][i] = '.';
			grid[i][0] = '.';
			grid[i][PADDED_GRID_SIZE - 1] = '.';
		}
		
		// Deep copy for the stuck lights, because Java.
		char[][] stuckGrid = new char[PADDED_GRID_SIZE][PADDED_GRID_SIZE];
		for (int i = 0; i < PADDED_GRID_SIZE; i++) {
			for (int j = 0; j < PADDED_GRID_SIZE ; j++) {
				stuckGrid[i][j] = grid[i][j];
			}
		}
		// Set the corners as always on.
		stuckGrid[1][1] = '#';		
		stuckGrid[GRID_SIZE][GRID_SIZE] = '#'; 
		stuckGrid[1][GRID_SIZE] = '#';
		stuckGrid[GRID_SIZE][1] = '#';
		
		for (int i = 0; i < ITERATIONS; i++) {
			grid = run(grid);
			stuckGrid = runWithStuckLights(stuckGrid);
		}
		
		int numOn = numberOfLightsOn(grid);
		int numOnWithStuckLights = numberOfLightsOn(stuckGrid);
		
		System.out.format("After %d turns, there should be %d lights on, ", ITERATIONS, numOn);
		System.out.format("but there are actually %d lights on because the corner lights are always on.\n", numOnWithStuckLights);
	}
	
	private static char[][] run(char[][] grid) {
		// Deep copy, because Java.
		char[][] nextGrid = new char[PADDED_GRID_SIZE][PADDED_GRID_SIZE];
		for (int i = 0; i < PADDED_GRID_SIZE; i++) {
			for (int j = 0; j < PADDED_GRID_SIZE ; j++) {
				nextGrid[i][j] = grid[i][j];
			}
		}
		
		for (int i = 1; i <= GRID_SIZE; i++) {
			for (int j = 1; j <= GRID_SIZE ; j++) {
				int numNeighborsOn = 0;
				if (grid[i - 1][j - 1] == '#') {
					numNeighborsOn++;
				}
				if (grid[i - 1][j + 1] == '#') {
					numNeighborsOn++;
				}
				if (grid[i + 1][j - 1] == '#') {
					numNeighborsOn++;
				}
				if (grid[i + 1][j + 1] == '#') {
					numNeighborsOn++;
				}
				if (grid[i][j - 1] == '#') {
					numNeighborsOn++;
				}
				if (grid[i][j + 1] == '#') {
					numNeighborsOn++;
				}
				if (grid[i - 1][j] == '#') {
					numNeighborsOn++;
				}
				if (grid[i + 1][j] == '#') {
					numNeighborsOn++;
				}
				
				char currentVal = grid[i][j];
				
				// A light which is on stays on when 2 or 3 neighbors are on, 
				// and turns off otherwise.
				if (currentVal == '#') {
					if (numNeighborsOn != 2 && numNeighborsOn != 3) {
						currentVal = '.';
					}
				}
				// A light which is off turns on if exactly 3 neighbors are on, 
				// and stays off otherwise.
				else {
					if (numNeighborsOn == 3) {
						currentVal = '#';
					}
				}
				nextGrid[i][j] = currentVal;
			}
		}
		
		return nextGrid;
	}
	
	private static char[][] runWithStuckLights(char[][] grid) {
		// Deep copy, because Java.
		char[][] nextGrid = new char[PADDED_GRID_SIZE][PADDED_GRID_SIZE];
		for (int i = 0; i < PADDED_GRID_SIZE; i++) {
			for (int j = 0; j < PADDED_GRID_SIZE ; j++) {
				nextGrid[i][j] = grid[i][j];
			}
		}
		
		for (int i = 1; i <= GRID_SIZE; i++) {
			for (int j = 1; j <= GRID_SIZE ; j++) {
				if (!isCorner(i, j)) {
					int numNeighborsOn = 0;
					if (grid[i - 1][j - 1] == '#') {
						numNeighborsOn++;
					}
					if (grid[i - 1][j + 1] == '#') {
						numNeighborsOn++;
					}
					if (grid[i + 1][j - 1] == '#') {
						numNeighborsOn++;
					}
					if (grid[i + 1][j + 1] == '#') {
						numNeighborsOn++;
					}
					if (grid[i][j - 1] == '#') {
						numNeighborsOn++;
					}
					if (grid[i][j + 1] == '#') {
						numNeighborsOn++;
					}
					if (grid[i - 1][j] == '#') {
						numNeighborsOn++;
					}
					if (grid[i + 1][j] == '#') {
						numNeighborsOn++;
					}
					
					char currentVal = grid[i][j];
					
					// A light which is on stays on when 2 or 3 neighbors are on, 
					// and turns off otherwise.
					if (currentVal == '#') {
						if (numNeighborsOn != 2 && numNeighborsOn != 3) {
							currentVal = '.';
						}
					}
					// A light which is off turns on if exactly 3 neighbors are on, 
					// and stays off otherwise.
					else {
						if (numNeighborsOn == 3) {
							currentVal = '#';
						}
					}
					nextGrid[i][j] = currentVal;
				}
				// The corners are always on.
				else {
					nextGrid[i][j] = '#';
				}
				
			}
		}
		
		return nextGrid;
	}
	
	private static int numberOfLightsOn(char[][] grid) {
		int numOn = 0;
		for (int i = 1; i <= GRID_SIZE; i++) {
			for (int j = 1; j <= GRID_SIZE ; j++) {
				if (grid[i][j] == '#') {
					numOn++;
				}
			}
		}
		return numOn;
	}
	
	private static boolean isCorner(int i, int j) {
		if (i == 1 & j == 1) {
			return true;
		}
		if (i == GRID_SIZE && j == GRID_SIZE) {
			return true;
		}
		if (i == 1 & j == GRID_SIZE) {
			return true;
		}
		if (i == GRID_SIZE && j == 1) {
			return true;
		}
		return false;
	}
}