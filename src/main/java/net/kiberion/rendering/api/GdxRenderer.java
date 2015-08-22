package net.kiberion.rendering.api;

import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;

public class GdxRenderer implements Renderer {

	@Getter
	private final Stage stage;

	public GdxRenderer(Stage stage) {
		super();
		this.stage = stage;
	}

	@Override
	public void render(float dt) {
		getStage().act(dt);
		getStage().draw();
	}

}
