package Board;
 
import javax.swing.*;
import Game.Puzzle;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class Figure extends JButton implements ActionListener
{

	private final int _solCell;
	private int _currentCell;
	
	public Figure(int index ,ImageIcon figure) 
	{
		_currentCell = index;
		_solCell = index;
		this.setBorderPainted(false);
		//this.setMargin(new Insets(0, 0, 0, 0));
		this.setIcon(figure);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Puzzle.figurePressed(this);				//whenever the figure is pressed, puzzle is getting an update
	}
	
	/**
	 * changes the current figure position
	 */
	public void setCurrentIndex(int index)
	{
		_currentCell = index;
	}
	
	// -------------------------- GETTERS -------------------------- //
	
	public int getCurrentIndex()
	{
		return _currentCell;
	}
}