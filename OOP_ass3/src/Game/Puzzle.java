package Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import Board.*;

public class Puzzle extends JPanel implements ActionListener, KeyListener
{

	public static Figure EMPTY_FIGURE;
	Board _board;
	
	public Puzzle (int dimension)
	{
		_board = new Board(dimension);
		JFrame puzzle = new JFrame("Sliding Puzzle");
		puzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InitatePuzzle(puzzle.getContentPane());
		puzzle.pack();
		puzzle.setVisible(true);
		
		
		//super(new GridLayout(dimension,dimension+2));
		
		// setResizable(false);
	}
	
	private static void InitatePuzzle(Container puzzle)
	{
		if (!(puzzle.getLayout() instanceof BorderLayout))
		{
			return;
		}
		
		
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
	
	
	
}
