/*******************  Assignment 2A  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Bubble sort             *
 * Date                    -   23-Sept-2018            *
 *******************************************************
 * Program Description     -                           *
 *    Program to sort the input file numbers using     *
 *    Bubble sort and compare the result using the     *
 *    inbuilt sort method                              *    
 *******************************************************/


import java.io.*;
import java.util.*;

// main class
public class CS6082ASheth {

	public static void main(String[] args) {

		SortTheFileInput sortFile = new SortTheFileInput();
		//sequence of method calls to open,read,sort the file and then compare it using inbuilt sort method
		sortFile.openFile();
		sortFile.readFile();
		sortFile.sortFile();
		sortFile.closeFile();
		sortFile.compareFile();		
	}

}

// main logic class
class SortTheFileInput{

	private Scanner fileScan;         //to read the file
	private Integer[] fileDataInt ;   //to hold the array of numbers read from the file
	//backup the array for sorting it using java method at the end of the program for comparing the result
	private Integer[] arrayBackup;


	// this is a constructor to print the program output header
	SortTheFileInput(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 2A -  Bubble Sort                                ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData2A.txt");
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

		//build the array of Integer. 
		fileDataInt = new Integer[fileData.size()]; 
		arrayBackup = new Integer[fileData.size()]; 
		arrayBackup = fileData.toArray(arrayBackup);
		fileDataInt = fileData.toArray(fileDataInt);

		System.out.println("Array Items Before Sorting");
		printArrayItems(fileDataInt);

	}

	// ************** sort the file using bubble sort method *************	
	void sortFile(){

		int k = fileDataInt.length ,  temp , i , flag = 0;	

		//loop to go until n,n-1,n-2 item of array where n = length of an array
		for(int j = ( k - 1 ) ; j > 0  ; j-- ) {		
			//loop to compare adjacent elements of array
			for(i = 0; i < j ; i++) {
				//find the largest no and shift it to left end 
				if( fileDataInt[i] > fileDataInt[i+1] ) {
					//for shortcut exit
					flag = 1;
					//swap the numbers
					temp = fileDataInt[i];
					fileDataInt[i] = fileDataInt[i+1];
					fileDataInt[i+1] = temp;

				}
			}

			if( flag == 0 ) {
				break;
			}
			flag = 0;


		}
		//print the output
		System.out.println("Array Items After Sorting using Bubble Sort Method");
		printArrayItems(fileDataInt);
	}

	// ************** close the file *************	
	void closeFile() {
		fileScan.close();
	}


	// ************** compare the result of bubble sort with the result of inbuilt sort method *************	
	void compareFile() {

		//inbuilt method to sort the array
		Arrays.sort(arrayBackup);

		System.out.println("Array Items After Sorting using Inbuilt Sort Method");
		printArrayItems(arrayBackup);

		try {

			if(     arrayBackup[10].equals(fileDataInt[10]) &
					arrayBackup[25].equals(fileDataInt[25]) &
					arrayBackup[50].equals(fileDataInt[50]) &
					arrayBackup[70].equals(fileDataInt[70]) &
					arrayBackup[90].equals(fileDataInt[90] ) )	
			{
				System.out.println("Conclusion - Result using Bubble Sort Method and Inbuilt method match exactly.");
			}
		}
		catch(Exception e) {
		}
	}

	// ************** print the array items *************	
	void printArrayItems(  Integer[] arrayList) {
		try {
			System.out.println("A[10] : " + arrayList[10]);
			System.out.println("A[25] : " + arrayList[25]);
			System.out.println("A[50] : " + arrayList[50]);
			System.out.println("A[70] : " + arrayList[70]);
			System.out.println("A[90] : " + arrayList[90]);
			System.out.println("*********************************************************************************");
		}
		catch(Exception e) {	
		}
	}

} // end of logic class 

