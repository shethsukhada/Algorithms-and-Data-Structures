/*******************  Assignment 6A  ***************************
 * Name                    -   Sukhada Surendra Sheth          *
 * Program Name            -   Infix to Postfix usning Stack   *
 * Date                    -   17-Oct-2018                     *
 ***************************************************************
 * Program Description     -                                   *
 *    Program to implement infix to postfix conversion using   *
 *    stack                                                    *    
 ***************************************************************/
import java.io.*;
import java.util.*;

// main class
public class CS6086ASheth {

	public static void main(String[] args) throws IOException{

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 6A - Infix to Postfix                           ");
		System.out.println("*********************************************************************************");
		System.out.println();

		StackApp sa = new StackApp();	
		sa.readFile();
		sa.processFile();	

	}
}

class StackApp{

	String strArray[] = new String[30];
	int countInfix;

	void readFile() {
		try {

			FileReader fileReader = new FileReader("infixData6A.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int j = 0;
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				strArray[j] = line;
				strArray[j] = strArray[j].replaceAll("( )+", ""); //to remove all the spaces
				strArray[j] = strArray[j].replaceAll("\t+", "");  //to remove tab spaces
				j++;
			}

			fileReader.close();

			for(j = 0 ; j< 30; j++) {
				if(strArray[j] != null) {
					//System.out.println(strArray[j]);
					countInfix++;}
				else {
					continue; }
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in File Reading");
		}

	}
	void processFile() {

		String output;
		StackImpl opStack = new StackImpl(countInfix);

		for( int j = 0 ; j < countInfix; j++) {

			int infixLen = strArray[j].length();
			output = "";

			for(int i = 0; i<infixLen ; i++) {

				char currentChar = strArray[j].charAt(i);

				if( currentChar >= 'a' && currentChar <= 'z')
					output += currentChar;
				else {

					switch(currentChar) {
					case '+':
					case '-':
						if(opStack.isEmpty() == true) {
							opStack.push(currentChar);
						}
						else {
							while(opStack.isEmpty() !=  true) {
								output += (char)opStack.pop();							
							}
							opStack.push(currentChar);
						}
						break;

					case '*':
					case '/':
						if( opStack.isEmpty() || opStack.topElemenet() == '+' || opStack.topElemenet() == '-' ) {
							opStack.push(currentChar);
						}
						else {
							while(opStack.isEmpty() != true && 
									((char)opStack.topElemenet()) != '+' &&
									((char)opStack.topElemenet()) != '-') {
								output += (char)opStack.pop();
							}
							opStack.push(currentChar);
						}
						break;


					case '^':
						if( opStack.isEmpty() || opStack.topElemenet() == '+' || opStack.topElemenet() == '-'
						|| opStack.topElemenet() == '/' || opStack.topElemenet() == '*' ) {
							opStack.push(currentChar);
						}
						else {
							while(opStack.isEmpty() != true && 
									((char)opStack.topElemenet()) != '+' &&
									((char)opStack.topElemenet()) != '-' &&
									((char)opStack.topElemenet()) != '/' &&
									((char)opStack.topElemenet()) != '*'){
								output += (char)opStack.pop();
							}
							opStack.push(currentChar);
							break;
						}									

					}	//end of switch

				}//end of else

			} //for loop on current infix expression

			while(!opStack.isEmpty())
				output += (char)opStack.pop();

			System.out.println("Given Infix Expression" +"\t"+  strArray[j]);
			System.out.println("Postfix Expression" +"\t"+ output);
			System.out.println();

		} // for on array of infix expressions
	} //method

}//class

//******************************* Stack using Array Implementation******************
class StackImpl{

	private int top;
	private char[] opArray;
	public StackImpl(int n) {
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
