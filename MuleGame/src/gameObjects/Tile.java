package gameObjects;

import renderers.MapRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.me.mygdxgame.Mule;

/**
 * This class represents a tile in the map. Possible states are
 * 0-	Plains
 * 1-	River
 * 2-	Town
 * 3-	1 Ridge Mountain
 * 4-	2 Ridge Mountain
 * 5-	3 Ridge Mountain
 * @author DavidW
 */
public class Tile {
	private static Texture[] textures = new Texture[7];
	private static final float SIZE = 1f;
	
	private final int COST = 500;
	
	private int tileType;
	private int x,y;
	
	/*
	 * -1 = no mule
	 * 0 = ore
	 * 1 = food
	 * 2 = energy
	 */
	private int muleOn = -1;
	
	private final int PLAYER_BOX_WIDTH = 10;
	
	private Rectangle rect;
	
	private boolean isOwned;

	private boolean isHighlighted;

	private Color highlightColor;
	
	private Player owner;
	
	private ShapeRenderer shapeRenderer;
	
	/**
	 * Creates a Tile with only its position in the Grid
	 * @param x
	 * @param y
	 */
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
		rect = new Rectangle(x * MapRenderer.ppuX, MapRenderer.ppuY * y, MapRenderer.ppuX * SIZE, MapRenderer.ppuY * SIZE);
		isOwned = false;
		isHighlighted = false;
		owner = null;
		shapeRenderer = new ShapeRenderer();
	}
	
	
	
	public int getCost(){
		return COST;
	}
	
	public boolean isOwned(){
		return isOwned;
	}
	
	public boolean setOwner(Player p){
		if(!isOwned){
			owner = p;
			isOwned = true;
			return true;
		}
		return false;
	}

	public Player getOwner(){
		return owner;
	}

	/**
	 * Draws the tile to the SpriteBatch of the map
	 */
	public void draw(SpriteBatch batch){
		batch.draw(textures[tileType], x * MapRenderer.ppuX, MapRenderer.ppuY * y, MapRenderer.ppuX * SIZE, MapRenderer.ppuY * SIZE);
		if(muleOn != -1)drawMule(batch);
	}
	
	/**
	 * RED IF ORE MULE
	 * GREEN IF FOOD MULE
	 * BLUE IF ENERGY MULE
	 * @param batch
	 */
	public void drawMule(SpriteBatch batch){
		batch.end();
		ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeRenderer.ShapeType.Filled);
		if(muleOn == 0) sr.setColor(new Color(255,0,0,1));
		if(muleOn == 1) sr.setColor(new Color(0,255,0,1));
		if(muleOn == 2) sr.setColor(new Color(0,0,255,1));
		sr.rect(x * MapRenderer.ppuX + 30, y * MapRenderer.ppuY + 30, 10, 10);
		sr.end();
		batch.begin();
	}

	public void drawOwner(SpriteBatch batch){
		if(isOwned){
			batch.end();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(owner.getColor());
			shapeRenderer.circle(MapRenderer.ppuX * (x) + PLAYER_BOX_WIDTH, MapRenderer.ppuY * (y)
						+ PLAYER_BOX_WIDTH, PLAYER_BOX_WIDTH);	
			shapeRenderer.end();
			batch.begin();
		}
	}

	/**
	  * Draws highlight around box for currentPlayer's color.
	  * @param batch
	  */
	public void drawHighlight(SpriteBatch batch){
		if(isHighlighted){
			batch.end();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.identity();
			shapeRenderer.setColor(highlightColor);
			shapeRenderer.rect(MapRenderer.ppuX * (x), MapRenderer.ppuY * (y), MapRenderer.ppuX * SIZE, MapRenderer.ppuY * SIZE);
			shapeRenderer.end();
			batch.begin();
		}
	}

	public void setType(int newType){
		tileType = newType;
	}
	
	public int getTileType(){
		return tileType;
	}
	
	
	public static void loadTextures(){
		textures[0] = new Texture("MapScreen/plains.png");
		textures[1] = new Texture("MapScreen/river.png");
		textures[2] = new Texture("MapScreen/town.png");
		textures[3] = new Texture("MapScreen/oneRidge.png");
		textures[4] = new Texture("MapScreen/twoRidge.png");
		textures[5] = new Texture("MapScreen/threeRidge.png");
	}
	
	public Rectangle getRect(){
		return rect;
	}
	
	public String toString(){
		String s = "";
		if(tileType == 0){
			s += "P";
		} else if(tileType == 1){
			s += "R";
		} else if(tileType == 2){
			s += "T";
		} else{
			s += "M";
		}
		return s;
	}

	public void produce(){
		if(isOwned){
			if(muleOn!=-1){
				int value;
				int[][] howmuch = {{1,0,2,3,4},{2,4,1,1,1},{3,2,1,1,1}};
				switch(tileType){
						case 0: value=howmuch[muleOn][0];//plain
						break;
						case 1: value=howmuch[muleOn][1];//river
						break;
						case 3: value=howmuch[muleOn][2];//1mount
						break;
						case 4: value=howmuch[muleOn][3];//2mount
						break;
						case 5: value=howmuch[muleOn][4];//3mount
						break;
						default: value=-1;//town or invalid
						break;
				}
				if (value!=-1){
					boolean ok=true;
					if(muleOn!=2){
						ok = owner.spendResources(2,1)==1;
					}
					if(ok){
						switch(muleOn){
							case 0: owner.gainResources(3,value);
							break;
							case 1: owner.gainResources(1,value);
							break;
							case 2: owner.gainResources(2,value);
							break;
							default: ;
							break;
						}
					}
				}
			}
		}
	}
	
	public void setOwner(Player p, boolean b){
		if(b)p.incrementMoney(-1 * COST);
		owner = p;
		isOwned = true;
	}

	public void setIsHighlighted(boolean b){
		isHighlighted = false;
	}

	public void setIsHighlighted(Color c, boolean b){
		highlightColor = c;
		isHighlighted = b;
	}
	
	public void setMule(int muleType){
		this.muleOn = muleType;
	}
	
	public int getMule(){
		return muleOn;
	}
	
	public String getCords(){
		return x + " , " + y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
