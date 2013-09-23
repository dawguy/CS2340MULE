package interfaces;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.mygdxgame.Mule;

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
	
	/**
	 * This method will return the point of the mouse where the origin is the bottom left of
	 * the computer.
	 * @return a point representing the mouse's position
	 */
	public Point getMousePoint(){
		return new Point(input.getX(), Mule.HEIGHT - input.getY());
	}
}
