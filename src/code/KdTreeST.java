package code;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

/**
 * CSIS 2420
 * KD-Trees Assignment 
 * @author David Jones and Mason Parry
 */
public class KdTreeST<Value> 
{
    private Node root; // Root of KDTree
    private int size;
    
    private class Node 
    {
        private Point2D p; // keys
        private Value val; // value
        private RectHV rect;
        private Node left, right;

        public Node(Point2D p, Value val, RectHV rect) {
            this.p = p;
            this.val = val;
            this.rect = rect;
        }
    }
	
	//construct an empty symbol table of points
	public KdTreeST()
	{
		root = null;
		size = 0;
	}
	
	//is the symbol table empty?
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	//number of points
	public int size()
	{
		return size;
	}
	
	//associate the value val with point p
	public void put(Point2D p, Value val) {
		root = put(root, p, val, false);
    }

    private Node put(Node x, Point2D p, Value val, boolean vertical) {
    	if (x == null) {
    		size++;
    		return new Node(p, val, new RectHV(-Double.MAX_VALUE, -Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));
    	}
    	double cmp;
		if(vertical)
        	cmp = p.y()-x.p.y();
    	else
    		cmp = p.x()-x.p.x();
    	if (cmp == 0) {
    		x.val = val;
    		return x;
    	}
    	else if (cmp < 0) {
    		x.left = put(x.left, p, val, !vertical);
    		return x;
    	}
    	else {
    		x.right = put(x.right, p, val, !vertical);
    		return x;
    	}
    }
	
	//value associated with point p
	public Value get(Point2D p)
	{
		return get(root, p, false);
	}
	
	// return value associated with the given key rooted in tree at x.
    private Value get(Node x, Point2D p, boolean vertical) {
    	if (x == null) return null;
    	double cmp;
		if(vertical)
        	cmp = p.y()-x.p.y();
    	else
    		cmp = p.x()-x.p.x();
    	if (cmp == 0) return x.val;
    	else if (cmp < 0)  return get(x.left, p, !vertical);
    	return get(x.right, p, !vertical);
    }
	
	//does the symbol table contain point p?
	public boolean contains(Point2D p)
	{
		return get(p) != null;
	}
	
	//all points in the symobl table
	public Iterable<Point2D> points()
	{
        Queue<Point2D> p = new Queue<Point2D>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            p.enqueue(x.p);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return p;
	}
	
	//all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect)
	{
		Stack<Point2D> s = new Stack<Point2D>();
		return(range(root, rect, s));
	}
	
	private Iterable<Point2D> range(Node x, RectHV rect, Stack<Point2D> s)
	{
		if(x==null) return s;
		if(!x.rect.intersects(rect)) return s;
		if (rect.contains(x.p)) s.push(x.p);
		range(x.left, rect, s);
		range(x.right, rect, s);
		return s;
	}
	
	//a nearest neighbor to point p; null if the symbol table is empty
	public Point2D nearest(Point2D p)
	{
		if(p == null)
			return null;
		
		//start at the root
		Node currentNode = root;
		
		//check current node left and right nodes and compare distances to find
		//out which subtree to traverse
		while(currentNode.left != null && currentNode.right != null)
		{
			double dist = p.distanceSquaredTo(currentNode.p);
			double distL = p.distanceSquaredTo(currentNode.left.p);
			double distR = p.distanceSquaredTo(currentNode.right.p);
			
			if(distL < distR && distL < dist)
			{
				currentNode = currentNode.left;
			}
			else if(distR < distL && distR < dist)
			{
				currentNode = currentNode.right;
			}
			else if(dist <= distL && dist <= distR)
			{
				return currentNode.p;
			}
			
		}
		return null;
    }
    
    public static void main(String[] args) {
		RectHV rect1 = new RectHV(0,1,2,5);
		RectHV rect2 = new RectHV(-1,2,3,4);
		Point2D point1 = new Point2D(3, 0.5);
		Point2D point2 = new Point2D(6, 2);
		Point2D point3 = new Point2D(2,0);
		
		KdTreeST<Integer> st = new KdTreeST<Integer>();
		
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
		
		
		In input = new In("src/resources/input100K.txt");
		KdTreeST<String> kd = new KdTreeST<String>();
		int count = 0;
		while(input.hasNextLine())
		{
			String[] str = input.readLine().split(" ");
			Point2D p = new Point2D(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
			kd.put(p, "Point " + count);
			count++;
		}
		
		
		long bT = System.currentTimeMillis();
		int count2 = 0;
		for(Point2D p : kd.points())
		{
			if(count2 >= 1000)
				break;			
			kd.nearest(p);
			count2++;
		}
		
		System.out.println((System.currentTimeMillis() - bT) * 0.001);
		System.out.println();
	}
}
