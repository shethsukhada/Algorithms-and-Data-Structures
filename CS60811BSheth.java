import java.io.*;
import java.lang.*;
import java.util.*;

public class CS60811BSheth
{
	public static void main(String[] args)
	{       
		FileOperation fo = new FileOperation();
		fo.openFile();
		int adjacency_matrix[][] = fo.readFile();
		System.out.print("\nDFS Traversal : ");
		DFSTrav dfs = new DFSTrav();
		dfs.dfs(adjacency_matrix, 1);		
		BFSTrav bfs = new BFSTrav();
		System.out.print("\n\nBFS Traversal : ");
		bfs.bfs(adjacency_matrix, 1);
		System.out.println("\n\nShortest Distance from A to every other vertext:");
		ShortestPath sp = new ShortestPath(adjacency_matrix[1].length);
		sp.dijkstra(adjacency_matrix, 1);
			}
}


 class BFSTrav
{ 
 
    private Queue<Integer> queue;
 
    public BFSTrav()
    {
        queue = new LinkedList<Integer>();
    }
 
    public void bfs(int adjacency_matrix[][], int source)
    {
        int number_of_nodes = adjacency_matrix[source].length - 1;
 
        int[] visited = new int[number_of_nodes + 1];
        int i, element;
 
        visited[source] = 1;
        queue.add(source);
 
        //System.out.println();
        while (!queue.isEmpty())
        {  i = 1;
            element = queue.remove();
            //i = element;
            System.out.print((char)(element+'A'-1) + " ");
            while (i <= number_of_nodes)
            {
                if (adjacency_matrix[element][i] != 0  && visited[i] == 0)
                {
                    queue.add(i);
                    visited[i] = 1;
                }
                i++;
            }
        }
    }
 
}

class DFSTrav{

	private Stack<Integer> stack;

	public DFSTrav() 
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



class ShortestPath 
{ 
	
	
  // A utility function to find the vertex with minimum distance value, 
  // from the set of vertices not yet included in shortest path tree 
  static  int num =0; 

  ShortestPath(int n){
	  num = n-1;
  }
  
  int minDistance(int dist[], Boolean sptSet[]) 
  { 
      // Initialize min value 
      int min = Integer.MAX_VALUE, min_index=-1; 

      for (int v = 1; v <= num; v++) 
          if (sptSet[v] == false && dist[v] < min) 
          { 
              min = dist[v]; 
              min_index = v; 
          } 

      return min_index; 
  } 

  // A utility function to print the constructed distance array 
  void printSolution(int dist[], int n, String path[]) 
  {  int x = -1;
     StringBuffer stf = new StringBuffer();
      System.out.println("Vertex   Distance from Source  Path"); 
      for (int i = 1; i <= num; i++) 
      {   x = i;
          stf.setLength(0);
    	  System.out.print((char)(i+'A'-1) + " "+" \t\t "+dist[i] + "\t\t" );
    	  while( x != 1) {   	  
    	 
    	  stf.append(String.valueOf(path[x]));
    	  x = Integer.parseInt(path[x]);
    	  } 
    	  String q = stf.toString();
    	  for(int j = q.length()-1 ; j >= 0 ; j--)
    	  {   		  
    		  String p = Character.toString(q.charAt(j));
    		  System.out.print((char)( Integer.parseInt(p)+'A'-1) + " ");
    	  }
    	  System.out.println();
      }
  } 

  // Funtion that implements Dijkstra's single source shortest path 
  // algorithm for a graph represented using adjacency matrix 
  // representation 
  void dijkstra(int graph[][], int src) 
  { 
      int dist[] = new int[num+1]; // The output array. dist[i] will hold 
                               // the shortest distance from src to i 
      
      String path[] = new String[num+1];

      // sptSet[i] will true if vertex i is included in shortest 
      // path tree or shortest distance from src to i is finalized 
      Boolean sptSet[] = new Boolean[num+1]; 

      // Initialize all distances as INFINITE and stpSet[] as false 
      for (int i = 1; i <= num; i++) 
      { 
          dist[i] = Integer.MAX_VALUE; 
          sptSet[i] = false; 
          path[i] = "";
      } 

      // Distance of source vertex from itself is always 0 
      dist[src] = 0; 

      
      // Find shortest path for all vertices 
      for (int count = 1; count < num; count++) 
      { 
          // Pick the minimum distance vertex from the set of vertices 
          // not yet processed. u is always equal to src in first 
          // iteration. 
          int u = minDistance(dist, sptSet); 

          // Mark the picked vertex as processed 
          sptSet[u] = true; 

          // Update dist value of the adjacent vertices of the 
          // picked vertex. 
          for (int v = 1; v <= num; v++) 

              // Update dist[v] only if is not in sptSet, there is an 
              // edge from u to v, and total weight of path from src to 
              // v through u is smaller than current value of dist[v] 
              if (!sptSet[v] && graph[u][v]!=0 && 
                      dist[u] != Integer.MAX_VALUE && 
                      dist[u]+graph[u][v] < dist[v]) 
              {  dist[v] = dist[u] + graph[u][v];
         
                        
                   path[v] = String.valueOf(u); }
      } 

      // print the constructed distance array 
      printSolution(dist, num, path); 
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
		System.out.println("             Assignment 11B -  BFS , DFS , Dikjtras Traversal                    ");
		System.out.println("*********************************************************************************");
		System.out.println(" Assumption - Path starts from A  and number of nodes 15                         ");
		System.out.println("*********************************************************************************");
		System.out.println("Ref - https://www.geeksforgeeks.org/ and https://www.sanfoundry.com/             ");
		System.out.println("*********************************************************************************");
	}

	// ************** open the file *************	
	void openFile() {
		try {
			File inputFile = new File("inputData11B.txt");
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