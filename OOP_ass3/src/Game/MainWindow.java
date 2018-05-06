package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class MainWindow extends JFrame {
	
	private JButton exit;
	private JButton start_Game;
	
	public MainWindow() 
	{
		super("Welcome");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		exit = new JButton("Exit");
		start_Game = new JButton("Start to play");
		this.getContentPane().add(exit,FlowLayout.LEFT);
		this.getContentPane().add(start_Game,FlowLayout.LEFT);
		this.setSize(300,300);
		this.setVisible(true);
	}

}
