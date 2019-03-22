import java.io.*;
import java.util.*;

public class CS60811ASheth
{
	public static void main(String[] args)
	{       
		FileOperations fo = new FileOperations();
		fo.openFile();
		int adjacency_matrix[][] = fo.readFile();
		DFS dfs = new DFS();
		dfs.dfs(adjacency_matrix, 1);					
	}
}




class DFS{

	private Stack<Integer> stack;

	public DFS() 
	{
		stack = new Stack<Integer>();
	}

	public void dfs(int adjacency_matrix[][], int source)
	{
		int number_of_nodes = adjacency_matrix[source].length - 1;
		int visited[] = new int[number_of_nodes + 1];		
		int element = source;		
		int i = source;		
		System.out.print((char)(element+'A'-1) + " ");		
		visited[source] = 1;		
		stack.push(source);

		while (!stack.isEmpty())
		{
			element = stack.peek();
			i = element;	
			while (i <= number_of_nodes)
			{
				if (adjacency_matrix[element][i] != 0  && visited[i] == 0)
				{
					stack.push(i);
					visited[i] = 1;
					element = i;
					i = 1;
					System.out.print((char)(element+'A'-1) + " ");
					continue;
				}
				i++;
			}
			stack.pop();	
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
		System.out.println("             Assignment 11A -  DFS Graph Traversal                               ");
		System.out.println("*********************************************************************************");
		System.out.println(" Assumption - Path starts from A and number of nodes = 15                        ");
		System.out.println("*********************************************************************************");
		System.out.println("Ref - https://www.geeksforgeeks.org/ and https://www.sanfoundry.com/             ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData11A.txt");
			fileScan = new Scanner(inputFile);
		}	
		catch(Exception e) {
			System.out.println("File Not Found");
			System.exit(1);
		}
	}

	// ************** read the file *************
	int[][] readFile(){

		int number_of_nodes = 15;

		int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];


		//read each line until end of the file has reached
		if( fileScan != null ) {
			for (int i = 1; i <= number_of_nodes; i++)
				for (int j = 1; j <= number_of_nodes; j++)	                  
				{
					if(fileScan.hasNext()) {

						String line = fileScan.next();
						if( isNumeric(line) ==  true) {			
							//convert the string data to int type
							int i2 = Integer.parseInt(line); 
							//add int into the array 
							adjacency_matrix[i][j] = i2;
						}
					}
				}

			return adjacency_matrix;
		}

		return adjacency_matrix;
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