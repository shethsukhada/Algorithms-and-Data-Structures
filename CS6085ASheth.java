/*******************  Assignment 5A  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Linked List             *
 * Date                    -   15-Oct-2018             *
 *******************************************************
 * Program Description     -                           *
 *    Program to perform operations on Linked List     *    
 *******************************************************/


import java.io.*;
import java.util.*;

// main class
public class CS6085ASheth {


	private static class Node {
		private int data;
		private Node next;

		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node head;
	private static Scanner fileScan;         //to read the file
	private static int x , y, z;

	//constructs an empty list
	public CS6085ASheth() {
		head = null;

		// this is a constructor to print the program output header
		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 5A - Linked List Operations                     ");
		System.out.println("*********************************************************************************");
	}

	//returns true if list is empty
	public boolean isEmpty() {
		return(head == null);		
	}

	//inserts a node at the beginning of the linked list
	public void insertAtBeginning(int element) {
		head = new Node(element, head);
	}

	//returns the first element in the list
	public int getFirstElement() {
		if( head == null) {
			System.out.println("List is empty");
			throw new IndexOutOfBoundsException();
		}
		else {
			return head.data;
		}
	}

	//removed the first node in the list
	public int removeFirstNode() {
		int tmp = getFirstElement();
		head = head.next;
		return tmp;
	}

	//empties linked list
	public void clear() {
		head = null;
	}

	//returns the length of the linked list
	public static int LLlength(Node head) {
		int length = 0;
		Node currentNode = head;
		while(currentNode != null) {
			length++;
			currentNode = currentNode.next;
		}
		return length;
	}


	public static int getLastElement(Node head) {
		Node currentNode = head;
		if ( head == null ) {
			System.out.println("List is empty");
			throw new IndexOutOfBoundsException();
		}
		else
		{			
			while(currentNode.next != null) {	
				currentNode = currentNode.next;
			}
			return currentNode.data;
		}

	}
	
	
	
	public static void insertAtEnd(int z, Node head) {
		Node currentNode = head;
		if ( head == null ) {
			System.out.println("List is empty");
			throw new IndexOutOfBoundsException();
		}
		else
		{			
			while(currentNode.next != null) {	
				currentNode = currentNode.next;
			}
			
		}
		currentNode.next = new Node(z, null);
	}


	public static void searchFor(int x, Node head) {
		Node currentNode = head;
		if ( head == null ) {
			System.out.println("List is empty");
			throw new IndexOutOfBoundsException();
		}
		else
		{			
			while(currentNode != null && currentNode.data != x) {	
				currentNode = currentNode.next;
			}

			if(currentNode != null && currentNode.data == x ) 
				System.out.println("Element "+x+" found");
			else
				System.out.println("Element "+x+" not found");
		}

	}

	//displays the linked list elements
	public static void display(Node head) {
		if ( head == null ) {
			System.out.println("List is empty");
			throw new IndexOutOfBoundsException();
		}
		else
		{
			Node currentNode = head;
			while(currentNode != null) {
				System.out.print(currentNode.data + "   ");
				currentNode = currentNode.next;
			}
		}
	}

	//display linked list in reverse order 
	public void displayReverse(Node head) {
		if(head == null) {}
		else {
			Node currentNode = head;
			displayReverse(currentNode.next);
			System.out.print(currentNode.data + "   ");	
		}
	}


	public static void main(String[] args) {

		openFile();
		processFile();	
		closeFile();

	}


	// ************** open the file *************	
	static void openFile() {
		try {
			File inputFile = new File("dataForLinkedList.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
		}
	}


	static boolean isNumeric(String strNum) {
		try {
			double d = Double.parseDouble(strNum);

		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}

		return true;
	}

	// ************** read the file *************

	static void processFile(){

		int arrayTemp[] = new int[3];
		boolean flag = false;
		int j = 0;
		CS6085ASheth list = new CS6085ASheth();

		//read each line until end of the file has reached
		while(fileScan.hasNext()) {
			String line = fileScan.next();
			if( isNumeric(line) ==  true) {			
				//convert the string data to int type
				int i2 = Integer.parseInt(line); //using Integer as array list  cannot handle the primitive types like int
				//add int into the array list
				if(i2 != 0 && flag == false) {
					list.insertAtBeginning(i2);
				}
				else {
					if(flag == false && i2 == 0)
						{flag = true; 
						continue;}
					
						
					arrayTemp[j] = i2;
					j++;
				}//else
			}//numeric
		}//while

		j = 0;
		x  = arrayTemp[j++]; 
		y  = arrayTemp[j++]; 
		z  = arrayTemp[j]; 


		//2. display list
		System.out.println("Linked list elements are ");
		display(list.head);
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------------");

		//3. lenght of the list
		System.out.println("Linked list size is " + LLlength(list.head));
		System.out.println("----------------------------------------------------------------------------------");


		//4. search for x
		searchFor(x, list.head);
		System.out.println("----------------------------------------------------------------------------------");


		//5. print the last element of the linked list
		System.out.println("Last element of the list is " + getLastElement(list.head));
		System.out.println("----------------------------------------------------------------------------------");

		//6. 
		list.insertAtBeginning(y);
		

		//7. updated  list
		System.out.println("Linked list after adding "+y+" at the beginning is ");
		display(list.head);
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");

		//8. 
		insertAtEnd(z , list.head);

		//9. updated  list
		System.out.println("Linked list after adding "+ z +" at the end is");
		display(list.head);
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		
	}

	// ************** close the file *************	
	static void closeFile() {
		fileScan.close();
	}



} // outer class

