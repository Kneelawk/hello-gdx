package com.github.kneelawk.hellogdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.bullet.Bullet;

public class HelloGdx extends Game {

	private HelloGdxScreen helloGdx;

	@Override
	public void create() {
		Bullet.init();

		helloGdx = new HelloGdxScreen();

		setScreen(helloGdx);
	}
}
