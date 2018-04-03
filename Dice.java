/*
 * A class that represents a dice in a Fortnite Yahtzee game.
 * 
 * CPSC224-02, Spring 2018
 * Final Assignment
 * 
 * @authors Isak Bjornson, Kevin Shaw, & Cole deSilva
 * @version v1.0
 */

import java.util.Random;

public class Dice 
{
	private int roll;
	private int diceSide;
	
	/*
	 * Constructor for Dice object
	 */
	public Dice(int diceSides)
	{
		diceSide = diceSides;
		roll = 0;
	}
	
	/*
	 * @returns the roll that the Dice object stores
	 */
	public int getRoll()
	{
		return roll;
	}
	
	/*
	 * Rolls a die with diceSide amount of sides
	 * 
	 * @returns A random value from 1-diceSide.
	 */
	public void rollDice()
	{
		Random random = new Random();
		roll = random.nextInt(diceSide) + 1;
	}
}
