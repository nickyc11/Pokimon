import javax.swing.JFrame;

/**
 * GameFrame.java
 * JFrame for the game instrucitons
 * extends JFrame
 * @author Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class InstructionFrame extends JFrame {
	/**
     * InstructionFrame constructor
     * Constructs the instruction frame
     */
	public InstructionFrame() {
		super("Game Instructions");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(GameFrame.screenWidth, GameFrame.screenHeight);
		this.setResizable(false);
		this.add(new InstructionPanel());
		this.setFocusable(false); 
		this.setVisible(true);
	}
}
