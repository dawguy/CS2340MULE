package interfaces;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Handles input for the entire game.
 * @author antonio
 *
 */
public class InputHandler extends InputListener{
	
	private Input input = Gdx.input;
	
	public InputHandler(){
		
	}
	
	public boolean mouseClicked(){
		return input.isButtonPressed(Input.Buttons.LEFT);
	}
	
	public Point getMousePoint(){
		return new Point(input.getX(), input.getY());
	}
}
