import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics;

/**
 * Map.java
 * The game map displayed to the screen
 * @author Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class Map {
    private File mapFile;
    private char[][] map;
    private Tile[][] tileMap;
    private int boxLength;
    private ArrayList<Tile> tileWalls;

    /**
     * Map Constructor
	 * Creates the world map
     */
    public Map(String mapFileName) {
        tileWalls = new ArrayList<Tile>();
        createWorldMap(mapFileName);
    }

    /**
     * getTileWalls
     * @return tileWalls, an arraylist of all tile walls
     */
    public ArrayList<Tile> getTileWalls() {
        return tileWalls;
    }

    /**
     * createWorldMap
	 * Reads the map text files, then builds out an array of tiles
     * @param mapFileName, the name of the file of map to be drawn
     */
    public void createWorldMap(String mapFileName) {
        try {
            mapFile = new File(mapFileName);
            Scanner mapScanner = new Scanner(mapFile);
            int numRows = mapScanner.nextInt();
            int numCols = mapScanner.nextInt();
            this.boxLength = 32;
            mapScanner.nextLine();
            map = new char[numRows][numCols];
            tileMap = new Tile[numRows][numCols];
            String currLine = "";
            for (int i = 0; i < map.length; i++) {
                if (mapScanner.hasNext()) {
                    currLine = mapScanner.nextLine();
                    for (int j = 0; j < map[i].length; j++) {
                        map[i][j] = currLine.charAt(j);
                    }
                }

            }
            mapScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (map[i][j] == '0') {
                    tileMap[i][j] = new Tile("./Tiles/grass.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                } else if (map[i][j] == '1') {
                    tileMap[i][j] = new Tile("./Tiles/water.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                    tileWalls.add(tileMap[i][j]);
                } else if (map[i][j] == '2') {
                    tileMap[i][j] = new Tile("./Tiles/bottomTree.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                    tileWalls.add(tileMap[i][j]);
                } else if (map[i][j] == '3') {
                    tileMap[i][j] = new Tile("./Tiles/rock.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                    tileWalls.add(tileMap[i][j]);
                } else if (map[i][j] == '4') {
                    tileMap[i][j] = new Tile("./Tiles/bush.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                    tileWalls.add(tileMap[i][j]);
                } else if (map[i][j] == '5') {
                    tileMap[i][j] = new Tile("./Tiles/log.png", j * boxLength, i * boxLength, boxLength, boxLength);
                    tileWalls.add(tileMap[i][j]);
                } else if (map[i][j] == '6') {
                    tileMap[i][j] = new Tile("./Tiles/topTree.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                    tileWalls.add(tileMap[i][j]);
                } else if (map[i][j] == '7') {
                    tileMap[i][j] = new Tile("./Tiles/sign.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                } else if (map[i][j] == '8') {
                    tileMap[i][j] = new Tile("./Tiles/path.png", j * boxLength, i * boxLength, boxLength,
                            boxLength);
                }
            }
        }
    }

    /**
     * draw
	 * Draws the 2d Array of tiles
     * @param g, Graphics
     */
    public void draw(Graphics g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                g.drawImage(tileMap[i][j].getImage(), j * boxLength, i * boxLength, null);
            }
        }
    }
}
