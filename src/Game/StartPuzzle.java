package Game;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Board.*;
import ImageHandler.ImageResizer;


public class StartPuzzle extends JFrame implements ActionListener
{
	private JButton _openButton,_random, _playButton,_sushiButton,_catButton,_cyberButton;
	private JFileChooser _fileChooser;
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


	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton)e.getSource();
		switch (button.getName())
		{
		case "Play":
		{
			String N = _nxn.getText();
			_puzzleSize = getBoardSize(N);
			Board board = new Board (_puzzleSize, _puzzleImage);
			Puzzle p = new Puzzle (board);
			//p.setLocationRelativeTo(this);
			dispose();
		}
		break;
		case "Open":
		{
			_fileChooser = new JFileChooser();
			int action = _fileChooser.showOpenDialog(null);
			if(action == JFileChooser.APPROVE_OPTION){
				File file = _fileChooser.getSelectedFile();
				try {
					_puzzleImage = ImageIO.read(file);
					applychanges();
				} catch (IOException e1) {
					System.out.println("You must select an image");
				}
			}
		}
		break;
		case "Random":
		{
			chooseRandomPicture();
			applychanges();
			break;

		}
		case "Cat":
		{
			_puzzleImage = _catImage;
			applychanges();
			break;

		}
		case "Sushi":
		{
			_puzzleImage = _sushiImage;
			applychanges();
			break;

		}
		case "Cyber":
		{
			_puzzleImage = _cyberImage;
			applychanges();
			break;

		}
		}

	}


	private void applychanges() {
		_puzzleImage = ImageResizer.resizeImage(_puzzleImage, 400, 400);
		_openButton.setIcon( new ImageIcon(_puzzleImage.getScaledInstance(250, 250, Image.SCALE_DEFAULT)));		
	}

	private void chooseRandomPicture() 
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(3);
		switch (randomNum)
		{
		case 0:
		{
			_puzzleImage = _cyberImage;
			break;
		}
		case 1:
		{
			_puzzleImage = _sushiImage;
			break;
		}

		case 2:
		{
			_puzzleImage = _catImage;
			break;
		}
		}

	}

	//============= Additional Methods
	private int getBoardSize(String input) {
		try {
			int N = Integer.parseInt(input);
			return N;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Only numbers are allowd for NxN board");
			return 1;
			}
		
	}



	public static void main(String[] args) {
		StartPuzzle sp = new StartPuzzle();

	}
}