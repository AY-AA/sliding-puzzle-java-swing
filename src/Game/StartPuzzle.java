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
import javax.swing.JOptionPane;
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
	private JPanel main = new JPanel();
	private JButton open,play;
	private JFileChooser fileChooser;
	private BufferedImage image = null;
	private JLabel puzzleImage = new JLabel("Select an image and difficulty level");
	private JTextField nxn;
	private GridBagConstraints gbc = new GridBagConstraints();
	private ImageIcon open_Icon,play_Icon;
	private int puzzle_Size = 0;

	public StartPuzzle()
	{
		setTitle("Welcome");
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.setLayout(new GridBagLayout());
		//gbc.anchor = GridBagConstraints.LINE_END;


		//============= Buttons and Text
		nxn = new JTextField("Select size");
		nxn.addActionListener(this);
		nxn.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				nxn.setText("");
				nxn.setSize(80,20);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				nxn.setText("");
				nxn.setSize(80,20);
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

		open_Icon = new ImageIcon("openIcon.png");
		open = new JButton("Open");
		open.setName("Open");
		open.setIcon(open_Icon);
		open.addActionListener(this);

		play_Icon = new ImageIcon("playIcon.png");
		play = new JButton("Play");
		play.setName("Play");
		play.setIcon(play_Icon);  
		play.addActionListener(this);

		//============= adding to JPanel main
		gbc.gridx = 0;
		gbc.gridy = 0;
		main.add(open, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		main.add(play, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		main.add(nxn, gbc);

		add(main);
		//pack();
		setVisible(true);

	}

	//============= Getters and Setters
	public int getPuzzleSize() {
		return puzzle_Size;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getName().equals("Play")){ //if play button was pressed
			String N = nxn.getText();
			puzzle_Size = getBoardSize(N);
			if(image != null) {
				BufferedImage puzzelImage = ImageResizer.resizeImage(image, 400, 400);
				Board board = new Board (3, puzzelImage);
				new Puzzle (board);	
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null,"You must first choose a photo");
			}
		}

		if(button.getName().equals("Open")) { //if open button was pressed
			fileChooser = new JFileChooser();
			int action = fileChooser.showOpenDialog(null);
			if(action == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				try {
					image = ImageIO.read(file);
					open.setIcon(new ImageIcon(image.getScaledInstance(open.getWidth(), open.getHeight(), Image.SCALE_DEFAULT)));

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"You must first choose a photo");
				}
			}
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
