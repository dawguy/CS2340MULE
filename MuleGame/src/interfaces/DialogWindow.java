package interfaces;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.Mule;

/**
 * This class is used to draw a simple window overlay that displays a message.
 * @author Eric Rabinowitz
 */
public class DialogWindow {
	private final int WIDTH = 600;
	private final int HEIGHT = 100;
	private BitmapFont font;
	private Button closeButton;
	private int x;
	private int y;

	private String message;

	public DialogWindow(String m, int x, int y){
		message = m;
		font = new BitmapFont();
		this.x = x;
		this.y = y;
		closeButton = new Button(new Texture(Gdx.files.internal("TownScreen/CloseWindowButton.jpg")), x, y+HEIGHT-24);
	}

	public void draw(SpriteBatch batch){
		ShapeRenderer sr = new ShapeRenderer();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.BLACK);
		sr.rect(x, y, WIDTH, HEIGHT);
		sr.end();

		batch.begin();
		font.drawWrapped(batch, message, x+20, y+60, WIDTH-40);
		batch.end();

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