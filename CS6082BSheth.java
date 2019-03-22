/*******************  Assignment 2B  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Sorting Methods         *
 * Date                    -   23-Sept-2018            *
 *******************************************************
 * Program Description     -                           *
 *    Program to sort the input file content using     *
 *    Bubble sort,Selection Sort,Double Ended sort and *
 *    compare the result using the inbuilt sort method *    
 *   Also print the the comparison count and time taken*
 *******************************************************/


import java.io.*;
import java.util.*;


// main class
public class CS6082BSheth {

	public static void main(String[] args) {

		SortMethods sm = new SortMethods();
		//sequence of method calls to open,read,sort the file using various methods and then compare it using inbuilt sort method
		sm.openFile();
		sm.readFile();
		sm.inbuiltSort();
		sm.bubbleSort();
		sm.selectionSort();
		sm.doubleEndedSelectionSort();
		sm.closeFile();

	}

}

// main logic class
class SortMethods{

	private Scanner fileScan;         //to read the file
	private int[] bubbleArray ;   //for bubble sort
	private int[] selectionArray; //for selection sort
	private int[] doubleEndedArray;//for double ended selection sort
	private int[] backupArray;     //for inbuilt sort method
	private Integer[] arrayClass;


	// this is a constructor to print the program output header
	SortMethods(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 2B -  Sorting Methods                           ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData2B.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
		}
	}

	// ************** read the file *************

	void readFile(){
		//array list to read the file data
		List<Integer> fileData = new ArrayList<>();
		//read each line until end of the file has reached
		if( fileScan != null ) {
			while(fileScan.hasNextLine()) {
				String line = fileScan.next();
				//convert the string data to int type
				int i2 = Integer.parseInt(line); //using Integer as array list cannot handle the primitive types like int
				//add int into the array list
				fileData.add(i2);
			}
		}

		//build the array of Integer  for different sort methods. 
		arrayClass = new Integer[fileData.size()]; 
		arrayClass = fileData.toArray(arrayClass);

		bubbleArray = new int[fileData.size()] ;
		doubleEndedArray = new int[fileData.size()] ;
		selectionArray = new int[fileData.size()] ;
		backupArray = new int[fileData.size()] ;


		for( int j = 0; j < fileData.size() ; j++) {
			bubbleArray[j] = arrayClass[j];
			doubleEndedArray[j] = arrayClass[j];
			selectionArray[j] = arrayClass[j];
			backupArray[j] = arrayClass[j];
		}
	}

	// ************** sort the file using bubble sort method *************	
	void bubbleSort(){

		int k = bubbleArray.length ,  temp , i , flag = 0, counter = 0;	
		long beforeTime, afterTime , timeTaken;

		//just before sorting starts 
		beforeTime = System.nanoTime();

		//loop to go until n,n-1,n-2 item of array where n = length of an array
		for(int j = ( k - 1 ) ; j > 0  ; j-- ) {		

			//loop to compare adjacent elements of array
			for(i = 0; i < j ; i++) {

				//count the number of comparisons
				counter++;				

				//find the largest no and shift it to left end 
				if( bubbleArray[i] > bubbleArray[i+1] ) {


					//for shortcut exit
					flag = 1;

					//swap the numbers
					temp = bubbleArray[i];
					bubbleArray[i] = bubbleArray[i+1];
					bubbleArray[i+1] = temp;

				}

			}
			if( flag == 0 ) {
				break;			
			}
			flag = 0;			

		}


		//just after sorting ends 
		afterTime = System.nanoTime();
		timeTaken = afterTime - beforeTime;
		//print the output
		System.out.println("                                  Bubble Sort                                    ");
		System.out.println("*********************************************************************************");
		System.out.println("Array Items After Sorting using Bubble Sort Method");
		printArrayItems(bubbleArray);
		System.out.println("Time taken by algorithm to sort - "+ timeTaken);
		System.out.println("Number of Comparisions made -     "+ counter);		
		compareResult(bubbleArray, backupArray);
		System.out.println("*********************************************************************************");

	}

	// ************** sort the file using selection sort method *************
	void selectionSort() {

		int k = selectionArray.length ,  temp , i ,  counter = 0 , minIndex = 0;	
		long beforeTime, afterTime , timeTaken;

		//just before sorting starts 
		beforeTime = System.nanoTime();

		//loop through k-1 , k-2 , k-3 where k = length of the array
		for(int j = 0 ; j < k-1  ; j++ ) {	

			//find the index of minimum number
			minIndex = j;

			for(i = j+1; i < k ; i++) {

				//count the number of comparisons

				counter++;

				if( selectionArray[i] < selectionArray[minIndex] ) {
					//count the number of comparisons
					minIndex = i;	

				}
			}
			//shift the minimum no to left end
			temp =  selectionArray[minIndex];
			selectionArray[minIndex] = selectionArray[j];
			selectionArray[j] = temp;

		}
		//just after sorting ends 
		afterTime = System.nanoTime();
		timeTaken = afterTime - beforeTime;
		//print the output
		System.out.println("                               Selection Sort                                    ");
		System.out.println("*********************************************************************************");
		System.out.println("Array Items After Sorting using Selection Sort Method");
		printArrayItems(selectionArray);
		System.out.println("Time taken by algorithm to sort - "+ timeTaken);
		System.out.println("Number of Comparisions made -     "+ counter);		
		compareResult(selectionArray, backupArray);
		System.out.println("*********************************************************************************");
	}



	// ************** sort the file using bubble sort method *************
	void doubleEndedSelectionSort() {

		int end = doubleEndedArray.length  ,  temp , i , j , minIndex , maxIndex,  counter = 0 , start = 0 ;	
		long beforeTime, afterTime , timeTaken;

		//just before sorting starts 
		beforeTime = System.nanoTime();

		//step 1 - pairwise comparision 
		for(i = start ; i < end/2   ; i++) {
			counter++;
			if(doubleEndedArray[i] > doubleEndedArray[end-i-1])
			{
				temp = doubleEndedArray[i];
				doubleEndedArray[i] = doubleEndedArray[end-i-1];
				doubleEndedArray[end-i-1] = temp;

			}
		}
		
			//step2 selection sort from two ends
		while(start < end ) {

			//get the least number of the left half
			for(i = start; i < end/2 - 1 ; i++) {
				minIndex = i;

				for ( j = i + 1; j < end/2 ; j++ ) {
					counter++;
					if( doubleEndedArray[minIndex] > doubleEndedArray[j] ) {
						minIndex = j;
					}
				}
				temp = doubleEndedArray[minIndex];
				doubleEndedArray[minIndex] = doubleEndedArray[start];
				doubleEndedArray[start] = temp;	

			}

			//get the largest number of the right half 
			for(i = end - 1 ;  i > end/2 ; i--) {
				maxIndex = i;
				for ( j = i - 1; j >= end/2 ; j-- ) {
					counter++;
					if( doubleEndedArray[maxIndex] < doubleEndedArray[j] ) {
						maxIndex = j;
					}
				}
				temp = doubleEndedArray[maxIndex];
				doubleEndedArray[maxIndex] = doubleEndedArray[end - 1];
				doubleEndedArray[end - 1] = temp;								
			}

			start++;
			end--;

		}
		//just after sorting ends 
		afterTime = System.nanoTime();
		timeTaken = afterTime - beforeTime;
		//print the output
		System.out.println("                          Double Ended Selection Sort                            ");
		System.out.println("*********************************************************************************");
		System.out.println("Array Items After Sorting using Double Ended Selection Sort Method");
		printArrayItems(bubbleArray);
		System.out.println("Time taken by algorithm to sort - "+ timeTaken);
		System.out.println("Number of Comparisions made -     "+ counter);		
		compareResult(bubbleArray, backupArray);
		System.out.println("*********************************************************************************");

	}		



	// ************** close the file *************	
	void closeFile() {
		fileScan.close();
	}


	void inbuiltSort() {

		long beforeTime, afterTime , timeTaken;

		beforeTime = System.nanoTime();

		//inbuilt method to sort the array
		Arrays.sort(backupArray);

		//just after sorting ends 
		afterTime = System.nanoTime();
		timeTaken = afterTime - beforeTime;


		System.out.println("Array Items After Sorting using Inbuilt Sort Method");
		printArrayItems(backupArray);
		System.out.println("Time taken by algorithm to sort - "+ timeTaken);
		System.out.println("*********************************************************************************");
	}

	// ************** compare the result of sort methods with the result of inbuilt sort method *************	
	void compareResult( int[] sortMethodRe , int[] inbuiltMethodRe ) {

		try {

			if(     sortMethodRe[100] == inbuiltMethodRe[100]  &
					sortMethodRe[125] == inbuiltMethodRe[125]  &
					sortMethodRe[250] == inbuiltMethodRe[250]  &
					sortMethodRe[700] == inbuiltMethodRe[700]  &
					sortMethodRe[900] == inbuiltMethodRe[900] )  {
				System.out.println("Conclusion - Result using Selection Sort Method and Inbuilt method match exactly.");
			}
		}
		catch(Exception e ) {}
	}

	// ************** print the array items *************	
	void printArrayItems(  int array[]) {
		try {
			System.out.println("A[100]" + "\t" + "A[125]" + "\t" + "A[250]" + "\t" + "A[700]" + "\t" + "A[900]");
			System.out.println(array[100] + "\t" + array[125] + "\t" + array[250] + "\t" + array[700] + "\t" + array[900]);		
		}
		catch(Exception e) {}
	}
} // end of logic class 


