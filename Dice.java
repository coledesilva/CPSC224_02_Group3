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
	private Weapon type;
	private int diceSide = 9;
	
	public enum Weapon
	{
		pistol, 
		crossbow, 
		minigun, 
		submachinegun, 
		grenadeL, 
		shotgun, 
		sniper,
		rocketL, 
		assaultrifle
	}
	
	/*
	 * Constructor for Dice object
	 */
	public Dice()
	{
		roll = 0;
		type = null;
	}
	
	/*
	 * @returns the roll that the Dice object stores
	 */
	public int getRoll()
	{
		return roll;
	}
	
	public Weapon getWeapon()
	{
		return type;
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
		switch(roll)
		{
			case 1:
				type = Weapon.pistol;
				break;
			case 2:
				type = Weapon.crossbow;
				break;
			case 3:
				type = Weapon.minigun;
				break;
			case 4:
				type = Weapon.submachinegun;
				break;
			case 5:
				type = Weapon.grenadeL;
				break;
			case 6:
				type = Weapon.shotgun;
				break;
			case 7:
				type = Weapon.sniper;
				break;
			case 8:
				type = Weapon.rocketL;
				break;
			case 9:
				type = Weapon.assaultrifle;
				break;
				
		}
	}
}
