/*******************  Assignment 5B  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Linked List Factorial   *
 * Date                    -   17-Oct-2018             *
 *******************************************************
 * Program Description     -                           *
 *    Program to perform operations on Linked List     *    
 *******************************************************/
import java.io.*;
import java.util.*;

// main class
public class CS6085BSheth {

	public static void main(String[] args) throws IOException{
		
		int n = 1;
		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 5B - Factorial Using Linked List                     ");
		System.out.println("*********************************************************************************");
		System.out.println();

		//accept the number from user to calculate factorial
		while(n != 0) {
			
			System.out.println("Please enter a number to calculate its factorial OR Enter 0 to terminate. ");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			String temp  = reader.readLine();
			
			if( isNumeric(temp) == true ){
				
				n = Integer.parseInt(temp);

				if(n == 0) {
					System.out.println("Process Terminated. Bye Bye!");
					System.exit(1);
				}
				LinkedListFact fact = new LinkedListFact();
				fact.calculateFactorial(n , fact); 
			}
			
			else{
				System.out.println(" !!! Wrong input type !!!");
			}
		}
	}

// check if the inout number is numeric
	static boolean isNumeric(String strNum) {
		try {
			double d = Double.parseDouble(strNum);

		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}

		return true;
	}
} 

class LinkedListFact{
	
	//linked list definition
	private  class Node {
		private int data;
		private Node next;

		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	private StringBuilder stringTemp = new StringBuilder(); //for storing final result with commas
	private Node head;

	//constructs an empty list
	public LinkedListFact() {
		head = null;
	}

	//returns true if list is empty
	public boolean isEmpty() {
		return(head == null);		
	}

	//inserts a node at the beginning of the linked list
	public void insertAtBeginning(int element) {
		head = new Node(element, head);
	}

	//calculate factorial using linked list
	void calculateFactorial(int n , LinkedListFact fact) {

		fact.insertAtBeginning(1);

		for(int i=1; i<=n; i++) {
			fact = fact.listMultiplication(fact,i);
		}
		//display the final output
		fact.displayReverse(fact.head);
		stringTemp.deleteCharAt(stringTemp.length() - 1);
		System.out.println("Factorial of "+ n +" is "+stringTemp);
		System.out.println("---------------------------------------------------------------------------------");
	}

	//insert at the end of the linked list. So that head points to the last 3 digits of the number
	// if number is 301,409,223
	//linked list would look like 
	// head --> 223 --> 409 --> 301
	public  void insertAtEnd(int z, Node head) {
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


	
	//display linked list in reverse order 
	public  void displayReverse(Node head) {	

		if(head == null) {}
		else {
			Node currentNode = head;
			displayReverse(currentNode.next);
			//System.out.print(currentNode.data+",");
		    stringTemp.append(String.format("%03d", currentNode.data)+",");
		}

	}

	//factorial calculation
	//mainatin only 3 digits in each node amd whatever is extra, call it as a carry and add it to next node
	public  LinkedListFact listMultiplication(LinkedListFact fact, int n) {
		int carry = 0, temp = 0;
		Node currentNode = fact.head;
		while(currentNode != null)
		{  
			temp = currentNode.data * n + carry;
			carry = temp / 1000 ;
			currentNode.data = temp % 1000;		
			currentNode = currentNode.next;
		}
		if(carry != 0) {
			fact.insertAtEnd(carry, fact.head);	
		}	
		return fact;
	}

}