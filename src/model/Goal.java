package model;

import java.util.Observable;
import java.util.Observer;

public class Goal extends FieldObject implements Observer{

	/**represents the bottem center portion of the goal;*/
	private Point pt;
	private boolean score;
	public double goalLength;
	public double goalHeight;
	private Ball b;
	/**tells if the old score hasn't yet been counted*/
	private boolean scoreable;
	private Point l;
	private Point r;
	public double height;
	
	
	public Goal(Point p, Ball b, double goallength, double goalHeight,
			double l, double r, double up, double down) {
		super(l,r,up,down);
		height = cieling - floor;
		pt = p;
		score = false;
		goalLength = goallength;
		this.goalHeight = goalHeight;
		this.b = b;
		b.addObserver(this);
		scoreable = true;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (scoreable) {
			setChanged();
			notifyObservers();
			scoreable = false;
		}
		
	}

	public void reset()	{
		scoreable = true;
	}
	
	public boolean leftSide() {
		return pt.getX() < (rightcoord - leftcoord)/2;
	}
	
	public Point leftPoint() {
		return l;
	}
	
	public Point rightPoint() {
		return r;
	}
}
