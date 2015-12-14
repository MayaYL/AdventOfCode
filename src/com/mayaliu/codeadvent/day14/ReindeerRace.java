package com.mayaliu.codeadvent.day14;

import java.util.ArrayList;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 14 puzzle, December 2015. See 
 * http://adventofcode.com/day/14
 */
public class ReindeerRace {

	/**
	 * @param args
	 *   The string or file path containing the list of reindeer information.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String reindeerInfo = CommonUtils.processInput(args, "ReindeerRace");
		ArrayList<Reindeer> reindeerList = new ArrayList<Reindeer>();
		int totalTime = 2503;
		
		String[] reindeerInfoLines = reindeerInfo.split("\n");
		
		for (String reindeerInfoLine : reindeerInfoLines) {
			String[] infoParts = reindeerInfoLine.split(" ");
			String name = infoParts[0];
			int speed = Integer.parseInt(infoParts[3]);
			int duration = Integer.parseInt(infoParts[6]);
			int rest = Integer.parseInt(infoParts[13]);
			
			Reindeer reindeer = new Reindeer(name, speed, duration, rest);
			reindeerList.add(reindeer);
		}
		
		// Part 1.
		int maxDistance = 0;
		for (Reindeer deer : reindeerList) {
			int cycles = totalTime/(deer.getRest() + deer.getDuration());
			int leftOver =  totalTime%(deer.getRest() + deer.getDuration());
			
			int extraSecondsToFly = leftOver < deer.getDuration() ? leftOver : deer.getDuration();
			deer.fly(cycles * deer.getDuration() + extraSecondsToFly);
			
			maxDistance = deer.getDistance() > maxDistance ? deer.getDistance() : maxDistance;
		}
		
		System.out.format("At the end of %d seconds, the winning reindeer has travelled %d km.\n", totalTime, maxDistance);

		// Part 2.
		for (Reindeer deer : reindeerList) {
			deer.setDistance(0);
		}
		
		for (int i = 0; i < totalTime; i++) {
			maxDistance = 0;
			for (Reindeer deer : reindeerList) {
				int leftOver =  i%(deer.getRest() + deer.getDuration());
				int extraSecondsToFly = leftOver < deer.getDuration() ? 1 : 0;
				deer.fly(extraSecondsToFly);
				
				maxDistance = deer.getDistance() > maxDistance ? deer.getDistance() : maxDistance;
			}
			
			// TODO: Sorting would be a good idea, actually.
			for (Reindeer deer : reindeerList) {
				if (deer.getDistance() == maxDistance) {
					deer.setScore(deer.getScore() + 1);
				}
			}
		}
		
		int maxScore = 0;
		// TODO: sort.
		for (Reindeer deer : reindeerList) {
			maxScore = deer.getScore() > maxScore ? deer.getScore() : maxScore;
		}
		
		System.out.format("At the end of %d seconds, the winning reindeer has %d points.\n", totalTime, maxScore);

	}
	
	private static class Reindeer implements Comparable<Reindeer> {
		String name;
		int speed;
		int duration;
		int rest;
		int distance;
		int score;
		
		public Reindeer(String name, int speed, int duration, int rest) {
			this.name = name;
			this.speed = speed;
			this.duration = duration;
			this.rest = rest;
			this.distance = 0;
			this.score = 0;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}
		
		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public int getRest() {
			return rest;
		}

		public void setRest(int rest) {
			this.rest = rest;
		}
		
		public void fly(int seconds) {
			this.distance += seconds * speed;
		}
		
		public int getDistance() {
			return distance;
		}
		
		public void setDistance(int distance) {
			this.distance = distance;
		}
		
		public int getScore() {
			return this.score;
		}
		
		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public int compareTo(Reindeer o) {
			if (this.getDistance() > o.getDistance()) {
				return 1;
			}
			return -1;
		}
	}
}

