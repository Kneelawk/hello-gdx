package com.github.kneelawk.hellogdx;

import com.badlogic.gdx.Screen;

public abstract class ScreenGame implements Screen {

	protected boolean initialized = false;

	public void init() {
	}

	public void showGame() {
	}

	public void hideGame() {
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public final void show() {
		if (!initialized) {
			init();
			initialized = true;
		}
		showGame();
	}

	@Override
	public final void hide() {
		hideGame();
	}

}
