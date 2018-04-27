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
	protected static void runGame()
	{
		String playAgain = "y";
		Scanner input = new Scanner(System.in);
		
		while(playAgain.equals("y"))
		{
			Player[] player = getNumPlayers(input);
			printBS(2);
			
			//Sets up turns for the game.
			int gameTurn = 1;
			 
			System.out.println("You are playing with 5 dice.");
			System.out.println("Each dice has 9 sides.");
			System.out.println("You have 4 rolls per hand.");
			printBS(2);
			
			while(gameTurn <= diceSide + 8)
			{
				for(int i = 0; i < player.length; i++)
				{
					System.out.println("Player " + (i + 1) + ", Turn " + gameTurn + ":");
					//Creates hand for the game .
					Hand yHand = new Hand();
					
					//Creates strings to keep track of which dice are kept or not.
					StringBuilder keepDieBuilder = new StringBuilder();
					for(int j = 0; j < diceNum; j++)
					{
						keepDieBuilder.append("n");
					}
					String keepDie = keepDieBuilder.toString();
					
					StringBuilder testIfAllYesBuilder = new StringBuilder();
					for(int j = 0; j < diceNum; j++)
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
							while(true)
							{
								System.out.print("Enter dice to keep (y or n) ");
								keepDie = input.nextLine();
								if(keepDie.length() != 5)
								{
									System.out.println("Incorrect Input: Please input five dice to keep/remove");
								}
								else
								{
									break;
								}
							}
						}
						currentRolls++;
					}
					
					//Prints sorted hand.
					yHand.sort();
					printBS(1);
					System.out.print("Your sorted hand: ");
					yHand.print();
					printBS(1);
					
					//Creating a temporary score card to hold the scores from this turn.
					ScoreCard tmpCard = new ScoreCard();
					
					//Sets scores into temporary score card.
					tmpCard.upperScores(yHand, tmpCard);
					tmpCard.lowerScores(yHand, tmpCard);
					tmpCard.calculateGrand();
									
					//Prints out scores available to put into their game score card.
					System.out.println("Your scores this turn are...");
					tmpCard.print();
					printBS(1);
					
					//User chooses which score to add to their card.
					System.out.println("Which line from the printed scores above do you want to add to your score card?");
					int lineNum;
					
					boolean isEmpty = true;
					while(isEmpty)
					{
						lineNum = Integer.parseInt(input.nextLine());
						if(player[i].getScoreCard().get(lineNum - 1) == 0)
						{
							player[i].getScoreCard().set(lineNum - 1, tmpCard.get(lineNum - 1));
							isEmpty = false;
						}
						else
						{
							System.out.println("You already have a score in that line. Please choose another line:");
						}
					}
					System.out.println();
				}
				//Increments turn so that the game continues.
				gameTurn++;
			}
			
			//Prints end-game text and final card.
			System.out.println("You have completed a game of Fortnite Yahtzee!");
			
			printBS(1);
			
			for(int i = 0; i < player.length; i++)
			{
				//Sets totals for the game card.
				player[i].getScoreCard().setUpper(player[i].getScoreCard().calculateUpper());
				player[i].getScoreCard().setLower(player[i].getScoreCard().calculateLower());
				player[i].getScoreCard().calculateGrand();
				
				//Prints out complete game card with totals.
				System.out.println("Score Card for player " + (i + 1) +": ");
				player[i].getScoreCard().printComplete();
				printBS(1);
			}
			
			printBS(1);
			//Asks user if they want to play again.
			System.out.print("Would you like to play again? (y or n) ");
			playAgain = input.nextLine();
			printBS(2);
		}
		input.close();
		
		System.out.println("Thank you for playing!");
	}
	
	/*
	 * Gets the number of players for the game.
	 * 
	 * @input The scanner for user input in the game.
	 * 
	 * @returns Am array of type Player with the specified number of players.
	 */
	private static Player[] getNumPlayers(Scanner input)
	{
		System.out.println("How many players would like to play? (Up to 5 players)");
		int numPlayers;
		while(true)
		{
			numPlayers = Integer.parseInt(input.nextLine());
			if(numPlayers > 5)
			{
				System.out.println("ERROR: Invalid number of players. Please try again.");
			}
			else
			{
				break;
			}
		}
		
		Player[] players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++)
		{
			players[i] = new Player();
		}
		
		return players;
	}
	
	/* 
	 * Prints blank lines.
	 * 
	 * @numLines The number of blank lines wished to be printed.
	 */
	public static void printBS(int numLines)
	{
		for(int i = 0; i < numLines; i++)
		{
			System.out.println();
		}
	}
}



