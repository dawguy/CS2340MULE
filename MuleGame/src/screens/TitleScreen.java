package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.me.mygdxgame.Mule;

/**
 * The main title screen that will contain the title, a background image, and our names.
 * @author antonio
 *
 */
public class TitleScreen implements Screen{

	private Mule currentGame;
	
	public TitleScreen(Mule g){
		currentGame = g;
	}
	
	@Override
	public void render(float delta) {
		update(delta);		
	}

	private void update(float delta){
		if(currentGame.INPUT.mouseClicked()){
			Gdx.app.exit();
		}
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
