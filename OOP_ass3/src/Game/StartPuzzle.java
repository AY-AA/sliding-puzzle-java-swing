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
import javax.swing.JFrame;

public class StartPuzzle extends JFrame
{
	public StartPuzzle()
	{
		setTitle("Background Color for JFrame");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("C://"));
		add(background);
		background.setLayout(new FlowLayout());
	
	
	}
	public static void main(String[] args) {
		StartPuzzle sp = new StartPuzzle();
	}

}
