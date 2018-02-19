package graphics;

import javafx.scene.Group;
import model.Field;

public class BallLayer extends Layer {

	private BallGraphic ball;
	
	public BallLayer(Field f, Group g, int width, int height) {
		super(f, g, width, height);
		ball = new BallGraphic(ap, f.ball, width, height);
	}

	@Override
	public void update() {
		ball.update();	
	}

}
