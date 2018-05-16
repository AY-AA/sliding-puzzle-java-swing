package Game;
import IHandler.FilesHandler;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainWindow extends JFrame implements ActionListener {

	private JButton _exitButton,_startGameButton;
	private ImageIcon[] _imagesPack;
	private ImagePanel _panel;
	private JLabel _welcomeMSG;
	private final String WELCOME = "Welcome to sliding puzzle game + '\n'"
								 + "csv file loaded: ";
	private FilesHandler _filesHandler;
	final Dimension _BUTTON_DIMENSION = new Dimension(130, 70);
	
	public MainWindow(FilesHandler filesHandler) {  	
		super();
		_filesHandler = filesHandler;
		_imagesPack = _filesHandler.getMainPack();
		setSize(600	,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initiateWindow();		
		setVisible(true);
		setResizable(false);
	}
	
	private void initiateWindow()
	{
		if (_filesHandler.getIcon() != null)
			setIconImage(_filesHandler.getIcon());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5,5);
		_panel = new ImagePanel();

		//-------------------------- Labels
		_welcomeMSG = new JLabel();
		_welcomeMSG.setText("Welcome");
		_welcomeMSG.setFont(new Font ("Arial",Font.BOLD, 60));
		_welcomeMSG.setOpaque(true);
		_welcomeMSG.setBackground(new Color(1,196,252,70));
		gbc.gridx = 0;
		gbc.gridy = 0;
		_panel.add(_welcomeMSG, gbc);

		//-------------------------- Buttons
		_startGameButton = new JButton("Play");
		_startGameButton.setIcon(_imagesPack[0]);
		_startGameButton.addActionListener(this);
		_startGameButton.setPreferredSize(_BUTTON_DIMENSION);
		gbc.gridx = 0;
		gbc.gridy = 1;
		_panel.add(_startGameButton, gbc);
		
		JLabel x = new JLabel();
		x.setPreferredSize(new Dimension(230, 70));
		gbc.gridx = 1;
		gbc.gridy = 1;
		_panel.add(x, gbc);

		_exitButton = new JButton("Exit");
		_exitButton.setIcon(_imagesPack[1]);
		_exitButton.addActionListener(this);
		_exitButton.setPreferredSize(_BUTTON_DIMENSION);
		gbc.gridx = 0;
		gbc.gridy = 2;
		_panel.add(_exitButton, gbc);
        _panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(_panel);
	}

	public static void main(String args[]) {
		new MainWindow(new FilesHandler());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _exitButton) {
			System.exit(0);
		} 
		else if (e.getSource() == _startGameButton) {
			StartPuzzleWindow sp = new StartPuzzleWindow(_filesHandler);
			sp.setLocationRelativeTo(this);
			dispose();
		}

	}

}
