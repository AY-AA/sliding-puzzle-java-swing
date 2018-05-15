//package Stack;
//
//public class Stack 
//{
//	BoardNode _head;
//	public Stack() {}
//
//	public void push (int[] board)
//	{
//		BoardNode newHead = new BoardNode (board);
//		if (isEmpty())
//			_head = newHead;
//		else
//		{
//			newHead.setNext(_head);
//			_head = newHead;
//		}
//	}
//	
//	public int[] pop()
//	{
//
//		int[] ans = _head.getBoard();
//		_head = _head.getNext();
//		return ans;
//
//	}
//
//	public boolean isEmpty()
//	{
//		if (_head == null)
//			return true;
//		else return false;
//	}
//	public void clear() 
//	{
//		_head = null;
//	}
//
//}
