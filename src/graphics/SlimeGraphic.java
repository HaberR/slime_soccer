package graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.SlimeHead;

public class SlimeGraphic {
	private AnchorPane ap;
	ImageView iv;
	SlimeHead sh;
	double width;
	double height;
	
	public SlimeGraphic (AnchorPane ap, SlimeHead sh, double width, double height, boolean leftslime) {
		iv = new ImageView(new Image(leftslime ? "leftslime.png" : "rightslime.png"));
		iv.setPreserveRatio(true);
		iv.setFitWidth((sh.radius * 2 / sh.length) * width);
		ap.getChildren().add(iv);
		this.ap = ap;
		this.sh = sh;
		this.width = width;
		this.height = height;
		update();
	}

	public void update() {

		AnchorPane.clearConstraints(iv);
		AnchorPane.setLeftAnchor(iv, (sh.position.getX() - sh.radius) /sh.length * width);
		AnchorPane.setBottomAnchor(iv, (sh.position.getY()) /sh.height * height);
	}

}
