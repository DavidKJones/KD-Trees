/******************************************************************************
 *  Name: David Jones & Mason Parry
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name: Mason Parry & David Jones
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 5: Kd-Trees


/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/
	It based on the node data type used in bst.java, except it size isn't tracked
	in the node and we also keep track of the rectangle for that node. Each node
	has two children left and right. Left is less then the current node and right
	is greater then.
	
/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/
	It uses a private recursive method that will check if the point in that node
	intersects wit the predefined range. If it does then we check it's two children
	as well. If it does intersect with the range then we push that point into a stack
	and when all succesful points have been checked we return the stack.

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/
	We compare the distance from the defined point to all the points inside the
	tree. We keep track of which point is currently closest and iterate through.

/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Show the math how you used to determine
 *  the operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt

input1M.txt



/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/


/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/




/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on  how helpful the class meeting was and on how much you learned 
 * from doing the assignment, and whether you enjoyed doing it.
 *****************************************************************************/
