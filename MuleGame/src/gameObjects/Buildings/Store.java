package gameObjects.Buildings;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This class represents the store where the player can buy certain items
 * @author antonio
 *
 */
public class Store extends Building{

	public final static String IMAGE_LOCATION = "TownScreen/Store.jpeg";
	
	public Store(Stage s){
		super(s, IMAGE_LOCATION);
	}
	
	public Store(Stage s, int posX, int posY){
		super(s, IMAGE_LOCATION, posX, posY);
	}
}
