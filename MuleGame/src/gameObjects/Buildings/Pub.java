package gameObjects.Buildings;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This class will represent the pub in the game.
 * @author antonio
 *
 */
public class Pub extends Building{
	
	private final static String IMAGE_LOCATION = "TownScreen/Pub.jpeg";
	
	public Pub(Stage s){
		super(s, IMAGE_LOCATION);
	}
	
	public Pub(Stage s, int posX, int posY){
		super(s, IMAGE_LOCATION, posX, posY);
	}
}
