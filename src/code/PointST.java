package code;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

/**
 * CSIS 2420
 * KD-Trees Assignment 
 * @author David Jones and Mason Parry
 */
public class PointST<Value> 
{
	private RedBlackBST<Point2D, Value> bst;
	
	//construct an empty symbol table of points
	public PointST()
	{
		bst = new RedBlackBST<Point2D, Value>();
	}
	
	//is the symbol table empty?
	public boolean isEmpty()
	{
		return true;
	}
	
	//number of points
	public int size()
	{
		return 0;
	}
	
	//associate the value val with point p
	public void put(Point2D p, Value val)
	{
		bst.put(p, val);
	}
	
	//value associated with point p
	public Value get(Point2D p)
	{
		return bst.get(p);
	}
	
	//does the symbol table contain point p?
	public boolean contains(Point2D p)
	{
		return bst.contains(p);
	}
	
	//all points in the symbol table
	public Iterable<Point2D> points()
	{
		return bst.keys();
	}
	
	//all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect)
	{	
		Stack<Point2D> s = new Stack<Point2D>();
		for( Point2D p : bst.keys())
		{
			if(rect.contains(p))
			{
				s.push(p);
			}
		}
		
		return s;
	}
	
	//a nearest neighbor to point p; null if the symbol table is empty
	public Point2D nearest(Point2D p)
	{
		//return null if the binary tree is empty
		if(bst.isEmpty())
			return null;
		
		Point2D closestPoint = null;
		double distance = Double.MAX_VALUE;
		
		//loops through all the points to find the closest one to p
		for(Point2D cP : points())
		{
			//dont want to compare the distance with itself (it would automatically win!)
			if(p.equals(cP))
				continue;
			
			double currentDist = cP.distanceSquaredTo(p);
			if(currentDist < distance)
			{
				distance = currentDist;
				closestPoint = cP;
			}
		}
		
		return closestPoint;
	}
	
	//unit testing
	public static void main(String[] args)
	{
		RectHV rect1 = new RectHV(0,1,2,5);
		RectHV rect2 = new RectHV(-1,2,3,4);
		Point2D point1 = new Point2D(3, 0.5);
		Point2D point2 = new Point2D(6, 2);
		Point2D point3 = new Point2D(2,0);
		
		PointST<Integer> st = new PointST<Integer>();
		
		st.put(point1, 0);
		st.put(point2, 1);
		st.put(point3, 2);
		
		System.out.println("Points:");
		for(Point2D p : st.points())
		{
			System.out.println(p);
		}
		
		System.out.println(String.format("The point %s has a value of %d.", point2, st.get(point2)));
		
		System.out.println(String.format("%s intersects with %s: %b", rect1, rect2, rect1.intersects(rect2)));
		
		System.out.println(String.format("The point %s intersects with %s: %b", point1, rect2, rect2.contains(point1)));
		System.out.println(String.format("The point %s intersects with %s: %b", point2, rect2, rect2.contains(point2)));
		
		System.out.println(String.format("Closest distance to %s from point %s is %f units.", rect2, point2, rect2.distanceSquaredTo(point2)));
		System.out.println(String.format("Closest distance to %s from point %s is %f units.", rect1, point3, rect1.distanceSquaredTo(point3)));
		
		System.out.println("All Points are within " + rect1.toString() + ": ");
		for(Point2D p : st.range(rect1))
		{
			System.out.println(p);
		}
	}
}
