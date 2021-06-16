import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * ExitButtonListener.java
 * Button listener for game exit
 * implements ActionListener
 * @author Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class ExitButtonListener implements ActionListener {

	JFrame parentFrame;

	/**
     * ExitButtonListener constructor
     */
	public ExitButtonListener(JFrame parent) { 
		parentFrame = parent;
	}

	/**
     * actionedPerformed
     * Exits the starting frame
     */
	public void actionPerformed(ActionEvent event)  {  
		System.out.println("Exiting Game");
		parentFrame.dispose();
	}

}