package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "MuleGame";
		cfg.useGL20 = false;
		cfg.width = Mule.WIDTH;
		cfg.height = Mule.HEIGHT;
		
		new LwjglApplication(new Mule(), cfg);
	}
}
