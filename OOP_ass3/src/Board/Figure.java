package Board;
import javax.swing.*;
import Game.Puzzle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Figure extends JButton implements ActionListener
{
	private final int DIMENSION;
	private final int SOLUTION_X_POSITION;
	private final int SOLUTION_Y_POSITION;
	
	private int X_POSITION;
	private int Y_POSITION;
	
	private final int CELL_NUMBER;
	private boolean IS_IN_SOLUTION;
	
	public Figure(int solX, int xolY, int x, int y,int dim, int cell, ImageIcon figure) 
	{
		SOLUTION_X_POSITION=solX;
		SOLUTION_Y_POSITION=xolY;
		X_POSITION = x;
		Y_POSITION = y;
		DIMENSION = dim;
		CELL_NUMBER = cell;
		
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
		Figure tFigure = Puzzle.currentEmpty;
		int tCellY = tFigure.getY();
		int tCellX = tFigure.getX();
		if (legalRow(Y_POSITION+1) && Y_POSITION == tCellY)				//check if up is empty
		{
			switchFigures(tFigure);
		}
		else if (legalRow(Y_POSITION-1) && Y_POSITION == tCellY)		//check if down is empty
		{
			switchFigures(tFigure);
		}
		else if (legalColumn(X_POSITION-1) && Y_POSITION == tCellX)		//check if left is empty
		{
			switchFigures(tFigure);
		}
		else if (legalColumn(X_POSITION+1) && Y_POSITION == tCellX)		//check if right is empty
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
		tFigure.setX(X_POSITION);
		tFigure.setY(Y_POSITION);
		X_POSITION = tX;
		Y_POSITION = tY;		
	}
	/**
	 * checks if figure can be moved to the next or previous row by user's selection
	 * @param x is the move. 1 is down, -1 is up.
	 * @return true if the move is legal, false either 
	 */
	private boolean legalRow(int y)
	{
		return y+Y_POSITION < DIMENSION && y+Y_POSITION >= DIMENSION;
	}
	/**
	 * checks if figure can be moved to the next or previous column by user's selection
	 * @param x is the move. 1 is right, -1 is left.
	 * @return true if the move is legal, false either 
	 */
	private boolean legalColumn(int x)
	{
		return x+X_POSITION < DIMENSION && x+X_POSITION >= DIMENSION;
	}
	/**
	 * checks if figure is in its solution's cell
	 */
	private void checkPosition()
	{
		boolean xPosition = SOLUTION_X_POSITION == X_POSITION;
		boolean yPosition = SOLUTION_Y_POSITION == Y_POSITION;

		IS_IN_SOLUTION = xPosition && yPosition;
	}

	
	// -------------------------- GETTERS -------------------------- //
	
	/**
	 * gets the status of the figure
	 * @return true if in solution position
	 */
	public boolean getStatus()
	{
		return IS_IN_SOLUTION;
	}
	@Override
	public int getX()
	{
		return X_POSITION;
	}
	@Override
	public int getY()
	{
		return Y_POSITION;
	}

	public int getCellNumber()
	{
		return CELL_NUMBER;
	}
	
	// -------------------------- SETTERS -------------------------- //

	/**
	 * sets the x position of the figure
	 */
	public void setX(int x)
	{
		X_POSITION = x;
	}	
	/**
	 * sets the y position of the figure
	 */
	public void setY(int y)
	{
		Y_POSITION = y;
	}
}
