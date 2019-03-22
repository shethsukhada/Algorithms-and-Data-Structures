/* Program to build CBS and demonstrate BST,AVL,CBS comparisons
   Author - Sukhada Sheth  -  12/05/2018   */

import java.util.*;
import java.io.*;

public class CS60810BSheth
{
	public static void main(String[] args) {
		int[] array = new int[1000];
		FileOperation fo = new FileOperation();
		fo.openFile();
		array = fo.readFile();
		TreesMain tm = new TreesMain();
		tm.process(array);
	}
}

class TreesMain{

	void process(int[] inputData) 
	{
		BSTExample bst = new BSTExample();
		AVLExample avl = new AVLExample();
		CBSTree cbs = new CBSTree();

		//-----------------------------------------------------------------------------------------------		

		//...................building CBS..............................
		double starttime=System.nanoTime();
		for(int k = 0; k < 1000 ; k++)
		{
			cbs.addToTree(inputData[k]);
		}
		double endtime=System.nanoTime();
		System.out.println("Time taken to build CBS  is : "+ (endtime-starttime) + " nanoseconds");

		//...................building BST..............................
		starttime=System.nanoTime();
		for(int i = 0 ; i < inputData.length ; i++)
		{
			bst.insertStart(inputData[i]);
		}
		endtime=System.nanoTime();
		System.out.println("Time taken to build BST is : "+ (endtime-starttime) + " nanoseconds");

		//..................building AVL...............................
		starttime=System.nanoTime();
		for(int i = 0	 ; i < inputData.length ; i++)
		{
			avl.insertStart(inputData[i]);		
		}
		endtime=System.nanoTime();
		System.out.println("Time taken to build AVL  is : "+ (endtime-starttime)+ " nanoseconds");

		//-----------------------------------------------------------------------------------------------

		System.out.println("\nHeight of CBS tree : " + cbs.height(1));  

		System.out.println("Height of BST tree : " + bst.heightOfTree(bst.root));

		System.out.println("Height of AVL tree : " + avl.heightOfTree(avl.root));

		//----------------------------searching of keys --------------------------------------------------

		//...................CBS Search..............................

		int[] keysToSearch = {250,2504,2078,2158,3502,7138,6230,9661,1330,6136};
		int[] keysFound = new int[keysToSearch.length];
		int k=0;


		starttime=System.nanoTime();
		for(int i = 0 ; i < keysToSearch.length ; i++)
		{
			if ( cbs.searchKey(keysToSearch[i], 1) == true )
				keysFound[k++] = keysToSearch[i];
		}
		endtime=System.nanoTime();
		System.out.println("\nTime taken by CBS to search for 10 keys in search key array is : "
				+ (endtime - starttime) + " nanoseconds");
		System.out.print("List of keys found in CBS : ");
		for(int i = 0 ; i < keysToSearch.length ; i++)
		{   if(keysFound[i]!= 0)
			System.out.print( keysFound[i] + "  " );
		else
			break;
		}  

		//...................BST Search..............................

		k = 0;
		starttime=System.nanoTime();
		for(int i = 0 ; i < keysToSearch.length ; i++)
		{
			if ( bst.search(bst.root, keysToSearch[i]) == true )
				keysFound[k++] = keysToSearch[i];
		}
		endtime=System.nanoTime();
		System.out.println("\nTime taken by BST to search for 10 keys in search key array is : "
				+ (endtime - starttime) + " nanoseconds");
		System.out.print("List of keys found in BST : ");
		for(int i = 0 ; i < keysToSearch.length ; i++)
		{   if(keysFound[i]!= 0)
			System.out.print( keysFound[i] + "  " );
		else
			break;
		}  

		//.....................AVL Search............................

		k = 0;
		starttime=System.nanoTime();
		for(int i = 0 ; i < keysToSearch.length ; i++)
		{
			if ( avl.search(avl.root, keysToSearch[i]) == true )
				keysFound[k++] = keysToSearch[i];
		}
		endtime=System.nanoTime();
		System.out.println("\nTime taken by AVL to search for 10 keys in search key array is : "
				+ (endtime - starttime) + " nanoseconds");
		System.out.print("List of keys found in AVL : ");
		for(int i = 0 ; i < keysToSearch.length ; i++)
		{   if(keysFound[i]!= 0)
			System.out.print( keysFound[i] + "  " );
		else
			break;
		} 

	}
}

//BST tree definition
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

//AVL tree definition
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


//CBS tree operations
class CBSTree{

	private 	int CBSArray[] = new int[2047];
	private int subLast; //is the total number of elements in the CBS added so far including the current which is being processed
	private int INDEX;  //right most element is the tree
	private int DP = 0; //desired position where new element should be added to maintain the CBS properties
	private int NP = 0; //position derived as per the search tree rule for adding new element

	CBSTree(){
		subLast = 0;
	}

	void inorder(int sub) {
		if(sub <= subLast) {
			inorder(2*sub);
			System.out.println( " " + CBSArray[sub]);
			inorder(2*sub +  1);
		}
	}

	//go to the extreme left child and everytime new level is reached, increment a count
	int height(int sub)
	{
		int height = 0;
		while(sub*2 <= subLast)
		{
			sub = sub * 2;
			height++;
		}
		return height;
	}

	//adding elemenents one by one to tree
	void addToTree(int n) {

		//get the position of new element so that search tree properties are maintained
		NP = getNewPosition( n , 1);

		//DP is the position where new element must have been added to maintain the CBS properties.
		//so now adjust the entire tree to fill the DP instead of NP

		DP = subLast + 1;
		CBSArray[NP] = n;

		//if NP and DP are same, then no adjustments are required
		if( NP == DP)
		{   subLast = NP;
		updateINDEX();
		return;
		}

		//check if the NP is on the last level or one level below
		if( isNewLvl(NP) == true )
			//if its on new level i.e. one level below the last level
			fixNewLvlAddition(NP,DP);

		else
			//if its on same level
			fixSameLvlAddition(NP,DP);

		//every time new element is added update the INDEX - which is right most element in the tree
		updateINDEX();
	}


	void fixNewLvlAddition(int NP , int DP) {

		int inorder = 0 , next = NP;
		int temp , prevNo;

		prevNo = CBSArray[NP];
		CBSArray[NP] = 0;

		subLast = subLast + 1;

		//traverse from NP to DP and go on pushing element towards DP
		while(inorder != DP) {

			//traverse the inorder path while moving from NP to DP
			inorder = nextInOrder(next);
			temp = CBSArray[inorder];
			CBSArray[inorder] = prevNo;			
			next = inorder;
			prevNo = temp;

		}

		subLast = DP ;
	}

	void fixSameLvlAddition(int NP, int DP) {

		int inorder = 0;
		int prev = DP;
		subLast = subLast + 1;

		//traverse from DP to NP and go on pulling element towards NP
		while(inorder != NP) {

			//traverse the inorder path while moving from DP to NP
			inorder =  nextInOrder(prev);
			if(inorder == 0) {
				CBSArray[prev] = CBSArray[NP];
				CBSArray[NP] = 0;			
				break;
			}
			CBSArray[prev] = CBSArray[inorder];
			prev = inorder;
		}

		CBSArray[NP] = 0;
		subLast = DP ;

	}

	// get the right most element subscript
	void updateINDEX() {
		while ( INDEX * 2 + 1 <= subLast)
			INDEX = INDEX * 2 + 1;
	}

	//get the next subscript in inorder traversal
	int nextInOrder(int sub) {

		//if new element is added on NP and next inorder is NP then return NP
		if ( 2 * sub + 1 == NP )
			return NP;

		if( 2 * sub + 1 <= subLast )
		{
			sub = sub * 2 + 1;
			while( 2* sub <= subLast || 2*sub == NP) //also check if next inorder is equal to NP
				sub = 2 * sub;
			return sub;	

		}	
		else {
			if(sub %2 == 0 )
				return sub/2;
			else {
				while(sub % 2 != 0 )
					sub = sub/2;
				return sub/2;
			}
		}
	}

	//search keys in tree
	boolean searchKey(int n , int sub)
	{   
		int NP = 0 ;
		if ( sub > subLast && CBSArray[sub] != n) 
			return false;
		if(CBSArray[sub] == n)
			return true;
		if( n < CBSArray[sub] )
			return searchKey(n , sub * 2);
		else 
			return searchKey(n , sub * 2 + 1);
	}

	//get the position for adding new element in tree considering it to be search tree
	int getNewPosition(int n , int sub)
	{   int NP = 0 ;
	if(sub > subLast)
		return sub;
	if( n < CBSArray[sub] )
		NP = getNewPosition(n , sub * 2);
	else 
		NP = getNewPosition(n , sub * 2 + 1);
	return NP;
	}

	//check if new element is to be added on new level or last level
	boolean isNewLvl(int newSub)
	{
		if ( newSub <= 2 * INDEX + 1)
			return false;
		else 
			return true;

	}
}


class FileOperation{
	private Scanner fileScan;

	FileOperation(){
		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("             Assignment 10B -  CBS,BST and AVL Trre Operations                      ");
		System.out.println("*********************************************************************************");
		System.out.println(" Assumption - Height/Level of root is zero                                       ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData10.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
			System.exit(1);
		}
	}

	// ************** read the file *************
	int[] readFile(){

		int[] inputArray = new int[1000];
		int i = 0;

		//read each line until end of the file has reached
		if( fileScan != null ) {
			while(fileScan.hasNext()) {
				String line = fileScan.next();
				if( isNumeric(line) ==  true) {			
					//convert the string data to int type
					int i2 = Integer.parseInt(line); 
					//add int into the array 
					inputArray[i++] = i2;
				}
			}
		}

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


//BST Operations
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


//AVL operations

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
