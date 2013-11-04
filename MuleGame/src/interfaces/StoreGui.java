package interfaces;

import java.util.ArrayList;
import java.util.List;

import managers.StoreInventory;

import gameObjects.Player;
import gameObjects.Resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

	private final int WIDTH = 550;
	private final int HEIGHT = 300;
	private final int BUFFER_X = 80;
	private final int NUMBER_OF_ITEMS = 7;
	
	private final int MULE_SPOT = 0;
	private final int FOOD_SPOT = 1;
	private final int ENERGY_SPOT = 2;
	private final int ORE_SPOT = 3;
	
	private BitmapFont font;
	private Button closeButton;
	private List<StoreItemGui> storeItemGuis;
	private Stage stage;
	private int x;
	private int y;
	
	private StoreInventory inventory;
	
	private StoreGui(){
		super();
		stage = new Stage(Mule.WIDTH, HEIGHT, false);
		storeItemGuis = new ArrayList<StoreItemGui>();
		font = new BitmapFont();
		inventory = new StoreInventory();
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
		for(int i = 0 ; i < NUMBER_OF_ITEMS ; i++){
			storeItemGuis.add(new StoreItemGui(x+20+BUFFER_X+(i*((WIDTH-BUFFER_X)/NUMBER_OF_ITEMS)), y, stage));
		}
		setItemValues();
	}
	
	/**
	 * Draws the interface
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		drawBackground(batch);
		Gdx.input.setInputProcessor(stage);
		stage.draw();
		for(StoreItemGui item : storeItemGuis){
			item.draw(batch);
		}

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
		
		int sizeItemX = (WIDTH - BUFFER_X) / NUMBER_OF_ITEMS;
		int sizeItemY = HEIGHT;
		int widthOfLines = 3;

		batch.begin();
		font.draw(batch, "In Stock:", x+20, y+150);
		font.draw(batch, "Price:", x+20, y+130);
		font.draw(batch, "Owned:", x+20, y+110);
		font.draw(batch, "Trading:", x+20, y+60);
		batch.end();
		
		for(int i = 0 ; i < NUMBER_OF_ITEMS ; i ++){
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.BLACK);
			sr.rect(x + BUFFER_X + (sizeItemX * i), y, widthOfLines, sizeItemY);
			sr.end();
		}

		batch.begin();
		closeButton.draw(batch);
		batch.end();
	}

	private void setItemValues(){
		storeItemGuis.get(MULE_SPOT).setName("Mule");
		storeItemGuis.get(MULE_SPOT).setPrice(inventory.getResourcePrice(StoreInventory.MULES_INDEX));
		storeItemGuis.get(MULE_SPOT).setStock(inventory.getResourceAmount(StoreInventory.MULES_INDEX));

		storeItemGuis.get(FOOD_SPOT).setName("Food");
		storeItemGuis.get(FOOD_SPOT).setPrice(inventory.getResourcePrice(StoreInventory.FOOD_INDEX));
		storeItemGuis.get(FOOD_SPOT).setStock(inventory.getResourceAmount(StoreInventory.FOOD_INDEX));

		storeItemGuis.get(ENERGY_SPOT).setName("Energy");
		storeItemGuis.get(ENERGY_SPOT).setPrice(inventory.getResourcePrice(StoreInventory.ENERGY_INDEX));
		storeItemGuis.get(ENERGY_SPOT).setStock(inventory.getResourceAmount(StoreInventory.ENERGY_INDEX));
			
		storeItemGuis.get(ORE_SPOT).setName("Ore");
		storeItemGuis.get(ORE_SPOT).setPrice(inventory.getResourcePrice(StoreInventory.ORE_INDEX));
		storeItemGuis.get(ORE_SPOT).setStock(inventory.getResourceAmount(StoreInventory.ORE_INDEX));

	}
	
	public void resetItemFields(){
		Player p = Mule.pm.getCurrentPlayer();
		
		for(StoreItemGui item : storeItemGuis){
			item.resetAmount();
		}
		storeItemGuis.get(FOOD_SPOT).setOwned(p.getNumberOfResource(1)); //food
		storeItemGuis.get(ENERGY_SPOT).setOwned(p.getNumberOfResource(2)); //energy
		storeItemGuis.get(ORE_SPOT).setOwned(p.getNumberOfResource(3)); //ore
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
	
	
	/**
	 * This method will take mouseclicks on the town screen and pass the buck off to the correct store
	 * item gui
	 * @param x
	 * @param y
	 */
	public void mouseClicked(int x, int y){
		Player p = Mule.pm.getCurrentPlayer();
		int index = (x - this.x - BUFFER_X) / ((WIDTH - BUFFER_X) / (NUMBER_OF_ITEMS));
		if(index >= 0 && index < storeItemGuis.size()){
			if(storeItemGuis.get(index).plusPressed(x, Mule.HEIGHT - y)){
				inventory.buyResource(p, index);
			}
			else if(storeItemGuis.get(index).minusPressed(x, Mule.HEIGHT - y)){
				inventory.sellResource(p, index);
			}
		}
		setItemValues();
		storeItemGuis.get(FOOD_SPOT).setOwned(p.getNumberOfResource(1)); //food
		storeItemGuis.get(ENERGY_SPOT).setOwned(p.getNumberOfResource(2)); //energy
		storeItemGuis.get(ORE_SPOT).setOwned(p.getNumberOfResource(3)); //ore
	}
}
