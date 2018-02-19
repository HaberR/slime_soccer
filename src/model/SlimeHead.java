package model;

public class SlimeHead extends Moveable{
	private double forward_increment;
	private double jump_vel;
	public double radius;
	/**Class Invariant: the place represents the position of the bottom center part of the
	 * SlimeHead
	 */
	/**keeps track of the direction of the last move. True for forward, false otherwise;*/
	private boolean direction;
	private boolean moving;
	
	public SlimeHead (Point place, double gravity, double forwardMovement, double jumpVel, double radius,
			double l, double r, double up, double fl) {
		super (l,r,up,fl, place, gravity);
		forward_increment = forwardMovement;
		jump_vel = jumpVel;
		this.radius = radius;
		direction = false;
		x_velocity = 0;
		moving = false;
	}
	
	public void move() {
		x_move();
		y_move();
	}
	
	private void x_move() {
		position.change(x_velocity, 0);
		if (position.illegal()) {
			position.legalize();
		}
	}

	private void y_move() {
		y_velocity = y_velocity - grav_accel;
		position.change(0, y_velocity);
		if (position.illegal()) {
			position.legalize();
			y_velocity = 0;
		}
	}

	public void x_start(boolean forward) {
		x_velocity = forward ? forward_increment : -forward_increment;
		direction = forward;
		moving = true;
	}
	
	public void x_stop() {
		x_velocity = 0;
		moving = false;
	}
	
	public boolean isMoving() {
		return moving;
	}
	/**requires isMoving to have been called in order to ensure that it is appropriate
	 * to request a direction. <br>
	 * returns: true for forward, false otherwise.
	 * @return
	 */
	public boolean whichDirection() {
		return direction;
	}
	
	public void jump() {
		y_velocity = jump_vel;
	}
	
	public double getRadius() {
		return radius;
	}
}
