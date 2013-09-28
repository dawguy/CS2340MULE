package interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This class represents a simple dropdown menu. It acts similar to other actors allowing it to interface with the
 * stage.
 * @author antonio
 *
 */
public class DropMenu extends Actor{

	private final int DEFAULT_WIDTH = 100;
	private final int DEFAULT_HEIGHT = 25;
	private final int FONT_Y_SPACING = 25;
	
	private String[] items;
	
	private String currentItem;
	
	private boolean isMenu;
	
	private BitmapFont font;
	
	private DropMenu(){
		super();
	}
	
	public DropMenu(String[] i){
		this();
		items = i;
		currentItem = i[0];
		isMenu = false;
		super.setWidth(DEFAULT_WIDTH);
		super.setHeight(DEFAULT_HEIGHT);
		font = new BitmapFont();
		
		super.addListener(new MyClickListener());
	}
	
	public DropMenu(String[] i, int x, int y){
		this(i);
		setX(x);
		setY(y);
	}
	
	private void switchState(){
		if(isMenu){
			isMenu = false;
			super.setWidth(DEFAULT_WIDTH);
			super.setHeight(DEFAULT_HEIGHT);
		} else{
			isMenu = true;
			super.setWidth(DEFAULT_WIDTH);
			super.setHeight(DEFAULT_HEIGHT * items.length);
		}
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		if(isMenu){
			drawDropMenu(batch, parentAlpha);
		} else{
			drawDefault(batch, parentAlpha);
		}
	}
	
	private void drawDefault(SpriteBatch batch, float parentAlpha){
		batch.end();
		drawOutlineRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		
		batch.begin();
		batch.setColor(Color.BLACK);
		font.setColor(Color.BLACK);
		font.draw(batch, currentItem, getX(), getY() + FONT_Y_SPACING);
	}
	
	private void drawOutlineRect(float x, float y, float w, float h){
		ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.rect(x, y, w, h);
		sr.end();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.BLACK);
		sr.rect(x, y, w, h);
		sr.end();
	}
	
	private void drawDropMenu(SpriteBatch batch, float parentAlpha){
		batch.end();
		for(int i = 0 ; i < items.length ; i ++){
			drawOutlineRect(getX(), getY() + (DEFAULT_HEIGHT * i), DEFAULT_WIDTH, DEFAULT_HEIGHT);
			batch.begin();
			font.draw(batch, items[i], getX(), getY() + (DEFAULT_HEIGHT * i) + FONT_Y_SPACING);
			batch.end();
		}
		batch.begin();
	}
	
	public String getCurrentItem(){
		return currentItem;
	}
	
	private class MyClickListener extends ClickListener{
		
		public void clicked(InputEvent event, float x, float y){
			int index = (int)(y / DEFAULT_HEIGHT);
			currentItem = items[index];
			switchState();
		}
	}
}
