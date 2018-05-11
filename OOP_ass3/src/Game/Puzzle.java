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



public class Puzzle extends JFrame implements ActionListener, KeyListener, PropertyChangeListener
{
	// --- STACK ---
	private int[][] _currentBoard;				//for each 0<i<(board size)^2 this int array specifies where is the current index i in the board matrix
	private Stack _boardsStack;					//stack which holds the boards
	private static Figure _lastPressed;			//last pressed figure is hold by this static property
	private ArrayList<Cell> _boardDS;
	
	// --- TIMER ---
	private Timer _timer;
	private boolean _isStopped;
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
	private static int _boardDimension=0;
		
	// --- FOOTER INFO ---
	private JLabel _secondsLabel;
	private JLabel _minutesLabel;
	private JLabel _hoursLabel;
	private JLabel _movesCounterLabel;
	private int _movesCounter;
	private JToolBar _infoToolbar;
	
	// --- GAME FINISHED ---
	private boolean _isFinished;
	private JButton _playAgainButton;

	
	public Puzzle (Board board)
	{
		super("Sliding Puzzle");
		_board = board;
		_boardDS = _board.getBoardDS();
		_boardDimension = _board.getDimension();
		_boardsStack = new Stack();
		_boardsStack.push(_board);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addComponents();
		
		resetTimer();
		_timer = new Timer(1000,this);
		_timer.start();
		
		setResizable(false);
		pack();
		setVisible(true);		
	}

	private void addComponents()
	{
	//	GridBagConstraints cell = new GridBagConstraints();
		//cell.fill = GridBagConstraints.CENTER;
		
		// set icons
		_stopIcon = new ImageIcon("stopIcon.png"); 
		_startIcon = new ImageIcon("startIcon.png"); 
		_undoIcon = new ImageIcon("undoIcon.png"); 
		_changeImageIcon = new ImageIcon("changeImageIcon.png"); 
		_menuIcon = new ImageIcon("menuIcon.png"); 
		
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
		
		_playAgainButton = new JButton("Play again");
		_playAgainButton.setIcon(_playAgainIcon);
		
		// add items to toolbar
		_controlsToolbar = new JToolBar();
		_controlsToolbar.add(_stopStartButton);
		_controlsToolbar.add(_undoButton);
		_controlsToolbar.add(_changeImageButton);
		_controlsToolbar.add(_menuButton);
		_controlsToolbar.setFloatable(false);
//        _controlsToolbar.setRollover(true);

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
		_movesCounterLabel.setText(" Total moves: "+_movesCounter);
		
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
	
	public static void figurePressed(Figure fig)
	{
		if (fig.getDimension() == _boardDimension)
			_lastPressed = fig;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == _stopStartButton)
		{
			changePauseStartButton();
			resetTimer();
		}
		if (e.getSource() == _timer && !_isStopped)
		{
			updateTimerLabel();
		}
		if (e.getSource() == _menuButton)
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

	private void backToMenu() 
	{
		dispose();
		//setVisible(false);
		
	}

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

	private void undo()
	{
		_currentBoard = (int[][])_boardsStack.pop();
		_board = new Board(_currentBoard, _boardDS);
	}

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
		
	}
	private void resetTimer()
	{
		if (_isStopped == false)
		{
			_seconds = 0;
			_minutes = 0;
			_hours = 0;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) 
	    { 
	        case KeyEvent.VK_UP:
	            if (!_isStopped && _board.moveByKey("UP"))
	            	updateBoardStack(); 
	            else
	            	alert("paused");
	            break;
	        case KeyEvent.VK_DOWN:
	        	if (!_isStopped && _board.moveByKey("DOWN"))
	            	updateBoardStack(); 
	            else
	            	alert("paused");
	            break;
	        case KeyEvent.VK_LEFT:
	        	if (!_isStopped && _board.moveByKey("LEFT"))
	            	updateBoardStack(); 
	            else
	            	alert("paused");
	            break;
	        case KeyEvent.VK_RIGHT :
	        	if (!_isStopped && _board.moveByKey("RIGHT"))
	            	updateBoardStack(); 
	            else
	            	alert("paused");
	            break;
	        case KeyEvent.VK_SPACE :
	        	_isStopped = !_isStopped;
	        	break;
	        case KeyEvent.VK_Z:
	        	if (!_isStopped && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0)   //ctrl+z
	        		undo();
	            else
	            	alert("paused");
	     }
	} 

	private void alert(String alert) 
	{
		if (alert.equals("paused"))
	        JOptionPane.showMessageDialog(null, "Can not make moves while game is paused,"
					+ '\n' +  "please press play first and then make moves ", 
					"Game is paused", JOptionPane.CANCEL_OPTION);
		if (alert.equals("finish"))
	        JOptionPane.showMessageDialog(null, "GOOD JOB!","Game is over", JOptionPane.CLOSED_OPTION);
	}

	public void updateBoardStack() 
	{
		_movesCounter++;
		_currentBoard = _board.getBoard();
		_boardsStack.push(_currentBoard);
		if (_board.isGameOver())
			finishGame();
	}
	
	private void finishGame()
	{
		alert("finish");
		
		
		
		add((JPanel)_board, BorderLayout.CENTER);

		
	}
	
	public static void main(String args[])
	{
//		BufferedImage x = new BufferedImage(100,100,4);
//		Puzzle p = new Puzzle(new Board(3, x));
	}

	@Override
	public void propertyChange(PropertyChangeEvent prop)
	{
		if (prop.getSource().equals(_lastPressed))
		{
			if (_board.move(_lastPressed))
				updateBoardStack();	
		}
		else if (prop.getSource().equals(_seconds))
			_secondsLabel.setText(String.format("%02d", _seconds));
		else if (prop.getSource().equals(_minutes))
			_minutesLabel.setText(String.format("%02d", _minutes) + ":");
		else if (prop.getSource().equals(_hours))
			_hoursLabel.setText(String.format("%02d", _hours) + ":");		
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
