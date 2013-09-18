package screens;

import gameObjects.Player;
import gameObjects.PlayerHandler;
import interfaces.MyTextInputListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
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
	
	private BitmapFont basicFont;
	
	private PlayerHandler players;
	
	private Table tableLayout;
	
	private TextField field;
	
	private TextFieldStyle style;
	
	public SettingsScreen(Mule g){
		super();
		currentGame = g;
		background = new Texture("SettingsScreen/GameSettingsScreen.jpeg");
		batch = new SpriteBatch();
		basicFont = new BitmapFont();
		players = new PlayerHandler(4);
		
		style = new TextFieldStyle();
		style.font = new BitmapFont();
		style.fontColor = Color.BLACK;
		
		field = new TextField("TEST", style);
		field.setDisabled(false);
		field.setMessageText("TEST2");
		field.setTextFieldListener(new TextFieldListener(){

			@Override
			public void keyTyped(TextField textField, char key) {
				textField.setText("" + key);
				System.out.println("OK");
			}
			
		});
		tableLayout = new Table();
		tableLayout.setHeight(100);
		tableLayout.setWidth(100);
		tableLayout.setPosition(100, 100);
		tableLayout.add(field);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		
		batch.begin();
		
		batch.draw(background, 0, 0);
		
		tableLayout.draw(batch, 1);		
		
		batch.end();
		
		update(delta);
	}
	
	private void update(float delta){

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
