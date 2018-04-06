package code;

import edu.princeton.cs.algs4.Point2D;

/**
 * CSIS 2420
 * KD-Trees Assignment 
 * @author David Jones and Mason Parry
 */
public class RectHV 
{
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	
	//construct the rectangle [xmin, xmax] x [ymin, ymax]
	public RectHV( double xmin, double xmax, double ymin, double ymax)
	{
		if(xmin > xmax || ymin > ymax)throw new IllegalArgumentException("The min values need to be less than the max values of the rectangle.");
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	//minimum x-coordinate of rectangle
	public double xMin()
	{
		return xmin;
	}
	
	//maximum x-coordinate of rectangle
	public double xMax()
	{
		return xmax;
	}
	
	//minimum y-coordinate of rectangle
	public double yMin()
	{
		return ymin;
	}
	
	//maximum y-coordinate of rectangle
	public double yMax()
	{
		return ymax;
	}
	
	//does this rectangle contain the point p (either inside or on boundary)?
	public boolean contains(Point2D p)
	{
		if(p.x() <= xmax && p.x() >= xmin && p.y() <= ymax && p.y() >= ymin) return true;
		
		return false;
	}
	
	//does this rectangle intersect the rectangle (at one or more points)?
	public boolean intersects(RectHV that)
	{
		//return false if we are dealing with the same rectangle
		if(equals(that))
			return false;
		
		if(xmin > that.xMax() || xmax < that.xMin() || ymin > that.yMax() || ymax < that.yMin())
			return false;
		
		return true;
	}
	
	//square of Euclidean distance from point p to closest point in rectangle
	public double distanceSquaredTo(Point2D p)
	{
		//no need to calculate distance if the point is within the rectangle
		if(contains(p))
			return 0;
		
		double x;
		double y;
		
		//get the x-coordinate
		if(p.x() < xmin) x = xmin;
		else if(p.x() > xmax) x = xmax;
		else
		{
			x = (xmax - p.x()) - xmin;
		}
		
		//get the y-coordinate
		if(p.y() < ymin) y = ymin;
		else if(p.y() > ymax) y = ymax;
		else
		{
			y = (ymax - p.y()) - ymin;
		}
		
		Point2D rectanglePoint = new Point2D(x,y);
		
		return rectanglePoint.distanceSquaredTo(p);
	}
	
	//does this rectangle equal that object?
	public boolean equals(Object that)
	{
		if(that == null)return false;
		
		if(that == this)return true;
		
		//if y is Board class return false
		if(that.getClass() != this.getClass())return false;
		
		//cast y as Board
		RectHV otherRect = (RectHV)that;
		
		//check to see if the values within the rectangle are the same
		if(otherRect.xMax() != xmax)return false;
		if(otherRect.xMin() != xmin)return false;
		if(otherRect.yMax() != ymax)return false;
		if(otherRect.yMin() != ymin)return false;
		
		return true;
	}
	
	//string representation
	public String toString()
	{
		return String.format("Rectangle:[%f, %f] x [%f, %f]", xmin, xmax, ymin, ymax);
	}
}
