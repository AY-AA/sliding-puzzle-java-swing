package Game;

import javax.swing.JFrame;

public abstract class Window extends JFrame {

	public Window()
	{
		super("Sliding Puzzle");
	}
	/**
	 * loads images from hd
	 */
	abstract void loadImages();
	/**
	 * initializes the game: creates all components
	 * toolbars and labels are created here
	 */
	abstract void initiateWindow();

	
}
