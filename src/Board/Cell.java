package Board;
public class Cell {
	
	private final int x, y, placeInBoard;
	private Figure figure;
	
	public Cell(int x, int y,int placeInBoard, Figure figure){
		this.x = x;
		this.y = y;
		this.placeInBoard = placeInBoard;
		this.figure = figure;
	}
	public Cell(int x, int y, int placeInBoard){
		this.x = x;
		this.y = y;
		this.placeInBoard = placeInBoard;
		figure = null;
	}
	public Figure getFigure(){
		return figure;
	}
	public void setFigure(Figure figure){
		this.figure = figure;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getPlaec() {
		return placeInBoard;
	}
	
}
