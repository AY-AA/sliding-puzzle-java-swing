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
	private JButton open,start;
	private JFileChooser fileChooser;
	private BufferedImage image;
	private JLabel puzzleImage = new JLabel("Select an image and difficulty level");
	private JTextField nxn;

	public StartPuzzle()
	{
		setTitle("Welcome");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//============= Buttons 
		//ImageIcon open_Image = 
        open = new JButton();
        
        
        start = new JButton();
	}
	public static void main(String[] args) {
		StartPuzzle sp = new StartPuzzle();
	}

}
