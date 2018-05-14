package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainWindow extends Window implements ActionListener {

	private JButton _exitButton,_startGameButton;
	private ImageIcon _exitIcon,_startGameIcon;
	private ImagePanel _menu;
	private JLabel _welcomeMSG,_instructions;

	public MainWindow() {  	
		super();
		setSize(600	,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		loadImages();
		initiateWindow();		
		setVisible(true);
		setResizable(false);
	}

	protected void loadImages() 
	{
		try {
			Image icon = ImageIO.read(new File("Images/icon.png"));
			setIconImage(icon);
			_startGameIcon = new ImageIcon("Images/playMainIcon.png");
			_exitIcon = new ImageIcon("Images/exitIcon.png");		
		} catch (IOException e1) {
			System.out.println("error: could not load images in MainWindows screen");
		}
	}

	protected void initiateWindow()
	{
		GridBagConstraints gbc = new GridBagConstraints();

		final Insets insets = new Insets(5, 5, 5,5);
		final Dimension buttonDimension = new Dimension(230, 70);

		gbc.insets = insets;
		_menu = new ImagePanel();

		//-------------------------- Labels
		_welcomeMSG = new JLabel();
		_welcomeMSG.setText("Welcome,");
		_welcomeMSG.setFont(new Font ("Arial",Font.BOLD, 60));
		_welcomeMSG.setOpaque(true);
		_welcomeMSG.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 0;
		_menu.add(_welcomeMSG, gbc);

		_instructions = new JLabel();
		_instructions.setText("Please choose one of the options");
		_instructions.setFont(new Font ("Arial",Font.BOLD, 20));
		_instructions.setOpaque(true);
		_instructions.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 1;
		_menu.add(_instructions, gbc);

		//-------------------------- Buttons
		_startGameButton = new JButton("Play");
		_startGameButton.setIcon(_startGameIcon);
		_startGameButton.addActionListener(this);
		_startGameButton.setPreferredSize(buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 0;
		_menu.add(_startGameButton, gbc);


		_exitButton = new JButton("Exit");
		_exitButton.setIcon(_exitIcon);
		_exitButton.addActionListener(this);
		_exitButton.setPreferredSize(buttonDimension);
		gbc.gridx = 1;
		gbc.gridy = 1;
		_menu.add(_exitButton, gbc);
		add(_menu);
	}

	public static void main(String args[]) {
		new MainWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _exitButton) {
			System.exit(0);
		} 
		else if (e.getSource() == _startGameButton) {
			StartPuzzleWindow sp = new StartPuzzleWindow();
			sp.setLocationRelativeTo(this);
			dispose();
		}

	}

}
