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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
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
		MyPanel panel = new MyPanel(imageIcon);
		panel.setLayout(null);

        frmFortniteYahtzee.getContentPane().add(panel);
        
        panel.repaint();
        
        JButton playButton = new JButton();
        playButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        playButton.setText("PLAY");
        playButton.setBounds(315, 535, 320, 125);
        panel.add(playButton);
        
        JButton instrucButton = new JButton();
        instrucButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        instrucButton.setText("INSTRUCTIONS");
        instrucButton.setBounds(315, 670, 320, 125);
        panel.add(instrucButton);
        
        JLabel labelNumPlayers = new JLabel();
        labelNumPlayers.setBounds(645, 540, 200, 75);
        labelNumPlayers.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        labelNumPlayers.setForeground(Color.WHITE);
        labelNumPlayers.setText("Number of Players");
        panel.add(labelNumPlayers);
        
        JTextField numPlayers = new JTextField();
        numPlayers.setEditable(true);
        numPlayers.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        numPlayers.setBounds(645, 600, 50, 50);
        panel.add(numPlayers);
        
       playButton.addActionListener(new ActionListener()
       {
    	   		public void actionPerformed(ActionEvent e) 
    	   		{
    	   			int numberOfPlayers = Integer.parseInt(numPlayers.getText());
    	   			panel.setVisible(false);
				runGame(numberOfPlayers);
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
    	   	        panel.setVisible(false);
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
		    	   			panel.setVisible(true);
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
	
	private void runGame(int numPlayers)
	{
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
       	JScrollPane scroll = new JScrollPane(game1Score, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       	game1.add(scroll);
        
        JButton rollButton = new JButton();
        rollButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        rollButton.setText("Roll");
        rollButton.setBounds(50, 100, 100, 75);
        game1.add(rollButton);
        
        JLabel playerCount = new JLabel();
        playerCount.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        
        int gameTurn = 1;
		while(gameTurn <= diceSide + 8)
		{
			boolean keepDie = true;
			for(int i = 0; i < numPlayers; i++)
			{
				Hand yHand = new Hand();
				int currentRolls = 1;
				
				while(currentRolls <= rollsPerHand && keepDie)
				{
					for(int dieNumber = 0; dieNumber < diceNum; dieNumber++)
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
			}
		}
        rollButton.addActionListener(new ActionListener()
        		{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						
					}
        		});
	}
}



