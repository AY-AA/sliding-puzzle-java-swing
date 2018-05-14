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
import java.util.Random;

import Board.*;
import Stack.*;

import javax.imageio.ImageIO;
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
	//	private Board _board;
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

	///////
	JPanel _board;
	private int _dimension;
	private int _n;
	private JLabel  _emptyFigure;
	private ArrayList<Figure> _boardDS; //Data Structure to hold the board.
	private boolean _isGameOver;
	private int[] _positions;


	// --- CONSTRUCTOR ---
	/**
	 * Puzzle object constructor, creates a new windows and adds components using addComponents method
	 * @param board is the sliding puzzle game component
	 */
	public PuzzleWindow (int puzzleSize, BufferedImage puzzleImage)
	{
		super();
		createBoard(puzzleSize, puzzleImage);
		//		_board = new Board();
		//		_boardDimension = _board.getDimension();
		_boardsStack = new Stack();
		_boardsStack.push(_currentBoard);
		addKeyListener(this);
		setFocusable(true);


		int x = _n;
		int y = _n + 80;
		setPreferredSize(new Dimension(x,y));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadImages();
		initiateWindow();

		resetTimer();
		_timer = new Timer(1000,this);
		_timer.start();

		//setResizable(false);
		//		pack();
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

	private void createBoard(int puzzleSize, BufferedImage puzzleImage) 
	{
		_board = new JPanel();
		int figureWidth = puzzleImage.getWidth() / puzzleSize; //size of each button
		int figureHeight = puzzleImage.getHeight() / puzzleSize;
		_emptyFigure = new JLabel();
		_emptyFigure.setPreferredSize(new Dimension(figureWidth, figureHeight));
		_emptyFigure.setOpaque(true);
		_emptyFigure.setBackground(Color.BLACK);
		_dimension = puzzleSize;
		int boardSize = figureWidth* _dimension;
		_n = _dimension * _dimension;
		setSize(boardSize, boardSize);
		_boardDS = new ArrayList<Figure>();
		_currentBoard = new int[_n];
		_positions = new int [_n];
		_board.setLayout(new GridLayout(_dimension, _dimension, 1, 1));
		initBoard(puzzleImage,figureWidth,figureHeight);
		_isGameOver = false;
		setVisible(true);

	}
	/**
	 * Initiating the board data structure in order to create from it the board itself
	 *
	 * @param puzzle
	 */
	private void initBoard(BufferedImage puzzleImage, int figureWidth, int figureHeight)
	{

		int x = 0, y = 0;
		for (int i = 0; i < _n - 1; i++) {
			ImageIcon _imgToAdd = new ImageIcon(puzzleImage.getSubimage(x, y, figureWidth, figureHeight));
			Figure _figToAdd = new Figure(i + 1, _imgToAdd);
			_figToAdd.addActionListener(this);
			_boardDS.add(_figToAdd);
			if ((i + 1) % _dimension == 0) {
				x = 0;
				y += figureHeight;
			} else {
				x += figureWidth;
			}
		}
		_boardDS.add(null);
		boardShuffle();
	}
	/**
	 * Shuffling the board itself and adding it to the JPanel
	 */
	public void boardShuffle() {

		Random randomGenerator = new Random();
		ArrayList<Figure> hardCopy = new ArrayList<Figure>(_boardDS);

		for (int i = 0; i < _n; i++) {
			int randomIndex = randomGenerator.nextInt(_boardDS.size());
			Figure tmpFig = _boardDS.get(randomIndex);
			if (tmpFig == null) 
			{
				_positions[i] = 0;
			} 
			else 
			{
				_positions[i] = _boardDS.get(randomIndex).getCurrentIndex();
				tmpFig.setCurrentIndex(i + 1);
			}
			_boardDS.remove(randomIndex);
		}
		_boardDS = hardCopy;
		remover();
	}

	public void remover() 
	{
		_board.removeAll();
		updateBoard();
	}

	/**
	 * Updating the board each move by user
	 */
	public void updateBoard() {
		for (int i = 0; i < _n; i++) {
			int currPos = _positions[i];
			if (currPos != 0) {
				Figure tmp = _boardDS.get(currPos - 1);
				_board.add(tmp);
			}
			else {
				_board.add(_emptyFigure);
			}
		}
	}



	private void switchFig(int a, int b) {
		int temp = _positions[a];
		_positions[a] = _positions[b];
		_positions[b] = temp;

		//		
		//		Figure figure1 = _boardDS.get(_positions[a]);
		//		Figure figure2 = _boardDS.get(_positions[b]);
		//		int figure1Index = figure1.getCurrentIndex();
		//		int figure2Index = figure2.getCurrentIndex();
		//		figure1.setCurrentIndex(figure2Index);
		//		figure2.setCurrentIndex(figure1Index);


	}

	/**
	 * moving figure on the board if the move is legal
	 *
	 * @param movingFigure
	 * @return
	 */
	public boolean move(Figure movingFigure) {
		int toChange = movingFigure.getCurrentIndex() - 1;
		try {
			if (_positions[toChange - _dimension] == 0) { // if up is empty
				switchFig(toChange - _dimension, toChange);
				movingFigure.setCurrentIndex(toChange - _dimension + 1);
				remover();
				checkAnswer();
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (_positions[toChange + 1] == 0) { // if right is empty
				switchFig(toChange + 1, toChange);
				movingFigure.setCurrentIndex(toChange + 2);
				remover();
				checkAnswer();
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {

			if (_positions[toChange - 1] == 0) { // if left is empty
				switchFig(toChange - 1, toChange);
				movingFigure.setCurrentIndex(toChange);
				remover();
				checkAnswer();
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (_positions[toChange + _dimension] == 0) { // if down is empty
				switchFig(toChange + _dimension, toChange);
				movingFigure.setCurrentIndex(toChange + _dimension + 1);
				remover();
				checkAnswer();
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return false;
	}

	/**
	 * moving figure using the keyboard keys by user
	 *
	 * @param move
	 */
	public boolean moveByKey(int moving, int x) {
		moving = moving + x;
		if (moving >= 0 && moving < _n)
		{
			int movingIndex = _positions[moving] -1;
			Figure movingFigure = _boardDS.get(movingIndex);
			move(movingFigure);

			//            switchFig(moving, x );
			//            if(_boardDS.get(_positions[x]) != null) 
			//                _boardDS.get(_positions[x]).setCurrentIndex(x + 1);
			//            remover();
			return true;
		}
		return false;

		//		if (move >= 0 && move < _n) {
		//			switchFig(move, x);
		//			checkAnswer();
		//			return true;
		//		}
		//		return false;
	}

	//	private boolean checkValidMove(int empty, int moving) 
	//	{
	//		if (empty)
	//		
	//		
	//		if(pos1 < pos2) 
	//		{
	//            if (pos1 + 1 % _dimension == 0 && pos2 == pos1 + 1) 
	//            {
	//                return false;
	//            }
	//        }
	//        else if(pos2 + 1 % _dimension == 0 && pos1 == pos2 + 1) 
	//        {
	//            return false;
	//        }
	//        return true;
	//	}

	private int findZero() {
		for (int i = 0; i < _n; i++)
			if (_positions[i] == 0)
				return i;
		return 0;
	}

	/**
	 * checks if the game is done
	 */
	private void checkAnswer() {
		for (int i = 0; i < _n - 1; i++) {
			if (_positions[i] != i + 1) {
				_isGameOver = false;
				return;
			}
		}
		_isGameOver = true;
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
		if (!_boardsStack.isEmpty())
		{
			_currentBoard = _boardsStack.pop();
			undoMove(_currentBoard);
		}
		else
			alert("cant undo");
	}

	public void undoMove(int[] arr) {
		_positions = arr;
		for (int i = 0; i < _n; i++) {
			int x = _positions[i];
			Figure tempFig = _boardDS.get(x);
			if (tempFig != null) {
				tempFig.setCurrentIndex(i + 1);
			}
		}
		updateBoard();
	}

	/**
	 * changes the start and stop icons whenever it is clicked and also stop the timer if game is paused
	 */
	private void changePauseStartButton()
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
		resetTimer();
		_timer.start();
		_boardsStack.clear();
		boardShuffle();
		//		_currentBoard = _board.getCurrBoard();
		//		_boardsStack.push(_currentBoard);
		_isFinished = false;
		_isStopped = false;
		_stopStartButton.setIcon(_stopIcon);
		_stopStartButton.setText("Stop");
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
	 * pushes a new board to the stack after a move is done and updates the moves counter
	 */
	private void updateBoardStack() 
	{
		_movesCounter++;
		_movesCounterLabel.setText("        Total moves: "+_movesCounter);
		_boardsStack.push(_currentBoard);
		if (_isGameOver)
			finishGame();
	}

	/**
	 * finishes the game whenever the user wins
	 */
	private void finishGame()
	{
		_timer.stop();
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
		case ("paused"):
		{
			JOptionPane.showMessageDialog(null, "Can not make moves while game is paused,"
					+ '\n' +  "please press play first and then make moves ", 
					"Game is paused", JOptionPane.CANCEL_OPTION);
			break;
		}

		case ("finish"):
		{
			JOptionPane.showMessageDialog(null, "GOOD JOB!" + '\n' 
					+ "Total moves :" + _movesCounter + '\n'
					+ "Total time :" + _hoursLabel.getText() + _minutesLabel.getText() + _secondsLabel.getText(), "Game is over", JOptionPane.CLOSED_OPTION);
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
					+ '\n' +  "You are in the first move, cannot undo it.", "No more undos", JOptionPane.CANCEL_OPTION);				

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
				playAgain();
			else
				alert("play again");
		}		
		else if (_isStopped)
		{
			if (e.getSource() == _stopStartButton)
				changePauseStartButton();
			else
				alert("paused");
		}
		else if ( e.getSource() instanceof Figure)
		{
			if (move((Figure)e.getSource()))
				updateBoardStack();
		}
		else if (e.getSource() == _stopStartButton)
		{
			changePauseStartButton();
		}
		else if (e.getSource() == _timer)
		{
			updateTimerLabel();
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
		if (keyCode == KeyEvent.VK_SPACE)
		{
			if (_isFinished)
				playAgain();
			else
				changePauseStartButton();
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
			int x = findZero();
			switch( keyCode )
			{
			case KeyEvent.VK_UP:			//move up
			{
				if (x <_n- _dimension && moveByKey(_dimension,x))
					updateBoardStack();

			}
			break;
			case KeyEvent.VK_DOWN:			//move down
			{
				if (x >= _dimension && moveByKey(-1 * _dimension,x))
					updateBoardStack();
			}
			break;
			case KeyEvent.VK_LEFT:			//move left
			{
				if (!(x % _dimension == _dimension-1) && moveByKey(1,x))
					updateBoardStack();

			}
			break;
			case KeyEvent.VK_RIGHT :		//move right
			{
				if (!(x % _dimension == 0) && moveByKey(-1,x))
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
