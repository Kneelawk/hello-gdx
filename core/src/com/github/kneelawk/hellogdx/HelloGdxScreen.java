package com.github.kneelawk.hellogdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HelloGdxScreen extends ScreenGame {
	private HelloGdx application;
	private Stage stage;
	private ModelBatch models;
	private PerspectiveCamera cam;
	private Environment environment;
	private Model model;
	private ModelInstance instance;
	private float rotation;

	public HelloGdxScreen(HelloGdx application) {
		this.application = application;
	}

	@Override
	public void init() {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);

		stage = new Stage(new FitViewport(800, 480));

		Skin skin = new Skin();
		skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/default-k/default-k.atlas")));
		skin.load(Gdx.files.internal("skins/default-k/default-k.json"));

		Table root = new Table(skin);
		root.setFillParent(true);
		stage.addActor(root);

		ImageButton back = new ImageButton(skin.getDrawable("back-button"));
		root.add(back).width(32).height(32).expand().align(Align.topLeft);

		back.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				application.mainMenu();
			}
		});

		models = new ModelBatch();

		cam = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(4f, 4f, 6f);
		cam.lookAt(0f, 0f, 0f);
		cam.near = 0.01f;
		cam.far = 100f;
		cam.update();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		ObjLoader loader = new ObjLoader();
		model = loader.loadModel(Gdx.files.internal("BadLogicGames.obj"));
		model.materials.get(0).set(new Material(ColorAttribute.createDiffuse(new Color(0f, 0.4f, 0.6f, 1f))));
		instance = new ModelInstance(model);
	}

	@Override
	public void showGame() {
		Gdx.input.setInputProcessor(stage);

		rotation = 0;
	}

	@Override
	public void hideGame() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		rotation += 45 * delta;
		instance.transform.setToRotation(0f, 1f, 0f, rotation);

		models.begin(cam);
		models.render(instance, environment);
		models.end();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void disposeGame() {
		models.dispose();
		model.dispose();
		stage.dispose();
	}
}
