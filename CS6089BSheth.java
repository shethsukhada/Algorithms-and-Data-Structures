import java.io.*;
import java.util.*;

public class CS6089BSheth {

	public static void main(String[] args) throws NumberFormatException, IOException , java.util.InputMismatchException
	{
		int inputArray[];
		Wrapper wp = new Wrapper();
		wp.openFile();
		inputArray = wp.readFile();
		System.out.println("Input tree elements are");
		for(int i = 0 ; i< inputArray.length ; i++)
			System.out.print(inputArray[i] + " ");
		System.out.println();
		TreesMain tm = new TreesMain();
		tm.process(inputArray);
	}
}

class TreesMain{

	void process(int[] inputData) throws NumberFormatException, IOException , java.util.InputMismatchException{

		BSTExample bst = new BSTExample();
		AVLExample avl = new AVLExample();

		double starttime=System.nanoTime();
		for(int i = 0 ; i < inputData.length ; i++)
		{
			bst.insertStart(inputData[i]);
		}
		double endtime=System.nanoTime();
		System.out.println("\nTime taken to build BST is : "+ (endtime-starttime));

		starttime=System.nanoTime();
		for(int i = 0 ; i < inputData.length ; i++)
		{
			avl.insertStart(inputData[i]);		
		}
		endtime=System.nanoTime();
		System.out.println("Time taken to build AVL  is : "+ (endtime-starttime));

		System.out.println("\nInOrder Traversal BST");
		bst.inOrder(bst.root);
		System.out.println("\nInOrder Traversal AVL");
		avl.inOrder(avl.root);

		System.out.println("\n\nHeight of BST tree : " + bst.heightOfTree(bst.root));

		System.out.println("Height of AVL tree : " + avl.heightOfTree(avl.root));

		System.out.println("\nLevel Order of BST");
		bst.levelOrder(bst.root);
		System.out.println("\nLevel Order of AVL");
		avl.levelOrder(avl.root);

		//System.out.println("Number of Nodes in a tree" + bst.numberOfNodes(bst.root));

		System.out.println("\nLargest elemenet in tree BST is " + bst.largest(bst.root));
		System.out.println("Largest elemenet in tree AVL is " + avl.largest(avl.root));

		System.out.println();
		int input = 1;
		//Scanner sc = new Scanner(System.in) ;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(input != 0)
		{
			System.out.println("\nEnter number to be searched in both the trees. Enter 0 to cancel");
			try{

				input=Integer.parseInt(br.readLine());
				if(input != 0)
				{
					boolean result = false;;
					starttime = System.nanoTime();
					result = bst.search(bst.root, input);
					endtime = System.nanoTime();
					
					System.out.println("Does element " + input + " exist in the tree BST ? "  + result + "\nTime taken to search : " + ( endtime -starttime )); 
					
					starttime = System.nanoTime();
					result = avl.search(avl.root, input);
					endtime = System.nanoTime();
					System.out.println("Does element " + input + " exist in the tree AVL ? " + result + "\nTime taken to search : " + ( endtime -starttime )); 
				}
				else
					System.out.println("Process Terminated.Bye!");
			}
			catch(Exception e)
			{
				System.out.println("Please enter number only");
			}
		}
	}
}
class BSTNode{
	BSTNode left;
	BSTNode right;
	int element;

	public BSTNode() {}
	public BSTNode(int n)
	{
		left = null;
		right = null;
		element = n;
	}
}

class AVLNode{
	AVLNode left;
	AVLNode right;
	int element;
	int height;

	public AVLNode() {
		left=right=null;
		element=height=0;

	}
	public AVLNode(int n)
	{
		left = null;
		right = null;
		element = n;
		height = 0;
	}
}

class BSTExample{
	public BSTNode root;
	BSTExample(){}
	void insertStart(int element) {
		root = insert(root,element);
	}

	BSTNode insert(BSTNode node,int element) {
		if(node == null) {
			node = new BSTNode(element);
		}
		else {
			if(element <= node.element)
				node.left = insert(node.left , element);
			else
				node.right = insert(node.right , element);
		}
		return node;
	}

	void inOrder(BSTNode node) {
		if(node!= null) {
			inOrder(node.left);
			System.out.print(" "+ node.element );
			inOrder(node.right);
		}
	}

	int heightOfTree(BSTNode node) {
		if(node == null)
			return -1;
		else
		{
			int lheight = heightOfTree(node.left);

			int rheight = heightOfTree(node.right);

			if(lheight > rheight)
				return lheight + 1;
			else
				return rheight + 1;
		}
	}

	void printElementsOnALevel(BSTNode node, int level) {
		if(node == null)
			return;
		if(level == 0) System.out.print(node.element + " ");
		else if (level > 0)
		{
			printElementsOnALevel(node.left , level-1);
			printElementsOnALevel(node.right, level-1);
		}
	}

	void levelOrder(BSTNode node) {
		int n = heightOfTree(node);
		for(int i = 0 ; i <= n ; i++ )
		{
			printElementsOnALevel(node, i);
			System.out.println();
		}
	}

	int numberOfNodes(BSTNode node) {
		int count;
		if(node == null)
			return 0;
		else {
			count = numberOfNodes(node.left) + numberOfNodes(node.right) + 1;
		}
		return count;
	}


	int largest(BSTNode node) {
		if(node == null) return -1;
		while(node.right != null) {
			node = node.right;
		}
		return node.element;
	}

	boolean search(BSTNode node , int key) {
		if(node == null)
			return false;
		if(node.element == key)
			return true;
		else {
			if( key < node.element)
				return search(node.left,key);
			else
				return search(node.right,key);
		}
	}
}


class AVLExample{
	AVLNode root;
	AVLExample(){
		root=null;
	}
	void insertStart(int element) {
		root=insert(element,root);
	}

	AVLNode insert(int element, AVLNode node) {
		if(node==null)
			node=new AVLNode(element);
		else if(element<node.element) {
			node.left=insert(element,node.left);
			if(heightOfSubtree(node.left)-heightOfSubtree(node.right)==2) {
				if(element<node.left.element)
					node=singleRightRotation(node);
				else
					node=doubleRightRotation(node);
			}
		}
		else if(element > node.element) {
			node.right=insert(element,node.right);
			if(heightOfSubtree(node.right)-heightOfSubtree(node.left)==2) {
				if(element>node.right.element)
					node=singleLeftRotation(node);
				else
					node=doubleLeftRotation(node);
			}
		}
		else {
			System.out.println("Element already exist in the tree");
		}
		node.height=larger(heightOfSubtree(node.left),heightOfSubtree(node.right))+1;
		return node;
	}

	AVLNode returnroot() {
		return root;
	}
	int heightOfSubtree(AVLNode node) {
		if(node==null)
			return -1;
		else return(node.height);
	}

	int larger(int a, int b) {
		if(a>b)
			return a;
		else
			return b;
	}

	private AVLNode singleRightRotation(AVLNode node) {
		AVLNode temp =node.left;
		node.left=temp.right;
		temp.right=node;
		node.height=larger(heightOfSubtree(node.left),heightOfSubtree(node.right))+1;
		temp.height=larger(heightOfSubtree(temp.left),temp.height)+1;
		return temp;
	}

	private AVLNode singleLeftRotation(AVLNode node) {
		AVLNode temp =node.right;
		node.right=temp.left;
		temp.left=node;
		node.height=larger(heightOfSubtree(node.left),heightOfSubtree(node.right))+1;
		temp.height=larger(heightOfSubtree(temp.left),node.height)+1;
		return temp;
	}

	private AVLNode doubleRightRotation(AVLNode node) {
		return singleRightRotation(node);
	}

	private AVLNode doubleLeftRotation(AVLNode node) {
		node.right=singleRightRotation(node.right);
		return singleLeftRotation(node);
	}

	void inOrder(AVLNode node) {
		if(node!= null) {
			inOrder(node.left);
			System.out.print(" "+ node.element );
			inOrder(node.right);
		}
	}

	int heightOfTree(AVLNode node) {
		if(node == null)
			return -1;
		else
		{
			int lheight = heightOfTree(node.left);

			int rheight = heightOfTree(node.right);

			if(lheight > rheight)
				return lheight + 1;
			else
				return rheight + 1;
		}
	}

	void printElementsOnALevel(AVLNode node, int level) {
		if(node == null)
			return;
		if(level == 0) System.out.print(node.element + " ");
		else if (level > 0)
		{
			printElementsOnALevel(node.left , level-1);
			printElementsOnALevel(node.right, level-1);
		}
	}

	void levelOrder(AVLNode node) {
		int n = heightOfTree(node);
		for(int i = 0 ; i <=n ; i++ )
		{
			printElementsOnALevel(node, i);
			System.out.println();
		}
	}

	int numberOfNodes(AVLNode node) {
		int count;
		if(node == null)
			return 0;
		else {
			count = numberOfNodes(node.left) + numberOfNodes(node.right) + 1;
		}
		return count;
	}


	int largest(AVLNode node) {
		if(node == null) return -1;
		while(node.right != null) {
			node = node.right;
		}
		return node.element;
	}

	boolean search(AVLNode node , int key) {
		if(node == null)
			return false;
		if(node.element == key)
			return true;
		else {
			if( key < node.element)
				return search(node.left,key);
			else
				return search(node.right,key);
		}
	}
}
class Wrapper {
	private Scanner fileScan;         //to read the file
	private int[] inputArray ;        //for BST
	private int[] backupArray;        //for AVL
	private Integer[] arrayClass;

	// this is a constructor to print the program output header
	Wrapper(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("             Assignment 9A&B -  BST and AVL Trre Operations                      ");
		System.out.println("*********************************************************************************");
		System.out.println(" Assumption - Height/Level of root is zero                                       ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("BSTData.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
			System.exit(1);
		}
	}

	// ************** read the file *************
	int[] readFile(){

		//array list to read the file data
		List<Integer> fileData = new ArrayList<>();

		//read each line until end of the file has reached
		if( fileScan != null ) {
			while(fileScan.hasNext()) {
				String line = fileScan.next();
				if( isNumeric(line) ==  true) {			
					//convert the string data to int type
					int i2 = Integer.parseInt(line); //using Integer as array list  cannot handle the primitive types like int
					//add int into the array list
					fileData.add(i2);
				}
			}
		}

		//build the array of Integer  
		arrayClass = new Integer[fileData.size()]; 
		arrayClass = fileData.toArray(arrayClass);
		//convert Integer[] to int[]
		inputArray = Arrays.stream(arrayClass).mapToInt(Integer::intValue).toArray();
		//backupArray = inputArray.clbst();
		return inputArray;
	}

	boolean isNumeric(String strNum) {
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}
}
