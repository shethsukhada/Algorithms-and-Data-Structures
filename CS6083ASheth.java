/*******************  Assignment 3A  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Binary Insertion Sort   *
 * Date                    -   29-Sept-2018            *
 *******************************************************
 * Program Description     -                           *
 *    Program to sort the input file content using     *
 *    Binary Insertion Sort                            *
 *******************************************************/
import java.io.*;
import java.util.*;

// main class
public class CS6083ASheth {

	public static void main(String[] args) {

		BinaryInsertionSort bi = new BinaryInsertionSort();
		//sequence of method calls to open,read,sort the file using various methods and then compare it using inbuilt sort method
		bi.openFile();
		bi.readFile();
		bi.inbuiltSort();
		bi.sortArray();
		bi.closeFile();
	}
}

// main logic class
class BinaryInsertionSort{

	private Scanner fileScan;               //to read the file
	private int[] binaryInserArray ;        //for binary insertion sort
	private int[] backupArray;              //for inbuilt sort method
	private Integer[] arrayClass;

	// this is a constructor to print the program output header
	BinaryInsertionSort(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("             Assignment 3A -  Sorting using Binary Insertion Method               ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData3A.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
			System.exit(1);
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
				int i2 = Integer.parseInt(line); //using Integer as array list  cannot handle the primitive types like int
				//add int into the array list
				fileData.add(i2);
			}
		}

		//build the array of Integer  
		arrayClass = new Integer[fileData.size()]; 
		arrayClass = fileData.toArray(arrayClass);

		//convert Integer[] to int[]
		binaryInserArray = Arrays.stream(arrayClass).mapToInt(Integer::intValue).toArray();
		backupArray = binaryInserArray;

	}

	// ************** sort the file using binary insert method *************

	void sortArray(){

		int k = binaryInserArray.length ,  temp , i , j , left = 0 , right = 0 , mid ;

		for(i = 1 ; i < k ; i++ ) {	

			left = 0;
			right = i - 1;
			temp = binaryInserArray[i];

			while(left<=right) {
				mid = ( left + right ) / 2;
				if( temp < binaryInserArray[mid])
					right = mid-1 ; 
				else
					left = mid + 1;
			}

			for(j = i - 1;  j >= left  ; j--) 
				binaryInserArray[j+1] = binaryInserArray[j];

			binaryInserArray[left] = temp;

		}

		//print the output
		System.out.println("                             Binary Insertion Sort                               ");
		System.out.println("*********************************************************************************");
		System.out.println("Array Items After Sorting using Binary Insertion Sort");
		printArrayItems(binaryInserArray);	
		compareResult(binaryInserArray, backupArray);
		System.out.println("*********************************************************************************");

	}

	// ************** close the file *************	
	void closeFile() {
		fileScan.close();
	}


	void inbuiltSort() {

		//inbuilt method to sort the array
		Arrays.sort(backupArray);
		System.out.println("Array Items After Sorting using Inbuilt Sort Method");
		printArrayItems(backupArray);

	}

	// ************** compare the result of sort methods with the result of inbuilt sort method *************	
	void compareResult( int[] sortMethodRe , int[] inbuiltMethodRe ) {

		try {

			if(     sortMethodRe[10] == inbuiltMethodRe[10]  &
					sortMethodRe[25] == inbuiltMethodRe[25]  &
					sortMethodRe[50] == inbuiltMethodRe[50]  &
					sortMethodRe[70] == inbuiltMethodRe[70]  &
					sortMethodRe[90] == inbuiltMethodRe[90] )  {
				System.out.println("Conclusion - Result using Binary Insertion Sort Method and Inbuilt method match exactly.");
			}
		}
		catch(Exception e ) {}
	}

	// ************** print the array items *************	
	void printArrayItems(  int arrayList[]) {
		try {
			System.out.println("A[10] : " + arrayList[10]);
			System.out.println("A[25] : " + arrayList[25]);
			System.out.println("A[50] : " + arrayList[50]);
			System.out.println("A[70] : " + arrayList[70]);
			System.out.println("A[90] : " + arrayList[90]);
			System.out.println("*********************************************************************************");
		}
		catch(Exception e) {}
	}
} // end of logic class 


