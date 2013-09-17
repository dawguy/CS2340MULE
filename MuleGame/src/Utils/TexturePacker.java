package Utils;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

/**
 * Class to help pack images into a single larger Sprite.
 * @author David Wright
 */
public class TexturePacker {
	public static void main(String[] args){
		//Sample path C:\\Users\\David Wright\\Desktop\\RandomCoding\\GolfGame\\MyGolfGame-android\\assets\\images
		//Change this based on your current computer's setup. Because this will not be used that frequently
		//I am not that worried about having to manually set the file directory
		String filePath = "C:\\Users\\David Wright\\Images";
		
		TexturePacker2.process(filePath,
				filePath,
				"textures.pack");
	}
}