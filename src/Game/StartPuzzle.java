package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.Timer;

import Board.*;
import ImageHandler.ImageResizer;

import javax.swing.JFrame;

public class StartPuzzle extends JFrame implements ActionListener
{
	private JButton open,play;
	private JFileChooser fileChooser;
	private BufferedImage image = null;
	private JLabel puzzleLabel;
	private JTextField nxn;
	private GridBagConstraints gbc = new GridBagConstraints();
	private ImageIcon open_Icon,play_Icon;
	private int puzzle_Size = 0;
    private ImagePanel menu;
    
	public StartPuzzle()
	{
        super("Sliding Puzzle");
        setSize(400	,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initiateWindow();
        setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

//        pack();
        setVisible(true);
	}

	private void initiateWindow() 
	{
		GridBagConstraints gbc = new GridBagConstraints();

        final Insets insets = new Insets(10, 10, 10,10);
        gbc.insets = insets;

        menu = new ImagePanel();
        menu.setSize(400,600);
        
		// Buttons and Text addition to gridbag
        puzzleLabel = new JLabel("Select an image and difficulty level");
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.LINE_START;
		menu.add(puzzleLabel,gbc);
		
		open_Icon = new ImageIcon("openIcon.png");
		open = new JButton("Open");
		open.setName("Open");
		open.setIcon(open_Icon);
		open.addActionListener(this);
		open.setMaximumSize(new Dimension (20, 20));
		
		$$GRID MAX 
		gbc.gridx = 1;
		gbc.gridy = 0;
        menu.add(open, gbc);
        
		play_Icon = new ImageIcon("playIcon.png");
		play = new JButton("Play");
		play.setName("Play");
		play.setIcon(play_Icon);  
		play.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy =1;
        menu.add(play, gbc);
		
		nxn = new JTextField("Select size");
		nxn.addActionListener(this);
		nxn.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				nxn.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				nxn.setText("");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		gbc.gridx = 1;
		gbc.gridy = 2;
        menu.add(nxn, gbc);
        		
        add(menu);
        
        // game icon
        Image icon;
        try {                
        	icon = ImageIO.read(new File("icon.png"));
            setIconImage(icon);
         } catch (IOException ex) {}
       	
	}

	//============= Getters and Setters
	public int getPuzzleSize() {
		return puzzle_Size;
	}

	public static void main(String[] args) {
		StartPuzzle sp = new StartPuzzle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		if(button.getName().equals("Play")){
			String N = nxn.getText();
			puzzle_Size = getBoardSize(N);
			Board board = new Board (puzzle_Size, image);
			new Puzzle (board);
			dispose();
			}

		 if(button.getName().equals("Open")) {
			
			fileChooser = new JFileChooser();
			int action = fileChooser.showOpenDialog(null);
			if(action == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				try {
					image = ImageIO.read(file);
					open.setIcon(new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_DEFAULT)));

				} catch (IOException e1) {
					System.out.println("You must select an image");
				}
			}
		}
		 if(image != null) {
				BufferedImage puzzelImage = ImageResizer.resizeImage(image, 400, 400);
				BufferedImage miniImage = ImageResizer.resizeImage(image, 200, 200);
				//creating puzzle
				
		 }
	}

	//============= Additional Methods
	private int getBoardSize(String input) {
		char i1 = input.charAt(0);
		String temp = "";
		temp = temp + i1;
		int N = Integer.parseInt(temp);
		return N;
	}

}
