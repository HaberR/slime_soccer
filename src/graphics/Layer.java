package graphics;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import model.Field;

public abstract class Layer {
	/*private SlimeGraphic slimeone;
	private SlimeGraphic slimetwo;
	private BallGraphic ball;*/
	public Field f;
	protected AnchorPane ap;
	
	public Layer (Field f, Group g, int width, int height) {
		ap = new AnchorPane();
		ap.setPrefHeight(height- 23);
		ap.setMaxWidth(width);
		g.getChildren().add(ap);
		this.f = f;
		/*slimeone = new SlimeGraphic(ap, f.slimeone, width, height, true);
		slimetwo = new SlimeGraphic(ap, f.slimetwo, width, height, false);
		ball = new BallGraphic(ap, f.ball, width, height);*/
	}

	public abstract void update();/* {
		slimeone.update();
		slimetwo.update();
		ball.update();	
	}*/

}
