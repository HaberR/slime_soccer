package graphics;

import javafx.scene.Group;
import model.Field;

public class SlimesLayer extends Layer{
	private SlimeGraphic slimeone;
	private SlimeGraphic slimetwo;
	
	public SlimesLayer(Field f, Group g, int width, int height) {
		super(f, g, width, height);
		slimeone = new SlimeGraphic(ap, f.slimeone, width, height, true);
		slimetwo = new SlimeGraphic(ap, f.slimetwo, width, height, false);
		
	}


	@Override
	public void update() {
		slimeone.update();
		slimetwo.update();
	}

}
