package screens;

import interfaces.MyTextInputListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.me.mygdxgame.Mule;

/**
 * A screen that represents the initial game settings. The user will be allowed to change various settings
 * including the number of players, their race, name, and color.
 * @author antonio
 * @since 9/16/13
 */
public class SettingsScreen implements Screen{

	public Mule currentGame;
	
	private Texture background;
	
	private SpriteBatch batch;
	
	private MyTextInputListener textListener;
	
	private boolean wasMouseClicked;
	
	public SettingsScreen(Mule g){
		super();
		currentGame = g;
		background = new Texture("SettingsScreen/GameSettingsScreen.jpeg");
		batch = new SpriteBatch();
		textListener = new MyTextInputListener();
		wasMouseClicked = true;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();
		
		update(delta);
	}
	
	private void update(float delta){
		if(Mule.INPUT.mouseClicked() && !wasMouseClicked){
			wasMouseClicked = true;
			Gdx.input.getTextInput(textListener, "Test", "AHH");
		} else if(!Mule.INPUT.mouseClicked() && wasMouseClicked){
			wasMouseClicked = false;
		}
		System.out.println(textListener.getData());
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}