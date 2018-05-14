//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import Board.*;
//import java.awt.image.BufferedImage;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.util.ArrayList;
//import java.util.Stack;
//
//import javax.swing.*;
//public class Testing  extends JFrame{
//	
//	private JPanel menu;
//	private BufferedImage puzzle;
//	private int n = 4;
//	private ArrayList<Figure> AL; 
//	private int x=0,y=0;
//	private int w,h;
//	
//	public Testing(BufferedImage puzzle) {
//		this.puzzle = puzzle;
//		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
//		AL = new ArrayList<Figure>(4);
//		w=puzzle.getWidth()/4;
//		h=puzzle.getHeight()/4;
//		init();
//		this.setVisible(true);
//	}
//	public void init() {
//		for(int i=0;i<4;i++) {
//			AL.add(new Figure(i,new ImageIcon(puzzle.getSubimage(x, y, w, h))));
//			update();
//		}
//	}
//	public void update() {
//		for (int i = 0; i < AL.size(); i++) {
//			this.add(AL.get(i));
//		}
//	}
//	public static void main(String[] args) {
//		Testing t = new Testing(new BufferedImage())
//	}
//
//}
