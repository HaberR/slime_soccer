package graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Ball;
import model.SlimeHead;

public class BallGraphic {
	private AnchorPane ap;
	private ImageView iv;
	private double width;
	private double height;
	private Ball b;
	
	public BallGraphic (AnchorPane ap, Ball b, double width, double height) {
		iv = new ImageView(new Image("ball.png"));
		iv.setPreserveRatio(true);
		iv.setFitWidth((b.radius * 2 / b.length) * width);
		ap.getChildren().add(iv);
		this.ap = ap;
		this.width = width;
		this.height = height;
		this.b = b;
		update();
	}

	public void update() {
		AnchorPane.clearConstraints(iv);
		AnchorPane.setLeftAnchor(iv, (b.position.getX() - b.radius) /b.length * width);
		AnchorPane.setBottomAnchor(iv, b.position.getY() /b.height * height);
	}

}
