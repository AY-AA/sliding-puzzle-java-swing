package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements ActionListener {

	private JButton exit;
	private JButton start_Game;
	private ImagePanel menu;
	private JLabel background;
	private JLabel _welcomeMSG;
	private JLabel _instructions;

	public MainWindow() {  	
		//-------------------------- Window Preferences
		super("Sliding Puzzle");
		setSize(400	,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initiateWindow();		
		setVisible(true);
	}


	private void initiateWindow()
	{
		_welcomeMSG = new JLabel();
		_instructions = new JLabel();
		_welcomeMSG.setText("Welcome,");
		_instructions.setText("Please choose one of the options below");

		menu = new ImagePanel();

		//-------------------------- Buttons
		ImageIcon exitIcon = new ImageIcon("exitIcon.png");
		exit = new JButton("Exit", exitIcon);
		exit.addActionListener(this);

		ImageIcon puzzle1_64 = new ImageIcon("playMainIcon.png");
		start_Game = new JButton("Play",puzzle1_64);
		start_Game.addActionListener(this);

		//-------------------------- Adding to panel and frame
		menu.add(_welcomeMSG);
		menu.add(_instructions);
		menu.add(start_Game);
		menu.add(exit);

		Image icon;
		try {                
			icon = ImageIO.read(new File("icon.png"));
			setIconImage(icon);
		} catch (IOException ex) {}

		add(menu);
		//        pack();		
	}


	public static void main(String args[]) {
		new MainWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		} else {
			StartPuzzle sp = new StartPuzzle();
			sp.setLocationRelativeTo(this);
			dispose();
		}

	}

}
