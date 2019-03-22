import java.util.*;
import java.io.*;

public class CS60813BSheth {

	public static void main(String[] args) {

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                            Assignment 13B - LUP Using Binary Tree                               ");
		System.out.println("*********************************************************************************");
		System.out.println();

		LUPTree lup = new LUPTree();	
		lup.readFile();
		lup.process();	

	}
}


class LUPTree{



	private static class Node{

		private Node left;
		private Node right;
		private int element;

		Node(Node left, Node right, int element){
			this.left = left;
			this.right = right;
			this.element = element;
		}	

	}

	private static Stack<Node>  hangerHeads = new Stack();

	private Node root = null;

	private List<String> fileData = new ArrayList<>();

	void LUPTree() {
		root=null;
	}

	void process() {

		int n = fileData.size() , i = 0;
		while(i < n) {
			String temp = fileData.get(i);
			temp = temp.replaceAll("( )+", " ");//tabs removal
			temp = temp.replaceAll("\\s", " ");//space bar hits removal
			String[] items = temp.split(" ");

			int array[] = new int[items.length];
			for(int k = 0 ;k<items.length; k++)
				array[k] = Integer.parseInt(items[k]);
			generateLUP(array);
			System.out.println("\n\n");
			i++;
		}

	}


	void generateLUP(int array[]) {

		LUPTree tree = new LUPTree();
		Node currentNode = null;
		for (int i = 0; i<array.length ; i++)
		{
			if(tree.isEmpty())
				tree.insertAtBeginning(array[i]);
			else {
				currentNode = tree.root;
				while(currentNode.right!= null && array[i] > currentNode.right.element ) {
					currentNode = currentNode.right;
				}
				if(currentNode.right == null)
				{
					tree.insertAtEnd(array[i]);
					//System.out.println("end : " + array[i]);
				}
				else
				{
					//System.out.println("between : " + array[i] + " top " + currentNode.element + " bottom  " + currentNode.right.element);
					tree.insertInBetween(array[i], currentNode);

				}
			}

		}

		//tree.printTree();
		tree.printHangers();
		tree.printLUP();

	}

	void printLUP()
	{

		this.buildHangerHeadsStack();

		Node lastNode = hangerHeads.pop();

		int last = lastNode.element;

		Node hangerHead = hangerHeads.pop(); //last but one

		System.out.print(last + "  ");


		Node currentNode = hangerHead; 

		while(hangerHead!=null) 
		{
			int max = -1;
			while(currentNode!=null)
			{
				if( currentNode.element < last && currentNode.element > max  )
					max = currentNode.element;

				currentNode = currentNode.left;

			}

			last = max;

			System.out.print(last + "  ");
			if(!hangerHeads.isEmpty())		  
				currentNode = hangerHead = hangerHeads.pop(); 			   			  
			else
				break;
		}


	}

	void buildHangerHeadsStack() {
		Node currentNode = root;

		hangerHeads.push(currentNode);

		while(currentNode.right!=null)
		{ 
			currentNode = currentNode.right ;

			hangerHeads.push(currentNode);
		}

	}

	void printHangers() {
		Node currentNode = root;
		int i = 0;
		System.out.println();

		while(currentNode!=null)
		{
			i++;

			Node currentNode2 = currentNode;
			System.out.print(" Hanger " + i + " : " );

			System.out.print(currentNode.element + "  ");

			while(currentNode2.left!=null) {
				System.out.print(currentNode2.left.element + "  ");
				currentNode2 = currentNode2.left;
			}
			System.out.println();

			currentNode = currentNode.right;
		}
	}
	void printTree() 
	{
		Node currentNode = root;
		System.out.println();
		while(currentNode!=null)
		{
			Node currentNode2 = currentNode;
			System.out.print(currentNode.element + "");
			System.out.print("(");
			while(currentNode2.left!=null) {
				System.out.print(currentNode2.left.element + " ");
				currentNode2 = currentNode2.left;
			}
			System.out.print(")");
			currentNode = currentNode.right;
		}
	}
	//returns true if list is empty
	public boolean isEmpty() {
		return(root == null);		
	}

	//inserts a node at the end of the LUP Tree
	public void insertAtEnd(int element) {
		Node currentNode = root;
		if( root != null ) {
			while(currentNode.right!=null)
				currentNode = currentNode.right;
			currentNode.right = new Node(null,null,element);
		}

	}	

	//insert in between
	public void insertInBetween(int newElement, Node prevNode) {
		Node temp = prevNode.right.right;
		Node newNode = new Node(prevNode.right, prevNode.right.right, newElement );
		prevNode.right = newNode;
		temp = newNode;
	}

	//inserts a node at the beginning of the linked list
	public void insertAtBeginning(int element) {
		root = new Node(null,null,element);
	}

	//empties linked list
	public void clear() {
		root = null;
	}

	void readFile() { 
		try {

			String line;
			FileReader fileReader = new FileReader("LUPData.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);	

			while ((line = bufferedReader.readLine()) != null) {
				fileData.add(line);
			}

			fileReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in File Reading");
		}
	}


}
