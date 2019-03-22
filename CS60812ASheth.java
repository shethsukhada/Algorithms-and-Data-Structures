
import java.util.*; 
import java.lang.*; 
import java.io.*; 


public class CS60812ASheth {
	public static void main(String[] args)
	{ 
		FileOperations fo = new FileOperations();
		fo.openFile();
		int adjacency_matrix[][] = fo.readFile();
		MST t = new MST(); 
		// Print the solution 
		t.primMST(adjacency_matrix);
	}
}


class MST 
{ 

	private static final int V=15; 

	// A utility function to find the vertex with minimum key 
	// value, from the set of vertices not yet included in MST 
	int minKey(int key[], Boolean mstSet[]) 
	{ 
		// Initialize min value 
		int min = Integer.MAX_VALUE, min_index=-1; 

		for (int v = 1; v <= V; v++) 
			if (mstSet[v] == false && key[v] < min) 
			{ 
				min = key[v]; 
				min_index = v; 
			} 

		return min_index; 
	} 

	// A utility function to print the constructed MST stored in 
	// parent[] 
	void printMST(int parent[], int n, int graph[][]) 
	{  int weight = 0;
	System.out.println("Edge \tWeight"); 
	for (int i = 2; i <= V; i++) 
	{	System.out.println((char)(parent[i]+'A'-1)+" - "+ (char)(i+'A'-1)+"\t"+ 
			graph[i][parent[i]]);
	weight = weight + graph[i][parent[i]];
	}
	System.out.println("Total Weight is : "+weight);
	
	}

	// Function to construct and print MST for a graph represented 
	// using adjacency matrix representation 
	void primMST(int graph[][]) 
	{ 
		// Array to store constructed MST 
		int parent[] = new int[V+1]; 

		// Key values used to pick minimum weight edge in cut 
		int key[] = new int [V+1]; 

		// To represent set of vertices not yet included in MST 
		Boolean mstSet[] = new Boolean[V+1]; 

		// Initialize all keys as INFINITE 
		for (int i = 1; i <= V; i++) 
		{ 
			key[i] = Integer.MAX_VALUE; 
			mstSet[i] = false; 
		} 

		// Always include first 1st vertex in MST. 
		key[1] = 0;     // Make key 1 so that this vertex is 
		// picked as first vertex 
		parent[1] = -1; // First node is always root of MST 

		// The MST will have V vertices 
		for (int count = 0; count < V-1; count++) 
		{ 
			// Pick thd minimum key vertex from the set of vertices 
			// not yet included in MST 
			int u = minKey(key, mstSet); 

			// Add the picked vertex to the MST Set 
			mstSet[u] = true; 

			// Update key value and parent index of the adjacent 
			// vertices of the picked vertex. Consider only those 
			// vertices which are not yet included in MST 
			for (int v = 1; v <= V; v++) 

				// graph[u][v] is non zero only for adjacent vertices of m 
				// mstSet[v] is false for vertices not yet included in MST 
				// Update the key only if graph[u][v] is smaller than key[v] 
				if (graph[u][v]!=0 && mstSet[v] == false && 
				graph[u][v] < key[v]) 
				{ 
					parent[v] = u; 
					key[v] = graph[u][v]; 
				} 
		} 

		// print the constructed MST 
		printMST(parent, V, graph); 
	} 

} 
//This code is contributed by Aakash Hasija 



class FileOperations{
	private Scanner fileScan;
	FileOperations(){
		// create date instance
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("             Assignment 12A -  MST using Prim's algorithm                        ");
		System.out.println("*********************************************************************************");
		System.out.println("                                  Assumption - Number of nodes = 15              ");
		System.out.println("*********************************************************************************");

	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData12A.txt");
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