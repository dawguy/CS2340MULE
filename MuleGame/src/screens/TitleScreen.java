package screens;

import java.io.File;

import interfaces.Button;
import managers.XMLReader;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Mule;

/**
 * The main title screen that will contain the title, a background image, and our names.
 * @author antonio
 *
 */
public class TitleScreen implements Screen{

	private Mule currentGame;
	private Texture titleBackground;
	private SpriteBatch batch;
	
	private Button exitGameButton;
	private Button startGameButton;
	private Button loadGameButton;
	
	public TitleScreen(Mule g){
		Texture.setEnforcePotImages(false);
		currentGame = g;
		titleBackground = new Texture(Gdx.files.internal("TitleScreen/TitleScreenBackground2.jpeg"));
		batch = new SpriteBatch();
		exitGameButton = new Button(new Texture(Gdx.files.internal("TitleScreen/ExitButton.jpeg")), 350, 100);
		startGameButton = new Button(new Texture(Gdx.files.internal("TitleScreen/StartButton.jpeg")), 350, 200);
		loadGameButton = new Button(new Texture(Gdx.files.internal("TitleScreen/LoadButton.jpeg")),350,25);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		
		batch.begin();
		batch.draw(titleBackground, 0, 0);
		exitGameButton.draw(batch);
		startGameButton.draw(batch);
		loadGameButton.draw(batch);
		batch.end();
	}

	private void update(float delta){
		if(currentGame.INPUT.mouseClicked()){
			if(exitGameButton.isClicked(currentGame.INPUT)){
				Gdx.app.exit();
			} else if(startGameButton.isClicked(currentGame.INPUT)){
				currentGame.setScreen(Mule.SETTINGSCREEN);
			} else if(loadGameButton.isClicked(currentGame.INPUT)){
				XMLReader xRead = new XMLReader(currentGame);
				xRead.loadGame();
			}
		}
	}
	
	public void startMusic(){
		Audio audio = Gdx.audio;
		Music test = audio.newMusic(Gdx.files.internal("Sounds/Test.mp3"));
		test.setLooping(true);
		test.play();
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
