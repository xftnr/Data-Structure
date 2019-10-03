/*  Student information for assignment:
 *
 *  On my honor, <PENGDI XIA>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:px353
 *  email address:xiapengdi@yahoo.com
 *  Grader name:51925 
 *  Unique section number:Gilbert Maldonado
 *  Number of slip days I am using:0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinartSearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    // CS314 students. Add any other instance variables you want here
    private int size;
    // CS314 students. Add a default constructor here.
    public BinarySearchTree(){
    	root=null;
    	size =0;
    }
    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     */
    public boolean add(E value) {
    	//check the precondition
    	if(value == null){
    		throw new IllegalStateException("The add method value can not be null.");
    	}
    	//when the root is null
    	if(root==null){
    		root= new BSTNode<E>(value);
    		size++;
    		return true;
    	}
    	//recursive add method
        return recursiveAdd(root, value);
    }
    
    //the helper method
    private boolean recursiveAdd(BSTNode<E> position, E value){
        //base case, when the data already exist
        if(position.getData().compareTo(value)==0){
        	return false;
        }
    	//recursive to left
        else if(position.getData().compareTo(value)>0){
    		//since we do not need to back tracking so we can directly return recursive
        	//if is not the leaf then continue doing recursive
    		if(position.getLeft() != null){
    			return recursiveAdd(position.getLeft(), value);
    		}
    		//add element at the leaf
    		else{
    			position.setLeft(new BSTNode<E>(value));
    		}
        }
        //same recursive to right
    	else{
    		if(position.getRight() !=null){
    			return recursiveAdd(position.getRight(), value);
    		}
    		else{
    			position.setRight(new BSTNode<E>(value));
    		}
    	}
    	//remember to change the size
		size++;
		return true;
    }

    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {
    	//check the precondition
    	if(value == null){
    		throw new IllegalStateException("The remove method value can not be null.");
    	}
    	//recursive remove method
        return recursiveRemove(root, null, value);
    }
    
    private boolean recursiveRemove(BSTNode<E> current, BSTNode<E> pre, E value){
    	//base case 1 no target
    	if(current==null){
    		return false;
    	}
    	//base case 2, find the target
    	if(current.getData().compareTo(value)==0){
    		//when the target value is leaf
    		if(current.getLeft()==null && current.getRight()==null){
    			//to find out it belong to left child or right child
    			whichChild(null, pre, value);
    		}
    		//when the target is not leaf
    		//current has no left child
    		else if(current.getLeft()==null){
    			whichChild(current.getRight(),pre,value);
    		}
    		//current has no right child
    		else if(current.getRight()==null){
    			whichChild(current.getLeft(),pre,value);
    		}
    		//current has both children
    		else{
    			whichChild(current.getLeft(),pre,value);
    			//move the right child branch to end of the left child of right child
    			//it does not matter to move right to left or left to right
    			//i choose move right child to left right
    			rebuild(current);
    		}
    		return true;
    	}
    	//the recursive part to find the target
    	//in the binary tree no need for backtracking
    	else if(current.getData().compareTo(value)>0){
    		return recursiveRemove(current.getLeft(),current,value);
    	}
    	else
    		return recursiveRemove(current.getRight(),current,value);
    }
    
    //helper method to redundancy
    //To actually remove the target
    private void whichChild(BSTNode<E> target, BSTNode<E> pre, E value){
    	//exception when the height is 1 or 0
    	if(pre==null){
    		root=target;
    		size--;
    	}
    	else if(pre.getData().compareTo(value)>0){
			pre.setLeft(target);
			size--;
		}
		else{
			pre.setRight(target);
			size--;
		}
    }
    
    //helper method to redundancy
    //To rebuild tree since the target has child
    private void rebuild(BSTNode<E> current){
    	//current left child
    	BSTNode<E> temp=current.getLeft();
    	//find the left child`s most right child until no right child
    	while(temp.getRight()!=null){
    		temp=temp.getRight();
    	}
    	//put the current right child here
    	temp.setRight(current.getRight());
    }


    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value){
    	//check the precondition
    	if(value == null){
    		throw new IllegalStateException("The ispresent method value can not be null.");
    	}
    	//set up the local variable
    	BSTNode<E> temp=root;
    	//no recursive needed since it is sorted
    	while(temp!=null){
    		//find the target
    		if(temp.getData().compareTo(value)==0){
    			return true;
    		}
    		//the target is smaller
    		else if(temp.getData().compareTo(value)>0){
    			temp=temp.getLeft();
    		}
    		//the target is bigger
    		else
    			temp=temp.getRight();
    	}
        return false;
    }


    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
        return recursiveHeight(root);
    }
    
    //helper method to recursive
    private int recursiveHeight(BSTNode<E> current){
    	//base case
    	if(current == null){
    		//if it is empty return -1
    		return -1;
    	}
    	//find the max height in the tree
    	return Math.max(recursiveHeight(current.getLeft()), recursiveHeight(current.getRight()))+1;
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order. 
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        ArrayList<E> result= new ArrayList<E>();
        buildList(result, root);
    	return result;
    }
    
    //the helper method for recursive
    private void buildList(ArrayList<E> result, BSTNode<E> current){
    	//the condition which the current node is not null
    	if(current!=null){
    		//the ascending order is in-order of binary tree
    		buildList(result,current.getLeft());
    		result.add(current.getData());
    		buildList(result,current.getRight());
    	}
    }


    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max(){
    	//check precondition
    	if(size<=0){
    		throw new IllegalStateException("The max method value can not be null.");
    	}
    	BSTNode<E> temp = root;
    	//to find the most right child
    	while(temp.getRight()!=null){
    		temp= temp.getRight();
    	}
        return temp.getData();
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
    	//check precondition
    	if(size<=0){
    		throw new IllegalStateException("The min method value can not be null.");
    	}
    	BSTNode<E> temp = root;
    	//to find the most left child
    	while(temp.getLeft()!=null){
    		temp= temp.getLeft();
    	}
        return temp.getData();
    }

    /**
     * An add method that implements the add algorithm iteratively instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree, otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add, false otherwise.
     */
    public boolean iterativeAdd(E data) {
    	//check the precondition
    	if(data == null){
    		throw new IllegalStateException("The iterativeadd method value can not be null.");
    	}
    	//the special case when the tree is empty
    	if(root==null){
    		root=new BSTNode<E>(data);
    		size++;
    		return true;
    	}
    	BSTNode<E> current = root;
    	BSTNode<E> pre =null;
    	//find the path to the destination leaf
    	while(current!=null){
    		//record parents
    		pre = current;
    		//go left
    		if(current.getData().compareTo(data)>0){
    			current= current.getLeft();
    		}
    		//go right
    		else if(current.getData().compareTo(data)<0){
    			current= current.getRight();
    		}
    		//the data exists
    		else
    			return false;
    	}
    	//parent has new child which we add
    	//find which child belong to the parent
    	if(pre.getData().compareTo(data)>0){
			pre.setLeft(new BSTNode<E>(data));
			size++;
		}
		else{
			pre.setRight(new BSTNode<E>(data));
			size++;
		}
        return true;
    }


    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the 
     * smallest value (minimum) is returned. If kth = 1 the second smallest value is
     * returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
    	//check the precondition
    	if(kth < 0 && kth >= size()){
    		throw new IllegalStateException("The get method index is invalid.");
    	}
    	ArrayList<E> result= new ArrayList<E>();
    	findTarget(result,root,kth);
    	//output the last element in Array list
        return result.get(result.size()-1);
    }

    //helper method to find the target
    private void findTarget(ArrayList<E> result, BSTNode<E> current, int index){
    	if(index==result.size()-1){
    		return;
    	}
    	//the condition which the current node is not null
    	if(current!=null){
    		//the ascending order is in-order of binary tree
    		findTarget(result,current.getLeft(),index);
    		//to be efficiency, stop at when find the index
    		if(result.size()<=index){
    			result.add(current.getData());
    			findTarget(result,current.getRight(),index);
    		}
    	}
    }

    
    /**
     * Return a List with all values in this Binary Search Tree that are less than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than the parameter value. If there are
     * no values in this tree less than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllLessThan(E value) {
    	//check the precondition
    	if(value == null){
    		throw new IllegalStateException("The getlessthan method value can not be null.");
    	}
    	ArrayList<E> result= new ArrayList<E>();
        buildListLessThan(result, root, value);
    	return result;
    }

    //the helper method for recursive
    private void buildListLessThan(ArrayList<E> result, BSTNode<E> current, E value){
    	//the condition which the current node is not null
    	if(current!=null){
    		//the ascending order is in-order of binary tree
    		buildListLessThan(result,current.getLeft(),value);
    		//find the first element is smaller than target
    		if(current.getData().compareTo(value)<0){
    			result.add(current.getData());
    			buildListLessThan(result,current.getRight(),value);
    		}
    	}
    }
    
    /**
     * Return a List with all values in this Binary Search Tree that are greater than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater than the parameter value. If there are
     * no values in this tree greater than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
    	//check the precondition
    	if(value == null){
    		throw new IllegalStateException("The getlessthan method value can not be null.");
    	}
    	Stack<E> temp= new Stack<E>();
    	//since we are looking for the greater than so I looked up from right to left
        //the best case is when there is no greater than value
        //however, average is smaller than looking up form left to right
        buildListGreaterThan(temp, root, value);
        List<E> result=new ArrayList<E>();
        //pop the stack to make it in ascending order
        while(!temp.isEmpty()){
        	result.add(temp.pop());
        }
    	return result;
    }

    //the helper method for recursive
    private void buildListGreaterThan(Stack<E> result, BSTNode<E> current, E value){
    	//the condition which the current node is not null
    	if(current!=null){
    		//the descending order is in-order of binary tree
    		buildListGreaterThan(result,current.getRight(),value);
    		//find the first element is bigger than target
    		if(current.getData().compareTo(value)>0){
    			//put the data in stack
    			result.push(current.getData());
    			buildListGreaterThan(result,current.getLeft(),value);
    		}
    	}
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return depthrecur(root,d);
    }
    
    //help method for recursive
    private int depthrecur (BSTNode<E> current, int height){
    	//base case 1, end of the leaf
    	if(current==null){
    		return 0;
    	}
    	//base case 2, find the target height
    	if(height==0){
    		return 1;
    	}
    	//recursive
    	return depthrecur(current.getLeft(),height-1)+depthrecur(current.getRight(),height-1);
    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if(n != null){
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null); 
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,        
                BSTNode<E> initRight) {
            data = initValue; 
            left = initLeft; 
            right = initRight; 
        }

        public E getData() { 
            return data; 
        }

        public BSTNode<E> getLeft() { 
            return left;
        }

        public BSTNode<E> getRight() { 
            return right; 
        }

        public void setData(E theNewValue) { 
            data = theNewValue; 
        }

        public void setLeft(BSTNode<E> theNewLeft) { 
            left = theNewLeft; 
        }

        public void setRight(BSTNode<E> theNewRight) { 
            right = theNewRight; 
        }    
    }
}
