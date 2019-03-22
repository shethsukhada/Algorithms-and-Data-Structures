/*******************  Assignment 4B  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Recurssion Blob Problem *
 * Date                    -   08-Oct-2018             *
 *******************************************************
 * Program Description     -                           *
 *    Program to count the number of Blobs and number  *
 *    of Bs in respective Blobs*    
 *******************************************************/
import java.io.*;
import java.util.*;

// main class
public class CS6084BSheth {

	public static void main(String[] args) {

		BlobProb bp = new BlobProb();
		bp.openFile();
		bp.readFile();
		bp.countBlobs();
		bp.closeFile();
	}
}

// main logic class
class BlobProb{

	private Scanner fileScan;               //to read the file
	private int rows =  25 , columns = 25;
	private char [][] inputArray = new char[rows][columns];
	private char [][] checkedArray = new char[rows][columns];

	// this is a constructor to print the program output header
	BlobProb(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("             Assignment 4B - Blob Problem                                        ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData4B.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
			System.exit(1);
		}
	}

	// ************** read the file *************
	void readFile(){

		//read each line until end of the file has reached
		if( fileScan != null ) {
			while(fileScan.hasNext()) {
				for (int i=0; i<inputArray.length; i++) {
					String line = fileScan.nextLine();
					for (int j=0; j<line.length(); j++) {
						inputArray[i][j] = line.charAt(j);
					}
				}
			}
		}


	}

// count number of blobs recursively
// steps - 1. Assume each index has possibility of being part of the blob if it is B
//         2. Then go on checking its adjacent positions if they hold any Bs 
//         3. and repeat 2 until no new B is found in adjacent place
//         4. go back to 4 	
	void countBlobs() {

		int count = 0,  totalB = 0; // Number of blobs.

		//reset the array visited flag
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++) 
				checkedArray[r][c] = 'N';
	
		//check for possibility of presence of blob at every index of the matrix
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++) {
				int temp = getBlobSize(r,c) ; //count the blobs recurssively
				if (temp > 0) {
					//print total no of Bs in every blob
					System.out.println("The number Bs in the Blob starting from index [" + r + "][" + c + "] is : " + temp );	
					totalB = totalB + temp;
					count++;
				}
			}
		
		System.out.println("*********************************************************************************");
		//print the total number of blobs
		System.out.println("Total number of Bs is  " + totalB);
		System.out.println("Total number of Blobs is " + count);
	}

	private int getBlobSize(int r, int c) {

		// the basis clauses
		
		//if index goes out of bounds of the matrix
		if (r < 0 || r >= rows || c < 0 || c >= columns) {
			return 0;
		}

		//if a position has already been counted once
		if ( checkedArray[r][c] == 'Y' || inputArray[r][c] != 'B') {
			return 0;
		}

		//set the visited flag
		checkedArray[r][c] = 'Y';   

		int blobSize = 1;   

		//check the adjacent position to find the blob size
		blobSize += getBlobSize(r-1,c); //left
		blobSize += getBlobSize(r+1,c); //right
		blobSize += getBlobSize(r,c-1); //up
		blobSize += getBlobSize(r,c+1); //down
		blobSize += getBlobSize(r-1,c-1); //left up corner
		blobSize += getBlobSize(r+1,c+1); //right up corner
		blobSize += getBlobSize(r-1,c+1); //right down corner
		blobSize += getBlobSize(r+1,c-1); //left down corner

		return blobSize;
	}  // end getBlobSize()


	// ************** close the file *************	
	void closeFile() {
		fileScan.close();
	}
	
}//end of BlobProb class