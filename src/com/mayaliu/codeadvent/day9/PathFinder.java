package com.mayaliu.codeadvent.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 9 puzzle, December 2015. See 
 * http://adventofcode.com/day/9
 */
public class PathFinder {
	
	/**
	 * @param args
	 *   The string or file path containing the list of paths.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String pathList = CommonUtils.processInput(args, "PathFinder");
		
		String[] paths = pathList.split("\n");
		HashMap<String, Location> locations = new HashMap<String, Location>();
		
		// Parse out and store the path data.
		for (String path : paths) {
			Location from, to;
			
			String[] pathParts = path.split(" [(to)=]*( )*");
			String fromName = pathParts[0];
			String toName = pathParts[1];
			int distance = Integer.parseInt(pathParts[2]);
			
			from = locations.containsKey(fromName) ? locations.get(fromName) : new Location(fromName);
			to = locations.containsKey(toName) ? locations.get(toName) : new Location(toName);
			from.addDestination(to, distance);
			to.addDestination(from, distance);
			
			locations.put(fromName, from);
			locations.put(toName, to);
		}
		
		int minDistance = Integer.MAX_VALUE;
		
		for (Location loc : locations.values()) {
			System.out.println("starting from "+loc.getName());
			Location currentLocation = loc;
			
			int newDistance = visit(currentLocation, new HashMap<String, Location>(), 0);
			System.out.println("***new distance: "+newDistance);
			minDistance = minDistance < newDistance ? minDistance : newDistance;
			System.out.println("***min distance: "+minDistance);
		}
		
		System.out.format("The Shortest distance Santa can travel to visit all locations is %d.\n", minDistance);
	}
	
	// end case: all cities have been visited
	// step: visit closest city, mark it as visited, increment total distance
	// move to next step: take closest city not visited, call w/ new total distance
	private static int visit(Location current, HashMap<String, Location> visited, int distance) {
		if (current == null) {
			return distance;
		}
		
		else {
//			System.out.println("visiting " + current.getName());
			visited.put(current.getName(), current);
			PriorityQueue<Location> nextDestinations = current.getDestinations();
			Location next;
			
			do {
				next = nextDestinations.poll();
			} while (next != null && visited.containsKey(next.getName()));
			
			if (next == null) {
//				System.out.println("last visited: " + current.getName());
				return distance;
			}
			

//			System.out.println("visiting next: " + next.getName());
//			System.out.println("distance to next: "+next.getDistanceFromSource());
//			System.out.println("total distance: "+ (distance + next.getDistanceFromSource()));
			
			return visit(next, visited, distance + next.getDistanceFromSource());
		}
	}
	
}
