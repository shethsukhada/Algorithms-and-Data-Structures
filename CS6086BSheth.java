/*******************  Assignment 6B  ***************************
 * Name                    -   Sukhada Surendra Sheth          *
 * Program Name            -   Evaluating Infix Expression     *
 * Date                    -   17-Oct-2018                     *
 ***************************************************************
 * Program Description     -                                   *
 *    Program to evaluate Infix expression using Java          *    
 ***************************************************************/
import java.io.*;
import java.util.*;

// main class
public class CS6086BSheth {

	public static void main(String[] args) throws IOException{

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 6B - Infix Evaluation                           ");
		System.out.println("*********************************************************************************");
		System.out.println("Assumption : Program can accept only operands a,b,c,d in lower case on odd numbered lines of file");
		System.out.println();

		StackAppl sa = new StackAppl();	
		sa.readFile();

	}
}

class StackAppl{

	String str;

	void readFile() {
		try {

			FileReader fileReader = new FileReader("infixData6B.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int j = 0;
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				j++;

				if(j%2 == 1) { // all the odd numbered lines would have expression
					str = line;
					str = str.replaceAll("( )+", ""); //to remove all the spaces
					str = str.replaceAll("\t+", "");  //to remove tab spaces
				}
				else  // all the even numbered wud have numbers
				{
					String str2 = line;
					String[] parts = str2.split(" ");
					double a = Double.parseDouble(parts[0]);
					double b = Double.parseDouble(parts[1]);
					double c = Double.parseDouble(parts[2]);
					double d = Double.parseDouble(parts[3]);

					evaluate(str , a, b, c, d );
				}//ifelse
			}//while


			fileReader.close();


		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in File Reading");
		}

	}

	void evaluate( String str , double a , double b , double c , double d) {

		StackImplChar oprStack = new StackImplChar(30);  //operators stack
		StackImplDouble opdStack = new StackImplDouble(30); //operands stack
		double op1 , op2 , temp;
		char oper ;

		for( int j = 0 ; j < str.length() ; j++) {

			char currentChar = str.charAt(j);

			if(  currentChar == 'a' || currentChar == 'b' || currentChar == 'c' || currentChar == 'd' )
			{ 
				switch(currentChar) {
				case 'a': opdStack.push(a); break;
				case 'b': opdStack.push(b); break;
				case 'c': opdStack.push(c); break;
				case 'd': opdStack.push(d); break;
				}
				continue;
			}

			if( currentChar == '(' )
			{
				oprStack.push(currentChar);				  
				continue;
			}

			if( ( currentChar == '+' || currentChar == '-' || currentChar == '*' 
					|| currentChar == '/' ) &&  ( oprStack.isEmpty() || oprStack.topElemenet() == '(' ) )
			{
				oprStack.push(currentChar);
				continue;
			}

			else {


				switch(currentChar) {
				case '+':
				case '-':

					op1 = opdStack.pop();
					op2 = opdStack.pop();
					oper = oprStack.pop();
					temp = calculate(op1 , op2, oper);
					opdStack.push(temp);
					oprStack.push(currentChar);

					break;

				case '*':
				case '/':
					if( oprStack.isEmpty() || oprStack.topElemenet() == '+' || oprStack.topElemenet() == '-' ) {
						oprStack.push(currentChar);
					}
					else {

						op1 = opdStack.pop();
						op2 = opdStack.pop();
						oper = oprStack.pop();
						temp = calculate(op1 , op2, oper);
						opdStack.push(temp);
						oprStack.push(currentChar);


					}
					break;


				case')': 

					oper = oprStack.pop();

					while( oper != '(')
					{
						op1 = opdStack.pop();
						op2 = opdStack.pop();

						temp = calculate(op1 , op2, oper);
						opdStack.push(temp);
						oper = oprStack.pop();
					}

				}	

			}

		} //for loop on current infix expression

		while(!oprStack.isEmpty())
		{
			op1 = opdStack.pop();
			op2 = opdStack.pop();
			oper = oprStack.pop();
			temp = calculate(op1 , op2, oper);
			opdStack.push(temp);

		}

		System.out.println("Given Infix Expression" +"\t"+  str);
		System.out.println("Values of operands " + a + "  " + b + "  " + c + "  " + d);
		System.out.println("Value of Expression  " + opdStack.pop());
		System.out.println();


	} // for on array of infix expressions


	public double calculate(double op1 , double op2, char opr) 
	{
		double temp = 0;

		switch(opr) 
		{
		case '+':
			temp = op1 + op2;
			return temp;


		case '-':
			temp = op2 - op1;
			return temp;


		case '*':
			temp = op2 * op1;
			return temp;


		case '/':
			temp = op2 /  op1;
			return temp;

		}

		return temp;
	}


}//class


//******************************* Stack using Array Implementation******************

class StackImplDouble{

	private int top;
	private double[] opArray;
	public StackImplDouble(int n) {
		opArray = new double[n];
		top = -1;
	}

	public void push(double op) {
		top++;
		opArray[top] = op;
	}

	public double pop() {

		return opArray[top--]; 
	}

	public double topElemenet() {
		return opArray[top];
	}

	public boolean isEmpty() {
		return(top == -1);
	}
}

class StackImplChar{

	private int top;
	private char[] opArray;
	public StackImplChar(int n) {
		opArray = new char[n];
		top = -1;
	}

	public void push(char op) {
		top++;
		opArray[top] = op;
	}

	public char pop() {
		return opArray[top--]; 
	}

	public char topElemenet() {
		return opArray[top];
	}

	public boolean isEmpty() {
		return(top == -1);
	}

}