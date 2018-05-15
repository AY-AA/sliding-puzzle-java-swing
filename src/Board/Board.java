package Board;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import Stack.*;

public class Board {
	
	private final int _dimension;
	private int[] _positions;
	private final int _totalFigures;
	private Stack _boardsStack;	
	
	public Board(int puzzleSize) 
	{
		_dimension = puzzleSize;
		_totalFigures = _dimension*_dimension;
		_boardsStack = new Stack();
	}
	/**
	 * duplicates positions board
	 * @return a duplicated board
	 */
	private int[] duplicateBoard() 
	{
		int tN = _totalFigures;
		int[] tBoard = new int [tN];
		for (int i=0; i<tN ; i++)
			tBoard[i] = _positions[i];
		return tBoard;
	}
	/**
	 * Shuffling the board itself and adding it to the JPanel
	 */
	public ArrayList<Figure> boardShuffle(ArrayList<Figure> boardFigures)
	{
		Random randomGenerator = new Random();
		ArrayList<Figure> hardCopy = new ArrayList<Figure>(boardFigures);

		for (int i = 0; i < _totalFigures; i++) {
			int randomIndex = randomGenerator.nextInt(boardFigures.size());
			Figure tmpFig = boardFigures.get(randomIndex);
			if (tmpFig == null) 
			{
				_positions[i] = 0;
			} 
			else 
			{
				_positions[i] = boardFigures.get(randomIndex).getCurrentIndex();
				tmpFig.setCurrentIndex(i + 1);
			}
			boardFigures.remove(randomIndex);
		}
		return hardCopy;
	}
	/**
	 * switch indexes of given figure's indexes
	 * @param a is an index of figure
	 * @param b is an index of figure
	 */
	public void switchFig(int a, int b) 
	{
		pushToStack();
		int temp = _positions[a];
		_positions[a] = _positions[b];
		_positions[b] = temp;
	}
	/**
	 * finds the index which holds the empty figure
	 * @return
	 */
	public int findZero() {
		for (int i = 0; i < _totalFigures; i++)
			if (_positions[i] == 0)
				return i;
		return 0;
	}
	/**
	 * returns to the previous board
	 * @param figures is the figures array
	 * @return true whether undo move is done, else returns false 
	 */
	public boolean undoMove(ArrayList<Figure> figures) {
		if (_boardsStack.isEmpty())
			return false;
		_positions = (int[]) _boardsStack.pop();
		for (int i = 0; i < _totalFigures; i++) {
			int x = _positions[i];
			if (x != 0)
			{
				Figure tempFig = figures.get(x-1);
				tempFig.setCurrentIndex(i + 1);
			}
		}
		return true;
	}
	/**
	 * checks if the game is done
	 */
	public boolean checkAnswer() {
		for (int i = 0; i < _totalFigures - 1; i++) {
			if (_positions[i] != i + 1) {
				return false;
			}
		}
		return true;
	}
	/**
	 * clears the boards stack
	 */
	public void clearStack() {
		_boardsStack.clear();
		_boardsStack.push(_positions);
	}
	/**
	 * pushes the current board onto the stack
	 */
	public void pushToStack() {
		_boardsStack.push(duplicateBoard());
		
	}
	/**
	 * creates a new game
	 * @param bFigures
	 * @return
	 */
	public ArrayList<Figure> play(ArrayList<Figure> bFigures)
	{
		_positions = new int[_totalFigures];
		return boardShuffle(bFigures);
	}

	// --- GETTERS ---
	/**
	 * returns the number of total figures needed to create the board
	 * @return
	 */
	public int getTotalFigures()
	{
		return _totalFigures;
	}
	/**
	 * return the board's dimension
	 * @return
	 */
	public int getDimension()
	{
		return _dimension;
	}
	/**
	 * return the value of index i in positions array
	 * @param i
	 * @return
	 */
	public int get(int i) {
		return _positions[i];
	}


}


