package model;

import java.util.Observable;
import java.util.Observer;

public class Field extends Observable implements Observer {
	public SlimeHead slimeone;
	public SlimeHead slimetwo;
	public Goal leftGoal;
	public Goal rightGoal;
	public Ball ball;
	private double leftCoord;
	private double rightCoord;
	private double floor;
	private double cieling;
	private double grav;
	private double goalLength;
	private double fric;
	private double coefrest;
	private double forwardincr;
	private double jumpVel;
	private double radius;
	private double ballradius;
	private double hitspeed;
	private double threshspeed;
	private double goalheight;
	public int leftscore;
	public int rightscore;
	
	
	public Field() {
		leftCoord = 0;
		rightCoord = 300;
		floor = 0;
		cieling = 200;
		goalLength = (rightCoord - leftCoord) / 6;
		goalheight = goalLength;
		grav = 1;
		fric = 1;
		coefrest = .8;
		forwardincr = 3;
		jumpVel = 10;
		radius = 15;
		ballradius = 8;
		hitspeed = 3;
		threshspeed = 2;
		ball = new Ball(makePoint((rightCoord - leftCoord)/2, floor), 
				leftCoord, rightCoord, cieling, floor,
				grav, fric, coefrest, threshspeed, goalLength, goalheight, 
				ballradius, hitspeed);
		leftGoal = new Goal(makePoint(leftCoord + goalLength / 2, floor), ball,
				goalLength, goalheight, leftCoord, rightCoord, cieling, floor);
		rightGoal = new Goal(makePoint(rightCoord - goalLength / 2, floor), ball,
				goalLength, goalheight, leftCoord, rightCoord, cieling, floor);
		slimeone = new SlimeHead(makePoint(leftCoord + 1.5 * goalLength, floor),
				grav, forwardincr, jumpVel, radius,
				leftCoord, rightCoord, cieling, floor);
		slimetwo = new SlimeHead(makePoint(rightCoord - 1.5 * goalLength, floor),
				grav, forwardincr, jumpVel, radius,
				leftCoord, rightCoord, cieling, floor);

		leftGoal.addObserver(this);
		rightGoal.addObserver(this);
		leftscore = 0;
		rightscore = 0;
	}
	
	
	private Point makePoint(double x, double y) {
		return new Point(x, y, leftCoord, rightCoord, cieling, floor);
	}


	@Override
	public void update(Observable o, Object arg) {
		if (ball.position.getX() < (rightCoord + leftCoord)/2) {
			rightscore++;
		}
		else if (ball.position.getX() > (rightCoord + leftCoord)/2) {
			leftscore ++;
		}
		ball.resetPosition();
		slimeone.resetPosition();
		slimetwo.resetPosition();
		leftGoal.reset();
		rightGoal.reset();
		//setChanged();
		//notifyObservers();
	}
	
	public void nextStep() {
		slimeone.move();
		slimetwo.move();
		ball.move();
		if (contact(slimeone)) {
			ball.hit(slimeone);
		} else if (contact(slimetwo)) {
			ball.hit(slimetwo);
		}
	}


	private boolean contact(SlimeHead sh) {
		double dx = sh.position.getX() - ball.position.getX();
		double dy = sh.position.getY() - ball.position.getY();
		double distsqu = dx * dx + dy * dy;
		return (distsqu <= sh.radius * sh.radius);
	}

}
