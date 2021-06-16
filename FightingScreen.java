import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

/**
 * FightingScreen.java The fighting screen that plays when a player touches an
 * enemy NPC
 * 
 * @author Toby Ahn, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class FightingScreen {
	static AudioInputStream audioStream;
	static Clip fightSceneMusic;
	private Image attack;
	private Image backpack;
	private Image fightingScreen;
	private Image shield;

	/**
     * FightingScreen constructor
     * Loads in the required images on the fighting screen
     */
	public FightingScreen() {
		try {
			this.fightingScreen = ImageIO.read(new File("images/fightbg1920x1080.png"));
			this.attack = ImageIO.read(new File("sprites/Icons/sword.png")).getScaledInstance(100, 100,
					Image.SCALE_DEFAULT);
			this.backpack = ImageIO.read(new File("sprites/Icons/bag.png")).getScaledInstance(100, 100,
					Image.SCALE_DEFAULT);
			this.shield = ImageIO.read(new File("sprites/Icons/shield.png")).getScaledInstance(100, 100,
					Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * draw
	 * Draws fighting screen components
     * @param g, graphics
     */
	public void draw(Graphics g) {
		g.drawImage(fightingScreen, 0, 0, null);
		Graphics2D rect = (Graphics2D) g;
		rect.fillRect(0, 0, GameFrame.screenWidth - 5, 220);
		rect.drawRect(0, 0, GameFrame.screenWidth - 5, 220);
		rect.setColor(new Color(0, 0, 0));
		g.drawImage(attack, 539, 50, null);
		g.drawImage(backpack, 808, 50, null);
		g.drawImage(shield, 1077, 50, null);
	}

	/**
     * playAudio 
	 * Loads and plays game audio
     * @param audioStream, game audio
     */
	public void playAudio(AudioInputStream audioStream) {
		try {
			File fightMusic = new File("audio/music.wav");
			audioStream = AudioSystem.getAudioInputStream(fightMusic);
			fightSceneMusic = AudioSystem.getClip();
			fightSceneMusic.open(audioStream);
		} catch (Exception ex) {
		}
		fightSceneMusic.start();
		fightSceneMusic.loop(Clip.LOOP_CONTINUOUSLY);

	}
}
