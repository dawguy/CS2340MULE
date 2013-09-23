package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Mule Game";
		cfg.useGL20 = false;
		cfg.width = com.me.mygdxgame.Mule.WIDTH;
		cfg.height = com.me.mygdxgame.Mule.HEIGHT;
		
		new LwjglApplication(new com.me.mygdxgame.Mule(), cfg);
	}
}
