import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Tile.java A single tile on the map
 * 
 * @author Nicholas Chew, Mark Chen
 * @version 1.0, June 14, 2021
 **/

public class Tile {
	int xPosMap, yPosMap;
	int tileWidth, tileHeight;
	Image image;

	/**
	 * Tile Constructor
	 * @param s, the file path to the tile
	 * @param x, the x position of the tile
	 * @param y, the y position of the tile
	 * @param w, the width of the tile
	 * @param h, the height of the tile
	 */
	public Tile(String s, int x, int y, int w, int h) {
		image = loadImage(s);
		xPosMap = x;
		yPosMap = y;
		tileWidth = w;
		tileHeight = h;
	}

	/**
	 * getX
	 * @return xPosMap, the x position of the tile
	 */
	public int getX() {
		return xPosMap;
	}

	/**
	 * getY
	 * @return yPosMap, the y position of the tile
	 */
	public int getY() {
		return yPosMap;
	}

	/**
	 * getBounds
	 * @return Rectangle, the rectangle around the tile
	 */
	public Rectangle getBounds() {
		return new Rectangle(xPosMap, yPosMap, tileWidth, tileHeight);
	}

	/**
	 * getImage
	 * @return Image, the tile image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * loadImage
	 * loads up the tile image
	 * @return Image, the tile image
	 */
	public Image loadImage(String filePath) {
		try {
			return ImageIO.read(new File(filePath)).getScaledInstance(32, 32, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}