package graphics;


import java.util.Timer;
import java.util.TimerTask;

import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Field;

public class Graphics extends Application{

	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		int width = 600;
		int height = 500;
		Group game = new Group();
		Scene s = new Scene(game);
		primaryStage.setScene(s);
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.show();
		Field f = new Field();

		setupGoals(f, game, width, height);
		SlimesLayer slimes = new SlimesLayer(f, game, width, height);
		BallLayer ball = new BallLayer(f, game, width, height);
		DualLayer d = new DualLayer(slimes, ball);
		ScoreBoard sb = new ScoreBoard(game, f, width, height);
		Timer t = new Timer();
		refresh(t, d, sb);
		Controller c = new Controller(f, s);
		//primaryStage.setOnCloseRequest(new EventHandler() {
		//	
		//});
	}

	private static void refresh(Timer t, DualLayer d, ScoreBoard sb) {
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						d.step();
						d.update();
						sb.update();
					}
				});
			}
		};
		t.schedule(tt, 30, 30);
	}

	private void setupGoals(Field f, Group g, double width, double height) {
		VBox v = new VBox();
		HBox h = new HBox();
		g.getChildren().add(v);
		v.setAlignment(Pos.BOTTOM_CENTER);
		v.setMinHeight(height - 23);
		v.getChildren().add(h);
		//h.setPrefWidth(width);
		//h.setAlignment(Pos.BASELINE_CENTER);
		h.setSpacing(width * (1 - f.leftGoal.goalLength/f.leftGoal.length * 2));
		GoalGraphic.makeGoal(f.leftGoal, h, width, height);
		GoalGraphic.makeGoal(f.rightGoal, h, width, height);
		
	}
}
