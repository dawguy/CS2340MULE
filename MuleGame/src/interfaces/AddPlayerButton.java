package interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * A simple class that will allow for the adding of players to the PlayerCreationInput
 * @author antonio
 *
 */
public class AddPlayerButton extends Button{

	private final static String ADD_BUTTON_FILE = "SettingsScreen/AddPlayerButton.jpg";
	private PlayerCreationInput input;
	
	public AddPlayerButton(int x, int y, PlayerCreationInput inp){
		super(new Texture(Gdx.files.internal(ADD_BUTTON_FILE)), x, y);
		input = inp;
		super.addListener(new MyClickListener());
	}
	
	private class MyClickListener extends ClickListener{
		
		public void clicked(InputEvent e, float x, float y){
			input.addPlayer();
		}
	}
}
