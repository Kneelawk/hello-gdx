package com.github.kneelawk.hellogdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu extends ScreenGame {

	private Stage stage;

	private HelloGdx application;
	private Array<String> games;

	public MainMenu(HelloGdx application, Array<String> games) {
		this.application = application;
		this.games = games;
	}

	@Override
	public void init() {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

		stage = new Stage(new FitViewport(800, 480));

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skins/default-k/default-k.atlas"));

		Skin skin = new Skin();
		skin.addRegions(atlas);
		skin.load(Gdx.files.internal("skins/default-k/default-k.json"));

		Table table = new Table(skin);
		table.setFillParent(true);
		table.defaults().pad(5);
		stage.addActor(table);

		Image image = new Image(skin.getDrawable("hello-libgdx"));
		table.add(image).colspan(2);

		table.row();

		List<String> list = new List<>(skin);
		list.setItems(games);
		ScrollPane listScroller = new ScrollPane(list, skin);
		listScroller.setWidth(400);
		table.add(listScroller).colspan(2).fillX();

		table.row();

		TextButton quit = new TextButton("Quit", skin);
		table.add(quit).fillX();

		quit.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				application.exit();
			}
		});

		TextButton play = new TextButton("Play", skin);
		table.add(play).fillX();

		play.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				application.play(list.getSelected());
			}
		});
	}

	@Override
	public void showGame() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hideGame() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void disposeGame() {
		stage.dispose();
	}

}
