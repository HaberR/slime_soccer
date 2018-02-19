package model;

public class Point extends FieldObject {
	private double xcoord;
	private double ycoord;
	public Point (double x, double y, double l, double r, double up, double d) {
		super(l,r,up,d);
		xcoord = x;
		ycoord = y;
	}
	
	public void change(double dx, double dy) {
		xcoord += dx;
		ycoord += dy;
	}
	public void ground() {
		ycoord = this.floor;
	}
	
	public boolean isGrounded() {
		return ycoord == floor;
	}
	
	public boolean illegal() {
		return xcoord > rightcoord || xcoord < leftcoord || ycoord > this.cieling || ycoord < this.floor;
	}
	
	public void legalize() {
		if (xcoord > rightcoord) {
			xcoord = rightcoord;
		}
		else if (xcoord < leftcoord) {
			xcoord = leftcoord;
		}
		if (ycoord > cieling) {
			ycoord = cieling;
		}
		else if (ycoord < floor) {
			ycoord = floor;
		}
	}
	
	public double getX() {
		return xcoord;
	}
	
	public double getY() {
		return ycoord;
	}
	
	public String toString() {
		return new String(xcoord + ", " + ycoord);
	}
	
	public void copy(Point p) {
		xcoord = p.xcoord;
		ycoord = p.ycoord;
	}
	
	/**Returns true if an object moving in a straight line from one to two
	 * passes through the line determined by lone, to ltwo. Its imperfect but it should work
	 * for small enough distances between one and two. <br>
	 * Requires that lone and ltwo are Points in a horizontal line
	 * @param one the starting point
	 * @param two the ending point
	 * @param lone the left coordinate of the line
	 * @param ltwo the right coordinate of the line
	 * @return
	 */
	public static boolean crosses(Point one, Point two, Point lone, Point ltwo) {
		if (two.getX() > lone.getX() && two.getX() < ltwo.getX()) {
			if (one.getY() > lone.getY() && two.getY() < lone.getY()) {
				return true;
			}
		}
		return false;
	}
}
