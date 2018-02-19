package controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import model.Field;

public class Controller {
	private Field f;
	
	public Controller(Field f, Scene s) {
		s.setOnKeyPressed(new EventHandler<KeyEvent> () {

			@Override
			public void handle(KeyEvent event) {
				synchronized (f) {
					switch (event.getCode()) {
					case A:
						f.slimeone.x_start(false);;
						break;
					case W:
						f.slimeone.jump();
						break;
					case D:
						f.slimeone.x_start(true);
						break;
					case UP:
						f.slimetwo.jump();
						break;
					case LEFT:
						f.slimetwo.x_start(false);
						break;
					case RIGHT:
						f.slimetwo.x_start(true);
						break;
					default:
						break;
					}
				}
				
			}
			
		});
		
		s.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case A:
					f.slimeone.x_stop();
					break;
				case D:
					f.slimeone.x_stop();
					break;
				case LEFT:
					f.slimetwo.x_stop();
					break;
				case RIGHT:
					f.slimetwo.x_stop();
					break;
				default:
					break;
				}
			}
			
		});
	}

}
