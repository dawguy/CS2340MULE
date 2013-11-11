package interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
	
	private String startString;
	
	private String currentItem;
	
	private boolean isMenu;
	
	private BitmapFont font;
	
	private boolean drawMenuDown;
	
	private boolean changed;
	
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
		
		drawMenuDown = true;
		
		super.addListener(new MyClickListener());
		
		startString = null;
		changed = true;
	}
	
	public DropMenu(String[] i, int x, int y){
		this(i);
		setX(x);
		setY(y);
	}
	
	public DropMenu(String[] i, int x, int y, boolean b){
		this(i, x, y);
		drawMenuDown = b;
	}
	
	public DropMenu(String[] i, int x, int y, boolean b, String defString){
		this(i, x, y, b);
		startString = defString;
		changed = false;
	}
	
	private void switchState(){
		if(isMenu){
			isMenu = false;
			super.setWidth(DEFAULT_WIDTH);
			super.setHeight(DEFAULT_HEIGHT);
			if(drawMenuDown){
				super.setPosition(getX(), getY() + (DEFAULT_HEIGHT * (items.length - 1)));
			}
		} else{
			changed = true;
			isMenu = true;
			super.setWidth(DEFAULT_WIDTH);
			super.setHeight(DEFAULT_HEIGHT * items.length);
			if(drawMenuDown){
				super.setPosition(getX(), getY() - (DEFAULT_HEIGHT * (items.length - 1)));
			}
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
		String s = currentItem;
		if(!changed){
			s = startString;
		}
		font.draw(batch, s, getX(), getY() + FONT_Y_SPACING);
	}
	
	private void drawOutlineRect(float x, float y, float w, float h){
		ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeType.Filled);
		//Color c = new Color(21/255, 143/255, 169/255, 1);
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
		int orientationFactor = 1;
		/*if(!drawMenuDown){
			orientationFactor *= -1;
		}*/
		for(int i = 0 ; i < items.length ; i ++){
			drawOutlineRect(getX(), getY() + (DEFAULT_HEIGHT * i * orientationFactor), DEFAULT_WIDTH, DEFAULT_HEIGHT);
			batch.begin();
			font.draw(batch, items[i], getX(), getY() + (DEFAULT_HEIGHT * i * orientationFactor) + FONT_Y_SPACING);
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
