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

/**
 * Representing the main window of the game
 */
public class MainWindow extends JFrame implements ActionListener {

<<<<<<< HEAD
    public MainWindow() {
    	//-------------------------- Window Preferences
        super("Welcome");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        background = new JLabel(new ImageIcon("MyBackground.jpg"));
        this.setContentPane(background);
        this.setLayout(new BorderLayout());
        menu = new JPanel();
        menu.setLayout(new GridBagLayout());

        //-------------------------- Buttons
        ImageIcon exit_64 = new ImageIcon("exit1_64.png");
        exit = new JButton("Exit", exit_64);
        exit.addActionListener(this);
        
        ImageIcon puzzle1_64 = new ImageIcon("puzzle_play.png");
        start_Game = new JButton("play",puzzle1_64);
        start_Game.addActionListener(this);
        
        //-------------------------- Grid Layout preferences
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        //-------------------------- Adding to panel and frame
        menu.add(start_Game, gbc);
        menu.add(exit, gbc);
=======
	private JButton _exitButton,_startGameButton;
	private ImageIcon[] _iconsPack;
	private ImagePanel _panel;
	private JLabel _welcomeMSG;
	private FilesHandler _filesHandler;
	private final Dimension _BUTTON_DIMENSION = new Dimension(130, 70);

    /**
     * Constructor
     * @param filesHandler
     */
	public MainWindow(FilesHandler filesHandler) {
		super("Sliding Puzzle");
		_filesHandler = filesHandler;
		_iconsPack = _filesHandler.getMainPack();
		setSize(600	,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initiateWindow();		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		requestFocusInWindow();
	}
>>>>>>> alex

    /**
     * creating the swing preferences of the window
     */
	private void initiateWindow()
	{
		if (_filesHandler.getIcon() != null)
			setIconImage(_filesHandler.getIcon());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5,5);
		_panel = new ImagePanel("Images/Background.jpg");
		Color color = new Color(248,244,233);
		//-------------------------- Labels
		_welcomeMSG = new JLabel();
		_welcomeMSG.setText("Welcome");
		_welcomeMSG.setFont(new Font ("Arial",Font.BOLD, 60));
		gbc.gridx = 0;
		gbc.gridy = 0;
		_panel.add(_welcomeMSG, gbc);

<<<<<<< HEAD
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else {
        	new StartPuzzle();
        	dispose();
        }
=======
		//-------------------------- Buttons
		_startGameButton = new JButton("Play");
		_startGameButton.setIcon(_iconsPack[0]);
		_startGameButton.addActionListener(this);
		_startGameButton.setPreferredSize(_BUTTON_DIMENSION);
		_startGameButton.setBackground(color);
		gbc.gridx = 0;
		gbc.gridy = 1;
		_panel.add(_startGameButton, gbc);
		
		JLabel x = new JLabel();
		x.setPreferredSize(new Dimension(220, 70));
		gbc.gridx = 1;
		gbc.gridy = 1;
		_panel.add(x, gbc);
>>>>>>> alex

		_exitButton = new JButton("Exit");
		_exitButton.setIcon(_iconsPack[1]);
		_exitButton.addActionListener(this);
		_exitButton.setPreferredSize(_BUTTON_DIMENSION);
		_exitButton.setBackground(color);
		gbc.gridx = 0;
		gbc.gridy = 2;
		_panel.add(_exitButton, gbc);
        _panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(_panel);
	}

	public static void main(String args[]) {
		FilesHandler fh = new FilesHandler();
		if (!fh.getStatus())
			System.out.println("can't launch: missing files");
		else
			new MainWindow(fh);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _exitButton) { //if exit button has been pressed
			System.exit(0);
		} 
		else if (e.getSource() == _startGameButton) { //if start button has been pressed
			StartPuzzleWindow sp = new StartPuzzleWindow(_filesHandler);
			sp.setLocationRelativeTo(this);
			dispose();
		}

	}

}
