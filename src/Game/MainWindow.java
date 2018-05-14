package Game;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

	private JButton _exitButton,_startGameButton;
	private ImageIcon _exitIcon,_startGameIcon;
	private ImagePanel _menu;
	private JLabel _welcomeMSG,_instructions;

	public MainWindow() {  	
		super("Sliding Puzzle");
		setSize(600	,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		loadImages();
		initiateWindow();		
		setVisible(true);
	}

	private void loadImages() 
	{
		try {
			Image icon = ImageIO.read(new File("icon.png"));
			setIconImage(icon);
			_startGameIcon = new ImageIcon("playMainIcon.png");
			_exitIcon = new ImageIcon("exitIcon.png");		
		} catch (IOException e1) {
			System.out.println("error: could not load images in MainWindows screen");
		}
	}

	private void initiateWindow()
	{
		GridBagConstraints gbc = new GridBagConstraints();

		final Insets insets = new Insets(5, 5, 5,5);

		gbc.insets = insets;
		_menu = new ImagePanel();

		//-------------------------- Labels
		_welcomeMSG = new JLabel();
		_welcomeMSG.setText("Welcome,");
		gbc.gridx = 0;
		gbc.gridy = 0;
		_menu.add(_welcomeMSG, gbc);

		_instructions = new JLabel();
		_instructions.setText("Please choose one of the options");
		gbc.gridx = 0;
		gbc.gridy = 1;
		_menu.add(_instructions, gbc);

		//-------------------------- Buttons
		_startGameButton = new JButton("Play");
		_startGameButton.setIcon(_startGameIcon);
		_startGameButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 0;
		_menu.add(_startGameButton, gbc);


		_exitButton = new JButton("Exit");
		_exitButton.setIcon(_exitIcon);
		_exitButton.addActionListener(this);
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
			StartPuzzle sp = new StartPuzzle();
			sp.setLocationRelativeTo(this);
			dispose();
		}

	}

}