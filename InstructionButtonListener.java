import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * InstructionButtonListener.java
 * Button listener for the game instructions
 * implements ActionListener
 * @author Mangat, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class InstructionButtonListener implements ActionListener {

	JFrame parentFrame;

	/**
     * InstructionButtonListener constructor
     */
	public InstructionButtonListener(JFrame parent) { 
		parentFrame = parent;
	}

	/**
     * actionedPerformed
     * Opens a new instruction frame
     */
	public void actionPerformed(ActionEvent event)  {  
		// System.out.println("Instruction page opened");
		new InstructionFrame(); //create a new frame after removing the current one
	}
}