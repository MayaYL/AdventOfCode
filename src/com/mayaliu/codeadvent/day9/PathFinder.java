package com.mayaliu.codeadvent.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.mayaliu.codeadvent.common.CommonUtils;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 9 puzzle, December 2015. See 
 * http://adventofcode.com/day/9
 */
public class PathFinder {
	public static HashMap<String, PriorityQueue<Destination>> destinations;
	
	/**
	 * @param args
	 *   The string or file path containing the list of paths.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		String pathList = CommonUtils.processInput(args, "PathFinder");
		// Indicates whether we are finding the longest or shortest path.
		boolean shortest = true;
		
		String[] paths = pathList.split("\n");
		destinations = new HashMap<String, PriorityQueue<Destination>>();
		
		// Parse out and store the path data.
		for (String path : paths) {
			Destination from;
			Destination to;
			
			String[] pathParts = path.split(" [(to)=]*( )*");
			String fromName = pathParts[0];
			String toName = pathParts[1];
			int distance = Integer.parseInt(pathParts[2]);
			
			PriorityQueue<Destination> fromsDestinations = destinations.get(fromName);
			if (fromsDestinations == null) {
				fromsDestinations = new PriorityQueue<Destination>();
			}
			PriorityQueue<Destination> tosDestinations = destinations.get(toName);
			if (tosDestinations == null) {
				tosDestinations = new PriorityQueue<Destination>();
			}
			
			from = new Destination(fromName, shortest);
			to = new Destination(toName, shortest);

			from.setDistance(distance);
			to.setDistance(distance);
			fromsDestinations.add(to);
			tosDestinations.add(from);
			
			destinations.put(fromName, fromsDestinations);
			destinations.put(toName, tosDestinations);
		}
		
		int currentBestRoute = shortest? Integer.MAX_VALUE : Integer.MIN_VALUE;
		
		for (String currentLocation : destinations.keySet()) {			
			int newDistance = visit(currentLocation, new ArrayList<String>(), 0);
			if (shortest) {
				currentBestRoute = currentBestRoute < newDistance ? currentBestRoute : newDistance;
			}
			else {
				currentBestRoute = currentBestRoute > newDistance ? currentBestRoute : newDistance;
			}
		}
		
		System.out.format("The %s distance Santa can travel to visit all locations is %d.\n", shortest ? "shortest" : "longest", currentBestRoute);
	}
	
	// end case: all cities have been visited
	// step: visit next optimal city, mark it as visited, increment total distance
	// move to next step: take first city not visited, call w/ new total distance
	private static int visit(String current, ArrayList<String> visited, int distance) {
		if (current == null) {
			return distance;
		}
		
		else {
			visited.add(current);
			PriorityQueue<Destination> nextDestinations = new PriorityQueue<Destination>();
			nextDestinations.addAll(destinations.get(current));
			Destination next;
			
			do {
				next = nextDestinations.poll();
			} while (next != null && visited.contains(next.getName()));
			
			if (next == null) {
				return distance;
			}

			return visit(next.getName(), visited, distance + next.getDistance());
		}
	}
	
}
