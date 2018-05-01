import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class GUI extends Yahtzee
{
	private static final int diceSide = 9;
	private static final int diceNum = 5;
	private static final int rollsPerHand = 4;
	
	private static int currentRolls = 1;
	private static int gameTurn = 1;
	private static int playerNum;
	private static Hand yHand;
	private boolean keepDie;
	
	private ImageIcon emptySlot = new ImageIcon("emptySlot.jpg");
	private ImageIcon pistol = new ImageIcon("pistol.png");
	private ImageIcon crossbow = new ImageIcon("crossbow.png");
	private ImageIcon minigun = new ImageIcon("minigun.png");
	private ImageIcon submachinegun = new ImageIcon("submachinegun.png");
	private ImageIcon grenadelauncher = new ImageIcon("grenadelauncher.png");
	private ImageIcon shotgun = new ImageIcon("shotgun.png");
	private ImageIcon sniper = new ImageIcon("sniper.png");
	private ImageIcon rocketlauncher = new ImageIcon("rocketlauncher.png");
	private ImageIcon assaultrifle = new ImageIcon("assaultrifle.png");
	private JFrame frmFortniteYahtzee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmFortniteYahtzee.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	class MyPanel extends JPanel
	{

	    ImageIcon imageIcon;

	    public MyPanel(ImageIcon icon)
	    {
	        super();

	        this.imageIcon = icon;
	    }

	    public void setIcon(ImageIcon icon)
	    {
	    		this.imageIcon = icon;
	    }
	    
	    @Override
	    public void paintComponent(Graphics g)

	    {
	        super.paintComponent(g);

	        g.drawImage(imageIcon.getImage(),0,0,getWidth(),getHeight(),this);
	    }
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frmFortniteYahtzee = new JFrame();
		frmFortniteYahtzee.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		frmFortniteYahtzee.setTitle("Fortnite Yahtzee");
		frmFortniteYahtzee.setSize(screenSize.width, screenSize.height);
		frmFortniteYahtzee.setResizable(false);
		frmFortniteYahtzee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainMenu(screenSize);
		
	}
	
	private void mainMenu(Dimension screenSize)
	{
		ImageIcon imageIcon = new ImageIcon("mainMenu.jpg");
		MyPanel mainPanel = new MyPanel(imageIcon);
		mainPanel.setLayout(null);

        frmFortniteYahtzee.getContentPane().add(mainPanel);
        
        mainPanel.repaint();
        
        JButton playButton = new JButton();
        playButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        playButton.setText("PLAY");
        playButton.setBounds(315, 535, 320, 125);
        mainPanel.add(playButton);
        
        JButton instrucButton = new JButton();
        instrucButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        instrucButton.setText("INSTRUCTIONS");
        instrucButton.setBounds(315, 670, 320, 125);
        mainPanel.add(instrucButton);
        
        JLabel labelNumPlayers = new JLabel();
        labelNumPlayers.setBounds(645, 540, 200, 75);
        labelNumPlayers.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        labelNumPlayers.setForeground(Color.WHITE);
        labelNumPlayers.setText("Number of Players");
        mainPanel.add(labelNumPlayers);
        
        JTextField numberOfPlayers = new JTextField();
        numberOfPlayers.setEditable(true);
        numberOfPlayers.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        numberOfPlayers.setBounds(645, 600, 50, 50);
        mainPanel.add(numberOfPlayers);
        
       playButton.addActionListener(new ActionListener()
       {
    	   		public void actionPerformed(ActionEvent e) 
    	   		{
    	   			int numPlayers = Integer.parseInt(numberOfPlayers.getText());
    	   			mainPanel.setVisible(false);
				runGame(numPlayers, mainPanel);
			}
       });
       
       instrucButton.addActionListener(new ActionListener()
       {
    	   		public void actionPerformed(ActionEvent e) 
    	   		{
    	   			ImageIcon instruc1 = new ImageIcon("instruction1.jpg");
    	   			MyPanel instrucPanel = new MyPanel(instruc1);
    	   			
    	   			instrucPanel.setLayout(null);

    	   			frmFortniteYahtzee.getContentPane().add(instrucPanel);
    	   			
    	   	        instrucPanel.repaint();
    	   	     mainPanel.setVisible(false);
    	   	        instrucPanel.setVisible(true);
    	   	        
    	   	        JTextArea instructions1 = new JTextArea();
    	   	        instructions1.setEditable(false);
    	   	        instructions1.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    	   	        instructions1.setText("- Dice:\n"
    	   	        		+ "\t- There are five nine-sided dice.\n"
    	   	        		+ "\t- Each side of the dice represents a certain weapon.\n"
    	   	        		+ "- How to Win:\n"
    	   	        		+ "\t- Roll dice up to 17 rounds.\n"
    	   	        		+ "\t- The best loadout after up to five rerolls is what you add to your score card.\n");
    	   	        instructions1.setBounds(50, 300, 1020, 210);
    	   	        instrucPanel.add(instructions1);
    	   	        instructions1.setBackground(Color.DARK_GRAY);
    	   	        instructions1.setForeground(Color.WHITE);
    	   	        
	    	   	    
	    	   	    JButton MainMenu = new JButton();
	    	   	    MainMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
	    	   	    MainMenu.setText("Main Menu");
	    	   	    MainMenu.setBounds(1150, 645, 250, 100);
	    	        instrucPanel.add(MainMenu);
	    	        
	    	        JButton next1 = new JButton();
	    	        next1.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
	    	        next1.setText("Next");
	            next1.setBounds(890, 645, 250, 100);
	   	        instrucPanel.add(next1);
	   	        
	   	        MainMenu.addActionListener(new ActionListener()
		        {
		    	   		public void actionPerformed(ActionEvent e) 
		    	   		{
		    	   			instrucPanel.setVisible(false);
		    	   			mainPanel.setVisible(true);
	     	   		}
		        });
		   	    
	   	        next1.addActionListener(new ActionListener()
	   	        {
	   	        		public void actionPerformed(ActionEvent e) 
	   	        		{
	   	        			ImageIcon instruc2 = new ImageIcon("instruction2.jpg");
	   	        			instrucPanel.setIcon(instruc2);
	   	        			instrucPanel.repaint();
	   	        			
	   	        			instructions1.setVisible(false);
	   	        			next1.setEnabled(false);
	   	        			next1.setVisible(false);
	   	        			
	   	        			JButton next2 = new JButton();
	   	        			next2.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
	   	        			next2.setText("Next");
	   	        			next2.setBounds(890, 645, 250, 100);
	   		   	        instrucPanel.add(next2);
	   		   	        
			   		   	JTextArea instructions2 = new JTextArea();
			   		   	instructions2.setEditable(false);
			   		 	instructions2.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
			   			instructions2.setText("Upper Section: Total score is based on point values\n"
			   					+ "from the associated dice sides.\n"
			   					+ "\t Pistol - 1 point\n"
			   					+ "\t Crossbow - 2 points\n"
			   					+ "\t Minigun - 3 points\n"
			   					+ "\t Sub-machine Gun - 4 points\n"
			   					+ "\t Grenade Launcher - 5 points\n"
			   					+ "\t Shotgun - 6 points\n"
			   					+ "\t Sniper - 7 points\n"
			   					+ "\t Rocket Launcher - 8 points\n"
			   					+ "\t Assault Rifle - 9 points\n"
			   					+ "Bonus: If your points in the upper section add up to 110 or more,\n"
			   					+ "add an additional 40 points.\n");
			   			instructions2.setBounds(50, 150, 800, 450);
		  	   	        instructions2.setBackground(Color.DARK_GRAY);
		  	   	        instructions2.setForeground(Color.WHITE);
		  	   	        instrucPanel.add(instructions2);
		  	   	        
			  	   	    next2.addActionListener(new ActionListener()
			  	   	    {
				    	   		public void actionPerformed(ActionEvent e) 
				    	   		{
				    	   			ImageIcon instruc3 = new ImageIcon("instruction3.jpg");
			   	        			instrucPanel.setIcon(instruc3);
			   	        			instrucPanel.repaint();
			   	        			
			   	        			instructions2.setVisible(false);
			   	        			next2.setEnabled(false);
			   	        			next2.setVisible(false);
			   		   	        
					   		   	JTextArea instructions3 = new JTextArea();
					   		   	instructions3.setEditable(false);
					   		 	instructions3.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
					   			instructions3.setText("Lower Section:\n"
					   					+ ""
					   					+ "\t- 3 of a Kind: 3 of the same weapon (10 points)\n"
					   					+ "\t- 4 of a Kind: 4 of the same weapon (20 points)\n"
					   					+ "\t- Full House: 3 and 2 of any combo (25 points)\n"
					   					+ "\t- Versatile Victor: 2 assault rifles, 1 shotgun,\n"
					   					+ "\t        and 1 rocket launcher/grenade launcher (30 points)\n"
					   					+ "\t- Having a Blast: 2 rocket launchers, 2 grenade\n"
					   					+ "\t        launchers, and any other weapon (35 points)\n"
					   					+ "\t- Masterful Marksman: 2 snipers, 2 crossbows,\n"
					   					+ "\t        and one assault rifle (40 points)\n"
					   					+ "\t- Runnin' and Gunnin': 2 sub-machine guns,\n"
					   					+ "\t        2 shotguns, and any other weapon (40 weapons)\n"
					   					+ "\t- Yahtzee: All of the same weapon excluding pisotls\n"
					   					+ "\t        and crossbows (50 points)\n");
					   			instructions3.setBounds(50, 150, 800, 500 );
				  	   	        instructions3.setBackground(Color.DARK_GRAY);
				  	   	        instructions3.setForeground(Color.WHITE);
				  	   	        instrucPanel.add(instructions3);
				    	   		}
			  	   	    	});
	   	        		}
	   	        });
	   	        
    	   		}
       });
	}
	
	private void runGame(int numPlayers, MyPanel mainPanel)
	{
		playerNum = 1;
		MyPanel[] slots = new MyPanel[5];
		for(int i = 0, x = 500; i < slots.length; i++)
		{
			slots[i] = new MyPanel(emptySlot);
			slots[i].setBounds(x, 100, 100, 100);
			slots[i].setBorder(BorderFactory.createLineBorder(Color.black));
			frmFortniteYahtzee.getContentPane().add(slots[i]); 
			x += 100;
		}
		
		Player[] players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++)
		{
			players[i] = new Player();
		}
		
		JPanel game1 = new JPanel();
		game1.setBackground(new Color(211, 211, 211));
		game1.setLayout(null);
		
        frmFortniteYahtzee.getContentPane().add(game1);
        
        JTextArea game1Text = new JTextArea();
        game1Text.setEditable(false);
        game1Text.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        game1Text.setText("Click the roll button to roll the dice");
        game1Text.setBounds(50,50, 405, 40);
        game1Text.setBorder(BorderFactory.createLineBorder(Color.black));
        game1Text.setBackground(new Color(32, 178, 170));
        game1Text.setForeground(Color.BLACK);
        game1.add(game1Text);
        
        JTextArea game1Score = new JTextArea();
        game1Score.setEditable(false);
        game1Score.setBounds(500, 250, 500, 400);
        game1Score.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        game1Score.setForeground(Color.BLACK);
        game1Score.setBackground(new Color(155, 145, 181));
       	game1.add(game1Score);
        
        JButton rollButton = new JButton();
        rollButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        rollButton.setText("Roll");
        rollButton.setBounds(50, 100, 100, 75);
        game1.add(rollButton);
        
        JButton calculateButton = new JButton();
        calculateButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        calculateButton.setText("Calculate\n Score");
        calculateButton.setBounds(160, 100, 300, 75);
        calculateButton.setVisible(false);
        game1.add(calculateButton);
        
        JButton inputScore = new JButton();
        inputScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        inputScore.setText("Input Score Bitch");
        inputScore.setBounds(110, 240, 200, 75);
        inputScore.setVisible(true);
        game1.add(inputScore);
        
        JLabel playerCount = new JLabel();
        playerCount.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        playerCount.setBounds(500, 50, 400, 50);
        playerCount.setText("Turn " + gameTurn +": Player " + playerNum);
        game1.add(playerCount);
        
        JLabel scoreLabel = new JLabel();
		scoreLabel.setText("Please input the score line you want to keep:");
        scoreLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setBounds(50, 200, 400, 50);
		scoreLabel.setVisible(true);
		game1.add(scoreLabel);
		
		JTextField scoreLine = new JTextField();
		scoreLine.setEditable(true);
		scoreLine.setBounds(50, 240, 50,50);
		scoreLine.setVisible(true);
		game1.add(scoreLine);
		
		JLabel scoreError = new JLabel();
		scoreError.setText("Error: You already have a score in that line");
		scoreError.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		scoreError.setForeground(Color.BLACK);
		scoreError.setBounds(50, 250, 200, 50);
		scoreError.setVisible(true);
		game1.add(scoreLabel);
		
		yHand = new Hand();
        
        boolean[] allDice = new boolean[5];
        for(int i = 0; i < allDice.length; i++)
        {
        		allDice[i] = false;
        }
        
       JCheckBox check1 = new JCheckBox();
       check1.setBounds(535, 200, 50, 50);
       game1.add(check1);
       check1.addItemListener(new ItemListener()
       {
    	   		public void itemStateChanged(ItemEvent e)
    	   		{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					allDice[0] = true;
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED)
				{
					allDice[0] = false;
				}
			}
       });
       JCheckBox check2 = new JCheckBox();
       check2.setBounds(635, 200, 50, 50);
       game1.add(check2);
       check2.addItemListener(new ItemListener()
       {
    	   		public void itemStateChanged(ItemEvent e)
    	   		{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					allDice[1] = true;
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED)
				{
					allDice[1] = false;
				}
			}
       });
       JCheckBox check3 = new JCheckBox();
       check3.setBounds(735, 200, 50, 50);
       game1.add(check3);
       check3.addItemListener(new ItemListener()
       {
    	   		public void itemStateChanged(ItemEvent e)
    	   		{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					allDice[2] = true;
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED)
				{
					allDice[2] = false;
				}
			}
       });
       JCheckBox check4 = new JCheckBox();
       check4.setBounds(835, 200, 50, 50);
       game1.add(check4);
       check4.addItemListener(new ItemListener()
       {
    	   		public void itemStateChanged(ItemEvent e)
    	   		{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					allDice[3] = true;
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED)
				{
					allDice[3] = false;
				}
			}
       });
       JCheckBox check5 = new JCheckBox();
       check5.setBounds(935, 200, 50, 50);
       game1.add(check5);
       check5.addItemListener(new ItemListener()
       {
    	   		public void itemStateChanged(ItemEvent e)
    	   		{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					allDice[4] = true;
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED)
				{
					allDice[4] = false;
				}
			}
       });

        
        keepDie = checkAllDice(allDice);
		
		rollButton.addActionListener(new ActionListener()
        	{
				public void actionPerformed(ActionEvent e) 
				{
					if(currentRolls <= rollsPerHand && numPlayers <= 5)
					{
						System.out.println(currentRolls);
						for(int dieNumber = 0; dieNumber < diceNum; dieNumber++)
						{
							if(allDice[dieNumber] != true)
							{
								Dice newDice = new Dice();
								if(currentRolls == 1)
								{
									try 
									{
										newDice.rollDice();
										yHand.add(newDice);
									} 
									catch (OutOfHandException e1) 
									{
										System.out.println("An exception was thrown...Here is what I know: ");
										e1.printStackTrace();
									}
								}
								else
								{
									newDice.rollDice();
									yHand.set(dieNumber, newDice);
								}
							}
						}
						if(currentRolls == rollsPerHand) 
						{
							calculateButton.setVisible(true);
						}
						
						printHand(yHand, slots);
						currentRolls++;
					}
				}
        	});
		
		calculateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ScoreCard tmpCard = new ScoreCard();
        			tmpCard.upperScores(yHand, tmpCard);
        			tmpCard.lowerScores(yHand, tmpCard);
		
        			tmpCard.print(game1Score);
        		
        			inputScore.addActionListener(new ActionListener()
        			{
						public void actionPerformed(ActionEvent e) 
						{
							int lineNum = Integer.parseInt(scoreLine.getText());
							
							if(players[playerNum - 1].getScoreCard().get(lineNum - 1) == 0) 
		        				{
		        					players[playerNum - 1].getScoreCard().set(lineNum - 1, tmpCard.get(lineNum - 1));
		        				}	
		        				else
		        				{
		        					scoreError.setVisible(true);
		        				}

		        				game1Score.setText("");
		        				scoreLine.setText("");
		        				for(int i = 0; i < slots.length; i++)
		        				{
		        					slots[i].setIcon(emptySlot);
		        					slots[i].setBorder(BorderFactory.createLineBorder(Color.black));

		        					slots[i].repaint();
		        				}
		        				
					        if(playerNum == numPlayers)
					        {
					        		gameTurn++;
					        		playerNum = 0;
	        					}
					        
					        playerNum++;
							currentRolls = 1;
					        playerCount.setText("Turn " + gameTurn +": Player " + playerNum);
					        
					        calculateButton.setVisible(false);
					        
		            			for(int i = 0; i < allDice.length; i++)
		            			{
		            				allDice[i] = false;
		            			}
		            			keepDie = checkAllDice(allDice);
		            			
		            			if(gameTurn == 3)
		            			{
		            				game1.setVisible(false);
		            				for(int i = 0; i < slots.length; i++)
		            				{
		            					slots[i].setVisible(false);
		            				}
		            				
		            				JPanel finalGame = new JPanel();
		            				finalGame.setBackground(new Color(211, 211, 211));
		            				finalGame.setLayout(null);
		            				frmFortniteYahtzee.getContentPane().add(finalGame);
		            				
		            				JLabel finalScoreLabel = new JLabel();
		            				finalScoreLabel.setText("Final Score Cards");
		            				finalScoreLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		            				finalScoreLabel.setForeground(Color.BLACK);
		            				finalScoreLabel.setBounds(50, 50, 400, 50);
		            				finalScoreLabel.setVisible(true);
		            				finalGame.add(finalScoreLabel);
		            				
		            				JTextArea finalScores = new JTextArea();
		            				finalScores.setEditable(false);
		            				finalScores.setBounds(50, 110, 500, 400);
		            				finalScores.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		            				finalScores.setForeground(Color.BLACK);
		            				finalScores.setBackground(new Color(155, 145, 181));
		            				JScrollPane scroll = new JScrollPane(finalScores);
		            				scroll.setBounds(50,110,500,400);
		            				finalGame.add(scroll);
		            				
		            				for(int i = 0; i < players.length; i++)
		            				{
		            					players[i].getScoreCard().setUpper(players[i].getScoreCard().calculateUpper());
		            					players[i].getScoreCard().setLower(players[i].getScoreCard().calculateLower());
		            					players[i].getScoreCard().calculateGrand();
		            					
		            					finalScores.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		            					finalScores.append("Player " + (i + 1) + " Score Card:\n");
		            					finalScores.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		            					finalScores.append("\n");
		            					players[i].getScoreCard().printComplete(finalScores);
		            				}
		            				
		            				JLabel playAgain = new JLabel();
		            				playAgain.setText("Would you like to play again?");
		            				playAgain.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		            				playAgain.setForeground(Color.BLACK);
		            				playAgain.setBounds(600, 110, 500, 250);
		            				playAgain.setVisible(true);
		            				finalGame.add(playAgain);
		            				
		            				JButton again = new JButton();
		            				again.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		            				again.setText("Yes");
		            				again.setBounds(725, 300, 100, 100);
		            				finalGame.add(again);
		            				
		            				again.addActionListener(new ActionListener()
		            				{
									public void actionPerformed(ActionEvent e) 
									{
										finalGame.setVisible(false);
										mainPanel.setVisible(true);
									}
		            				});
		            				
		            			}
						}
        			});
			}
		});
			
	}
	
	private void printHand(Hand yHand, MyPanel[] slots)
	{
		for(int i = 0; i < diceNum; i++)
		{
			switch(yHand.get(i).getWeapon())
			{
				case pistol: 
					slots[i].setIcon(pistol);
					slots[i].repaint();
					break;
				case crossbow: 
					slots[i].setIcon(crossbow);
					slots[i].repaint();
					break;
				case minigun: 
					slots[i].setIcon(minigun);
					slots[i].repaint();
					break;
				case submachinegun: 
					slots[i].setIcon(submachinegun);
					slots[i].repaint();
					break;
				case grenadeL: 
					slots[i].setIcon(grenadelauncher);
					slots[i].repaint();
					break;
				case shotgun: 
					slots[i].setIcon(shotgun);
					slots[i].repaint();
					break;
				case sniper: 
					slots[i].setIcon(sniper);
					slots[i].repaint();
					break;
				case rocketL: 
					slots[i].setIcon(rocketlauncher);
					slots[i].repaint();
					break;
				case assaultrifle: 
					slots[i].setIcon(assaultrifle);
					slots[i].repaint();
					break;
			}
		}
	}
	
	private boolean checkAllDice(boolean[] allDice)
	{
		if(allDice[0] && allDice[1] && allDice[2] && allDice[3]
				&& allDice[4])
		{
			return true;
		}
		return false;
	}
}



