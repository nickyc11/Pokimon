import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * InstructionMouseListener.java
 * Mouse listener for the instruction page
 * implements MouseListener
 * @author Mangat, Nicholas Chew, Toby Ahn
 * @version 1.0, June 14, 2021
 **/


public class InstructionMouseListener implements MouseListener {

	/**
     * InstructionMouseListener constructor
     */
	public InstructionMouseListener() {

	}

	/**
     * mouseclicked
	 * Detects for mouse click
     * @param e, mouseEvent detector
     */
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked!!");
		System.out.println("X:" + e.getX() + " y:" + e.getY());

        if ((e.getX() > 30) && (e.getX() < 260) && (e.getY() > 800) && (e.getY() < 870) && (InstructionPanel.page == 1)) {
            InstructionPanel.page = 2;
        } else if ((e.getX() > 30) && (e.getX() < 260) && (e.getY() > 800) && (e.getY() < 870) && (InstructionPanel.page == 2)) {
            InstructionPanel.page = 1;
        }
		
	}

	/**
     * mousePressed
	 * Detects for mouse press
     * @param e, mouseEvent detector
     */
	public void mousePressed(MouseEvent e) {

	}

	/**
     * mouseReleased
	 * Detects for mouse release
     * @param e, mouseEvent detector
     */
	public void mouseReleased(MouseEvent e) {

	}

	/**
     * mouseEntered
	 * Detects for mouse enter
     * @param e, mouseEvent detector
     */
	public void mouseEntered(MouseEvent e) {

	}

	/**
     * mouseExited
	 * Detects for mouse exit
     * @param e, mouseEvent detector
     */
	public void mouseExited(MouseEvent e) {

	}

}