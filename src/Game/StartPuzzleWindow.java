package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ImageHandler.*;
import Board.*;


public class StartPuzzleWindow extends Window implements ActionListener
{
	private JButton _backButton,_openButton,_choose,_random;
	private JFileChooser _fileChooser;
	private JTextField _nxn;
	private ImageIcon _openIcon,_backIcon,_chooseIcon,_randomIcon;
	private int _puzzleSize = 0;
	private JLabel _backLabel,_sizeLabel,_openLabel,_chooseLabel,_randomLabel;
	private ImagePanel _panel;
	private ChooseWindow _imageChooser;
	private BufferedImage _puzzleImage,_sushiImage,_catImage,_cyberImage;
	private final Insets _insets = new Insets(5, 5, 5,5);
	private final Dimension _buttonDimension = new Dimension(110, 60);

	public StartPuzzleWindow()
	{
		super();
		setSize(600	,400);
		loadImages();
		initiateWindow();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		_imageChooser = new ChooseWindow();
		_imageChooser.setLocationRelativeTo(this);

		setResizable(false);
		setVisible(true);
	}

	protected void loadImages()
	{
		try {
			_chooseIcon = new ImageIcon ("Images/changeImageIcon.png");
			_randomIcon = new ImageIcon("Images/randomIcon.png");
			_openIcon = new ImageIcon("Images/openIcon.png");
			_backIcon = new ImageIcon ("Images/backIcon.png");
			_sushiImage = ImageIO.read(new File("Images/sushi.jpg"));
			_catImage =  ImageIO.read(new File("Images/cat.jpeg"));
			_cyberImage = ImageIO.read(new File("Images/cyber.jpeg"));
			Image icon = ImageIO.read(new File("Images/icon.png"));
			setIconImage(icon);
		} catch (IOException e1) {
			System.out.println("error: could not load images in StartPuzzle screen");
		}
	}

	protected void initiateWindow() 
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = _insets;
		_panel = new ImagePanel();

		// ------ Labels ------
		_sizeLabel = new JLabel();
		_sizeLabel.setText("Choose puzzle size :");
		_sizeLabel.setFont(new Font ("Arial",Font.BOLD, 30));
		_sizeLabel.setOpaque(true);
		_sizeLabel.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 0;
		_panel.add(_sizeLabel, gbc);

		_chooseLabel = new JLabel();
		_chooseLabel.setText("Choose a picture :");
		_chooseLabel.setFont(new Font ("Arial",Font.BOLD, 30));
		_chooseLabel.setOpaque(true);
		_chooseLabel.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 1;
		_panel.add(_chooseLabel, gbc);

		_openLabel = new JLabel();
		_openLabel.setText("Load a picture :");
		_openLabel.setFont(new Font ("Arial",Font.BOLD, 30));
		_openLabel.setOpaque(true);
		_openLabel.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 2;
		_panel.add(_openLabel, gbc);

		_randomLabel = new JLabel();
		_randomLabel.setText("Random game :");
		_randomLabel.setFont(new Font ("Arial",Font.BOLD, 30));
		_randomLabel.setOpaque(true);
		_randomLabel.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 3;
		_panel.add(_randomLabel, gbc);

		_backLabel = new JLabel();
		_backLabel.setText("Back to main menu :");
		_backLabel.setFont(new Font ("Arial",Font.BOLD, 30));
		_backLabel.setOpaque(true);
		_backLabel.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 4;
		_panel.add(_backLabel, gbc);

		// ------ Buttons ------
		_nxn = new JTextField("Enter size");
		_nxn.setSize(_buttonDimension);
		_nxn.addActionListener(this);
		//mouse listener
		_nxn.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				_nxn.setText("");

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseExited(MouseEvent e) {


			}

		});
		gbc.gridx = 1;
		gbc.gridy = 0;
		_panel.add(_nxn, gbc);

		_choose = new JButton();
		_choose.setName("Choose");
		_choose.setIcon(_chooseIcon);
		_choose.addActionListener(this);
		_choose.setPreferredSize(_buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 1;
		_panel.add(_choose,gbc);

		_openButton = new JButton();
		_openButton.setName("Open");
		_openButton.setIcon(_openIcon);
		_openButton.addActionListener(this);
		_openButton.setPreferredSize(_buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 2;
		_panel.add(_openButton, gbc);

		_random = new JButton();
		_random.setName("Random");
		_random.setIcon(_randomIcon);
		_random.addActionListener(this);
		_random.setPreferredSize(_buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 3;
		_panel.add(_random,gbc);

		_backButton = new JButton();
		_backButton.setName("Back");
		_backButton.setIcon(_backIcon);
		_backButton.addActionListener(this);
		_backButton.setPreferredSize(_buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 4;
		_panel.add(_backButton,gbc);

		add(_panel);			//add JPanel to window
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button;
		if (e.getSource() instanceof JButton)
			button = (JButton)e.getSource();			
		else
			return;
		switch (button.getName())
		{
		case "Open":					//load a picture
		{
			if (generateBoardSize()) {
				_fileChooser = new JFileChooser();
				int action = _fileChooser.showOpenDialog(null);
				if(action == JFileChooser.APPROVE_OPTION){
					File file = _fileChooser.getSelectedFile();
					try {
						_puzzleImage = ImageIO.read(file);
						play();
					} catch (IOException e1) {
						System.out.println("error: image was not found");
					}
				}
			}
		}
		break;
		case "Choose":					//choose from game's library
		{
			if (generateBoardSize())
			{
				_imageChooser.openWindow(_puzzleSize,this);
				setVisible(false);
			}
			break;
		}
		case "Random":					//play a random game
		{
			chooseRandomGame();
			play();
			break;

		}
		case "Back":					//back to main menu
		{
			new MainWindow();
			dispose();
		}
		}

	}
	/**
	 * creates a new sliding puzzle game
	 */
	private void play() {
		_puzzleImage = ImageResizer.resizeImage(_puzzleImage, 700, 700);
		Board board = new Board (_puzzleSize);
		PuzzleWindow p = new PuzzleWindow (board,_puzzleImage);
		p.setLocationRelativeTo(this);
		dispose();
	}
	/**
	 * creates a random game
	 * choosing a picture of the sample pictures given in the assignment
	 * randomizes a puzzle size ( 3 , 4 or 5 sized)
	 */
	private void chooseRandomGame() 
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(3);
		_puzzleSize = randomNum + 3;
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
	/**
	 * creates an integer of given user's input
	 * @return
	 */
	private boolean generateBoardSize() {
		try {
			String input = _nxn.getText();
			_puzzleSize = Integer.parseInt(input);
			if (_puzzleSize > 0)
				return true;
			else
				alert("invalid size");
		}
		catch (NumberFormatException e) {
			alert("invalid size");
			return false;
		}
		return false;
	}
	/**
	 * handles the alerts shown to user 
	 * @param alert is string which represent an alert type
	 */
	private void alert(String alert) 
	{
		switch (alert)
		{
		case "invalid size":
			JOptionPane.showMessageDialog(null, "Please choose a valid puzzle size and then try again."
					+ '\n' + "A valid size is an integer bigger than 1.", 
					"Size is not chosen", JOptionPane.CANCEL_OPTION);
		}
	}

}

