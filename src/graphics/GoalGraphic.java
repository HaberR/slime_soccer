package graphics;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Goal;

public class GoalGraphic {
	public static void makeGoal(Goal g, Pane p, double width, double height) {
		ImageView iv = new ImageView(new Image(g.leftSide() ? "leftgoal.png" : "rightgoal.png"));
		//iv.setPreserveRatio(true);
		iv.setFitWidth((g.goalLength / g.length) * width);
		iv.setFitHeight((g.goalHeight/ g.height) * height);
		p.getChildren().add(iv);
	}

}
