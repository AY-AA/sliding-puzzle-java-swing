package Board;
 
import javax.swing.*;

public class Figure extends JButton
{

	private final int _SOL_CELL;
	private int _currentCell;
	
	public Figure(int index ,ImageIcon figure) 
	{
		_currentCell = index;
		_SOL_CELL = index;
		this.setBorderPainted(false);
		this.setIcon(figure);
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