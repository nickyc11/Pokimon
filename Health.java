import java.awt.Graphics;

/**
 * Health.java
 * Health interface for the player and enemies
 * @author Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public interface Health {
    /**
     * drawHealth
     * Draws a rectangle health bar
     * @param g, Draw Graphics
     */
    public void drawHealth(Graphics g);
    /**
     * getHealth
     * @return int, The health of the object
     */
    public int getHealth();
    /**
     * setHealth
     * sets the health of the object
     */
    public void setHealth(int health);
    /**
     * incrementHealth
     * increments the health of the object (increase or decrease)
     */
    public void incrementHealth(int health);

}  
