package com.github.kneelawk.hellogdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.bullet.Bullet;

public class HelloGdx extends Game {

	private MainMenu mainMenu;
	private HelloGdxScreen helloGdx;

	@Override
	public void create() {
		Bullet.init();

		mainMenu = new MainMenu();
		helloGdx = new HelloGdxScreen();

		setScreen(mainMenu);
	}

	@Override
	public void dispose() {
		super.dispose();
		mainMenu.dispose();
		helloGdx.dispose();
	}
}
