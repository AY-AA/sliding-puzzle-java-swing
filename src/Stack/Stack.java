package Stack;

public class Stack 
{
	BoardNode _head;
	public Stack() 
	{
	}
	private void insertFirst(int[] head)
	{
		_head = new BoardNode(head);
	}
	
	public void push (int[] board)
	{
		BoardNode newHead = new BoardNode (board);
		if (isEmpty())
			insertFirst (board);
		else
		{
			newHead.setNext(_head);
			_head = newHead;
		}
	}
	
	public int[] pop()
	{
		if(!isEmpty())
			return _head.getBoard();
		return null;
	}
//	public boolean sizeOne()
//	{
//		if (_head.getNext() == null)
//		{
//			return true;
//		}
//		return false;
//			
//	}
	public boolean isEmpty()
	{
		if (_head == null)
			return true;
		else return false;
	}
	public void clear() 
	{
		_head = null;
	}

}
