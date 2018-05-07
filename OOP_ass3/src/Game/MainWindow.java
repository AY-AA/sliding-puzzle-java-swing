package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
	private JLabel back_Label;
	private JPanel menue;
	
	public MainWindow() 
	{

		super("Welcome");
		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
//		menue = new JPanel();
//		menue.setLayout(new FlowLayout(FlowLayout.CENTER));
//		
//		//===== Buttons =====
//		exit = new JButton("Exit");
//		exit.addActionListener(this);
//		
//		start_Game = new JButton("Start to play");
//		start_Game.addActionListener(this);
//		
//		//===== Background =====
		puzzleBackground = new ImageIcon("MyBackground.jpg");
		back_Label = new JLabel(puzzleBackground);
//		
//		menue.add(start_Game);
//		menue.add(exit);
//		menue.add(back_Label);	
//		
//		add(menue);
//		//pack();
		add(back_Label);
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
