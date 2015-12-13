package com.mayaliu.codeadvent.day12;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.*;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 12 puzzle, December 2015. See 
 * http://adventofcode.com/day/12
 */
public class ParseJson {
	
	/**
	 * @param args
	 *   The string or file path containing the json.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String json = CommonUtils.processInput(args, "ParseJson");
		ArrayList<String> allParts = new ArrayList<String>();
		int sum = sumIntegers(json);

		System.out.format("The sum of all integers in the json file is %d.\n", sum);
		
		// Part 2.
		Gson gson = new Gson();
		JsonElement input = gson.fromJson(json, JsonElement.class);
	
		sum = parseJson(input);
		System.out.format("The sum of all integers in the json file (excluding red elements) is %d.\n", sum);
	}
	
	/**
	 * Sums all numbers in a given json excluding objects containing red values.
	 * 
	 * @param input
	 *   The input json.
	 *   
	 * @return
	 *   The value of all number values, excluding red.
	 */
	private static int parseJson(JsonElement input) {
		int sum = 0;
		
		// If input is an array, recursively call on each element.
		if (input.isJsonArray()) {
			JsonArray array = input.getAsJsonArray();
			for (JsonElement element : array) {
				sum += parseJson(element);
			}
		}
		// If input is an object, recursively call on each element.
		// If the value of one element is red, return 0 as the value
		// of this object.
		if (input.isJsonObject()) {
			JsonObject obj = input.getAsJsonObject();
			Set<Entry<String, JsonElement>> entries = obj.entrySet();
			for (Entry<String, JsonElement> entry : entries) {
				JsonElement element = entry.getValue();
				if (element.isJsonPrimitive()) {
					JsonPrimitive primitive = element.getAsJsonPrimitive();
					if (primitive.isString()) {
						if (primitive.getAsString().equals("red")) {
							return 0;
						}
					}
				}
				sum += parseJson(element);
			}
		}
		// If the input is a primitive, parse out its value- return
		// if it's a number, return 0 if it is "red".
		if (input.isJsonPrimitive()) {
			JsonPrimitive obj = input.getAsJsonPrimitive();
			if (obj.isNumber()) {
				return obj.getAsInt();
			}
			if (obj.isString()) {
				if (obj.getAsString().equals("red")) {
					return 0;
				}
			}
		}
		
		return sum;
	}
	
	private static int sumIntegers(String json) {
		Pattern intPattern = Pattern.compile("-?[0-9]+");
		Matcher intMatcher = intPattern.matcher(json);
		int intSum = 0;
		
		while (intMatcher.find()) {
			intSum += Integer.parseInt(intMatcher.group());
		}
		
		return intSum;
	}
	
}