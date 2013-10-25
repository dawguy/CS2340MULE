package interfaces;

import gameObjects.Player;

import java.awt.Rectangle;

import managers.PlayerManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.mygdxgame.Mule;

public class ResourceTracker extends Actor{
	private static Texture image;
	private Rectangle rect;
	private PlayerManager pm;
	
	public ResourceTracker(){
		pm = Mule.pm;
		loadTexture();
	}
	
	
	public void loadTexture(){
		image = new Texture("Skins/ResourceTab.png");
	}
	
	/**
	 * Draws the resources to the screen. Will definitely have to be changed with higher quality graphics.
	 * @param sb
	 */
	public void draw(SpriteBatch sb){
		//Draws template to top of screen
		sb.draw(image, 0, Mule.HEIGHT - 80, Mule.WIDTH, image.getHeight());
		int initialOffset = 110;
		int sectionOffset = 133;
		int energyOff = 30;
		int foodOff = 31;
		int oreOff = 32;
		int crystOff = 32;
		int yOff = 43;
		//Draws each players resources and other info.
		sb.end();
		for(int i = 0; i < pm.getNumberOfPlayers(); i++){
			sb.begin();
			int offset = initialOffset + sectionOffset * (i) + i * 3;
			Player p = pm.getPlayer(i);
			String name = p.getName();
			String money = "" + p.getNumberOfResource(0);
			String food = "" + p.getNumberOfResource(1);
			String energy = "" + p.getNumberOfResource(2);
			String ore = "" + p.getNumberOfResource(3);
			String time = ""+ (Mule.gm.getRoundTime() - Mule.gm.getCurrentPlayerTime());
			String crystite = "" + p.getNumberOfResource(4);
			sb.setColor(p.getColor());	//Doesn't seem to set color correctly
			BitmapFont bf = new BitmapFont(Gdx.files.internal("Skins/default.fnt"), Gdx.files.internal("Skins/default.png"), false);
			offset += energyOff;
			bf.draw(sb, energy, offset, Mule.HEIGHT - yOff);
			bf.draw(sb, time, 10, Mule.HEIGHT - 58);
			bf.draw(sb, name, offset, Mule.HEIGHT - 6);
			bf.draw(sb,  money, offset, Mule.HEIGHT - 26);
			offset += foodOff;
			bf.draw(sb, food, offset, Mule.HEIGHT - yOff);
			offset += oreOff;
			bf.draw(sb, ore, offset, Mule.HEIGHT - yOff);
			offset += crystOff;
			bf.draw(sb, crystite, offset, Mule.HEIGHT - yOff);
			sb.end();
			bf.dispose();
		}
		sb.setColor(Color.WHITE);
		sb.begin();
	}
}
