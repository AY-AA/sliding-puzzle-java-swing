package Board;
 
import javax.swing.*;
import Game.Puzzle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Figure extends JButton implements ActionListener
{

	private final int _solCell;
	private int _currentIndex;
	
	public Figure(int index ,ImageIcon figure) 
	{
		_currentIndex = index;
		_solCell = index;
		setIcon(figure);
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
		_currentIndex = index;
	}
	
	// -------------------------- GETTERS -------------------------- //
	
	public int getCurrentIndex()
	{
		return _currentIndex;
	}
}