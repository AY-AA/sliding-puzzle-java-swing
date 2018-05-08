package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.JFrame;

public class StartPuzzle extends JFrame
{
	private JPanel main = new JPanel();
	private JButton open,play;
	private JFileChooser fileChooser;
	private BufferedImage image;
	private JLabel puzzleImage = new JLabel("Select an image and difficulty level");
	private JTextField nxn;
	private GridBagConstraints gbc = new GridBagConstraints();
	private ImageIcon open_Icon,play_Icon;


	public StartPuzzle()
	{
		setTitle("Welcome");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.setLayout(new GridLayout());
		
		//============= Buttons 
		open_Icon = new ImageIcon("open_pic.png");
        open = new JButton("Open");      
        open.setIcon(open_Icon);
        play_Icon = new ImageIcon("play_pic.png");
        play = new JButton("Play");
        play.setIcon(play_Icon);      
        
      //============= adding to JPanel main
        gbc.gridx = 6;
        gbc.gridy = 8;
        main.add(open, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 8;
        main.add(play, gbc);
        
        add(main);
		setVisible(true);

        
	}
	public static void main(String[] args) {
		StartPuzzle sp = new StartPuzzle();
	}

}
