package com.github.kneelawk.hellogdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu extends ScreenAdapter {

	private Stage stage;

	public MainMenu() {
		stage = new Stage(new ScreenViewport());

		TextureAtlas atlas = new TextureAtlas(Gdx.files.absolute("skins/default-k/default-k.atlas"));

		Skin skin = new Skin();
		skin.addRegions(atlas);
		skin.load(Gdx.files.absolute("skins/default-k/default-k.json"));

		Table table = new Table(skin);
		table.setFillParent(true);
		stage.addActor(table);

		Image image = new Image(skin.getDrawable("hello-libgdx"));
		table.add(image).colspan(3);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
