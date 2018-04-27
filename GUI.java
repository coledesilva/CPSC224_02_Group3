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
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
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
		frmFortniteYahtzee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainMenu(screenSize);
		
	}
	
	private void mainMenu(Dimension screenSize)
	{
		ImageIcon imageIcon = new ImageIcon("mainMenu.jpg");
		MyPanel panel = new MyPanel(imageIcon);
		panel.setLayout(null);

        frmFortniteYahtzee.getContentPane().add(panel);

        frmFortniteYahtzee.addWindowListener( new WindowAdapter()
        {
            public void windowResized(WindowEvent evt)
            {
                panel.repaint();
            }
        });
        
        panel.repaint();
        
        JButton playButton = new JButton();
        playButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        playButton.setText("PLAY");
        playButton.setBounds(315, 455, 320, 125);
        panel.add(playButton);
        
        JButton instrucButton = new JButton();
        instrucButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        instrucButton.setText("INSTRUCTIONS");
        instrucButton.setBounds(315, 590, 320, 125);
        panel.add(instrucButton);
        
       playButton.addActionListener(new ActionListener()
       {
    	   		
    	   		public void actionPerformed(ActionEvent e) 
    	   		{
				runGame();
			}
       });
	}
}



