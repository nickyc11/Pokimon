import java.awt.Rectangle;

/**
 * Collision.java
 * Collision detection for players and enemy NPCs
 * @author Mark Chen, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class Collision {
    /**
     * playerCollidingTile
     * @param player, the player
     * @param tile, a tile on the map
     * @return boolean, whether or not the player is colliding with a tile
     **/
    public static boolean playerCollidingTile(Player player, Tile tile) {
        if (player.getBounds().intersects(tile.getBounds())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * playerCollidingNPC
     * @param player, the player
     * @param Rectangle, the bounding box of the NPC object
     * @return boolean, whether or not the player is colliding with an NPC
     **/
    public static boolean playerCollidingNPC(Player player, Rectangle rectangle) {
        if (player.getInteractionBounds().intersects(rectangle)) {
            return true;
        } else {
            return false;
        }
    }
}