/*******************  Assignment 1A  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Convergent Series       *
 * Date                    -   09-Sept-2018            *
 *******************************************************
 * Program Description     -                           *
 *    Program to determine if the below series is      *
 *    convergent or divergent.                         *
 *     1 + 1/2^2 + 1/3^2 + 1/4^2 + 1/5^2 .......       *
 *******************************************************/



import java.util.Date;

public class CS6081ASukhada{

	public static void main(String []args) {

		//instantiate the class SeriesCheck to test the series
		SeriesCheckA SC = new SeriesCheckA();
		//call the method to test the series
		SC.isConvergent(); 

	}//main method

}//class




// this class will check if the series is a convergent or divergent
class SeriesCheckA{

	// this is a constructor to print the program output header
	SeriesCheckA(){


		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("Program to check if series 1 + 1/2^2 + 1/3^2 + 1/4^2 + 1/5^2 ...... is convergent");
		System.out.println("*********************************************************************************");
	}


	//this method finally check the series type and prints the sum value
	void isConvergent(){


		// local variable declarations for sum
		float currentSum = 0.0f, prevSum = 0.0f; 
		// variables for loop iterations
		int j = 0;
		//char variable to store the result
		char result = 'N' ;


		// this loop will generate numbers - 0,1000,2000,3000 through 10000.				
		for( int i = 0; i < 1000*10 ; i = i+1000 ) {

			//this would loop through 1-1000, 1001-2000, 2001-3000.....9001-10000					
			for( j = i + 1; j <= i+1000; j++ ) {	

				currentSum = currentSum + (float)1/(j*j);

			}

			//print the sum of each series
			System.out.print("Sum of first "+ (j-1) +" terms is "+currentSum+"\t");	


			//series is convergent if sum difference of consecutive series is less than 0.0001
			if( java.lang.Math.abs( prevSum - currentSum) <= 0.0001) {	
				System.out.println("Series is likely convergent");	
				result = 'C';
			}
			else { 
				System.out.println("Series is likely Divergent");
				result = 'D';
			}

			//store the currentsum for comparison with the next series result
			prevSum = currentSum;

		}//outer loop

		System.out.println();

		//print the conclusion depending on last series' result
		if ( result == 'C') {
			System.out.println("Conclusion - Series is likely Convergent and the approximate sum value is "+ currentSum);
		} else{
			System.out.println( "Conclusion - Series is likely Divergent");
		}

	}

}





