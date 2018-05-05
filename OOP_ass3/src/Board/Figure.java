package Board;
import javax.swing.*;
import Game.Puzzle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Figure extends JButton implements ActionListener
{
	private final int _dimension;
	private final int _xSolution;
	private final int _ySolution;
	
	private int _xPosition;
	private int _yPosition;
	
	private final int _cellNumber;
	private boolean _inSolution;
	
	public Figure(int solX, int xolY, int x, int y,int dim, int cell, ImageIcon figure) 
	{
		_xSolution=solX;
		_ySolution=xolY;
		_xPosition = x;
		_yPosition = y;
		_dimension = dim;
		_cellNumber = cell;
		
		checkPosition();
		
		this.setIcon(figure);
		this.addActionListener(this);				//JButton uses this implemented action performed
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		move();
	}
	/**
	 * 
	 */
	private void move()
	{
		Figure tFigure = Puzzle.EMPTY_FIGURE;
		int tCellY = tFigure.getY();
		int tCellX = tFigure.getX();
		if (legalRow(_yPosition+1) && _yPosition == tCellY)				//check if up is empty
		{
			switchFigures(tFigure);
		}
		else if (legalRow(_yPosition-1) && _yPosition == tCellY)		//check if down is empty
		{
			switchFigures(tFigure);
		}
		else if (legalColumn(_xPosition-1) && _yPosition == tCellX)		//check if left is empty
		{
			switchFigures(tFigure);
		}
		else if (legalColumn(_xPosition+1) && _yPosition == tCellX)		//check if right is empty
		{
			switchFigures(tFigure);
		}
	}
	/**
	 * switches positions between clicked or pressed figure and epmty figure 
	 * @param tFigure is the empty figure
	 */
	public void switchFigures(Figure tFigure)
	{
		int tX = tFigure.getX();
		int tY = tFigure.getY();
		tFigure.setX(_xPosition);
		tFigure.setY(_yPosition);
		_xPosition = tX;
		_yPosition = tY;		
	}
	/**
	 * checks if figure can be moved to the next or previous row by user's selection
	 * @param x is the move. 1 is down, -1 is up.
	 * @return true if the move is legal, false either 
	 */
	private boolean legalRow(int y)
	{
		return y+_yPosition < _dimension && y+_yPosition >= _dimension;
	}
	/**
	 * checks if figure can be moved to the next or previous column by user's selection
	 * @param x is the move. 1 is right, -1 is left.
	 * @return true if the move is legal, false either 
	 */
	private boolean legalColumn(int x)
	{
		return x+_xPosition < _dimension && x+_xPosition >= _dimension;
	}
	/**
	 * checks if figure is in its solution's cell
	 */
	private void checkPosition()
	{
		boolean xPosition = _xSolution == _xPosition;
		boolean yPosition = _ySolution == _yPosition;

		_inSolution = xPosition && yPosition;
	}

	
	// -------------------------- GETTERS -------------------------- //
	
	/**
	 * gets the status of the figure
	 * @return true if in solution position
	 */
	public boolean getStatus()
	{
		return _inSolution;
	}
	@Override
	public int getX()
	{
		return _xPosition;
	}
	@Override
	public int getY()
	{
		return _yPosition;
	}

	public int getCellNumber()
	{
		return _cellNumber;
	}
	
	// -------------------------- SETTERS -------------------------- //

	/**
	 * sets the x position of the figure
	 */
	public void setX(int x)
	{
		_xPosition = x;
	}	
	/**
	 * sets the y position of the figure
	 */
	public void setY(int y)
	{
		_yPosition = y;
	}
}
