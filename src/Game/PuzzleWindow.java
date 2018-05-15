package Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Board.*;
import ImageHandler.ImageResizer;

import javax.imageio.ImageIO;
import javax.swing.*;



public class PuzzleWindow extends Window implements ActionListener, KeyListener
{

	// --- TIMER ---
	private Timer _timer;
	private int _seconds,_minutes,_hours;

	// --- HEADER TOOLBAR ---
	private JToolBar _controlsToolbar;
	private JButton _undoButton, _stopStartButton, _changeImageButton, _menuButton;
	private ImageIcon _stopIcon, _startIcon, _undoIcon, _changeImageIcon, _menuIcon, _playAgainIcon;	

	// --- FOOTER INFO ---
	private JLabel _secondsLabel,_minutesLabel,_hoursLabel,_movesCounterLabel;
	private int _movesCounter;
	private JToolBar _infoToolbar;

	// --- GAME STATUS ---
	private boolean _isFinished,_isStopped;

	// --- BOARD ---
	private JPanel _board;
	private int _dimension, _n;
	private JLabel  _emptyFigure;
	private ArrayList<Figure> _boardFigures; //Data Structure to hold the board.
	private Dimension _figureDimension;
	private Board _boardDS;
	private BufferedImage _puzzleImage;
	private int _figureSize;


	// --- CONSTRUCTOR ---
	/**
	 * Puzzle object constructor, creates a new windows and adds components using addComponents method
	 * @param board is the sliding puzzle game component
	 */
	public PuzzleWindow (Board board, BufferedImage puzzleImage)
	{
		super();
		_puzzleImage = puzzleImage;
		_boardDS = board;

		int x = _n;
		int y = _n + 80;
		setPreferredSize(new Dimension(x,y));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadImages();
		initiateWindow();
		addKeyListener(this);
		setFocusable(true);
		setResizable(false);
		setVisible(true);		
	}

	protected void loadImages()
	{
		try {
			// set icons
			_stopIcon = new ImageIcon("Images/stopIcon.png"); 
			_startIcon = new ImageIcon("Images/startIcon.png"); 
			_undoIcon = new ImageIcon("Images/undoIcon.png"); 
			_changeImageIcon = new ImageIcon("Images/changeImageIcon.png"); 
			_menuIcon = new ImageIcon("Images/menuIcon.png"); 
			_playAgainIcon = new ImageIcon ("Images/playAgainIcon.png");
			Image icon = ImageIO.read(new File("Images/icon.png"));
			setIconImage(icon);
		} catch (IOException e1) {
			System.out.println("error: could not load images in PuzzleWindow screen");
		}
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
		resetTimer();
		_timer = new Timer(1000,this);
		_timer.start();

		//initialize counter
		_movesCounter = -1;
		_movesCounterLabel = new JLabel();
		_movesCounterLabel.setText("        Total moves: "+_movesCounter);

		// add timer and moves counter to info toolbar 
		_infoToolbar = new JToolBar();
		_infoToolbar.add(_hoursLabel);
		_infoToolbar.add(_minutesLabel);
		_infoToolbar.add(_secondsLabel);

		_infoToolbar.add(_movesCounterLabel);
		_infoToolbar.setFloatable(false);

		//initialize board
		_board = new JPanel();
		_dimension = _boardDS.getDimension();
		_figureSize = _puzzleImage.getWidth() / _dimension; //size of each button
		_figureDimension = new Dimension(_figureSize, _figureSize);
		_emptyFigure = new JLabel();
		_emptyFigure.setPreferredSize(_figureDimension);
		_emptyFigure.setOpaque(true);
		_emptyFigure.setBackground(Color.BLACK);
		
		int boardSize = _figureSize * _dimension;
		_n = _boardDS.getTotalFigures();
		setSize(boardSize, boardSize);
		_boardFigures = new ArrayList<Figure>();
		_board.setLayout(new GridLayout(_dimension, _dimension, 1, 1));
		initFigureOntoBoardFigures();
		_boardFigures = _boardDS.play(_boardFigures);
		updater();
		
		// add all components to window
		add(_controlsToolbar, BorderLayout.NORTH);
		add(_board, BorderLayout.CENTER);
		add(_infoToolbar, BorderLayout.SOUTH);

	}

	/**
	 * Initiating the board data structure in order to create from it the board itself
	 *
	 * @param puzzle
	 */
	private void initFigureOntoBoardFigures()
	{

		int x = 0, y = 0;
		for (int i = 0; i < _n - 1; i++) {
			ImageIcon imgToAdd = new ImageIcon(_puzzleImage.getSubimage(x, y, _figureSize, _figureSize));
			Figure figToAdd = new Figure(i + 1, imgToAdd);
			figToAdd.addActionListener(this);
			_boardFigures.add(figToAdd);
			if ((i + 1) % _dimension == 0) {
				x = 0;
				y += _figureSize;
			} else {
				x += _figureSize;
			}
		}
		_boardFigures.add(null);
	}

	/**
	 * updates the shown board on window through the board's methods
	 */
	public void updater() 
	{
		_board.removeAll();
		updateBoard();
		updateMoves();
		if (_boardDS.checkAnswer())
			finishGame();
	}
	/**
	 * Updating the board each move by user
	 */
	public void updateBoard() {
		for (int i = 0; i < _n; i++) {
			int currPos = _boardDS.get(i);
			if (currPos != 0) {
				Figure tmp = _boardFigures.get(currPos - 1);
				_board.add(tmp);
			}
			else {
				_board.add(_emptyFigure);
			}
		}
		_isFinished = _boardDS.checkAnswer();

	}
	/**
	 * pushes a new board to the stack after a move is done and updates the moves counter
	 */
	private void updateMoves() 
	{
		_movesCounter++;
		_movesCounterLabel.setText("        Total moves: "+_movesCounter);
		
	}

	/**
	 * moving figure on the board if the move is legal
	 *
	 * @param movingFigure
	 * @return
	 */
	public void move(Figure movingFigure) 
	{
		int toChange = movingFigure.getCurrentIndex() - 1;
		int zero = _boardDS.findZero();
		try {
			if (zero + _dimension == toChange) { // if up is empty
				_boardDS.switchFig(toChange - _dimension, toChange);
				movingFigure.setCurrentIndex(toChange - _dimension + 1);
				updater();
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (zero - 1 == toChange) { // if right is empty
				_boardDS.switchFig(toChange + 1, toChange);
				movingFigure.setCurrentIndex(toChange + 2);
				updater();

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {

			if (zero + 1 == toChange) { // if left is empty
				_boardDS.switchFig(toChange - 1, toChange);
				movingFigure.setCurrentIndex(toChange);
				updater();

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (zero - _dimension == toChange) { // if down is empty
				_boardDS.switchFig(toChange + _dimension, toChange);
				movingFigure.setCurrentIndex(toChange + _dimension + 1);
				updater();
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	/**
	 * moving figure using the keyboard keys by user
	 *
	 * @param move
	 */
	public void moveByKey(int moving, int x) {
		moving = moving + x;
		if (moving >= 0 && moving < _n)
		{
			int movingIndex = _boardDS.get(moving) -1;
			Figure movingFigure = _boardFigures.get(movingIndex);
			move(movingFigure);
		}
	}
	
	/**
	 * finishes the game whenever the user wins
	 */
	private void finishGame()
	{
		_timer.stop();
		_isFinished = true;
		_isStopped = true;
		alert("finish");
		_stopStartButton.setIcon(_playAgainIcon);
		_stopStartButton.setText("Play Again");	
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
		if (_boardDS.undoMove(_boardFigures))
		{
			_board.removeAll();
			updateBoard();
		}
		else
			alert("cant undo");
	}
	/**
	 * changes the start and stop icons whenever it is clicked and also stop the timer if game is paused
	 */
	private void PauseStartButton()
	{
		_isStopped = !_isStopped;
		if (!_isStopped)
		{
			_stopStartButton.setIcon(_stopIcon);
			_stopStartButton.setText("Stop");
			_timer.start();
		}
		else
		{
			_stopStartButton.setIcon(_startIcon);
			_stopStartButton.setText("Start");
			_timer.stop();
		}
	}
	/**
	 * if the game is over and the user wants to play again, this method recreates the board.
	 */
	private void playAgain() 
	{
		_boardDS.clearStack();
		_boardFigures = _boardDS.play(_boardFigures);
		_movesCounter = -1;
		updater();

		_isFinished = false;
		_isStopped = false;
		_stopStartButton.setIcon(_stopIcon);
		_stopStartButton.setText("Stop");
		resetTimer();
		_timer.start();
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
		_hoursLabel.setText("Total Time: " + String.format("%02d", _hours) + ":");
	}
	/**
	 * resets the timer
	 */
	private void resetTimer()
	{
		_seconds = 0;
		_minutes = 0;
		_hours = 0;
		updateTimerLabel();
	}

	/**
	 * handles the alerts shows to user 
	 * @param alert is string which represent an alert type
	 */
	private void alert(String alert) 
	{
		switch (alert)
		{
		case ("paused"):
		{
			JOptionPane.showMessageDialog(null, "Can not make moves while game is paused,"
					+ '\n' +  "please press play first and then make moves ", 
					"Game is paused", JOptionPane.CANCEL_OPTION);
			break;
		}

		case ("finish"):
		{
			BufferedImage iconB = ImageResizer.resizeImage(_puzzleImage, 250,250);
			ImageIcon icon = new ImageIcon(iconB);
			JOptionPane.showMessageDialog(null, "GOOD JOB!" + '\n' 
					+ "Total moves :" + _movesCounter + '\n'
					+ _hoursLabel.getText() + _minutesLabel.getText() + _secondsLabel.getText(), "Game is over", JOptionPane.INFORMATION_MESSAGE,
                    icon);
			break;
		}

		case ("play again"):
		{
			JOptionPane.showMessageDialog(null, "The game is finished,"
					+ '\n' +  "please choose one of the buttons above.", "Game is over", JOptionPane.CANCEL_OPTION);				
			break;
		}

		case ("cant undo"):
		{
			JOptionPane.showMessageDialog(null, "Cannot undo,"
					+ '\n' +  "You have reached maximum undo phases", "No more undos", JOptionPane.CANCEL_OPTION);				

		}
		}
	}

	// --- OVERRIDES ---	
	@Override
	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == _menuButton)
		{
			backToMenu();
		}
		else if (e.getSource() == _changeImageButton)
		{
			backToChooseImage();
		}
		else if (_isFinished)
		{
			if (e.getSource() == _stopStartButton)
			{
				playAgain();
			}
			else
				alert("play again");
		}		
		else if (_isStopped)
		{
			if (e.getSource() == _stopStartButton)
				PauseStartButton();
			else
				alert("paused");
		}
		else if ( e.getSource() instanceof Figure)
		{
			move((Figure)e.getSource());
			
		}
		else if (e.getSource() == _stopStartButton)
		{
			PauseStartButton();
		}
		else if (e.getSource() == _timer)
		{
			updateTimerLabel();
		}
		else if (e.getSource() == _undoButton)
		{
			undo();
		}
		requestFocusInWindow();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE)
		{
			if (_isFinished)
				playAgain();
			else
				PauseStartButton();
		}
		else if (_isFinished)
		{
			alert("play again");
		}
		else if (_isStopped)
		{
			alert("paused");
		}

		else {
			int x = _boardDS.findZero();
			switch( keyCode )
			{
			case KeyEvent.VK_UP:			//move up
			{
				if (x <_n- _dimension) 
					moveByKey(_dimension,x);

			}
			break;
			case KeyEvent.VK_DOWN:			//move down
			{
				if (x >= _dimension) 
					moveByKey(-1 * _dimension,x);
			}
			break;
			case KeyEvent.VK_LEFT:			//move left
			{
				if (!(x % _dimension == _dimension-1))
					moveByKey(1,x);

			}
			break;
			case KeyEvent.VK_RIGHT :		//move right
			{
				if (!(x % _dimension == 0))
					moveByKey(-1,x);
			}
			break;
			case KeyEvent.VK_Z:				//undo
			{
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)   //ctrl+z
					undo();
			}
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
