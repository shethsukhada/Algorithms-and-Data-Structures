/*******************  Assignment 4A  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Quick Sort              *
 * Date                    -   09-Oct-2018             *
 *******************************************************
 * Program Description     -                           *
 *    Program to sort the input file content using     *
 *    Quick Sort                                       *
 *******************************************************/
import java.io.*;
import java.util.*;

// main class
public class CS6084ASheth {

	public static void main(String[] args) {

		QuickSort qs = new QuickSort();

		qs.openFile();
		qs.readFile();
		qs.inbuiltSort();
		qs.sortArray();
		qs.closeFile();
	}
}

// main logic class
class QuickSort{

	private Scanner fileScan;         //to read the file
	private int[] quickArray ;        //for quick sort
	private int[] backupArray;        //for inbuilt sort method
	private Integer[] arrayClass;
	private int partCount = 0;

	// this is a constructor to print the program output header
	QuickSort(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("             Assignment 4A -  Sorting using Quick Sort Method                    ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData4A.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
			System.exit(1);
		}
	}

	
	 boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	// ************** read the file *************
	void readFile(){

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
		quickArray = Arrays.stream(arrayClass).mapToInt(Integer::intValue).toArray();
		backupArray = quickArray.clone();

	}

	// ************** sort the file using binary insert method *************

	void sortArray(){

		int k = quickArray.length  ;

		quickSort(0,k-1,quickArray);
		//print the output
		System.out.println("Array Items After Sorting using Quick Sort");

		printArrayItems(quickArray);	
		compareResult(quickArray, backupArray);
		System.out.println("*********************************************************************************");
		System.out.println("Number of times method partition was called - " + partCount);

	}

	// ************** close the file *************	
	void closeFile() {
		fileScan.close();
	}


	int partition(int L, int R, int[] a) {
		int pivot = a[L];
		int up = L, down = R;
		partCount++;
		while(up < down) {
			while(up < down && pivot < a[down])
				down--;
			a[up] = a[down];
			while(up < down && a[up] <= pivot) 
				up++;
			a[down] = a[up];
		}

		a[up] = pivot;
		return up;

	}

	void quickSort(int L, int R, int[] a) {
		int p;
		if(L < R) {
			p = partition(L,R,a);
			quickSort(L,p-1,a);
			quickSort(p+1,R,a);
		}

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

			if(    
					sortMethodRe[10] == inbuiltMethodRe[10]  &
					sortMethodRe[25] == inbuiltMethodRe[25]  &
					sortMethodRe[50] == inbuiltMethodRe[50]  &
					sortMethodRe[70] == inbuiltMethodRe[70]  &
					sortMethodRe[90] == inbuiltMethodRe[90] )  {
				System.out.println("Conclusion - Result using Quick Sort Method and Inbuilt method match exactly.");


			}

		}
		catch(Exception e ) { System.exit(1);}
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
		catch(Exception e) { System.exit(1);}
	}
} // end of logic class 


