package interfaces;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.mygdxgame.Mule;

/**
 * This is going to be a simple gui overlay that represents the store, the player will be able to buy and 
 * sell various resources through this.
 * @author antonio
 *
 */
public class StoreGui {

	private final int WIDTH = 500;
	private final int HEIGHT = 300;
	private final int NUMBER_OF_ITEMS = 7;
	
	private Button closeButton;
	private List<StoreItemGui> storeItemGuis;
	private int x;
	private int y;
	
	private StoreGui(){
		super();
		storeItemGuis = new ArrayList<StoreItemGui>();
	}
	
	/**
	 * This will create the store gui at the given x and y
	 * @param posX
	 * @param posY
	 */
	public StoreGui(int posX, int posY){
		this();
		x = posX;
		y = posY;
		closeButton = new Button(new Texture(Gdx.files.internal("TownScreen/CloseWindowButton.jpg")), x, y+HEIGHT-24);
		for(int i = 0 ; i < NUMBER_OF_ITEMS ; i ++){
			storeItemGuis.add(new StoreItemGui(x+i*(WIDTH/NUMBER_OF_ITEMS),y));
		}
	}
	
	/**
	 * Draws the interface
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		drawBackground(batch);

	}
	
	private void drawBackground(SpriteBatch batch){
		ShapeRenderer sr = new ShapeRenderer();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.BLACK);
		sr.rect(x, y, WIDTH, HEIGHT);
		sr.end();		
		
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.GRAY);
		sr.rect(x + 10, y + 10, WIDTH - 20, HEIGHT - 20);
		sr.end();
		
		int sizeItemX = WIDTH / NUMBER_OF_ITEMS;
		int sizeItemY = HEIGHT;
		int widthOfLines = 3;
		
		for(int i = 0 ; i < NUMBER_OF_ITEMS ; i ++){
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.BLACK);
			sr.rect(x + (sizeItemX * i), y, widthOfLines, sizeItemY);
			sr.end();
		}

		batch.begin();
		closeButton.draw(batch);
		batch.end();
	}

	/*
	 * @return returns true if close button has been clicked
	 */
	public boolean isClosed(){
		if(Mule.INPUT.mouseClicked()){
			if(closeButton.isClicked(Mule.INPUT)){
				return true;
			}
		}
		return false;
	}
}
