package Board;
public class Cell {
	
	private final int placeInBoard;
	private Figure figure;
	
	public Cell(int placeInBoard, Figure figure){
		this.placeInBoard = placeInBoard;
		this.figure = figure;
	}
	public Cell(int placeInBoard){
		this.placeInBoard = placeInBoard;
		figure = null;
	}
	public Figure getFigure(){
		return figure;
	}
	public void setFigure(Figure figure){
		this.figure = figure;
	}
	public int getPlaec() {
		return placeInBoard;
	}
	
}
