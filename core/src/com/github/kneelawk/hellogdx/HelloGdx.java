package com.github.kneelawk.hellogdx;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.github.czyzby.kiwi.util.gdx.collection.GdxArrays;

public class HelloGdx extends Game {

	private MainMenu mainMenu;

	private Map<String, Screen> games;

	@Override
	public void create() {
		Bullet.init();

		games = new HashMap<>();

		games.put("HelloGDX", new HelloGdxScreen(this));

		mainMenu = new MainMenu(this, GdxArrays.newArray(games.keySet()));

		setScreen(mainMenu);
	}

	public void exit() {
		Gdx.app.exit();
	}

	public void mainMenu() {
		setScreen(mainMenu);
	}

	public void play(String name) {
		setScreen(games.get(name));
	}

	@Override
	public void dispose() {
		super.dispose();
		mainMenu.dispose();
		for (Screen game : games.values()) {
			game.dispose();
		}
	}
}
