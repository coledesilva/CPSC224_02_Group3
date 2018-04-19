import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class GUI 
{

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
		frmFortniteYahtzee = new JFrame();
		frmFortniteYahtzee.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		frmFortniteYahtzee.setTitle("Fortnite Yahtzee");
		frmFortniteYahtzee.setBounds(100, 100, 450, 300);
		frmFortniteYahtzee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainMenuPanel = new JPanel();
		frmFortniteYahtzee.getContentPane().add(mainMenuPanel, BorderLayout.CENTER);
		
		JLabel mainLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("mainMenu.jpg").getImage().getScaledInstance(mainLabel.getWidth(), mainLabel.getHeight(), Image.SCALE_DEFAULT));
		mainLabel.setIcon(imageIcon);
		
		mainMenuPanel.add(mainLabel);
	}

}



