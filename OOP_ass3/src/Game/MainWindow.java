package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;


public class MainWindow extends JFrame implements ActionListener {
	
	private JButton exit;
	private JButton start_Game;
	private ImageIcon puzzleBackground;
//	private JLabel back_Label;
//	private GridBagConstraints grid = new GridBagConstraints();
	
	public MainWindow() 
	{
		super("Welcome");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450,300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//===== Background =====
		puzzleBackground = new ImageIcon("MyBackground.jpg");
		setContentPane(new JLabel(puzzleBackground));
		
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
		
        exit = new JButton("Exit");
        exit.setSize(30, 30);
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(exit, c);
		exit.addActionListener(this);

		start_Game = new JButton("Start to play");
		start_Game.setSize(30, 50);
		c.ipadx=20;
		c.gridx = 0;
        c.gridy = 1;
        getContentPane().add(start_Game, c);
		exit.addActionListener(this);
		start_Game.addActionListener(this);

		
		
//		
//		back_Label = new JLabel(puzzleBackground);
//		back_Label.setLayout(new BorderLayout());
		
		//===== Buttons =====
//		back_Label.add(exit,BorderLayout.CENTER);
//		
//		back_Label.add(start_Game,BorderLayout.EAST);
//		
//		add(back_Label);
		pack();
		setVisible(true);
	}


	public static void main(String args[])
	{
		MainWindow a = new MainWindow();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
		else
		{
			//open start up window.
		}
		
	}

}
