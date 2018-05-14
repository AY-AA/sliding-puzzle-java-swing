package Stack;

public class BoardNode {
	
	private int[] _board;
	private BoardNode _next;
	
	public BoardNode(int[] board)
	{
		_board = board;
	}
	
	public void setNext(BoardNode bNext)
	{
		_next = bNext;
	}
	
	public BoardNode getNext()
	{
		if (_next != null)
			return _next;
		else return null;
	}
	
	public int[] getBoard()
	{
		return _board;
	}
}
