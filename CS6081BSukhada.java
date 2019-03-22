/*******************  Assignment 1B  *******************
 * Name                    -   Sukhada Surendra Sheth  *
 * Program Name            -   Convergent Series       *
 * Date                    -   09-Sept-2018            *
 *******************************************************
 * Program Description     -                           *
 *    Program to determine if the below series is      *
 *    convergent or divergent.                         *
 *     1 - 1/3 + 1/5 - 1/7 + 1/9................       *
 *******************************************************/



import java.util.Date;
import java.lang.Math;


public class CS6081BSukhada{

	public static void main(String []args) {

		//instantiate the class to test the series
		SeriesCheckB SC = new SeriesCheckB();
		//call method to test the series
		SC.isConvergent(); 

	}//main method

}//class



// this class will check if the alternate harmonic series is a convergent or divergent
class SeriesCheckB {


	// this is a constructor to print the program output header
	SeriesCheckB(){

		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("Program to check if series 1 - 1/3 + 1/5 - 1/7 + 1/9 .............. is convergent");
		System.out.println("*********************************************************************************");
	}

	//this method finally check the series type and prints the sum value
	void isConvergent(){

		// local variable declarations for sum
		float currentSum = 0.0f, prevSum = 0.0f;
		// variables for loop iterations
		int j = 0,  k = 1 ;
		//char variable to store the result
		char result = 'N';



		// for 10 iterations because we need to find sum of 10 series ( 1 - 1000, 1-2000, 1-3000 .....1-10,000) 				
		for( int i = 1; i <= 10 ; i++ ) {


			//for 1000 iterations for each new 1000 terms of the series				
			for( j = 1; j <= 1000 ; j++) {	

				//pow function has been used for alternate + - signs				
				currentSum = currentSum + ( ( (float)Math.pow(-1 , j+1 ) ) * (float)1/k ); 

				// for only odd numbers	
				k = k + 2;

			}

			//print the sum of each series
			System.out.print("Sum of first "+(i * 1000 ) +"\t"+ currentSum +"\t");	

			//series is convergent if sum difference of consecutive series is less than 0.0001
			if( java.lang.Math.abs( prevSum - currentSum) <= 0.0001) {	

				System.out.println("Series is likely convergent");
				//store the result for printing final conclusion
				result = 'C';
			}
			else { 
				System.out.println("Series is likely Divergent");
				result = 'D';
			}

			//store the currentsum for comparison with the next series result
			prevSum = currentSum;


		}//outer loop

		//print the conclusion depending on last series' result
		System.out.println();
		if ( result == 'C') {
			System.out.println("Conclusion - Series is likely Convergent and the approximate sum value after multiplying by 4 is "+ currentSum * 4);
		} else{
			System.out.println( "Conclusion - Series is likely Divergent");
		}

	}

}



