import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

/**
 * StartingFrame.java
 * The starting frame for the game
 * extends JFrame
 * @author Mangat, Nicholas Chew, Mark Chen
 * @version 1.0, June 14, 2021
 **/

public class StartingFrame extends JFrame {
	/**
     * StartingFrame Constructor
     * Opens the starting frame with JButtons
     */
	public StartingFrame() {
		super("Pokimon");
		this.setSize(GameFrame.screenWidth, GameFrame.screenHeight);
		this.setLocationRelativeTo(null); 
		this.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.black);

		JLabel title = new JLabel("Pokimon");
		title.setFont(new Font("Comic Sans MS", 1, 40));
		title.setBounds(730, 210, 400, 100);
		title.setBackground(Color.WHITE);

		JButton startButton = new JButton("PLAY");
		startButton.setBounds(730, 350, 150, 30);
		startButton.setBackground(Color.WHITE);
		startButton.setForeground(Color.BLACK);
		startButton.addActionListener(new StartButtonListener(this));

		JButton instructionButton = new JButton("INSTRUCTIONS");
		instructionButton.setBounds(730, 380, 150, 30);
		instructionButton.setBackground(Color.WHITE);
		instructionButton.setForeground(Color.BLACK);
		instructionButton.addActionListener(new InstructionButtonListener(this));

		JButton exitButton = new JButton("EXIT");
		exitButton.setBounds(730, 410, 150, 30);
		exitButton.setBackground(Color.WHITE);
		exitButton.setForeground(Color.BLACK);
		exitButton.addActionListener(new ExitButtonListener(this));

		this.add(title);
		this.add(startButton);
		this.add(instructionButton);
		this.add(exitButton);
		this.add(mainPanel);
		this.setVisible(true);
		this.requestFocusInWindow();
	}

	public static void main(String[] args) {
		new StartingFrame();
	}
}