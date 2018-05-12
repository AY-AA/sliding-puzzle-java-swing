package Board;
 
import javax.swing.*;
import Game.Puzzle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Figure extends JButton implements ActionListener
{
	private final int _dimension;
//	private final int _xSolution;		
//	private final int _ySolution;		
//	
//	private int _xPosition;				//current x position
//	private int _yPosition;				//current y position
	
	private final int _cellNumber;
	private boolean _inSolution;
	private int _currentIndex;
	
	public Figure(int dim, int cell, ImageIcon figure, int index) 
	{
//		_xSolution=solX;
//		_ySolution=solY;
//		_xPosition = x;
//		_yPosition = y;
		_currentIndex = index;
		_dimension = dim;
		_cellNumber = cell;
		setIcon(figure);
		addActionListener(this);
//		checkPosition();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Puzzle.figurePressed(this);				//whenever the figure is pressed, puzzle is getting an update
	}
	
	/**
	 * checks if figure is in its solution's cell
	 */

	public void setCurrentIndex(int index)
	{
		_currentIndex = index;
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
	public int getCellNumber()
	{
		return _cellNumber;
	}	
	public int getDimension()
	{
		return _dimension;
	}
	public int getCurrentIndex()
	{
		return _currentIndex;
	}
	
	
	
	
	
	
//	private void checkPosition()
//	{
//		boolean xPosition = _xSolution == _xPosition;
//		boolean yPosition = _ySolution == _yPosition;
//		_inSolution = xPosition && yPosition;
//	}

	
	
//	public int getX()
//	{
//		return _xPosition;
//	}
//	public int getY()
//	{
//		return _yPosition;
//	}
	
	// -------------------------- SETTERS -------------------------- //

//	/**
//	 * sets the x position of the figure
//	 */
//	public void setX(int x)
//	{
//		_xPosition = x;
//	}	
//	/**
//	 * sets the y position of the figure
//	 */
//	public void setY(int y)
//	{
//		_yPosition = y;
//	}
//	public void setCellNumber(int c)
//	{
//		_cellNumber = c;
//	}
	
	
	
	
//	------------ TO REMOVE ---------
	
	 
//	private void move()
//	{
//		Figure tFigure = Puzzle.EMPTY_FIGURE;
//		int tCellY = tFigure.getY();
//		int tCellX = tFigure.getX();
//		if (legalRow(_yPosition+1) && _yPosition == tCellY)				//check if up is empty
//		{
//			switchFigures(tFigure);
//		}
//		else if (legalRow(_yPosition-1) && _yPosition == tCellY)		//check if down is empty
//		{
//			switchFigures(tFigure);
//		}
//		else if (legalColumn(_xPosition-1) && _yPosition == tCellX)		//check if left is empty
//		{
//			switchFigures(tFigure);
//		}
//		else if (legalColumn(_xPosition+1) && _yPosition == tCellX)		//check if right is empty
//		{
//			switchFigures(tFigure);
//		}
//	}
//	/**
//	 * switches positions between clicked or pressed figure and epmty figure 
//	 * @param tFigure is the empty figure
//	 */
//	public void switchFigures(Figure tFigure)
//	{
//		int tX = tFigure.getX();
//		int tY = tFigure.getY();
//		tFigure.setX(_xPosition);
//		tFigure.setY(_yPosition);
//		_xPosition = tX;
//		_yPosition = tY;		
//	}
//	/**
//	 * checks if figure can be moved to the next or previous row by user's selection
//	 * @param x is the move. 1 is down, -1 is up.
//	 * @return true if the move is legal, false either 
//	 */
//	private boolean legalRow(int y)
//	{
//		return y+_yPosition < _dimension && y+_yPosition >= _dimension;
//	}
//	/**
//	 * checks if figure can be moved to the next or previous column by user's selection
//	 * @param x is the move. 1 is right, -1 is left.
//	 * @return true if the move is legal, false either 
//	 */
//	private boolean legalColumn(int x)
//	{
//		return x+_xPosition < _dimension && x+_xPosition >= _dimension;
//	}
}
