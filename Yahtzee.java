/*
 * This program runs a full game of Yahtzee.
 * 
 * CPSC224-02, Spring 2018
 * Programming Assignment #6
 * 
 * @author Cole deSilva
 * @version v3.0 3/22/2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Yahtzee
{
	private static int diceSide;
	private static int diceNum;
	private static int rollsPerHand;
	
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
			
			//Gets config from config file or edits config file per user request.
			try 
			{
				getConfig(input);
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("An exception was thrown...Here is what I know: ");
				e.printStackTrace();
			}
			
			ScoreCard gameCard = new ScoreCard(diceSide);
			
			while(gameTurn <= diceSide + 7)
			{
				System.out.println("Turn " + gameTurn + ":");
				//Creates hand for the game .
				Hand yHand = new Hand(diceNum);
				
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
							Dice newDice = new Dice(diceSide);
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
				ScoreCard tmpCard = new ScoreCard(diceSide);
				
				//Sets scores into temporary score card.
				upperScores(yHand, tmpCard);
				lowerScores(yHand, tmpCard);
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
			System.out.println("You have completed a game of Yahtzee!");
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
	
	/*
	 * Reads the config file for the game and updates it if the user specifies they want to change it.
	 * Throws FileNotFoundException if the file is not found. 
	 * 
	 * @input Scanner for user input.
	 */
	private static void getConfig(Scanner input) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File("yahtzeeConfig.txt"));
		diceSide = in.nextInt();
		diceNum = in.nextInt();
		rollsPerHand = in.nextInt();
		in.close();
		
		System.out.println("You are playing with " + diceNum + " " + diceSide + "-sided die.");
		System.out.println("You get " + rollsPerHand + " rolls per hand.");
		
		System.out.println();

		System.out.print("Press 'y' if you would like to change the configuration: ");
		
		String ans = input.nextLine();
		
		if(ans.equals("y"))
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream("yahtzeeConfig.txt", false));
			System.out.print("Enter the number of sides on each die: ");
			diceSide = Integer.parseInt(input.nextLine());
			pw.println(diceSide);
			
			System.out.print("Enter the number of dice in play: ");
			diceNum = Integer.parseInt(input.nextLine());
			pw.println(diceNum);
			
			System.out.print("Enter the number of rolls per hand ");
			rollsPerHand = Integer.parseInt(input.nextLine());
			pw.println(rollsPerHand);
			pw.close();
		}
		
		System.out.println();
	}
	
	/*
	 * Sets values for the upper score card for the game of Yahtzee.
	 * 
	 * @theHand The hand the player has in the game.
	 */
	private static void upperScores(Hand theHand, ScoreCard tmpCard)
	{
		int total = 0;
		int i = 0;
		for (int dieValue = 1; dieValue <= diceSide; dieValue++, i++)
        {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < diceNum; diePosition++)
            {
                if (theHand.get(diePosition).getRoll() == dieValue)
                {
                    currentCount++;
                }
            }
            
            	tmpCard.set(i, (dieValue * currentCount));
            	total += (dieValue * currentCount);
        }
		
		if(total >= 63)
		{
			tmpCard.setUpper(total + 35);
		}
		else
		{
			tmpCard.setUpper(total);
		}
	}
	
	/*
	 * Sets values for the lower score card for the game of Yahtzee.
	 * 
	 * @theHand The hand the player has in the game.
	 */
	private static void lowerScores(Hand theHand, ScoreCard tmpCard)
	{
		int total = 0;
		if(theHand.maxOfAKindFound() >= 3)
		{
			tmpCard.set(diceSide, theHand.totalAllDice());
			total += theHand.totalAllDice();
		}
		
		if(theHand.maxOfAKindFound() >= 4)
		{
			tmpCard.set(diceSide + 1, theHand.totalAllDice());
			total += theHand.totalAllDice();
		}
		
		if(theHand.fullHouseFound())
		{
			tmpCard.set(diceSide + 2, 25);
			total += 25;
		}
		
		if(theHand.maxStraightFound() >= 4)
		{
			tmpCard.set(diceSide + 3, 30);
			total += 30;
		}
		
		if(theHand.maxStraightFound() >= 5)
		{
			tmpCard.set(diceSide + 4, 40);
			total += 40;
		}
		
		if(theHand.maxOfAKindFound() >= 5)
		{
			tmpCard.set(diceSide + 5, 50);
			total += 50;
		}
		
		tmpCard.set(diceSide + 6, theHand.totalAllDice());
		total += theHand.totalAllDice();
		tmpCard.setLower(total);
	}
}

