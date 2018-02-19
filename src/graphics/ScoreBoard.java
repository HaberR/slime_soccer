package graphics;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Field;

public class ScoreBoard {
	private Text left;
	private Text right;
	private Field f;
	
	
	public ScoreBoard(Group game, Field f, int width, int height) {
		this.f = f;
		VBox v = new VBox();
		game.getChildren().add(v);
		HBox h = new HBox();
		v.getChildren().add(h);
		h.setAlignment(Pos.TOP_CENTER);
		left = new Text();
		right = new Text();
		left.setFill(Color.SKYBLUE);
		right.setFill(Color.RED);
		left.setFont(Font.font(30));
		right.setFont(Font.font(30));
		h.setPrefWidth(width);
		h.setSpacing(100);
		h.getChildren().add(left);
		h.getChildren().add(right);
		update();
	}
	public void update() {
		left.setText(((Integer) f.leftscore).toString());
		right.setText(((Integer) f.rightscore).toString());
	}

}
