import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * StartButtonListener.java
 * The start button listener to activate the game
 * implements ActionListener
 * @author Mangat, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class StartButtonListener implements ActionListener {
	JFrame parentFrame;

	/**
     * StartButtonListener Constructor
     * @param i, the inventory
    */
	public StartButtonListener(JFrame parent) { 
		parentFrame = parent;
	}

	/**
     * actionedPerformed
     * Opens a new game frame
     */
	public void actionPerformed(ActionEvent event)  {  
		System.out.println("Starting new Game");
		parentFrame.dispose();
		new GameFrame();
	}
}