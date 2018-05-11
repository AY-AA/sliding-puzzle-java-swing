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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        background = new JLabel(new ImageIcon("MyBackground.jpg"));
        this.setContentPane(background);
        this.setLayout(new BorderLayout());
        menu = new JPanel();
        menu.setLayout(new GridBagLayout());

        //-------------------------- Buttons
        ImageIcon exit_64 = new ImageIcon("exit1_64.png");
        exit = new JButton("Exit", exit_64);
        exit.addActionListener(this);
        
        ImageIcon puzzle1_64 = new ImageIcon("puzzle_play.png");
        start_Game = new JButton("play",puzzle1_64);
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
        MainWindow a = new MainWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else {
        	StartPuzzle nextWindow = new StartPuzzle();
        	this.dispose();
        }

    }

}
    