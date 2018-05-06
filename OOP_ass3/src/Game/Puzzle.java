package Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Board.*;
import java.awt.image.BufferedImage;

public class Puzzle implements ActionListener, KeyListener
{

	private JFrame _puzzle;
	
	
	private Stack _boardsStack;
	private GridLayout _puzzleWindowGrid;
	private int _dimension;
	
	private Container _container;
	
	// --- HEADER TOOLBAR ---
	private JToolBar _toolbar;
	private JButton _pauseStartButton;
	private JButton _undoButton;
	private JButton _changeImageButton;
	private JButton _menuButton;  //this.setVisible(false)
	private BufferedImage _pauseIcon, _startIcon, _undoIcon, _changeImageIcon, _menuIcon;
	
	// --- PUZZLE SCREEN ---
	private JPanel _puzzleBoard;
	private Board _board;
	
	public static Figure EMPTY_FIGURE;
	
	// --- FOOTER INFO ---
	private JLabel _timeLabel;
	private JLabel _movesLabel;
	private int _movesCounter;

	
	
	
	public Puzzle (int dimension)
	{
		_board = new Board(dimension);
		_puzzle = new JFrame("Sliding Puzzle");
		_puzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_container = new Container();
		addComponents();
		_puzzleWindowGrid = new GridLayout (dimension+2,dimension);
		_puzzle.pack();
		_puzzle.setResizable(false);
		_puzzle.setVisible(true);		
	}

	private void addComponents()
	{
		//		_puzzle.setLayout(new GridBagLayout());


		GridBagConstraints cell = new GridBagConstraints();
		cell.fill = GridBagConstraints.CENTER;

		// initialize toolbar items
		_pauseStartButton = new JButton();
		_pauseStartButton.setName("pause");
		_pauseStartButton.addActionListener(l);
		
		_undo = new JButton();
		_undo.setName("undo");
		
		_changeImage = new JButton();
		_changeImage.setName("change");
		
		_menu = new JButton();
		_menu.setName("menu");
		
		// add items to toolbar
		_toolbar = new JToolBar();
		_toolbar.add(_pauseStartButton);
		_toolbar.add(_undo);
		_toolbar.add(_changeImage);
		_toolbar.add(_menu);
		
		//initialize puzzle board
		_puzzleBoard = new JPanel();
		_puzzleBoard.setOpaque(true);
		_puzzleBoard.setBackground(Color.BLACK);
		
		
		
		
		
		// add items to container
		_container.add(_toolbar, BorderLayout.NORTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void updateBoard() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	
	
	
	
	
	/*
	JPanel controlsToolBar = new JPanel();
	cell.gridx = 0;
	cell.gridy = 0;
	controlsToolBar.add(new JButton ("Undo"),cell);

	cell.gridx = 1;
	cell.gridy = 0;
	controlsToolBar.add(new JButton ("Change Puzzle"),cell);

	cell.gridx = 2;
	cell.gridy = 0;
	controlsToolBar.add(new JButton ("Menu"),cell);

	cell.gridx = 0;
	cell.gridy = 0;
	cell.gridwidth = _dimension;
//	pane.add(controlsToolBar,cell);

	// CENTER --- FIGURES
	cell.gridy = 1;
	cell.gridheight = _dimension;
//	Component tBoard = (Component) _board;
	//pane.add(_board,cell);

	// FOOTER --- INFORMATION

	cell.gridx = 0;
	cell.gridy = _dimension+1;
	cell.gridheight = 1;
	cell.gridwidth = 1;
	//TIMER
	//		pane.add(XXX,cell);

	cell.gridx = 0;
	cell.gridy = _dimension+1;
	cell.gridheight = 1;
	cell.gridwidth = 1;
	//MOVES
	//		pane.add(XXX,cell);
	 */


}
