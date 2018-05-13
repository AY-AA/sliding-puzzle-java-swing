package Board;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Board.Cell;
import Board.Figure;
import Game.Puzzle;
import ImageHandler.ImageLoader;
import ImageHandler.ImageResizer;
//the new Board
public class Board extends JPanel{

	private ArrayList<Figure> boardDS; //Data Structure to hold the board.
	private int[] positions;
	public final int dimension;
	private final int figureWidth, figureHeight;
	private JLabel label;
	private int place;
	private boolean isGameOver;
	private int n;

	/**
	 * Constructor, receiving the image of the puzzle and the dimension of the board
	 * @param dimension
	 * @param puzzle
	 */
	public Board(int dimension, BufferedImage puzzle){
		setPreferredSize(new Dimension(410, 410));
		//setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		//setBackground(Color.BLACK);
		this.dimension = dimension;
		n = this.dimension*this.dimension;	
		boardDS = new ArrayList<Figure>();
		positions = new int[n];
		figureWidth = puzzle.getWidth()/dimension; //size of each button
		figureHeight = puzzle.getHeight()/dimension;
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		initBoard(puzzle);
		isGameOver = false;
		setVisible(true);
	}

	//-------------------------- Getters and Setters
	public boolean isGameOver() {
		return this.isGameOver;
	}
	public int getDimension() {
		return this.dimension;
	}
	//--------------------------

	/**
	 * Initiating the board data structure in order to create from it the board itself
	 * @param puzzle
	 */
	private void initBoard(BufferedImage puzzle) {
		int x = 0, y = 0;
		for(int i = 0; i < n-1; i++){
			try {
			boardDS.add(new Figure(i+1, new ImageIcon(puzzle.getSubimage(x, y, figureWidth, figureHeight))));
			}
			catch(Exception e){
				
			}
			if((i+1) % dimension == 0)
			{
				x = 0;
				y += figureHeight;
			}
			else 
			{
				x += figureWidth;
			}
		}
		boardDS.add(null);
		boardShuffle();
	}

	/**
	 * Shuffling the board itself and adding it to the JPanel
	 */
	public void boardShuffle(){

		Random randomGenerator = new Random();
		ArrayList<Figure> hardCopy = new ArrayList<Figure>(boardDS);

		for(int i = 0; i < n; i++){
			int randomIndex = randomGenerator.nextInt(boardDS.size());
			Figure tmpFig = boardDS.get(randomIndex);
			if(tmpFig == null) {
				positions[i] = 0;
			}
			else {
				positions[i] = boardDS.get(randomIndex).getCurrentIndex();
			}
			boardDS.remove(randomIndex);
		}
		boardDS = hardCopy;
		remover();
	}

	/**
	 * Updating the board each move by user
	 */
	public void updateBoard(){
		for(int i = 0; i < n; i++){
			int currPos = positions[i];
			JButton tmp = (JButton)boardDS.get(currPos);
			if(tmp != null) {
			this.add(tmp);
			}
		}
		//Puzzle.getContainer().validate();
	}

	public void remover(){
		this.removeAll();
		updateBoard();
	}

	private void switchFig(int a, int b) {
		int temp = positions[a];
		positions[a] = positions[b];
		positions[b] =temp;
	}

	/**
	 * moving figure on the board if the move is legal
	 * @param movingFigure
	 * @return
	 */
	public boolean move(Figure movingFigure) {
		int toChange;
		try{ 
			if(positions[(toChange = movingFigure.getCurrentIndex() - dimension)] == 0){ // if up is empty
				switchFig(movingFigure.getCurrentIndex(),toChange);
				movingFigure.setCurrentIndex(toChange);
				removeAll();
				updateBoard();
				CheckAnswer();
				//Puzzle.add();
				return true;
			}

			if(positions[(toChange = movingFigure.getCurrentIndex() + 1)] == 0){ // if right is empty
				switchFig(movingFigure.getCurrentIndex(),toChange);
				movingFigure.setCurrentIndex(toChange);
				removeAll();
				updateBoard();
				CheckAnswer();
				//Puzzle.add();
				return true;
			}

			if(positions[(toChange = movingFigure.getCurrentIndex() - 1)] == 0){ // if left is empty
				switchFig(movingFigure.getCurrentIndex(),toChange);
				movingFigure.setCurrentIndex(toChange);
				removeAll();
				updateBoard();
				CheckAnswer();
				//Puzzle.add();
				return true;
			}

			if(positions[(toChange = movingFigure.getCurrentIndex() + dimension)] == 0){ // if down is empty
				switchFig(movingFigure.getCurrentIndex(),toChange);
				movingFigure.setCurrentIndex(toChange);
				removeAll();
				updateBoard();
				CheckAnswer();
				//Puzzle.add();
				return true;
			}
		}catch(ArrayIndexOutOfBoundsException e){

		}
		return false;
	}

	/**
	 * checks if the game is done
	 */
	private void CheckAnswer() {
		for(int i = 0; i < n - 1; i++){
			if(positions[i] != i+1) {
				isGameOver = false;
				return;
			}
		}
		isGameOver = true;
	}

	/**
	 * moving figure using the keyboard keys by user
	 * @param string
	 */
	public boolean moveByKey(int move) {
		int x = findZero();
		move = move + x;
		if (move >= 0 && move < n)
		{
			switchFig(move,x);
			return true;
		}
		return false;
	}

	private int findZero() {
		for (int i = 0 ; i<n-1 ; i++)
			if (positions[i] == 0)
				return i;
		return 0;
	}

	public void undo(int[] arr) {
		positions = arr;
		for(int i = 0; i < n; i++) {
			int x = positions[i];
			Figure tempFig = boardDS.get(x);
			if(tempFig != null) {
				tempFig.setCurrentIndex(i+1);
			}
		}
		updateBoard();
	}
	public int[] getCurrBoard() {
		return positions;
	}

}


