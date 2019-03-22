
import java.util.*; 
import java.lang.*; 
import java.io.*; 


public class CS60812BSheth {
	public static void main(String[] args)
	{ 
		FileOperation fo = new FileOperation();
		fo.openFile();
		int adjacency_matrix[][] = fo.readFile();
		GraphAlgos t = new GraphAlgos(); 
		// Print the solution 
		t.buildDegreeMat(adjacency_matrix);
		System.out.println("The Graph satisfies Euler path conditon( 0 or exactly 2 vertices with odd degree) : " + t.eulerPathCheck());
		System.out.println("The Graph satisfies Euler circuit conditon( All vertices with even degree) : " + t.eulerCircuitCheck());
		System.out.println("The graph satisfies Dirac Theorm(for all degree(v)>=n/2) " + t.diracCheck());
		System.out.println("The graph satisfies Ore's Therom (degree(X) + degree(Y) >= n for all X&Y non adj. vertices) " + t.oreCheck(adjacency_matrix));
	}
}

class GraphAlgos{
	private static final int V=15; 
	private int degreeMat[] = new int[V+1];
	void buildDegreeMat(int graph[][]){

		for(int i=1 ; i<=V ;i++)
			for(int j=1; j<=V ; j++)
			{
				if(graph[i][j]!=0)
					degreeMat[i] = degreeMat[i] + 1;
			}
		System.out.println("Vertex" + " - " + "Degree");	
		for(int i = 0; i<15 ; i++)
			System.out.println((char)(i + 'A' ) + " \t " + degreeMat[i+1]);		
	}
	boolean eulerPathCheck(){

		int count = 0; 
		for(int i=1; i<=V ; i++)
		{ 
			if ( degreeMat[i] % 2 != 0 )
			{ 
				count++;
				if(count==3)
					break;
			}

		}
		if(count == 2 || count == 0) 
			return true;
		else 
			return false;
	}
	boolean eulerCircuitCheck(){
		boolean flag = true; 
		for(int i=1; i<=V ; i++)
		{ 
			if ( degreeMat[i] % 2 != 0 )
			{ 
				flag = false;
				break;
			}

		}
		return flag;
	}
	boolean diracCheck(){
		boolean flag = true; 
		for(int i=1; i<=V ; i++)
		{ 
			if ( !(degreeMat[i]  >= V/2))
			{ 
				flag = false;
				break;
			}

		}
		return flag;
	}
	boolean oreCheck(int graph[][]){
		boolean flag  = true;
		for(int i = 1 ; i<=V ; i++)
			for(int j = 1 ; j<=V ; j++)
			{
				if(graph[i][j] == 0 && degreeMat[i] + degreeMat[j] < V)
				{
					flag = false;
					break;
				}
			}
		return flag;
		}
}

class FileOperation{
	private Scanner fileScan;
	FileOperation(){
		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                   Assignment 12B Graph Algorithms                               ");
		System.out.println("*********************************************************************************");
		System.out.println("                                  Assumption - Number of nodes = 15              ");
		System.out.println("*********************************************************************************");

	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData12B.txt");
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