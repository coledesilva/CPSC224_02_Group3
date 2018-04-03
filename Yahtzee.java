/*
 * This program runs a full game of Fortnite Yahtzee.
 * 
 * CPSC224-02, Spring 2018
 * Final Assignment 
 * 
 * @authors Isak Bjornson, Kevin Shaw, & Cole deSilva
 * @version v1.0
 */

import java.util.*;

public class Yahtzee
{
	private static final int diceSide = 9;
	private static final int diceNum = 5;
	private static final int rollsPerHand = 4;
	
	public static void main(String[] args) 
	{
		runGame();
	}
	
	/*
	 * Runs a game of Yahtzee until the user says they don't want to play again.
	 */
	private static void runGame()
	{
		String playAgain = "y";
		Scanner input = new Scanner(System.in);
		
		while(playAgain.equals("y"))
		{
			//Sets up turns for the game.
			int gameTurn = 1;
			
			ScoreCard gameCard = new ScoreCard();
			 
			System.out.println("You are playing with 5 dice.");
			System.out.println("Each dice has 9 sides.");
			System.out.println("You have 4 rolls per hand.");
			
			while(gameTurn <= diceSide + 7)
			{
				System.out.println("Turn " + gameTurn + ":");
				//Creates hand for the game .
				Hand yHand = new Hand();
				
				//Creates strings to keep track of which dice are kept or not.
				StringBuilder keepDieBuilder = new StringBuilder();
				for(int i = 0; i < diceNum; i++)
				{
					keepDieBuilder.append("n");
				}
				String keepDie = keepDieBuilder.toString();
				
				StringBuilder testIfAllYesBuilder = new StringBuilder();
				for(int i = 0; i < diceNum; i++)
				{
					testIfAllYesBuilder.append("y");
				}
				
				String testIfAllYes = testIfAllYesBuilder.toString();
				
				int currentRolls = 1;
				
				//Run as many rolls per hand or rolls until user wants to keep all dice values.
				while(currentRolls <= rollsPerHand && !keepDie.equals(testIfAllYes))
				{
					for(int dieNumber = 0; dieNumber < diceNum; dieNumber++)
					{
						if(keepDie.charAt(dieNumber) != 'y')
						{
							Dice newDice = new Dice();
							if(currentRolls == 1)
							{
								try 
								{
									newDice.rollDice();
									yHand.add(newDice);
								} 
								catch (OutOfHandException e) 
								{
									System.out.println("An exception was thrown...Here is what I know: ");
									e.printStackTrace();
								}
							}
							else
							{
								newDice.rollDice();
								yHand.set(dieNumber, newDice);
							}
						}
					}
					
					System.out.print("Your roll was: ");
					yHand.print();
					
					if(currentRolls < rollsPerHand)
					{
						System.out.print("Enter dice to keep (y or n) ");
						keepDie = input.nextLine();
					}
					currentRolls++;
				}
				
				//Prints sorted hand.
				yHand.sort();
				System.out.println();
				System.out.print("Your sorted hand: ");
				yHand.print();
				System.out.println();
				
				//Creating a temporary score card to hold the scores from this turn.
				ScoreCard tmpCard = new ScoreCard();
				
				//Sets scores into temporary score card.
				tmpCard.upperScores(yHand, tmpCard);
				tmpCard.lowerScores(yHand, tmpCard);
				tmpCard.calculateGrand();
								
				//Prints out scores available to put into their game score card.
				System.out.println("Your scores this turn are...");
				tmpCard.print();
				System.out.println();
				
				//User chooses which score to add to their card.
				System.out.println("Which line from the printed scores above do you want to add to your score card?");
				int lineNum;
				
				boolean isEmpty = true;
				while(isEmpty)
				{
					lineNum = Integer.parseInt(input.nextLine());
					if(gameCard.get(lineNum - 1) == 0)
					{
						gameCard.set(lineNum - 1, tmpCard.get(lineNum - 1));
						isEmpty = false;
					}
					else
					{
						System.out.println("You already have a score in that line. Please choose another line:");
					}
				}
				
				System.out.println();
				
				//Increments turn so that the game continues.
				gameTurn++;
			}
			
			//Prints end-game text and final card.
			System.out.println("You have completed a game of Fortnite Yahtzee!");
			System.out.println("Your final score card is:");
			
			//Sets totals for the game card.
			gameCard.setUpper(gameCard.calculateUpper());
			gameCard.setLower(gameCard.calculateLower());
			gameCard.calculateGrand();
			
			//Prints out complete game card with totals.
			gameCard.printComplete();
			System.out.println();
			
			//Asks user if they want to play again.
			System.out.print("Would you like to play again? (y or n) ");
			playAgain = input.nextLine();
			System.out.println();
			System.out.println();
		}
		input.close();
		
		System.out.println("Thank you for playing!");
	}
}

