package com.mayaliu.codeadvent.day21;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.math3.util.Combinations;

/**
 * @author MayaYL
 * 
 * Solution for adventofcode's Day 21 puzzle, December 2015. See 
 * http://adventofcode.com/day/21
 */
public class BossFight {
	
	public static final int BOSS_HITPOINT = 100;
	public static final int PLAYER_HITPOINT = 100;
	public static final int BOSS_DAMAGE = 8;
	public static final int BOSS_ARMOR = 2;
	
	private static ArrayList<Item> weapons = new ArrayList<Item>();
	private static ArrayList<Item> armors = new ArrayList<Item>();
	private static ArrayList<Item> rings = new ArrayList<Item>();
	
	/**
	 * @param args
	 *   The string or file path containing the light data.
	 *   
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		// No inputs required! Don't need CommonUtils to handle anything.
		
		// Weapons.
		weapons.add(new Item("Dagger", 8, 4, 0));
		weapons.add(new Item("Shortsword", 10, 5, 0));
		weapons.add(new Item("Warhammer", 25, 6, 0));
		weapons.add(new Item("Longsword", 40, 7, 0));
		weapons.add(new Item("Greataxe", 74, 8, 0));
		
		// Armor.
		armors.add(new Item("No armor", 0, 0, 0));
		armors.add(new Item("Leather", 13, 0, 1));
		armors.add(new Item("Chainmail", 31, 0, 2));
		armors.add(new Item("Splintmail", 53, 0, 3));
		armors.add(new Item("Bandedmail", 75, 0, 4));
		armors.add(new Item("Platemail", 102, 0, 5));
		
		// Rings.
		rings.add(new Item("No left ring", 0, 0, 0));
		rings.add(new Item("No right ring", 0, 0, 0));
		rings.add(new Item("Damage +1", 25, 1, 0));
		rings.add(new Item("Damage +2", 50, 2, 0));
		rings.add(new Item("Damage +3", 100, 3, 0));
		rings.add(new Item("Defense +1", 20, 0, 1));
		rings.add(new Item("Defense +2", 40, 0, 2));
		rings.add(new Item("Defense +3", 80, 0, 3));
		
		// The combos are: 1 weapon, 0/1 armor, 0/1/2 rings, no replacement.
		
		int lowestCost = Integer.MAX_VALUE;
		int highestCost = 0;
		
		for (int i = 0; i < weapons.size(); i++) {
			Item weapon = weapons.get(i);
			for (int j = 0; j < armors.size(); j++) {
				Item armor = armors.get(j);
				Iterator<int[]> iterator = new Combinations(rings.size(), 2).iterator();
				while (iterator.hasNext()) {
					int[] chosenRings = iterator.next();
					Item ring1 = rings.get(chosenRings[0]);
					Item ring2 = rings.get(chosenRings[1]);
					Item combinedRings = new Item(
							ring1.getName() + " and " + ring2.getName(),
							ring1.getCost() + ring2.getCost(),
							ring1.getDamage() + ring2.getDamage(),
							ring1.getArmor() + ring2.getArmor()
						);
					int totalCost = weapon.getCost() + armor.getCost() + combinedRings.getCost();

					if (beatsBoss(weapon, armor, combinedRings)) {
						lowestCost = lowestCost < totalCost? lowestCost : totalCost;
					}
					else {
						highestCost = highestCost > totalCost? highestCost : totalCost;
					}
				}
			}
		}
		
		System.out.format("The lowest cost to beat the boss is %d.\n", lowestCost);
		System.out.format("The highest cost to still lose to the boss is %d.\n", highestCost);
	}
	
	private static boolean beatsBoss(Item weapon, Item armor, Item combinedRings) {
		int leastHitpoint = BOSS_HITPOINT < PLAYER_HITPOINT ? BOSS_HITPOINT : PLAYER_HITPOINT;

		int damageDealtByPlayer = weapon.getDamage() + combinedRings.getDamage() - BOSS_ARMOR;
		int damageDealtByBoss = BOSS_DAMAGE - armor.getArmor() - combinedRings.getArmor();
		// The attacker always does at least one damage.
		damageDealtByPlayer = damageDealtByPlayer <= 0 ? 1 : damageDealtByPlayer;
		damageDealtByBoss = damageDealtByBoss <= 0 ? 1 : damageDealtByBoss;
		int mostDamageDealt = damageDealtByPlayer > damageDealtByBoss? damageDealtByPlayer: damageDealtByBoss;

		int numTurns = leastHitpoint/mostDamageDealt;
		
		if (damageDealtByPlayer <= 0) {
			return false;
		}
		
		if (numTurns * damageDealtByBoss > PLAYER_HITPOINT) {
			return false;
		}
		
		if ((numTurns + 1) * damageDealtByPlayer >= BOSS_HITPOINT) {
			return true;
		}
		
		return false;		
	}
	
	private static class Item {
		private String name;
		private int cost, damage, armor;
		
		public Item(String name, int cost, int damage, int armor) {
			this.name = name;
			this.cost = cost;
			this.damage = damage;
			this.armor = armor;
		}

		public String getName() {
			return name;
		}

		public int getCost() {
			return cost;
		}

		public int getDamage() {
			return damage;
		}

		public int getArmor() {
			return armor;
		}
	}
}