/**
 * MovingObject.java
 * Abstract class for a moving object
 * @author Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

abstract public class MovingObject {
    private int xPos, yPos;
    private double xVelocity, yVelocity;

    /**
     * MovingObject constructor
     * @param xPos, x coordinate of the moving object
     * @param yPos, y coordinate of the moving object
     */
    public MovingObject(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * getXPos
     * @return int, the x position of the moving object
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * setXPos 
     * Sets the x coordinate of the moving object
     * @param xPos, the new x coordinate of the moving object
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * changeXPos
     * Changes the x position based on x velocity
     * @param xVelocity, the change in the x coordinate of the moving object
     */
    public void changeXPos(double xVelocity) {
        this.xPos += xVelocity;
    }

    /**
     * getYPos 
     * @return int, the y position of the moving object
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * setYPos Sets the y position of the moving object
     * @param yPos, the new y coordinate of the moving object
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * changeYPos
     * Changes y position based on y velocity
     * @param yVelocity, the change in the y coordinate of the moving object
     */
    public void changeYPos(double yVelocity) {
        this.yPos += yVelocity;
    }

    /**
     * getXVelocity 
     * @return int, the x velocity of the moving object
     */

    public double getXVelocity() {
        return xVelocity;
    }

    /**
     * setXVeolocity 
     * Sets the x velocity of the moving object
     * @param xVelocity, the x velocity of the moving object
     */
    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * getYVelocity 
     * @return double, the y velocity of the moving object
     */
    public double getYVelocity() {
        return yVelocity;
    }

    /**
     * setYVelocity 
     * Sets the y velocity of the moving object
     * @param yVelocity, the y velocity of the moving object
     */
    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
}
