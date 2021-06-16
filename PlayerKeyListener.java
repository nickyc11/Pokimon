import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * PlayerKeyListener.java
 * The key listener for player interactions
 * implements KeyListener
 * @author Mangat, Nicholas Chew
 * @version 1.0
 **/

public class PlayerKeyListener implements KeyListener {

	private Player player;

	/**
	 * PlayerKeyListener Constructor
	 * @param p, the player
	 */
	public PlayerKeyListener(Player p) {
		player = p;
	}

	/**
	 * keyTyped
	 * @param e, Key event detection
	 */
	public void keyTyped(KeyEvent e) {

	}
    /**
     * keyPressed
	 * Moves the player if WASD is pressed
	 * @param e, Key event detection
     */
	public void keyPressed(KeyEvent e) {
		if (GamePanel.status == "map") {
			if (e.getKeyChar() == 'a') {
				player.moveLeft();
			} else if (e.getKeyChar() == 's') {
				player.moveDown();
			} else if (e.getKeyChar() == 'd') {
				player.moveRight();
			} else if (e.getKeyChar() == 'w') {
				player.moveUp();
			}
		}
	}

	/**
     * keyReleased
	 * Moves the player if WASD is released
	 * @param e, Key event detection
     */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a') {
			player.stopLeft();
		}
		if (e.getKeyChar() == 'd') {
			player.stopRight();
		}
		if (e.getKeyChar() == 'w') {
			player.stopUp();
		}
		if (e.getKeyChar() == 's') {
			player.stopDown();
		}
	}
} 
