package Board;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Figure extends JButton implements ActionListener
{
	private final int DIMENSION;
	private final int SOLUTION_X_POSITION;
	private final int SOLUTION_Y_POSITION;
	private int X_POSITION;
	private int Y_POSITION;
	
	private boolean IS_IN_SOLUTION;
	
	public Figure(int solX, int xolY, int x, int y,int dim, ImageIcon figure) 
	{
		SOLUTION_X_POSITION=solX;
		SOLUTION_Y_POSITION=xolY;
		X_POSITION = x;
		Y_POSITION = y;
		DIMENSION = dim;
		
		checkPosition();
		
		this.setIcon(figure);
		this.addActionListener(this);				//JButton uses this implemented action performed
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		move();
	}
	
	private void move()
	{
		Cell tCell = Board.currentEmpty;
		int tCellY = tCell.getY();
		int tCellX = tCell.getX();
		if (legalRow(Y_POSITION+1) && Y_POSITION == tCellY)		//check if up is empty
		{
			
		}
		else if (legalRow(Y_POSITION-1) && Y_POSITION == tCellY)		//check if down is empty
		{
			
		}
		else if (legalColumn(X_POSITION-1) && Y_POSITION == tCellX)		//check if left is empty
		{
			
		}
		else if (legalColumn(X_POSITION+1) && Y_POSITION == tCellX)		//check if right is empty
		{
			
		}
	}
	public void move(String side)
	{}
	private boolean legalRow(int y)
	{
		return y+Y_POSITION < DIMENSION && y+Y_POSITION >= DIMENSION;
	}
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
	/**
	 * gets the status of the figure
	 * @return true if in soultion position
	 */
	public boolean getStatus()
	{
		return IS_IN_SOLUTION;
	}
}
