package interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * A simple button that will remove the player from the creation screen
 * @author antonio
 *
 */
public class RemovePlayerButton extends Button{
	
	private final static String REMOVE_BUTTON_FILE = "SettingsScreen/RemovePlayerButton.jpg";
	private PlayerCreationInput input;
	
	public RemovePlayerButton(int x, int y, PlayerCreationInput inp){
		super(new Texture(Gdx.files.internal(REMOVE_BUTTON_FILE)), x, y);
		input = inp;
		super.addListener(new MyClickListener());
	}
	
	private class MyClickListener extends ClickListener{
		
		public void clicked(InputEvent e, float x, float y){
			input.removePlayer();
		}
	}

}
