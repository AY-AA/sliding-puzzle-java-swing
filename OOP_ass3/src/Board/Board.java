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
	private int counter;
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
		figureWidth = puzzle.getWidth()/dimension;
		figureHeight = puzzle.getHeight()/dimension;
		counter = 0;
		initBoard(puzzle);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	}
	/**
	 * Initiating the board data structure in order to create from it the board it self
	 * @param puzzle
	 */
	private void initBoard(BufferedImage puzzle) {
		for(int i=0; i<dimension; i++){
			for(int j=0; j<dimension; j++){
				counter++;
				boardDS.add(new Cell(i, j, new Figure(i, j, i, j, dimension, counter, new ImageIcon(puzzle.getSubimage(x, y, figureWidth, figureHeight)))));	
				x += figureWidth;
			}
			x = 0;
			y += figureHeight;
		}
		boardShuffle();
		remover();

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
				board[i][j] = new Cell(i, j, boardDS.get(randomIndex).getFigure());
				boardDS.remove(randomIndex);

			}
		}
		boardDS = hardCopy;
		remover();

	}
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
	
	public void moveByKey(String string) {
		
		if(string.equals("UP")) {

		}
		else if(string.equals("DOWN")) {

		}
		else if(string.equals("LEFT")) {

		}
		else { // if right
			
		}
	}
}
