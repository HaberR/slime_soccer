package model;

import java.util.Observable;

public class FieldObject extends Observable {
	protected double leftcoord;
	protected double rightcoord;
	protected double cieling;
	protected double floor;
	public double length;
	public double height;
	
	public FieldObject(double leftcoord, double rightcoord, double cieling, double floor) {
		this.leftcoord = leftcoord;
		this.rightcoord = rightcoord;
		this.cieling = cieling;
		this.floor = floor;
		length = rightcoord - leftcoord;
		height = cieling - floor;
	}

}
