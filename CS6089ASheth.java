import java.io.*;
import java.util.*;

public class CS6089ASheth {

	public static void main(String[] args) throws NumberFormatException, IOException , java.util.InputMismatchException
	{
		int inputArray[];

		Functions fn = new Functions();
		fn.openFile();
		inputArray = fn.readFile();
		System.out.println("Input tree elements are");
		for(int i = 0 ; i< inputArray.length ; i++)
			System.out.print(inputArray[i] + " ");
		System.out.println();
		Main tm = new Main();
		tm.process(inputArray);
	}
}

class Main{

	void process(int[] inputData) throws NumberFormatException, IOException , java.util.InputMismatchException{

		BSTTree bst = new BSTTree();
		
		for(int i = 0 ; i < inputData.length ; i++)
		{
			bst.insertStart(inputData[i]);
		}
		
		System.out.println("\nInOrder Traversal BST");
		bst.inOrder(bst.root);

		System.out.println("\n\nHeight of BST tree : " + bst.heightOfTree(bst.root));
	
		System.out.println("\nLevel Order of BST");
		bst.levelOrder(bst.root);
		System.out.println("\nNumber of Nodes in a BST tree is " + bst.numberOfNodes(bst.root));

		System.out.println("\nLargest elemenet in tree BST is " + bst.largest(bst.root));
		
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
					boolean result = false;
					
					result = bst.search(bst.root, input);
				
					System.out.println("Does element " + input + " exist in the tree BST ? "  + result );
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
class BSTNodeClass{
	BSTNodeClass left;
	BSTNodeClass right;
	int element;

	public BSTNodeClass() {}
	public BSTNodeClass(int n)
	{
		left = null;
		right = null;
		element = n;
	}
}

class BSTTree{
	public BSTNodeClass root;
	BSTTree(){}
	void insertStart(int element) {
		root = insert(root,element);
	}
	BSTNodeClass insert(BSTNodeClass node,int element) {
		if(node == null) {
			node = new BSTNodeClass(element);
		}
		else {
			if(element <= node.element)
				node.left = insert(node.left , element);
			else
				node.right = insert(node.right , element);
		}
		return node;
	}
	void inOrder(BSTNodeClass node) {
		if(node!= null) {
			inOrder(node.left);
			System.out.print(" "+ node.element );
			inOrder(node.right);
		}
	}

	int heightOfTree(BSTNodeClass node) {
	
		if(node == null)
			return -1;
		else
		{
			int lheight = heightOfTree(node.left);

			 int rheight = heightOfTree(node.right);

			if(lheight > rheight)
				return lheight + 1 ;
			else
				return rheight + 1 ;
		}
	}

	void printElementsOnALevel(BSTNodeClass node, int level) {
		if(node == null)
			return;
		if(level == 0) System.out.print(node.element + " ");
		else if (level > 0)
		{
			printElementsOnALevel(node.left , level-1);
			printElementsOnALevel(node.right, level-1);

		}
	}

	void levelOrder(BSTNodeClass node) {
		int n = heightOfTree(node);
		for(int i = 0 ; i <= n ; i++ )
		{
			printElementsOnALevel(node, i);
			System.out.println();
		}
	}

	int numberOfNodes(BSTNodeClass node) {
		int count;
		if(node == null)
			return 0;
		else {
			count = numberOfNodes(node.left) + numberOfNodes(node.right) + 1;
		}
		return count;
	}


	int largest(BSTNodeClass node) {
		if(node == null) return -1;
		while(node.right != null) {
			node = node.right;
		}
		return node.element;
	}

	boolean search(BSTNodeClass node , int key) {
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

class Functions {
	private Scanner fileScan;         //to read the file
	private int[] inputArray ;        //for BST
	private int[] backupArray;        //for AVL
	private Integer[] arrayClass;

	// this is a constructor to print the program output header
	Functions(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                       Assignment 9A -  BST Tree Operations                      ");
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

