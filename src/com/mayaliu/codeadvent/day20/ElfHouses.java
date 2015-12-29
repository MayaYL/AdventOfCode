package com.mayaliu.codeadvent.day20;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 20 puzzle, December 2015. See 
 * http://adventofcode.com/day/20
 */
public class ElfHouses {
	
	public static final int NUMBER_OF_PRESENTS = 36000000;
	
	public static void main(String[] args) {
		// No inputs required! Don't need CommonUtils to handle anything.
			
		for (int i = 1; ; i++) {
			int totalPresents = 0;
			for (int j = 1; j <= (int) Math.sqrt(i); j++) {
				// The jth elf should stop here...
				if (i%j == 0) {
					// ... if s/he's stopped 50 or less times already.
					if (i/j <= 50) {
						totalPresents += j * 11;
					}
					else {
						// otherwise, if the elf with the factor greather than
						// the squareroot of the house number has made less than
						// 50 stops, s/he stops here.
						if ((double) j != Math.sqrt(i) && j <= 50) {
							totalPresents += i/j * 11;
						}
					}
				}
				
				if (totalPresents >= NUMBER_OF_PRESENTS) {
					System.out.format("House number %d is the first to receive more than %d presents.\n", i, NUMBER_OF_PRESENTS);
					break;
				}
			}
			
			if (totalPresents >= NUMBER_OF_PRESENTS) {
				break;
			}
		}
	
	}
}