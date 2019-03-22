import java.util.*;
import java.io.*;


public class CS60810ASheth {

	public static void main(String[] args) {
		int[] array = new int[1000];
		FileOperations fo = new FileOperations();
		fo.openFile();
		array = fo.readFile();
		CBT cbt = new CBT(array);

		System.out.println("Height of the tree is " + cbt.height(1));
		System.out.println("Number of Nodes in tree are " + cbt.numberOfNodes());
		System.out.println("Largest element in the tree is " + cbt.largestNode(1));
		

		int input = 1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(input != 0)
		{
			System.out.println("\nEnter number to be searched in Complete binary tree. Enter 0 to cancel");
			try{

				input=Integer.parseInt(br.readLine());
				if(input != 0)
				{
					boolean result = false;;

					result = cbt.search(1, input);


					System.out.println("Does element " + input + " exist in the tree ? "  + result ); 

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

	class FileOperations{
		private Scanner fileScan;

		FileOperations(){
			// create date instance
			Date date = new Date();

			System.out.println("Name                    -   Sukhada Surendra Sheth        ");
			System.out.println("Class                   -   CS608                         ");			
			System.out.println("Date                    -   "+ date);		
			System.out.println("*********************************************************************************");
			System.out.println("             Assignment 10A -  Complete Binary Tree                              ");
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


	class CBT{

		private 	int CBTArray[] = new int[1001];
		private int subLast; //is the total number of elements in the CBS added so far including the current which is being processed

		CBT(int[] input){

			subLast = 0;
			for(int i = 0 ; i<input.length ; i++)
			{
				CBTArray[++subLast] = input[i];
			}
		}

		int numberOfNodes() {
			return subLast;
		}

		boolean search(int sub, int n) {
			if(sub > subLast)
			{
				return false;
			}
			else
			{
				if(CBTArray[sub] == n)
					return true;
				else
				{
					return search(sub * 2 , n) ||  search( sub * 2 + 1 , n);
				}
			}

		}

		int largestNode(int sub) {
			if( sub > subLast )
				return 0; 
			else
			{
				int center = CBTArray[sub];
				int left = largestNode(sub*2);
				int right = largestNode(sub*2+1);

				if(left > center)
					center = left;
				if(right > center)
					center = right;

				return center;
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



	}