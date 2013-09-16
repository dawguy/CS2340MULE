package com.me.mygdxgame;

import interfaces.InputHandler;

import com.badlogic.gdx.Game;

import screens.*;

/**
 * The setup class for the entire game. This class will instantiate the screens and
 * create the input handler.
 * @author antonio
 *
 */
public class Mule extends Game {

	public static TitleScreen TITLESCREEN;
	
	public static InputHandler INPUT;
	
	public static final int HEIGHT = 480;
	public static final int WIDTH = 640;
	
	@Override
	public void create() {
		TITLESCREEN = new TitleScreen(this);
		INPUT = new InputHandler();
		this.setScreen(TITLESCREEN);
	}
	
}