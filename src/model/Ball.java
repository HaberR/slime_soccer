package model;


public class Ball extends Moveable {
	private double decelerate;
	private double restitution;
	private double ythresh;
	private double goalRange;
	public double radius;
	private double hitspeed;
	private Point oldposition;
	private Point llgoal;
	private Point lrgoal;
	private Point rlgoal;
	private Point rrgoal;
	private double goalheight;
	
	public Ball(Point p, double left, double right, double up, double down,
			double gravity, double friction, double coeffrestitution,
			double y_threshold, double goalRange, double goalHeight, 
			double ballradius, double hitspeed) {
		super(left,right,up,down, p, gravity);
		oldposition = new Point(0, 0, left, right, up, down);
		oldposition.copy(p);
		decelerate = friction;
		restitution = coeffrestitution;
		ythresh = y_threshold;
		this.goalRange = goalRange;
		goalheight = goalHeight;
		radius = ballradius;
		this.hitspeed = hitspeed;
		llgoal = new Point(leftcoord, goalHeight, leftcoord, rightcoord, cieling, floor);
		lrgoal = new Point(leftcoord + goalRange, goalHeight, leftcoord, rightcoord, cieling, floor);
		rlgoal = new Point(rightcoord - goalRange, goalHeight, leftcoord, rightcoord, cieling, floor);
		rrgoal = new Point(rightcoord, goalHeight, leftcoord, rightcoord, cieling, floor);
	}
	
	
	public void hit(SlimeHead sh) {
		double yoverx = getRatio(sh);
		/*double totalVel = (sh.y_velocity - y_velocity);
		if (sh.isMoving()) {
			double change = sh.whichDirection() ? sh.forward_increment - x_velocity : -sh.forward_increment - x_velocity;
			change *= change < 0 ? -1 : 1;
			totalVel += change;
		} else {
			double change = x_velocity < 0 ? - x_velocity : x_velocity;
			totalVel += change;
		}*/
		if ((position.getX() > sh.position.getX() && x_velocity < 0) ||
				(position.getX() < sh.position.getX() && x_velocity > 0)) {
			x_velocity *= -1;
		}
		if (position.getX() > sh.position.getX()) {
			x_velocity += hitspeed;
		} else if (position.getX() < sh.position.getX()) {
			x_velocity -= hitspeed;
		}
		if (y_velocity < 0) {
			y_velocity *= -1;
		}
		else if (y_velocity == 0) {
			y_velocity += 8;
		}
		//double totalVel = hitspeed + y_velocity;
		//x_velocity = totalVel / (1 + yoverx);
		//y_velocity = totalVel - x_velocity;
		//System.out.println(x_velocity);
		//System.out.println(y_velocity);

	}
	
	public void move() {
		y_move();
		x_move();
	}
	
	private void y_move() {
		y_velocity = y_velocity - grav_accel;
		oldposition.copy(position);
		position.change(0, y_velocity);
		if (position.illegal()) {
			position.legalize();
			y_velocity = -y_velocity * restitution;
			if (y_velocity < ythresh) {
				y_velocity = 0;
			}
		}
		if (Point.crosses(oldposition, position, llgoal, lrgoal) || 
				Point.crosses(oldposition, position, rlgoal, rrgoal)) {
			y_velocity = -y_velocity * restitution;
			if (y_velocity < ythresh) {
				y_velocity = 0;
			}
			oldposition.copy(position);
			position.change(0, y_velocity);
		}
	}


	private void x_move() {
		if (position.isGrounded()) {
			//x_velocity = x_velocity - decelerate < 0 ? 0 : x_velocity - decelerate;
			if (x_velocity > 0) {
				x_velocity = x_velocity - decelerate < 0 ? 0 : x_velocity - decelerate;
			} else {
				x_velocity = -x_velocity - decelerate < 0 ? 0 : x_velocity + decelerate;
			}
		}
		position.change(x_velocity, 0);
		if (inRange()) {
			setChanged();
			notifyObservers();
		}
		if (position.illegal()) {
			//position.legalize();
			x_velocity = -x_velocity * restitution;
			position.legalize();
			//position.change(x_velocity, 0);
		}
	}


	private double getRatio(SlimeHead sh) {
		double diff = position.getX() - sh.position.getX();
		double y = Math.sqrt(sh.getRadius() * sh.getRadius() - diff * diff);
		return y/diff;
		
	}
	
	private boolean inRange() {
		return (position.getX() + goalRange > rightcoord && position.getY() < goalheight) ||
				(position.getX() < leftcoord + goalRange && position.getY() < goalheight);
	}
	

}
