package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements ActionListener {
	
    private JButton exit;
    private JButton start_Game;
    private JPanel menu;
    private JLabel background;

    public MainWindow() {
    	//-------------------------- Window Preferences
        super("Welcome");
        setSize(600	,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        background = new JLabel(new ImageIcon("Background.jpg"));
        setContentPane(background);
        setLayout(new BorderLayout());
        menu = new JPanel();
        menu.setLayout(new GridBagLayout());

        //-------------------------- Buttons
        ImageIcon exitIcon = new ImageIcon("exitIcon.png");
        exit = new JButton("Exit", exitIcon);
        exit.addActionListener(this);
        
        ImageIcon puzzle1_64 = new ImageIcon("playMainIcon.png");
        start_Game = new JButton("Play",puzzle1_64);
        start_Game.addActionListener(this);
        
        //-------------------------- Grid Layout preferences
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        //-------------------------- Adding to panel and frame
        menu.add(start_Game, gbc);
        menu.add(exit, gbc);

        add(menu, BorderLayout.EAST);
        pack();
        setVisible(true);
    }

    public static void main(String args[]) {
       new MainWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else {
        	StartPuzzle sp = new StartPuzzle();
        	sp.setLocationRelativeTo(this);
        	dispose();
        }

    }

}
    