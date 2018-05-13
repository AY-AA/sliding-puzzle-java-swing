
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Board.Cell;
import Board.Figure;
import Game.Puzzle;

public class Board1 extends JPanel{

	private ArrayList<Figure> boardDS; //Data Structure to hold the board.
	private int[] positions;
	public final int dimension;
	private int x, y;
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
	public Board1(int dimension, BufferedImage puzzle){
		this.setPreferredSize(new Dimension(410, 0));
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		this.setBackground(Color.BLACK);
		this.dimension = dimension;
		boardDS = new ArrayList<Figure>();
		positions = new int[n];
		x = 0;
		y = 0;
		figureWidth = puzzle.getWidth()/dimension; //size of each button
		figureHeight = puzzle.getHeight()/dimension;
		initBoard(puzzle);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		isGameOver = false;
		n = dimension*dimension;	
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
			for(int j=1; j < n-1; j++){
				boardDS.add(new Figure(dimension, j, j, new ImageIcon(puzzle.getSubimage(x, y, figureWidth, figureHeight))));
				x += figureWidth;
				if(j % dimension == 1)
				{
					y += figureHeight;
				}
			}
			
		boardShuffle();
		remover();
	}
	
	/**
	 * Shuffling the board itself and adding it to the JPanel
	 */
	public void boardShuffle(){

		Random randomGenerator = new Random();
		ArrayList<Figure> hardCopy = new ArrayList<Figure>(boardDS);
		
		for(int i = 0; i < n; i++){
				int randomIndex = randomGenerator.nextInt(boardDS.size());
				positions[i] = boardDS.get(randomIndex).getCurrentIndex();
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
				Figure tmp = boardDS.get(currPos);
				this.add(tmp);
		}
		//Puzzle.getContainer().validate();
	}
	
	public void remover(){
		this.removeAll();
		updateBoard();
	}
	
	/**
	 * moving figure on the board if the move is legal
	 * @param movingFigure
	 * @return
	 */
	public boolean move(Figure movingFigure) {
		
		try{ 
			if(positions[x][y+1] == 0){ // if up is empty
				board[x][y + 1] = new Cell(x, y + 1, currPlace - dimension, movingFigure);
				board[x][y] = null;
				movingFigure.setY(y + 1);
				removeAll();
				updateBoard();
				CheckAnswer();
				//Puzzle.add();
				return true;
			}
		}catch(ArrayIndexOutOfBoundsException e){

		}
		try{
			if(board[x + 1][y].getFigure() == null){ //if right is empty
				board[x + 1][y] = new Cell(x + 1, y, currPlace + 1, movingFigure);
				board[x][y] = null;
				movingFigure.setX(x + 1);
				removeAll();
				updateBoard();
				CheckAnswer();
				//Puzzle.add();
				return true;

			}
		}catch(ArrayIndexOutOfBoundsException e){

		}
		try{
			if(board[x - 1][y].getFigure() == null){ // if left is empty
				board[x - 1][y] = new Cell(x - 1, y, currPlace - 1, movingFigure);
				board[x][y] = null;
				movingFigure.setX(x - 1);
				removeAll();
				updateBoard();
				CheckAnswer();
				//Puzzle.add();
				return true;
			}
		}catch(ArrayIndexOutOfBoundsException e){

		}
		try{
			if(board[x][y - 1].getFigure() == null){ // if down is empty
				board[x + 1][y - 1] = new Cell(x, y - 1, currPlace + dimension, movingFigure);
				board[x][y] = null;
				movingFigure.setY(y - 1);
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
	public boolean moveByKey(String string) {

		if(string.equals("UP")) {

		}
		else if(string.equals("DOWN")) {

		}
		else if(string.equals("LEFT")) {

		}
		else { // if right

		}
		return false;
	}
	
//	public Board duplicateBoard() {
//		return null;
//	}
	//public static void main
}


