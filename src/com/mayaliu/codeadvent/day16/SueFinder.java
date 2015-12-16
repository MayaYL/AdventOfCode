package com.mayaliu.codeadvent.day16;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 16 puzzle, December 2015. See 
 * http://adventofcode.com/day/16
 */
public class SueFinder {
	
	/**
	 * @param args
	 *   The string or file path containing the list of Aunt Sues.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String sueData = CommonUtils.processInput(args, "SueFinder");
		String[] sueList = sueData.split("\n");
		
		for (String sue : sueList) {
			// Find the ID. The very first number in the line will be the ID.
			Pattern p = Pattern.compile("[0-9]+");
			Matcher m = p.matcher(sue);
			m.find();
			Sue s = new Sue(Integer.parseInt(m.group()));
			
			// Find each of the possible properties.
			// Pattern: goldfish: 9, (string): (int)
			
		}
	}

	/**
	 * A Sue object containing various information about the specific Sue.
	 * 
	 * @author MayaYL
	 */
	private static class Sue {
		private int id, children, cats, samoyeds, pomeranians, akitas, vizslas;
		private int goldfish, trees, cars, perfumes;
		private HashMap<String, Integer> properties;
		
		public Sue(int id) {
			this.id = id;
			this.properties = new HashMap<String, Integer>();
		}
		
		public void add(String property, int number) {
			this.properties.put(property, new Integer(number));
		}
		
		public HashMap<String, Integer> getProperties() {
			return this.properties;
		}
//
//		public int getId() {
//			return id;
//		}
//
//		public void setId(int id) {
//			this.id = id;
//		}
//
//		public int getChildren() {
//			return children;
//		}
//
//		public void setChildren(int children) {
//			this.children = children;
//		}
//
//		public int getCats() {
//			return cats;
//		}
//
//		public void setCats(int cats) {
//			this.cats = cats;
//		}
//
//		public int getSamoyeds() {
//			return samoyeds;
//		}
//
//		public void setSamoyeds(int samoyeds) {
//			this.samoyeds = samoyeds;
//		}
//
//		public int getPomeranians() {
//			return pomeranians;
//		}
//
//		public void setPomeranians(int pomeranians) {
//			this.pomeranians = pomeranians;
//		}
//
//		public int getAkitas() {
//			return akitas;
//		}
//
//		public void setAkitas(int akitas) {
//			this.akitas = akitas;
//		}
//
//		public int getVizslas() {
//			return vizslas;
//		}
//
//		public void setVizslas(int vizslas) {
//			this.vizslas = vizslas;
//		}
//
//		public int getGoldfish() {
//			return goldfish;
//		}
//
//		public void setGoldfish(int goldfish) {
//			this.goldfish = goldfish;
//		}
//
//		public int getTrees() {
//			return trees;
//		}
//
//		public void setTrees(int trees) {
//			this.trees = trees;
//		}
//
//		public int getCars() {
//			return cars;
//		}
//
//		public void setCars(int cars) {
//			this.cars = cars;
//		}
//
//		public int getPerfumes() {
//			return perfumes;
//		}
//
//		public void setPerfumes(int perfumes) {
//			this.perfumes = perfumes;
//		}
		
//		int id, int children, int
//		cats, int samoyeds, int pomeranians, int akitas, int vizslas,
//		int goldfish, int
//		trees, int
//		cars, int perfumes
	}
}