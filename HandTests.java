/*
 * An interface which represents the tests needed to be performed on a hand in a Fortnite Yahtzee game.
 * 
 * CPSC224-02, Spring 2018
 * Final Assignment
 * 
 * @authors Isak Bjornson, Kevin Shaw, & Cole deSilva
 * @version v1.0
 */

public interface HandTests 
{	
	/*
	 * @returns The count of the die value occurring most in the hand, but not the value itself.
	 */
	public int maxOfAKindFound();
	
	/*
	 * @returns The length of the longest straight found in a hand.
	 */
	public int maxStraightFound();
	
	/*
	 * Checks if a full house is found. 
	 * 
	 * @returns True if the hand is a full house.
	 * 			False if the hand is not a full house.
	 */
	public boolean fullHouseFound();
}
