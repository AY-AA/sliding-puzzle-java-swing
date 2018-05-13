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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

import Board.*;
import ImageHandler.ImageResizer;

import javax.swing.JFrame;

public class StartPuzzle extends JFrame implements ActionListener
{
	private JButton _openButton,_random, _playButton,_sushiButton,_catButton,_cyberButton;
	private JFileChooser _fileChooser;
	private JLabel _puzzleLabel;
	private JTextField _nxn;
	private ImageIcon _openIcon,_randomIcon,_playIcon,_sushiIcon,_catIcon,_cyberIcon;
	private int _puzzleSize = 0;
	private ImagePanel _menu;
	private BufferedImage _puzzleImage,_sushiImage,_catImage,_cyberImage;
	
	public StartPuzzle()
	{
		super("Sliding Puzzle");
		setSize(600	,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		loadImages();
		initiateWindow();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void loadImages()
	{
		try {
			_randomIcon = new ImageIcon("randomIcon.png");
			_openIcon = new ImageIcon("openIcon.png");
			_playIcon = new ImageIcon("playIcon.png");
			_sushiIcon = new ImageIcon ("sushi.jpg");
			_catIcon = new ImageIcon ("cat.jpeg");
			_cyberIcon = new ImageIcon ("cyber.jpeg");
			_sushiImage = ImageIO.read(new File("sushi.jpg"));
			_catImage =  ImageIO.read(new File("cat.jpeg"));
			_cyberImage = ImageIO.read(new File("cyber.jpeg"));
			Image icon = ImageIO.read(new File("icon.png"));
			setIconImage(icon);
		} catch (IOException e1) {
			System.out.println("error: could not load images in StartPuzzle screen");
		}
		
	}

	private void initiateWindow() 
	{
		GridBagConstraints gbc = new GridBagConstraints();

		final Insets insets = new Insets(5, 5, 5,5);
		final Dimension buttonDimension = new Dimension(100, 100);
		gbc.insets = insets;
		_menu = new ImagePanel();


		// Buttons and Text addition to gridbag
		
		_playButton = new JButton("Play");
		_playButton.setName("Play");
		_playButton.setIcon(_playIcon);  
		_playButton.addActionListener(this);
		_playButton.setPreferredSize(buttonDimension);
		gbc.gridx = 0;
		gbc.gridy =0;
		_menu.add(_playButton, gbc);
		
		_openButton = new JButton("Open");
		_openButton.setName("Open");
		_openButton.setIcon(_openIcon);
		_openButton.addActionListener(this);
		_openButton.setPreferredSize(buttonDimension);
		gbc.gridx = 0;
		gbc.gridy = 1;
		_menu.add(_openButton, gbc);
		
		_random = new JButton("Random");
		_random.setName("Random");
		_random.setIcon(_randomIcon);
		_random.addActionListener(this);
		_random.setPreferredSize(buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 0;
		_menu.add(_random,gbc);
		
		_catButton = new JButton();
		_catButton.setName("Cat");
		_catButton.setIcon(_catIcon);
		_catButton.addActionListener(this);
		_catButton.setPreferredSize(buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 1;
		_menu.add(_catButton,gbc);

		_sushiButton = new JButton();
		_sushiButton.setName("Sushi");
		_sushiButton.setIcon(_sushiIcon);
		_sushiButton.addActionListener(this);
		_sushiButton.setPreferredSize(buttonDimension);
		gbc.gridx = 2;
		gbc.gridy=0;
		_menu.add(_sushiButton,gbc);
		
		_cyberButton = new JButton();
		_cyberButton.setName("Cyber");
		_cyberButton.setIcon(_cyberIcon);
		_cyberButton.addActionListener(this);
		_cyberButton.setPreferredSize(buttonDimension);
		gbc.gridx = 2;
		gbc.gridy=1;
		_menu.add(_cyberButton,gbc);
		
		_nxn = new JTextField("Select size");
		_nxn.addActionListener(this);
		_nxn.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				_nxn.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				_nxn.setText("");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		gbc.gridx = 0;
		gbc.gridy = 2;
		_menu.add(_nxn, gbc);
		
		add(_menu);

		
	}

	//============= Getters and Setters

	public static void main(String[] args) {
		StartPuzzle sp = new StartPuzzle();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton)e.getSource();
		if(button.getName().equals("Play"))
		{
			String N = _nxn.getText();
			_puzzleSize = getBoardSize(N);
			Board board = new Board (_puzzleSize, _puzzleImage);
			Puzzle p = new Puzzle (board);
			p.setLocationRelativeTo(this);
			dispose();
		}

		if(button.getName().equals("Open")) 
		{
			_fileChooser = new JFileChooser();
			int action = _fileChooser.showOpenDialog(null);
			if(action == JFileChooser.APPROVE_OPTION){
				File file = _fileChooser.getSelectedFile();
				try {
					_puzzleImage = ImageIO.read(file);
					_openButton.setIcon(new ImageIcon(_puzzleImage));
				} catch (IOException e1) {
					System.out.println("You must select an image");
				}
			}
		}
		
		if(_puzzleImage != null)
		{
			_puzzleImage = ImageResizer.resizeImage(_puzzleImage, 400, 400);
		}
		
	}


	//============= Additional Methods
	private int getBoardSize(String input) {
		char i1 = input.charAt(0);
		String temp = "";
		temp = temp + i1;
		int N = Integer.parseInt(temp);
		return N;
	}

}
