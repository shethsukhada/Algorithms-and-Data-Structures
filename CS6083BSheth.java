/*******************  Assignment 3B  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Shell Sort              *
 * Date                    -   01-Oct-2018             *
 *******************************************************
 * Program Description     -                           *
 *    Program to sort the input file content using     *
 *    Shell Sort with different diminishing sequence   *
 *******************************************************/

import java.io.*;
import java.lang.Math;
import java.util.*;

// main class
public class CS6083BSheth {

	public static void main(String[] args) {

		SortMethods sm = new SortMethods();
		//sequence of method calls to open,read,sort the file using various methods and then compare it using inbuilt sort method
		sm.openFile();
		sm.readFile();
		sm.inbuiltSort();
		sm.sortData();
		sm.closeFile();

	}

}

// main logic class
class SortMethods{

	private Scanner fileScan;         //to read the file
	private int[] primeSeqArray ;     //for shell sort using prime nos sequence
	private int[] hibbardSeqArray ;   //for shell sort using hibbard nos sequence
	private int[] power2SeqArray;     //for shell sort using power 2 sequence
	private int[] backupArray;        //for inbuilt sort 
	private Integer[] arrayClass;     //to hold data read from file


	// this is a constructor to print the program output header
	SortMethods(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 3B -  Shell Sort                                ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData3B.txt");
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
				int i2 = Integer.parseInt(line); //using Integer as array list cannot handle the primitive types like int
				//add int into the array list
				fileData.add(i2);
			}
		}

		//build the array of Integer  for different sort methods. 
		arrayClass = new Integer[fileData.size()]; 
		arrayClass = fileData.toArray(arrayClass);

		//convert Integer[] to int[]
		power2SeqArray = Arrays.stream(arrayClass).mapToInt(Integer::intValue).toArray();
		backupArray = power2SeqArray.clone();
		primeSeqArray = power2SeqArray.clone();
		hibbardSeqArray = power2SeqArray.clone();

	}

	//***************** shell sort ****************
	
	void shellSort(ArrayList<Integer> gapSeq , int[] data) {

		int len = data.length ,  temp , i_gap , j , k ;	

		for( i_gap =  gapSeq.size() - 1 ; i_gap >= 0  ; i_gap--)
		{
			int gap = gapSeq.get(i_gap);
			for(j = gap; j<len ; j++) {
				k = j;
				temp = data[j];
				while(k >= gap && data[k-gap] > temp)
				{
					data[k] = data[k-gap];
					k = k - gap;
				}
				data[k] = temp;
			}
		}
	}


	void sortData(){

		long beforeTime, afterTime , timeTaken;
		ArrayList<Integer> gapList = new ArrayList<Integer>();

		System.out.println("                    Relatively Prime Number Sequnece Shell Short                 ");
		System.out.println("*********************************************************************************");

		gapList = generatePrimeSeq();	
		
		//just before sorting starts 
		beforeTime = System.nanoTime();
		shellSort(gapList , primeSeqArray );
		//just after sorting ends 
		afterTime = System.nanoTime();
		timeTaken = afterTime - beforeTime;
		
		//print the output
		System.out.println("Sort Result -  ");
		printArrayItems(primeSeqArray);
		System.out.println("Time taken by algorithm to sort - "+ timeTaken);	
		compareResult(primeSeqArray, backupArray);
		System.out.println("*********************************************************************************");

		//---------------------------------------------------------------------------------------------------------
		
		System.out.println("                           Hibbards Sequnece Shell Short                         ");
		System.out.println("*********************************************************************************");
		
		gapList.clear();
		gapList = generateHibbardSeq();
		
		//just before sorting starts 
		beforeTime = System.nanoTime();
		shellSort(gapList , hibbardSeqArray );
		//just after sorting ends 
		afterTime = System.nanoTime();
		timeTaken = afterTime - beforeTime;
		
		//print the output
		System.out.println("Sort Result -  ");
		printArrayItems(hibbardSeqArray);
		System.out.println("Time taken by algorithm to sort - "+ timeTaken);
		compareResult(hibbardSeqArray, backupArray);
		System.out.println("*********************************************************************************");
	
		//---------------------------------------------------------------------------------------------------------

		System.out.println("                        Power 2 Sequence Shell Sort                              ");
		System.out.println("*********************************************************************************");
		gapList.clear();
		
		gapList = generatePower2Seq();
		beforeTime = System.nanoTime();
		
		shellSort(gapList , power2SeqArray );
		//just after sorting ends 
		afterTime = System.nanoTime();
		timeTaken = afterTime - beforeTime;
		
		//print the output
		System.out.println("Sort Result -  ");
		printArrayItems(power2SeqArray);
		System.out.println("Time taken by algorithm to sort - "+ timeTaken);	
		compareResult(power2SeqArray, backupArray);
		System.out.println("*********************************************************************************");
		gapList.clear();

	}


	// ************** close the file *************	
	void closeFile() {
		fileScan.close();
	}


	//********** sort using java function sort() *************
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
		catch(Exception e ) { System.exit(1); }
	}

	// ************** print the array items *************	
	void printArrayItems(  int array[]) {
		try {
			System.out.println("A[100]" + "\t" + "A[125]" + "\t" + "A[250]" + "\t" + "A[700]" + "\t" + "A[900]");
			System.out.println(array[100] + "\t" + array[125] + "\t" + array[250] + "\t" + array[700] + "\t" + array[900]);		
		}
		catch(Exception e) { System.exit(1); }
	}


	//**********************generate different diminishing sequences*********
	public ArrayList<Integer> generatePower2Seq()    {
		//using array list to avoid static array length declaration
		ArrayList<Integer> power2Seq = new ArrayList<Integer>();
		int result = 0;

		for(int i = 0 ; i < 1000 && result < 500 ; i++)
		{   result = (int)Math.pow(2,i);
		power2Seq.add(result);		

		}

		System.out.print("Gap Sequence -  ");

		for(int i_gap =  power2Seq.size() - 1 ; i_gap >= 0  ; i_gap--)
			System.out.print(power2Seq.get(i_gap) + "  ");

		System.out.println();

		return(power2Seq);
	}


	public ArrayList<Integer> generateHibbardSeq()    {
		//using array list to avoid static array length declaration
		ArrayList<Integer> hibbardSeq = new ArrayList<Integer>();

		int result = 0;
		for(int i = 1 ; i < 1000 && result < 500 ; i++)
		{  
			result = (int)Math.pow(2,i);
			hibbardSeq.add(result - 1);		
		}

		System.out.print("Gap Sequence -  ");

		for(int i_gap =  hibbardSeq.size() - 1 ; i_gap >= 0  ; i_gap--)
			System.out.print( hibbardSeq.get(i_gap) + "  ");

		System.out.println();

		return(hibbardSeq);
	}

	public ArrayList<Integer> generatePrimeSeq()    {
		//using array list to avoid static array length declaration
		ArrayList<Integer> primeSeq = new ArrayList<Integer>();

		primeSeq.add(1);
		primeSeq.add(11);
		primeSeq.add(43);
		primeSeq.add(97);
		primeSeq.add(127);
		primeSeq.add(181);		
		primeSeq.add(223);		
		primeSeq.add(277);
		primeSeq.add(311);		
		primeSeq.add(353);
		primeSeq.add(389);
		primeSeq.add(421);		
		primeSeq.add(463);		
		primeSeq.add(499);

		System.out.print("Gap Sequence -  ");

		for(int i_gap =  primeSeq.size() - 1 ; i_gap >= 0  ; i_gap--)
			System.out.print(primeSeq.get(i_gap) + "  ");

		System.out.println();
		return(primeSeq);
	}

}//end of class