package Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Board.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.*;



public class Puzzle extends JFrame implements ActionListener, KeyListener
{
	// --- TIMER ---
	private Timer _timer;
	private boolean _isStopped;
	private int _seconds = 0;
	private int _minutes = 0;
	private int _hours = 0;	
	
	private Stack _boardsStack;
		
	// --- HEADER TOOLBAR ---
	private JToolBar _controlsToolbar;
	private JButton _stopStartButton;
	private JButton _undoButton;
	private JButton _changeImageButton;
	private JButton _menuButton;  //this.setVisible(false)
	private ImageIcon _stopIcon, _startIcon, _undoIcon, _changeImageIcon, _menuIcon;
	
	// --- PUZZLE SCREEN ---
	private JPanel _board;
	
	public static Figure EMPTY_FIGURE;
	
	// --- FOOTER INFO ---
	private JLabel _secondsLabel;
	private JLabel _minutesLabel;
	private JLabel _hoursLabel;
	private JLabel _movesCounterLabel;
	private int _movesCounter;

	private JToolBar _infoToolbar;
	
	public Puzzle (JPanel board)
	{
		super("Sliding Puzzle");
		_board = board;
		_boardsStack = new Stack();
		_boardsStack.push(_board);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addComponents();
		
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
		add(_board, BorderLayout.CENTER);
		add(_infoToolbar, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == _stopStartButton)
		{
			changePauseStartButton();
			resetTimer();
		}
		if (e.getSource() == _timer && _isStopped == false)
		{
			updateTimerLabel();
		}
		if (e.getSource() == _menuButton)
		{
			backToMenu();
		}
		else if (e.getSource() == _changeImageButton)
		{
//			backToChooseImage();
		}
		else if (e.getSource() == _undoButton)
		{
			undo();
		}
	}

	private void backToMenu() 
	{
		// TODO Auto-generated method stub
		setVisible(false);
		
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
		_board = (JPanel) _boardsStack.pop();
		updateBoard();
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
		_hoursLabel.setText(String.format("%02d", _hours) + ":");
		_minutesLabel.setText(String.format("%02d", _minutes) + ":");
		_secondsLabel.setText(String.format("%02d", _seconds));		
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
	            ((Board)_board).moveByKey("up");
	            break;
	        case KeyEvent.VK_DOWN:
	        	((Board)_board).moveByKey("down");
	            break;
	        case KeyEvent.VK_LEFT:
	        	((Board)_board).moveByKey("left");
	            break;
	        case KeyEvent.VK_RIGHT :
	        	((Board)_board).moveByKey("right");
	            break;
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

	public void updateBoard() 
	{
		_movesCounter++;
		_boardsStack.push(_board);
		
	}
	
	public static void main(String args[])
	{
		Puzzle p = new Puzzle(new JPanel());
	}
}
