package Board;
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

import Game.Puzzle;

public class Board extends JPanel{

	public Cell[][] board;

	private ArrayList<Cell> boardDS = new ArrayList<Cell>(); //Data Structure to hold the board.
	public final int dimension;
	private int x, y;
	private final int figureWidth, figureHeight;
	private JLabel label;
	private int place;
	private boolean isDone;

	/**
	 * Constructor, receiving the image of the puzzle and the dimension of the board
	 * @param dimension
	 * @param puzzle
	 */
	public Board(int dimension, BufferedImage puzzle){
		this.setPreferredSize(new Dimension(410, 0));
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		this.setBackground(Color.BLACK);
		this.dimension = dimension;
		board = new Cell[dimension][dimension];
		x = 0;
		y = 0;
		figureWidth = puzzle.getWidth()/dimension; //size of each button
		figureHeight = puzzle.getHeight()/dimension;
		place = 0;
		initBoard(puzzle);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		isDone = false;
	}
	
	//-------------------------- Getters and Setters
	public boolean isDone() {
		return this.isDone;
	}
	public int getDimension() {
		return this.dimension;
	}
	//--------------------------
	
	/**
	 * Initiating the board data structure in order to create from it the board it self
	 * @param puzzle
	 */
	private void initBoard(BufferedImage puzzle) {
		for(int i=0;  i< dimension; i++){
			for(int j=0; j < dimension; j++){
				place++;
				boardDS.add(new Cell(i, j, place, new Figure(i, j, i, j, dimension, place, new ImageIcon(puzzle.getSubimage(x, y, figureWidth, figureHeight)))));	
				x += figureWidth;
			}
			x = 0;
			y += figureHeight;
		}
		boardShuffle();
		remover();
		place = 0;
	}
	
	/**
	 * Shuffling the board itself and adding it to the JPanel
	 */
	public void boardShuffle(){

		Random randomGenerator = new Random();
		ArrayList<Cell> hardCopy = new ArrayList<Cell>(boardDS);

		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){	
				int randomIndex = randomGenerator.nextInt(boardDS.size());
				boardDS.get(randomIndex).getFigure().setX(i);
				boardDS.get(randomIndex).getFigure().setY(j);
				boardDS.get(randomIndex).getFigure().setCellNumber(place);
				board[i][j] = new Cell(i, j, place, boardDS.get(randomIndex).getFigure());
				boardDS.remove(randomIndex);
				place++;
			}
		}
		boardDS = hardCopy;
		remover();
	}
	
	/**
	 * Updating the board each move by user
	 */
	public void updateBoard(){

		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){	
				if(board[i][j].getFigure() == null){
					label = new JLabel();
					label.setPreferredSize(new Dimension(figureWidth, figureHeight));
					this.add(label);
					continue;
				}
				this.add(board[i][j].getFigure());
			}
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
		int x = movingFigure.getX();
		int y = movingFigure.getY();
		int currPlace = movingFigure.getCellNumber();
		try{ 
			if(board[x][y + 1].getFigure() == null){ // if up is empty
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
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){	
				if(board[i][j].getFigure().getCellNumber() != board[i][j].getPlaec()) {
					isDone = false;
					return;
				}
			}
		}
		isDone = true;
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
	
	public Board duplicateBoard() {
		return null;
	}
	public static void main
}


