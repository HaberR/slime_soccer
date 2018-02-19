package graphics;

public class DualLayer {
	SlimesLayer slimes;
	BallLayer ball;
	
	public DualLayer(SlimesLayer one, BallLayer two) {
		slimes = one;
		ball = two;
	}
	
	public void update() {
		slimes.update();
		ball.update();
	}
	
	public void step() {
		slimes.f.nextStep();
	}

}
