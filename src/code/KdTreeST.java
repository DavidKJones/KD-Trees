package code;

import edu.princeton.cs.algs4.Point2D;

/**
 * CSIS 2420
 * KD-Trees Assignment 
 * @author David Jones and Mason Parry
 */
public class KdTreeST<Value> 
{
	//construct an empty symbol table of points
	public KdTreeST()
	{
		
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
		
	}
	
	//value associated with point p
	public Value get(Point2D p)
	{
		return null;
	}
	
	//does the symbol table contain point p?
	public boolean contains(Point2D p)
	{
		return false;
	}
	
	//all points in the symobl table
	public Iterable<Point2D> points()
	{
		return null;
	}
	
	//all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect)
	{
		return null;
	}
	
	//a nearest neighbor to point p; null if the symbol table is empty
	public Point2D nearest(Point2D p)
	{
		return null;
	}
}
