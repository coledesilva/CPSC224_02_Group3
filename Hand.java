/*
 * A class that represents a hand of dice in a game of Fortnite Yahtzee.
 * Implemented using ArrayList.
 * 
 * CPSC224-02, Spring 2018
 * Final Assignment 
 * 
 * @authors Isak Bjornson, Kevin Shaw, & Cole deSilva
 * @version v1.0
 */

import java.util.*;

public class Hand implements HandTests
{
	private ArrayList<Dice> rollList;
	private static final int diceNum = 5;
	
	/*
	 * Constructor creates an Integer ArrayList with the initial capacity of 6 elements.
	 */
	public Hand()
	{
		rollList = new ArrayList<Dice>();
	}
	
	/*
	 * Adds a value to the ArrayList.
	 * Throws exception of OutOfHandException if tries to add when it holds 6 elements already.
	 * 
	 *  @value The value which is to be added to the ArrayList.
	 */
	public void add(Dice newDice) throws OutOfHandException
	{
		if(rollList.size() < diceNum)
		{
			rollList.add(newDice);
		}
		else
		{
			throw new OutOfHandException("Error: Too many dice rolled.");
		}
	}
	
	/*
	 * Sets a value at a certain index in the hand.
	 * 
	 * @index The index of the value to be set.
	 * @value The value to be set at the specified index.
	 */
	public void set(int index, Dice newDice)
	{
		rollList.set(index, newDice);
	}
	
	/*
	 * Gets a die at a certain index in the hand.
	 * 
	 * @index The index of the die to be returned.
	 * 
	 * @returns The die at the specified index in the hand.
	 */
	public Dice get(int index)
	{
		return rollList.get(index);
	}
	
	/*
	 * Prints out the hand.
	 */
	public void print()
	{
		for(int i = 0; i < rollList.size(); i++)
		{
			System.out.print(rollList.get(i).getWeapon() + " ");
		}
		System.out.println();
	}
	
	/*
	 * Sorts the hand from smallest to largest via insertion sort (because of small data set).
	 * 
	 * Found at: https://www.geeksforgeeks.org/insertion-sort/
	 */
	public void sort()
	{
		for(int i = 0; i < diceNum; i++)
		{
			Dice key = rollList.get(i);
			
			int j = i - 1; 
			while(j >= 0 && rollList.get(j).getRoll() > key.getRoll())
			{
				rollList.set(j + 1, rollList.get(j));
				j--;
			}
			rollList.set(j + 1, key);
		}
	}
	
	/*
	 * Totals all of the elements in the hand.
	 * 
	 * @returns The total of all of the elements in the hand.
	 */
	public int totalAllDice()
	{
		int total = 0;
		
		for(int i = 0; i < rollList.size(); i++)
		{
			total += rollList.get(i).getRoll();
		}
		
		return total;
	}
	
	/*
	 * @returns The count of the die value occurring most in the hand, but not the value itself.
	 */
	public int maxOfAKindFound()
	{
		int maxCount = 0;
	    int currentCount ;
	    for (int dieValue = 1; dieValue <= diceNum + 1; dieValue++)
	    {
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < diceNum; diePosition++)
	        {
	            if (rollList.get(diePosition).getRoll() == dieValue)
	                currentCount++;
	        }
	        if (currentCount > maxCount)
	            maxCount = currentCount;
	    }
	    return maxCount;
	}
	
	/*
	 * @returns The length of the longest straight found in a hand.
	 */
	public int maxStraightFound()
	{
		int maxLength = 1;
	    int curLength = 1;
	    for(int counter = 0; counter < diceNum - 1; counter++)
	    {
	        if (rollList.get(counter).getRoll() + 1 == rollList.get(counter + 1).getRoll())//jump of 1
	        {
	            curLength++;
	        }
	        else if (rollList.get(counter).getRoll() + 1 < rollList.get(counter + 1).getRoll()) //jump of >= 2
	        {
	            curLength = 1;
	        }
	        if (curLength > maxLength)
	        {
	            maxLength = curLength;
	        }
	    }
	    return maxLength;
	}
	
	/*
	 * Checks if a full house is found. 
	 * 
	 * @returns True if the hand is a full house.
	 * 			False if the hand is not a full house.
	 */
	public boolean fullHouseFound()
	{
	    boolean found2K = false;
	    boolean found3K = false;
	
	    int currentCount ;
	    for (int dieValue = 1; dieValue <= diceNum + 1; dieValue++)
	    {
	        currentCount = 0;
	        
	        for (int diePosition = 0; diePosition < diceNum; diePosition++)
	        {
	            if (rollList.get(diePosition).getRoll() == dieValue)
	            {
	                currentCount++;
	            }
	        }
	        if (currentCount == 2)
	        {
	            found2K = true;
	        }
	        if (currentCount == 3)
	        {
	            found3K = true;
	        }
	    }
	    
	    if (found2K && found3K)
	    {
	        return true;
	    }
	    
	    return false;
	}
	
	public int returnCount(int type)
	{
		int count = 0;
		for(int i = 0; i < diceNum; i++)
		{
			if(type == rollList.get(i).getRoll())
			{
				count++;
			}
		}
		
		return count;
	}
	
	public boolean versatileVictor() 
	{
		int assaultCount = returnCount(9);
		int shotgunCount = returnCount(6);
		int sniperCount = returnCount(7);
		int rocketLCount = returnCount(8);
		int grenadeLCount = returnCount(5);
		
		if(assaultCount == 2 && shotgunCount == 1 && sniperCount == 1 && (rocketLCount == 1 || grenadeLCount == 1))
		{
			return true;
		}
		
		return false;
	}

	public boolean havingABlast() 
	{
		int rocketLCount = returnCount(8);
		int grenadeLCount = returnCount(5);
		
		if(rocketLCount == 2 && grenadeLCount == 2)
		{
			return true;
		}
		
		return false;
	}

	public boolean masterfulMarksman() 
	{
		int sniperCount = returnCount(7);
		int crossbowCount = returnCount(2);
		int assaultCount = returnCount(9);
		
		if(sniperCount == 2 && crossbowCount == 2 && assaultCount == 1)
		{
			return true;
		}
		
		return false;
	}

	public boolean runninAndGunnin() 
	{
		int subCount = returnCount(4);
		int shotgunCount = returnCount(6);
		
		if(subCount == 2 && shotgunCount == 2)
		{
			return true;
		}
		
		return false;
	}
}
