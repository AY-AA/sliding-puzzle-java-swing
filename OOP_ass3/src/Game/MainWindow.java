package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
	private JLabel back_Label;
	
	public MainWindow() 
	{
		super("Welcome");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel menue = new JPanel();
		
		//===== Buttons =====
		exit = new JButton("Exit");
		exit.addActionListener(this);
		
		start_Game = new JButton("Start to play");
		start_Game.addActionListener(this);
		
		//===== Background =====
		puzzleBackground = new ImageIcon("Background.jpg");
		back_Label = new JLabel(puzzleBackground);
		
		//===== JFrame preferences =====
		menue.add(exit,FlowLayout.LEFT);
		menue.add(start_Game,FlowLayout.LEFT);
		menue.add(back_Label);
		this.setSize(300,300);
		this.add(menue,BorderLayout.SOUTH);
		back_Label.setVisible(true);
		this.setVisible(true);
		pack();
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
