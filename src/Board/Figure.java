package Board;
 
import javax.swing.*;

public class Figure extends JButton
{

	private final int _solCell;
	private int _currentCell;
	
	public Figure(int index ,ImageIcon figure) 
	{
		_currentCell = index;
		_solCell = index;
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