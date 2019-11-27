// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.

package HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entity.ToBeSaved;
import Entity.Player;
import Main.GamePanel;
import Manager.Content;

public class Hud {
	
	private int yoffset;
	
	private BufferedImage bar;
	private BufferedImage PipsToSaved;
	private BufferedImage mask;
	private BufferedImage key;
	
	private Player player;
	
	private int numParts;
	
	private Font font;
	private Color textColor; 
	
	public Hud(Player p, ArrayList<ToBeSaved> d) {
		
		player = p;
		numParts = d.size();
		yoffset = GamePanel.HEIGHT;
		
		bar = Content.BAR[0][0];
		PipsToSaved = Content.PARTS[0][0];
		mask = Content.ITEMS[0][0];
		key = Content.ITEMS[0][1];
		
		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(47, 64, 126);
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw hud
		g.drawImage(bar, 0, yoffset, null);
		
		// draw people bar
		g.setColor(textColor);
		g.fillRect(8, yoffset + 6, (int)(28.0 * player.numParts() / numParts), 4);
		
		// draw Persons amount
		g.setColor(textColor);
		g.setFont(font);
		String s = player.numParts() + "/" + numParts;
		Content.drawString(g, s, 40, yoffset + 3);
		if(player.numParts() >= 10) g.drawImage(PipsToSaved, 80, yoffset, null);
		else g.drawImage(PipsToSaved, 72, yoffset, null);
		
		// draw items
		if(player.hasMask()) g.drawImage(mask, 100, yoffset, null);
		if(player.hasKey()) g.drawImage(key, 112, yoffset, null);
		
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 45, 3);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 45, 3);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 45, 3);
			else Content.drawString(g, minutes + ":" + seconds, 45, 3);
		}
		
		
		
	}
	
}
