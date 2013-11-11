package screens;

import interfaces.PlayerCreationInput;
import managers.GameManager;
import managers.PlayerManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Mule;

/**
 * A screen that represents the initial game settings. The user will be allowed to change various settings
 * including the number of players, their race, name, and color.
 * @author antonio
 * @since 9/16/13
 */
public class SettingsScreen implements Screen{

	private final int BUFFER = 50;

	public Mule currentGame;

	private Texture background;

	private SpriteBatch batch;

	private PlayerCreationInput ui;

	public SettingsScreen(Mule g){
		super();
		currentGame = g;
		background = new Texture("SettingsScreen/GameSettingsScreen.jpeg");
		batch = new SpriteBatch();
		ui = new PlayerCreationInput(BUFFER, Mule.HEIGHT - BUFFER, Mule.WIDTH, Mule.HEIGHT, this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	

		batch.begin();

		batch.draw(background, 0, 0);

		batch.end();

		ui.draw(batch);
		update(delta);
	}

	private void update(float delta){
	
	}
	
	public boolean startGame(){
		PlayerManager manager = new PlayerManager();
		GameManager gameManager = new GameManager(currentGame, manager);
		Mule.gm = gameManager;
		Mule.pm = manager;
		ui.startGame(manager, gameManager);
		Mule.TOWNSCREEN.setGameManagerStoreInventory();
		Mule.SELECTTILESSCREEN = new SelectTilesScreen(currentGame);
		
		currentGame.setScreen(Mule.SELECTTILESSCREEN);
		
		//Gdx.app.exit();
		return true;
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
