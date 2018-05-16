package IHandler;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FilesHandler {
	private Image _icon;
	private boolean _csvFound,_csvLoaded;
	private ImageIcon[] _startPuzzleImageIconPack, _mainImagePack, _puzzleWindowPack,_chooseWindowIconPack;
	private Image[] _chooseWindowImagePack;
	private BufferedImage _puzzleImage,_sushiImage,_catImage,_cyberImage;
	private ArrayList<int[]> _boardsHolder3,_boardsHolder4,_boardsHolder5;
	private ArrayList<int[]>[] _boardSizes;
	private ImageLoader _imageLoader;
	private ImageResizer _imageResizer;
	
	public FilesHandler() {
		_icon = null;
		_imageResizer = new ImageResizer();
		_imageLoader = new ImageLoader();
		loadImages();
		loadCSV();
	}

	private void loadCSV() {
		_boardSizes = (ArrayList<int[]>[]) new ArrayList[3];
		_boardsHolder3 = new ArrayList<>();
		_boardsHolder4 = new ArrayList<>();
		_boardsHolder5 = new ArrayList<>();
		_boardSizes[0] = _boardsHolder3;
		_boardSizes[1] = _boardsHolder4;
		_boardSizes[2] = _boardsHolder5;
		getCsvBoards();
		System.out.println("UPLOADED");
	}
	private void getCsvBoards() {

		String tCsv = csvToString();
		if (!_csvFound || tCsv.isEmpty())
		{
			_csvLoaded = false;	
			return;
		}
		_csvLoaded = true;		
		String[] tCsvBoards = tCsv.split("\\r?\\n");
		int i = 0;
		while (i < tCsvBoards.length) { //Cycling all String from csv
			if (tCsvBoards[i].length() == 1) { //if its board size , backup check
				int tJump = Integer.parseInt(tCsvBoards[i]); //get size
				int[] tPositions = new int[tJump * tJump];
				for (int j = i + 1; j <= (i + tJump); j++) { //Cycling Rows of current board
					String[] tRow = tCsvBoards[j].split(",");
					for (int k = 0; k < tRow.length; k++) { // Cycling each row
						tPositions[k] = Integer.parseInt(tRow[k]); //
					}
				}
				_boardSizes[tJump - 3].add(tPositions);
				i += (tJump + 1);
			}
		}
	}
	private String csvToString() {
		try {
			Scanner reader;
			File tBoardCSV = new File("boards.csv");

			reader = new Scanner(tBoardCSV);
			_csvFound = true;
			reader.useDelimiter(",");
			String tNewBoard = "";
			while (reader.hasNext()) {
				tNewBoard = tNewBoard + reader.next() + ",";
			}
			reader.close();

			return tNewBoard;
		} catch (Exception e) {
			_csvFound = false;
			alert("not found csv");

		}
		return null;
	}

	private void loadImages() {
		try {
			_icon = ImageIO.read(new File("Images/icon.png"));
			loadStartPuzzleImages();
			loadMainWindowImages();
			loadPuzzleWindowImages();
			loadChooseWindowImages();
			loadGivenImages();
		} catch (IOException e1) {
			System.out.println("error: could not load images in PuzzleWindow screen");
		}

	}
	private void loadStartPuzzleImages()
	{
		_startPuzzleImageIconPack = new ImageIcon [6];
		_startPuzzleImageIconPack[0] = new ImageIcon ("Images/StartPuzzle/changeImageIcon.png");
		_startPuzzleImageIconPack[1] = new ImageIcon("Images/StartPuzzle/randomIcon.png");
		_startPuzzleImageIconPack[2] = new ImageIcon("Images/StartPuzzle/openIcon.png");
		_startPuzzleImageIconPack[3] = new ImageIcon ("Images/StartPuzzle/backIcon.png");
		_startPuzzleImageIconPack[4] = new ImageIcon ("Images/StartPuzzle/csvOff.png");
		_startPuzzleImageIconPack[5] = new ImageIcon ("Images/StartPuzzle/csvOn.png");
	}
	private void loadMainWindowImages()
	{
		_mainImagePack = new ImageIcon[2];
		_mainImagePack[0] = new ImageIcon("Images/Main/playMainIcon.png");
		_mainImagePack[1] = new ImageIcon("Images/Main/exitIcon.png");	
	}
	private void loadPuzzleWindowImages()
	{
		_puzzleWindowPack = new ImageIcon[8];
		_puzzleWindowPack[0] = new ImageIcon("Images/PuzzleWindow/stopIcon.png"); 
		_puzzleWindowPack[1] = new ImageIcon("Images/PuzzleWindow/startIcon.png"); 
		_puzzleWindowPack[2] = new ImageIcon("Images/PuzzleWindow/undoIcon.png"); 
		_puzzleWindowPack[3] = new ImageIcon("Images/PuzzleWindow/changeImageIcon.png"); 
		_puzzleWindowPack[4] = new ImageIcon("Images/PuzzleWindow/menuIcon.png"); 
		_puzzleWindowPack[5] = new ImageIcon ("Images/PuzzleWindow/playAgainIcon.png");
		_puzzleWindowPack[6] = new ImageIcon ("Images/PuzzleWindow/movesIcon.png");
		_puzzleWindowPack[7] = new ImageIcon ("Images/PuzzleWindow/timerIcon.png");
	}
	private void loadChooseWindowImages()
	{
		_chooseWindowIconPack = new ImageIcon[4];
		_chooseWindowIconPack[0] = new ImageIcon ("Images/ChooseWindow/cat.jpeg");
		_chooseWindowIconPack[1] = new ImageIcon ("Images/ChooseWindow/sushi.jpg");
		_chooseWindowIconPack[2] = new ImageIcon ("Images/ChooseWindow/cyber.jpeg");
		_chooseWindowIconPack[3] = new ImageIcon ("Images/ChooseWindow/backIcon.png");
	}
	private void loadGivenImages() {
		try {
			_sushiImage = ImageIO.read(new File("Images/ChooseWindow/sushi.jpg"));
			_catImage =  ImageIO.read(new File("Images/ChooseWindow/cat.jpeg"));
			_cyberImage = ImageIO.read(new File("Images/ChooseWindow/cyber.jpeg"));
			//resize
			_sushiImage = resizePictures(_catImage);
			_catImage = resizePictures(_catImage);
			_cyberImage = resizePictures(_cyberImage);
		} catch(IOException e) {}

	}	

	public boolean loadPuzzleImage()
	{
		BufferedImage bf = _imageLoader.loadImage();
		if (bf != null)
		{
			_puzzleImage = _imageResizer.resizeImage(bf, 700, 700);
			return true;
		}
		return false;
	}
	public BufferedImage resizePictures(BufferedImage pic)
	{
		return _imageResizer.resizeImage(pic, 700, 700);
	}
	public BufferedImage resizeFinish(BufferedImage pic) 
	{
		return _imageResizer.resizeImage(pic, 250, 250);
	}
	private void alert(String alert)
	{
		switch (alert)
		{
		case "empty csv":
		{
			JOptionPane.showMessageDialog(null,"error: empty csv file, please insert a legal file and try again."
					+ '\n' + "You can still play, you will get the options of a none csv file game.", 
					"EMPTY CSV FILE", JOptionPane.CANCEL_OPTION);
			break;
		}
		case "not found csv":
		{
			JOptionPane.showMessageDialog(null,"error: csv fild not found."
					+ '\n' + "You can still play, you will get the options of a none csv file game.", 
					"NO CSV FILE", JOptionPane.CANCEL_OPTION);
			break;
		}
		case "not found size":
			JOptionPane.showMessageDialog(null,"error: size request was not found on csv file", 
					"SIZE NOT FOUND", JOptionPane.CANCEL_OPTION);
			break;
		}
	}

	public void setPuzzleImage(String pic)
	{
		switch (pic)
		{
		case "Cyber":
		{
			_puzzleImage = _cyberImage;
			break;
		}
		case "Cat":
		{
			_puzzleImage = _catImage;
			break;
		}
		case "Sushi":
		{
			_puzzleImage = _sushiImage;
			break;
		}
		}
	}
	public void setPuzzleImage(int random)
	{
		switch (random)
		{
		case 3:
		{
			_puzzleImage = _cyberImage;
			break;
		}
		case 4:
		{
			_puzzleImage = _catImage;
			break;
		}
		case 5:
		{
			_puzzleImage = _sushiImage;
			break;
		}
		}
	}
	public ImageIcon[] getStartPuzzlePack()
	{
		return _startPuzzleImageIconPack;
	}
	public ImageIcon[] getMainPack()
	{
		return _mainImagePack;
	}
	public ImageIcon[] getPuzzlePack()
	{
		return _puzzleWindowPack;
	}
	public ImageIcon[] getChooseIconsPack()
	{
		return _chooseWindowIconPack;
	}
	public Image[] getChooseImagesPack()
	{
		return _chooseWindowImagePack;
	}
	public boolean getCSVStatus()
	{
		return _csvLoaded;
	}
	public Image getIcon() {
		return _icon;
	}

	public int[] getBoardFromCSV(int size)
	{
		Random rnd = new Random();
		int index = rnd.nextInt(_boardSizes[size].size());
		return _boardSizes[size].get(index);
	}
	public BufferedImage getPuzzleImage()
	{
		return _puzzleImage;
	}
	public void getLoadingStatus()
	{

	}
	public boolean legalCsvSize(int pSize) {
		switch(pSize)
		{
		case 3:
		{
			if (_boardsHolder3 == null || _boardsHolder3.isEmpty())
				return false;
			return true;
		}
		case 4:
		{
			if (_boardsHolder4 == null || _boardsHolder4.isEmpty())
				return false;
			return true;
		}
		case 5:
		{
			if (_boardsHolder5 == null || _boardsHolder5.isEmpty())
				return false;
			return true;
		}
		}
		return false;
	}
	public int[] getRandomCsv(int pSize) {
		if ( pSize == 0)
			return getRandomCSV();
		else
			return getRandomCsvSize(pSize);
	}
}
