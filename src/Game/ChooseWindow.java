package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Board.*;
import ImageHandler.ImageResizer;

public class ChooseWindow extends Window implements ActionListener{

	private JButton _backButton,_sushiButton,_catButton,_cyberButton;
	private ImageIcon _sushiIcon,_catIcon,_cyberIcon,_backIcon;
	private JLabel _chooseWindowsLabel,_backLabel;
	private ImagePanel _imageChooser;
	private BufferedImage _puzzleImage,_sushiImage,_catImage,_cyberImage;
	private final Insets _insets = new Insets(5, 5, 5,5);
	private final Dimension _buttonDimension = new Dimension(160, 150);
	private int _puzzleSize;
	private StartPuzzleWindow _backWindow;
	
	public ChooseWindow() {
		super();
		setSize(600	,400);
		setResizable(false);
		
		loadImages();
		initiateWindow();
		add(_imageChooser);
		pack();
	}
	protected void loadImages()
	{
		try {
			_sushiIcon = new ImageIcon ("Images/sushi.jpg");
			_catIcon = new ImageIcon ("Images/cat.jpeg");
			_cyberIcon = new ImageIcon ("Images/cyber.jpeg");
			_backIcon = new ImageIcon ("Images/backIcon.png");
			_sushiImage = ImageIO.read(new File("Images/sushi.jpg"));
			_catImage =  ImageIO.read(new File("Images/cat.jpeg"));
			_cyberImage = ImageIO.read(new File("Images/cyber.jpeg"));
			Image icon = ImageIO.read(new File("Images/icon.png"));
			setIconImage(icon);
			resizeImages();
		} catch (IOException e1) {
			System.out.println("error: could not load images in ChooseWindow screen");
		}

	}
	private void resizeImages() {
		_sushiImage = ImageResizer.resizeImage(_sushiImage, 400, 400);
		_catImage = ImageResizer.resizeImage(_catImage, 400, 400);
		_cyberImage = ImageResizer.resizeImage(_cyberImage, 400, 400);
	}
	protected void initiateWindow() {
		_imageChooser = new ImagePanel();
		GridBagConstraints gbco = new GridBagConstraints();
		gbco.insets = _insets;
		
		// ---- Labels ----
		
		_chooseWindowsLabel = new JLabel();
		_chooseWindowsLabel.setText("Choose a picture :");
		_chooseWindowsLabel.setFont(new Font ("Arial",Font.BOLD, 30));
		_chooseWindowsLabel.setOpaque(true);
		_chooseWindowsLabel.setBackground(new Color(1,196,252,70));
		gbco.gridx = 1;
		gbco.gridy = 0;
		_imageChooser.add(_chooseWindowsLabel, gbco);
		
		_backLabel = new JLabel();
		_backLabel.setText("Back to menu :");
		_backLabel.setFont(new Font ("Arial",Font.BOLD, 30));
		_backLabel.setOpaque(true);
		_backLabel.setBackground(new Color(1,196,252,70));
		gbco.gridx = 1;
		gbco.gridy = 2;
		_imageChooser.add(_backLabel, gbco);
		
		// ---- Buttons ----
		_catButton = new JButton();
		_catButton.setName("Cat");
		_catButton.setIcon(_catIcon);
		_catButton.addActionListener(this);
		_catButton.setPreferredSize(_buttonDimension);
		gbco.gridx = 0;
		gbco.gridy = 1;
		_imageChooser.add(_catButton,gbco);

		_sushiButton = new JButton();
		_sushiButton.setName("Sushi");
		_sushiButton.setIcon(_sushiIcon);
		_sushiButton.addActionListener(this);
		_sushiButton.setPreferredSize(_buttonDimension);
		gbco.gridx = 1;
		gbco.gridy = 1;
		_imageChooser.add(_sushiButton,gbco);

		_cyberButton = new JButton();
		_cyberButton.setName("Cyber");
		_cyberButton.setIcon(_cyberIcon);
		_cyberButton.addActionListener(this);
		_cyberButton.setPreferredSize(_buttonDimension);
		gbco.gridx = 2;
		gbco.gridy = 1;
		_imageChooser.add(_cyberButton,gbco);		
		
		_backButton = new JButton();
		_backButton.setName("Back");
		_backButton.setIcon(_backIcon);
		_backButton.addActionListener(this);
		_backButton.setPreferredSize(new Dimension (80,80));
		gbco.gridx = 2;
		gbco.gridy = 2;
		_imageChooser.add(_backButton,gbco);
	}
	public void openWindow(int pS, StartPuzzleWindow backWindow)
	{
		_backWindow = backWindow;
		_puzzleSize = pS;
		setVisible(true);
	}
	public void closeWindow()
	{
		setVisible(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String sender = button.getName();
		switch (sender)
		{
		case "Cyber":
		{
			_puzzleImage =_cyberImage;
			play();
			break;
		}
		case "Sushi":
		{
			_puzzleImage = _sushiImage;
			play();
			break;
		}
		case "Cat":
		{
			_puzzleImage = _catImage;
			play();
			break;
		}
		case "Back":
		{
			setVisible(false);
			_backWindow.setVisible(true);
		}
		}
		
	}
	private void play() {
		
		Board board = new Board (_puzzleSize);
		PuzzleWindow p = new PuzzleWindow (board,_puzzleImage);
		p.setLocationRelativeTo(this);
		dispose();
		_backWindow.dispose();
		
	}

}
