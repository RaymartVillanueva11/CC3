// The only subclass the fully utilizes the
// Entity superclass (no other class requires
// movement in a tile based map).
// Contains all the gameplay associated with
// the Player.
package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Manager.Content;
import TileMap.TileMap;

public class Player extends Entity {
	
	// sets the player's sprite to its appropriate title
	private BufferedImage[] downSprites; //when going down
	private BufferedImage[] leftSprites; // when going left
	private BufferedImage[] rightSprites; // when going right
	private BufferedImage[] upSprites; // when going up
	private BufferedImage[] downMaskSprites; // when player has a mask and goes down
	private BufferedImage[] leftMaskSprites; // when players has a mask and goes left
	private BufferedImage[] rightMaskSprites; // when players has a mask and goes right
	private BufferedImage[] upMaskSprites; // when players has a mask and goes up
	
	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNMASK = 4;
	private final int LEFTMASK = 5;
	private final int RIGHTMASK = 6;
	private final int UPMASK = 7;
	
	// gameplay
	private int numToSave;
	private int totalNumtoSave;
	private boolean hasMask;
	private boolean hasKey;
	private boolean onPoison;
	private long ticks;
	
	public Player(TileMap tm) { //sets the images declared above to the sprite image's position and location
		
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		moveSpeed = 3;
		
		numToSave = 0;
		
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downMaskSprites = Content.PLAYER[4];
		leftMaskSprites = Content.PLAYER[5];
		rightMaskSprites = Content.PLAYER[6];
		upMaskSprites = Content.PLAYER[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(10);
		
	}
	
	private void setAnimation(int i, BufferedImage[] bi, int d) {//sets the animation in place
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}
	
	public void collectedparts() { numToSave++; }//when the parts are collected the num adds one
	public int numParts() { return numToSave; }//returns the num of people
	public int getTotalParts() { return totalNumtoSave; } //returns total people collected
	public void setTotalParts(int i) { totalNumtoSave = i; }//declares i to total people
	
	public void gotMask() { hasMask = true; tileMap.replace(22, 4); }//when mask is collected replaced by normal(land) tile
	public void gotKey() { hasKey = true; } //when key is collected, item key can now be used
	public boolean hasMask() { return hasMask; }//returns boolean if players has collected the mask
	public boolean hasKey() { return hasKey; }//returns boolean if players has collected the key
	
	// Used to update time.
	public long getTicks() { return ticks; }
	
	// Keyboard input. Moves the player.
	public void setDown() {
		super.setDown();
	}
	public void setLeft() {
		super.setLeft();
	}
	public void setRight() {
		super.setRight();
	}
	public void setUp() {
		super.setUp();
	}
	
	// Keyboard input.
	// If Player has key, Doors in front
	// of the Player will be Open.
	public void setAction() {
		if(hasKey) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
				//sets the action if it has key, then the player can now open it
			}
			if(currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
			
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
				
			}
			if(currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
				
			}
		}
	}
	
	public void update() {
		
		ticks++;
		
		// check if on water 
		boolean current = onPoison;
		if(tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 4) {
			onPoison = true;
		}
		else {onPoison = false;
		}
		// if going from land to water
		if(!current && onPoison) {
		}
		
		// set animation
		if(down) {
			if(onPoison && currentAnimation != DOWNMASK) {
				setAnimation(DOWNMASK, downMaskSprites, 10);
			}
			else if(!onPoison && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if(left) {
			if(onPoison && currentAnimation != LEFTMASK) {
				setAnimation(LEFTMASK, leftMaskSprites, 10);
			}
			else if(!onPoison && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if(right) {
			if(onPoison && currentAnimation != RIGHTMASK) {
				setAnimation(RIGHTMASK, rightMaskSprites, 10);
			}
			else if(!onPoison && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if(up) {
			if(onPoison && currentAnimation != UPMASK) {
				setAnimation(UPMASK, upMaskSprites, 10);
			}
			else if(!onPoison && currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}
		
		// update position
		super.update();
		
	}
	
	// Draw Player.
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}