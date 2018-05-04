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

public class GUI
{
	private static final int diceSide = 9;
	private static final int diceNum = 5;
	private static final int rollsPerHand = 4;
	
	private static int currentRoll = 1;
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

		//sets up frame
		frmFortniteYahtzee = new JFrame();
		frmFortniteYahtzee.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		frmFortniteYahtzee.setTitle("Fortnite Yahtzee");
		frmFortniteYahtzee.setSize(1200, 800);
		frmFortniteYahtzee.setResizable(false);
		frmFortniteYahtzee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//starts game at main menu
		mainMenu();
		
	}
	
	private void mainMenu()
	{
		//sets up background for main menu
		ImageIcon imageIcon = new ImageIcon("mainMenu.jpg");
		MyPanel mainPanel = new MyPanel(imageIcon);
		mainPanel.setLayout(null);

        frmFortniteYahtzee.getContentPane().add(mainPanel);
        
        mainPanel.repaint();
        
        //adds play button
        JButton playButton = new JButton();
        playButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        playButton.setText("PLAY");
        playButton.setBounds(225, 450, 320, 125);
        mainPanel.add(playButton);
        
        //adds instrucion button
        JButton instrucButton = new JButton();
        instrucButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        instrucButton.setText("INSTRUCTIONS");
        instrucButton.setBounds(225, 575, 320, 125);
        mainPanel.add(instrucButton);
        
        //action listener for play button which brings up selection of number of players
       playButton.addActionListener(new ActionListener()
       {
    	   		public void actionPerformed(ActionEvent e) 
    	   		{
    	   			instrucButton.setVisible(false);
    	   			
    	   			JLabel labelNumPlayers = new JLabel();
    	   			labelNumPlayers.setBounds(225, 550, 250, 75);
    	   			labelNumPlayers.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    	   			labelNumPlayers.setForeground(Color.WHITE);
    	   			labelNumPlayers.setText("Number of Players:");
    	   			mainPanel.add(labelNumPlayers);
    	         
    	   			//creating buttons with number of players 
    	   			JButton playerNum1 = new JButton();
    	   			playerNum1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    	   			playerNum1.setText("1");
    	   			playerNum1.setBounds(225, 600, 50, 50);
    	   			playerNum1.setVisible(true);
    	   			mainPanel.add(playerNum1);
    	   			
    	   			JButton playerNum2 = new JButton();
    	   			playerNum2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    	   			playerNum2.setText("2");
    	   			playerNum2.setBounds(275, 600, 50, 50);
    	   			playerNum2.setVisible(true);
    	   			mainPanel.add(playerNum2);
    	   			
    	   			JButton playerNum3 = new JButton();
    	   			playerNum3.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    	   			playerNum3.setText("3");
    	   			playerNum3.setBounds(325, 600, 50, 50);
    	   			playerNum3.setVisible(true);
    	   			mainPanel.add(playerNum3);
    	   			
    	   			JButton playerNum4 = new JButton();
    	   			playerNum4.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    	   			playerNum4.setText("4");
    	   			playerNum4.setBounds(375, 600, 50, 50);
    	   			playerNum4.setVisible(true);
    	   			mainPanel.add(playerNum4);
    	   			
    	   			JButton playerNum5 = new JButton();
    	   			playerNum5.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    	   			playerNum5.setText("5");
    	   			playerNum5.setBounds(425, 600, 50, 50);
    	   			playerNum5.setVisible(true);
    	   			mainPanel.add(playerNum5);
    	   			
    	   			//adding action listeners to the numbered buttons
    	   			playerNum1.addActionListener(new ActionListener()
    	   			{
					public void actionPerformed(ActionEvent e) 
					{
						runGame(1, mainPanel);
						mainPanel.setVisible(false);
					}
    	   			});
    	   			
    	   			playerNum2.addActionListener(new ActionListener()
    	   			{
					public void actionPerformed(ActionEvent e) 
					{
						runGame(2, mainPanel);
						mainPanel.setVisible(false);
					}
    	   			});
    	   			
    	   			playerNum3.addActionListener(new ActionListener()
    	   			{
					public void actionPerformed(ActionEvent e) 
					{
						runGame(3, mainPanel);
						mainPanel.setVisible(false);
					}
    	   			});
    	   			
    	   			playerNum4.addActionListener(new ActionListener()
    	   			{
					public void actionPerformed(ActionEvent e) 
					{
						runGame(4, mainPanel);						
						mainPanel.setVisible(false);
					}
    	   			});
    	   			
    	   			playerNum5.addActionListener(new ActionListener()
    	   			{
					public void actionPerformed(ActionEvent e) 
					{
						runGame(5, mainPanel);						
						mainPanel.setVisible(false);
					}
    	   			});
 
			}
       });
       
       //adding instruction button action listener to bring user to instruction panels
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
	    	   	    MainMenu.setBounds(925, 645, 250, 100);
	    	        instrucPanel.add(MainMenu);
	    	        
	    	        JButton next1 = new JButton();
	    	        next1.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
	    	        next1.setText("Next");
	            next1.setBounds(675, 645, 250, 100);
	   	        instrucPanel.add(next1);
	   	        
	   	        MainMenu.addActionListener(new ActionListener()
		        {
		    	   		public void actionPerformed(ActionEvent e) 
		    	   		{
		    	   			instrucPanel.setVisible(false);
		    	   			mainPanel.setVisible(true);
	     	   		}
		        });
		   	    
	   	        //brings user to next instruction panel
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
	   	        			next2.setBounds(675, 645, 250, 100);
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
		  	   	        
		  	   	        //brings user to last instruction panel
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
	
	//runs game with the specified number of players
	private void runGame(int numPlayers, MyPanel mainPanel)
	{
		playerNum = 1;
		
		//creates the slots where the dice images are shown 
		MyPanel[] slots = new MyPanel[5];
		for(int i = 0, x = 500; i < slots.length; i++)
		{
			slots[i] = new MyPanel(emptySlot);
			slots[i].setBounds(x, 100, 100, 100);
			slots[i].setBorder(BorderFactory.createLineBorder(Color.black));
			frmFortniteYahtzee.getContentPane().add(slots[i]); 
			x += 100;
		}
		
		//creates an array of the correct number of players
		Player[] players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++)
		{
			players[i] = new Player();
		}
		
		//panel of the game and its components
		JPanel game1 = new JPanel();
		game1.setBackground(new Color(188, 158, 193));
		game1.setLayout(null);
		
        frmFortniteYahtzee.getContentPane().add(game1);
        
        JTextArea game1Score = new JTextArea();
        game1Score.setEditable(false);
        game1Score.setBounds(500, 250, 500, 400);
        game1Score.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        game1Score.setForeground(Color.BLACK);
        game1Score.setBackground(new Color(124, 165, 184));
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
        inputScore.setText("Input Score");
        inputScore.setBounds(50, 240, 200, 75);
        inputScore.setVisible(false);
        game1.add(inputScore);
        
        JLabel playerCount = new JLabel();
        playerCount.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        playerCount.setBounds(500, 50, 400, 50);
        playerCount.setText("Turn " + gameTurn +": Player " + playerNum);
        game1.add(playerCount);
        
        JLabel scoreLabel1 = new JLabel();
        JLabel scoreLabel2 = new JLabel();
		scoreLabel1.setText("Please click the score line");
        scoreLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        scoreLabel1.setForeground(Color.BLACK);
		scoreLabel1.setBounds(50, 200, 400, 50);
		scoreLabel1.setVisible(true);
		
		scoreLabel2.setText("you want to keep!");
		scoreLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        scoreLabel2.setForeground(Color.BLACK);
		scoreLabel2.setBounds(50, 250, 400, 50);
		scoreLabel2.setVisible(true);
		
		game1.add(scoreLabel1);
		game1.add(scoreLabel2);
		
		yHand = new Hand();
		ScoreCard tmpCard = new ScoreCard();
        
        boolean[] allDice = new boolean[5];
        for(int i = 0; i < allDice.length; i++)
        {
        		allDice[i] = false;
        }
        
        
        //creates check boxes for the user to keep dice
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

       //adds action listeners and creates the buttons which the user uses to select which
       //line they want to add to their score card
       JButton[] scoreButtons = new JButton[17];
       ActionListener scoreButtonListener = new ActionListener()
       {
       		public void actionPerformed(ActionEvent e) 
       		{
       			if(e.getSource() instanceof JButton)
       			{
       				yHand = new Hand();
       				
       				int buttonNum = Integer.parseInt(((JButton) e.getSource()).getText());
       				System.out.println("buttonNum " + buttonNum);
       				players[playerNum - 1].getScoreCard().set(buttonNum, tmpCard.get(buttonNum));
       				System.out.println("player card " + players[playerNum - 1].getScoreCard().get(buttonNum));

       				for(int i = 0; i < scoreButtons.length; i++)
       				{
       					scoreButtons[i].setVisible(false);
       				}
       				
       				game1Score.setText("");
       				
       				if(playerNum == numPlayers)
			        {
			        		gameTurn++;
			        		playerNum = 0;
   					}
			        
			        playerNum++;
					currentRoll = 1;
			        playerCount.setText("Turn " + gameTurn +": Player " + playerNum);
			        
			        calculateButton.setVisible(false);
			        rollButton.setVisible(true);
			        
				    for(int i = 0; i < allDice.length; i++)
           			{
           				allDice[i] = false;
           			}
           			keepDie = checkAllDice(allDice);
           			
           			for(int i = 0; i < slots.length; i++)
       				{
       					slots[i].setIcon(emptySlot);
       					slots[i].repaint();
       				}
           			
           			//after game turn hits 17 go into the end game panel
           			if(gameTurn == 17)
           			{
           				game1.setVisible(false);
           				for(int i = 0; i < slots.length; i++)
           				{
           					slots[i].setVisible(false);
           				}
           				
           				//creates final panel and shows the user their final scorecard
           				JPanel finalGame = new JPanel();
           				finalGame.setBackground(new Color(248, 199, 204));
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
           				
           				JButton yesButton = new JButton();
           				yesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
           				yesButton.setText("Yes");
           				yesButton.setBounds(725, 300, 100, 100);
           				finalGame.add(yesButton);
           				
           				JButton noButton = new JButton();
           				noButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
           				noButton.setText("No");
           				noButton.setBounds(835, 300, 100, 100);
           				finalGame.add(noButton);
           				
           				yesButton.addActionListener(new ActionListener()
           				{
							public void actionPerformed(ActionEvent e) 
							{
								finalGame.setVisible(false);
								mainPanel.setVisible(true);
								gameTurn = 1;
								currentRoll = 1;
							}
           				});
           				
           				noButton.addActionListener(new ActionListener()
   						{
           					public void actionPerformed(ActionEvent e)
           					{
           						System.exit(0);
           					}
   						});
           			}
       			}
       		}
       };
       
       for(int i = 0, y = 255; i < scoreButtons.length; i++)
       {
       		scoreButtons[i] = new JButton();
       		scoreButtons[i].setBounds(475, y, 10,10);
       		scoreButtons[i].setText(Integer.toString(i));
       		scoreButtons[i].addActionListener(scoreButtonListener);
       		scoreButtons[i].setVisible(false);
       		game1.add(scoreButtons[i]);
       		
       		y += 23;
       }
       
        keepDie = checkAllDice(allDice);
		//action listener for the roll button on the game panel
		rollButton.addActionListener(new ActionListener()
        	{
				public void actionPerformed(ActionEvent e) 
				{
					if(currentRoll <= rollsPerHand)
					{
						System.out.println(currentRoll);
						for(int dieNumber = 0; dieNumber < diceNum; dieNumber++)
						{
							if(allDice[dieNumber] != true)
							{
								Dice newDice = new Dice();
								if(currentRoll == 1)
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
						if(currentRoll == rollsPerHand) 
						{
							check1.setSelected(false);
							check2.setSelected(false);
							check3.setSelected(false);
							check4.setSelected(false);
							check5.setSelected(false);
							calculateButton.setVisible(true);
							rollButton.setVisible(false);
						}
						
						printHand(yHand, slots);
						currentRoll++;
					}
				}
        	});
		
		//action listener for the calculate button on the game panel
		calculateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculateButton.setVisible(false);
        			tmpCard.upperScores(yHand, tmpCard);
        			tmpCard.lowerScores(yHand, tmpCard);
		
        			tmpCard.print(game1Score);
        			
        			for(int i = 0; i < scoreButtons.length; i++)
    				{
    					if(players[playerNum - 1].getScoreCard().get(i) == 0)
    					{
    						scoreButtons[i].setVisible(true);
    					}
    					else
    					{
    						scoreButtons[i].setVisible(false);
    					}
    				}
			}
		});
			
	}
	
	//prints hand to the image slots on the game panel
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
	
	//checks to see if all dice are kept
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



