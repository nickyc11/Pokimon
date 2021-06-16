import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Rectangle;

/**
 * NPC.java The enemy NPC that roams the map extends MovingObject
 * 
 * @author Mark Chen, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class NPC extends MovingObject {
    private String name;
    private File spriteLeftFile;
    private File spriteRightFile;
    private Image spriteLeft;
    private Image spriteRight;
    private boolean allowedToMove;

    /**
     * NPC constructor
     * @param xPos,            x coordinate of the NPC
     * @param yPos,            Y coordinate of the NPC
     * @param spriteLeftFile,  file of sprite looking left
     * @param spriteRightFile, file of sprite looking right
     * @param name,            name of the NPC
     */
    public NPC(int xPos, int yPos, File spriteLeftFile, File spriteRightFile, String name) {
        super(xPos, yPos);
        this.name = name;
        this.spriteLeftFile = spriteLeftFile;
        this.spriteRightFile = spriteRightFile;
        this.allowedToMove = true;
        loadSprite();
    }

    /**
     * getName 
     * @return string, returns the name of the NPC
     */
    public String getName() {
        return this.name;
    }

    /**
     * loadSprite
     * Loads sprites Catches Exception if the sprite fails to
     */
    public void loadSprite() {
        try {
            spriteLeft = ImageIO.read(spriteLeftFile).getScaledInstance(64, 64, Image.SCALE_DEFAULT);
            spriteRight = ImageIO.read(spriteRightFile).getScaledInstance(64, 64, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            System.out.println("error loading sprite");
        }
    }

    /**
     * draw 
     * Draws the image on the tile map facing left or right
     * @param g, Graphics that allow drawings to be drawn on the game panel
     */
    public void draw(Graphics g) {
        if (getXVelocity() < 0) {
            g.drawImage(spriteLeft, getXPos(), getYPos(), null);
        } else if (getXVelocity() > 0) {
            g.drawImage(spriteRight, getXPos(), getYPos(), null);
        }
    }

    /**
     * getBounds
     * Retrieves the coordinates for the hitbox of the NPC
     */
    public Rectangle getBounds() {
        return new Rectangle(getXPos(), getYPos(), 32, 32);
    }

    /**
     * travel 
     * A method that sets the travel path of the NPCs if they are allowed to
     * @param x, the x position of the NPC
     * @param y, the y position of the NPC
     */
    public void travel(int x, int y) {
        double angle = (double) Math.atan2(y - getYPos(), x - getXPos());
        if (allowedToMove) {
            setXVelocity((double) (1.9 * Math.cos(angle)));
            setYVelocity((double) (1.9 * Math.sin(angle)));
            setXPos(getXPos() + (int) Math.round(getXVelocity()));
            setYPos(getYPos() + (int) Math.round(getYVelocity()));
        }
    }

    /**
     * changeAllowedToMove
     * Locks the NPCs if the player is in the combat screen
     * @param t, whether or not the NPC is allowed to move
     */
    public void changeAllowedToMove(boolean t) {
        this.allowedToMove = t;
    }

}