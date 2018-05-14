package Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Board.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;



public class PuzzleWindow extends Window implements ActionListener, KeyListener
{
	// --- STACK ---
	private int[] _currentBoard;				//for each 0<i<(board size)^2 this int array specifies where is the current index i in the board matrix
	private Stack _boardsStack;					//stack which holds the boards
	private static Figure _lastPressed;			//last pressed figure is hold by this static property

	// --- TIMER ---
	private Timer _timer;
	private int _seconds = 0;
	private int _minutes = 0;
	private int _hours = 0;	

	// --- HEADER TOOLBAR ---
	private JToolBar _controlsToolbar;
	private JButton _stopStartButton;
	private JButton _undoButton;
	private JButton _changeImageButton;
	private JButton _menuButton;  
	private ImageIcon _stopIcon, _startIcon, _undoIcon, _changeImageIcon, _menuIcon, _playAgainIcon;	

	// --- PUZZLE SCREEN ---
	private Board _board;
	private int _boardDimension = 0;

	// --- FOOTER INFO ---
	private JLabel _secondsLabel;
	private JLabel _minutesLabel;
	private JLabel _hoursLabel;
	private JLabel _movesCounterLabel;
	private int _movesCounter;
	private JToolBar _infoToolbar;

	// --- GAME STATUS ---
	private boolean _isFinished;
	private boolean _isStopped;

	// --- CONSTRUCTOR ---
	/**
	 * Puzzle object constructor, creates a new windows and adds components using addComponents method
	 * @param board is the sliding puzzle game component
	 */
	public PuzzleWindow (Board board)
	{
		super();
		_board = board;
		_boardDimension = _board.getDimension();
		_boardsStack = new Stack();
		_boardsStack.push(_board);
		

		setPreferredSize(new Dimension(450, 520));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadImages();
		initiateWindow();

		resetTimer();
		_timer = new Timer(1000,this);
		_timer.start();

		//setResizable(false);
		pack();
		setVisible(true);		
	}
	protected void loadImages()
	{
		// set icons
		_stopIcon = new ImageIcon("Images/stopIcon.png"); 
		_startIcon = new ImageIcon("Images/startIcon.png"); 
		_undoIcon = new ImageIcon("Images/undoIcon.png"); 
		_changeImageIcon = new ImageIcon("Images/changeImageIcon.png"); 
		_menuIcon = new ImageIcon("Images/menuIcon.png"); 
		_playAgainIcon = new ImageIcon ("Images/playAgainIcon.png");
	}
	protected void initiateWindow()
	{
		// initialize header toolbar items
		_isStopped = false;
		_isFinished = false;
		_stopStartButton = new JButton("Stop");
		_stopStartButton.setIcon(_stopIcon);
		_stopStartButton.addActionListener(this);

		_undoButton = new JButton("Undo");
		_undoButton.setIcon(_undoIcon);
		_undoButton.addActionListener(this);

		_changeImageButton = new JButton("Change");
		_changeImageButton.setIcon(_changeImageIcon);
		_changeImageButton.addActionListener(this);

		_menuButton = new JButton("Menu");
		_menuButton.setIcon(_menuIcon);
		_menuButton.addActionListener(this);


		// add items to toolbar
		_controlsToolbar = new JToolBar();
		_controlsToolbar.add(_stopStartButton);
		_controlsToolbar.add(_undoButton);
		_controlsToolbar.add(_changeImageButton);
		_controlsToolbar.add(_menuButton);
		_controlsToolbar.setFloatable(false);

		// initialize timer 
		_hoursLabel = new JLabel();
		_hoursLabel.setText("00 : ");
		_minutesLabel = new JLabel();
		_minutesLabel.setText("00 : ");
		_secondsLabel = new JLabel();
		_secondsLabel.setText("00");

		//initialize counter
		_movesCounter = 0;
		_movesCounterLabel = new JLabel();
		_movesCounterLabel.setText("        Total moves: "+_movesCounter);

		// add timer and moves counter to info toolbar 
		_infoToolbar = new JToolBar();
		_infoToolbar.add(_hoursLabel);
		_infoToolbar.add(_minutesLabel);
		_infoToolbar.add(_secondsLabel);

		_infoToolbar.add(_movesCounterLabel);
		_infoToolbar.setFloatable(false);

		// add items to container
		add(_controlsToolbar, BorderLayout.NORTH);
		add((JPanel)_board, BorderLayout.CENTER);
		add(_infoToolbar, BorderLayout.SOUTH);

	}

	// --- BUTTONS ---
	/**
	 * closes the game and goes back to image choosing window
	 */
	private void backToChooseImage() 
	{
		StartPuzzleWindow p = new StartPuzzleWindow();
		p.setLocationRelativeTo(this);
		dispose();
	}
	/**
	 * closes the game and goes back to main screen
	 */
	private void backToMenu() 
	{
		MainWindow m = new MainWindow();
		m.setLocationRelativeTo(this);
		dispose();		
	}
	/**
	 * undo last move
	 */
	private void undo()
	{
		_currentBoard = (int[])_boardsStack.pop();
		_board.undo(_currentBoard);
	}
	/**
	 * changes the start and stop icons whenever it is clicked and also stop the timer if game is paused
	 */
	private void changePauseStartButton()
	{
		_isStopped = !_isStopped;
		if (_isStopped)
		{
			_stopStartButton.setIcon(_startIcon);
			_stopStartButton.setText("Start");
		}
		else
		{
			_stopStartButton.setIcon(_stopIcon);
			_stopStartButton.setText("Stop");
		}
	}
	/**
	 * if the game is over and the user wants to play again, this method recreates the board.
	 */
	private void playAgain() 
	{
		_boardsStack.clear();
		_board.boardShuffle();
		_currentBoard = _board.getCurrBoard();
		_isFinished = false;
		_isStopped = false;
		_stopStartButton.setIcon(_stopIcon);
		_stopStartButton.setText("Stop");
		resetTimer();
	}

	// --- TIMER ---
	/**
	 * updates the timer every second
	 */
	private void updateTimerLabel() 
	{
		_seconds++;
		if (_seconds == 60)
		{
			_seconds = 0;
			_minutes++;
		}
		if (_minutes == 60)
		{
			_seconds = 0;
			_minutes = 0;
			_hours++;
		}		
		_secondsLabel.setText(String.format("%02d", _seconds));
		_minutesLabel.setText(String.format("%02d", _minutes) + ":");
		_hoursLabel.setText(String.format("%02d", _hours) + ":");
	}
	/**
	 * resets the timer
	 */
	private void resetTimer()
	{
		if (_isStopped == false)
		{
			_seconds = 0;
			_minutes = 0;
			_hours = 0;
		}
	}


	/**
	 * a static method to handle the last clicked button (figure)
	 * @param fig is the clicked figure the user chose
	 */
	public static void figurePressed(Figure fig)
	{
		_lastPressed = fig;
	}

	/**
	 * pushes a new board to the stack after a move is done and updates the moves counter
	 */
	private void updateBoardStack() 
	{
		_movesCounter++;
		_movesCounterLabel.setText("        Total moves: "+_movesCounter);

		_currentBoard = _board.getCurrBoard();
		_boardsStack.push(_currentBoard);
		if (_board.isGameOver())
			finishGame();
	}

	/**
	 * finishes the game whenever the user wins
	 */
	private void finishGame()
	{
		_isStopped = true;
		alert("finish");
		_stopStartButton.setIcon(_playAgainIcon);
		_stopStartButton.setText("Play Again");	
	}

	/**
	 * handles the alerts shows to user 
	 * @param alert is string which represent an alert type
	 */
	private void alert(String alert) 
	{
		switch (alert)
		{
		case "paused":
			JOptionPane.showMessageDialog(null, "Can not make moves while game is paused,"
					+ '\n' +  "please press play first and then make moves ", 
					"Game is paused", JOptionPane.CANCEL_OPTION);
		case "finish":
			JOptionPane.showMessageDialog(null, "GOOD JOB!" + '\n' 
					+ "Total moves :" + _movesCounter + '\n'
					+ "Total time :" + _hoursLabel.getText() + _minutesLabel.getText() + _secondsLabel.getText(), "Game is over", JOptionPane.CLOSED_OPTION);
		case "play again":
			JOptionPane.showMessageDialog(null, "The game is finished,"
					+ '\n' +  "please choose one of the buttons above.", "Game is over", JOptionPane.CANCEL_OPTION);				
		}
	}


	// --- OVERRIDES ---	
	@Override
	public void actionPerformed(ActionEvent e)
	{

		if (_isFinished && e.getSource() == _stopStartButton)
		{
			playAgain();
		}
		else if (e.getSource() == _stopStartButton)
		{
			changePauseStartButton();
			resetTimer();
		}
		else if (e.getSource() == _timer && !_isStopped)
		{
			updateTimerLabel();
		}
		else if (e.getSource() == _menuButton)
		{
			backToMenu();
		}
		else if (e.getSource() == _changeImageButton)
		{
			backToChooseImage();
		}
		else if (e.getSource() == _undoButton)
		{
			undo();
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		if (_isFinished)
		{
			alert("play again");
			return;
		}
		if (_isStopped)
		{
			alert("paused");
			return;
		}
		if (keyCode == KeyEvent.VK_SPACE)
		{
			changePauseStartButton();
			resetTimer();
			return;
		}

		switch( keyCode ) 
		{ 
		case KeyEvent.VK_UP:			//move up
		{
			if (_board.moveByKey(_boardDimension))
				updateBoardStack(); 

		}
		break;
		case KeyEvent.VK_DOWN:			//move down
		{
			if (_board.moveByKey(-1 * _boardDimension))
				updateBoardStack(); 
			else
				alert("paused");
		}
		break;
		case KeyEvent.VK_LEFT:			//move left
		{
			if (_board.moveByKey(1))
				updateBoardStack(); 

		}
		break;
		case KeyEvent.VK_RIGHT :		//move right
		{
			if (_board.moveByKey(-1))
				updateBoardStack(); 
		}
		break;
		case KeyEvent.VK_Z:				//undo
		{
			if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)   //ctrl+z
				undo();
		}
		}
	} 
	@Override

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
