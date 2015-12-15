package com.mayaliu.codeadvent.day15;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 15 puzzle, December 2015. See 
 * http://adventofcode.com/day/15
 */
public class CookieRecipe {
	
	/**
	 * @param args
	 *   The string or file path containing the ingredient information.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String ingredientData = CommonUtils.processInput(args, "CookieRecipe");
		String[] ingredientList = ingredientData.split("\n");
		ArrayList<ArrayList<Integer>> ingredients = new ArrayList<ArrayList<Integer>>();
		int bestScore = 0;
		int bestScoreWith500Calories = 0;
		
		for (String ingredient : ingredientList) {
			Pattern numberPattern = Pattern.compile("-?[0-9]+");
			Matcher m = numberPattern.matcher(ingredient);
			ArrayList<Integer> data = new ArrayList<Integer>();
			// There are 5 numbers associated with each ingredient.
			for (int i = 0; i < 5; i++) {
				m.find();
				data.add(Integer.parseInt(m.group()));
			}
			ingredients.add(data);
		}
		
		ArrayList<ArrayList<Integer>> permutations = getPermutations(100);
		for (ArrayList<Integer> permutation : permutations) {
			int[] scores = new int[] {0, 0, 0, 0, 0};
			
			// For each field (including calories).
			for (int i = 0; i < 5; i++) {
				// For each ingredient.
				for (int j = 0; j < 4; j++) {
					ArrayList<Integer> ingredient = ingredients.get(j);
					int value = permutation.get(j) * ingredient.get(i);
					scores[i] = scores[i] + value;
				}
			}
			if (scores[0] <= 0 || scores[1] <= 0 || scores[2] <= 0 || scores[3] <= 0) {
				continue;
			}
		
			int total = scores[0] * scores[1] * scores[2] * scores[3];
			
			bestScore = total > bestScore ? total : bestScore;
			if(scores[4] == 500) {
				bestScoreWith500Calories = total > bestScoreWith500Calories ? total : bestScoreWith500Calories;
			}
		}
		
		System.out.format("The best possible score is %d.\n", bestScore);
		System.out.format("The best possible score for a 500 calorie cookie is %d.\n", bestScoreWith500Calories);
	}
	
	private static ArrayList<ArrayList<Integer>> getPermutations(int totalSum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= totalSum; i++) {
			for (int j = 0; j <= totalSum - i; j++) {
				for (int k = 0; k <= totalSum - i - j; k++) {
						ArrayList<Integer> tuple = new ArrayList<Integer>();
						tuple.add(new Integer(i));
						tuple.add(new Integer(j));
						tuple.add(new Integer(k));
						tuple.add(new Integer(totalSum - i - j - k));
						result.add(tuple);
				}
			}
		}
		
		return result;
	}
	
}