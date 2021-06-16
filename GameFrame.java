import javax.swing.JFrame;

/**
 * GameFrame.java
 * The main frame of the game
 * extends JFrame
 * @author Mangat, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class GameFrame extends JFrame {
	public static final int screenWidth = 1616;
	public static final int screenHeight = 934;

	/**
     * GameFrame constructor
     * Constructs the game frame
     */
	public GameFrame() {
		super("Pokimon");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenWidth, screenHeight);
		this.setResizable(false);
		this.add(new GamePanel());
		this.setFocusable(false); 
		this.setVisible(true);
	}
}
