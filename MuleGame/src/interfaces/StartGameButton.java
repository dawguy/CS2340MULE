package interfaces;


import screens.SettingsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This button will communicate with start the game from the player config screen.
 * @author antonio
 *
 */
public class StartGameButton extends Button{

	private final static String START_BUTTON_FILE = "SettingsScreen/StartGameButton.jpg";
	//private PlayerCreationInput input;
	private SettingsScreen screen;
	
	public StartGameButton(int x, int y, PlayerCreationInput inp, SettingsScreen s){
		super(new Texture(Gdx.files.internal(START_BUTTON_FILE)), x, y);
		//input = inp;
		super.addListener(new MyClickListener());
		screen = s;
	}
	
	private class MyClickListener extends ClickListener{
		
		public void clicked(InputEvent e, float x, float y){
			screen.startGame();
		}
	}
}
