package model;

public class Moveable extends FieldObject {

	public Point position;
	protected double y_velocity;
	protected double x_velocity;
	protected double grav_accel;
	protected double initX;
	protected double initY;

	
	public Moveable(double leftcoord, double rightcoord, double cieling, double floor, 
			Point place, double gravity) {
		super(leftcoord, rightcoord, cieling, floor);
		position = place;
		grav_accel = gravity;
		initX = position.getX();
		initY = position.getY();
		// TODO Auto-generated constructor stub
	}
	
	public void resetPosition() {
		position = new Point(initX, initY, leftcoord, rightcoord, cieling, floor);
		x_velocity = 0;
		y_velocity = 0;
	}

}
