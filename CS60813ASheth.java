import java.io.*;
import java.util.*;

class CS60813ASheth{

	public static void main(String[] args) {

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                            Assignment 13A - LUP                                 ");
		System.out.println("*********************************************************************************");
		System.out.println();

		LUP lup = new LUP();	
		lup.readFile();
		lup.process();	

	}
}

class LUP{

	private List<String> fileData = new ArrayList<>();
	void readFile() { 
		try {

			String line;
			FileReader fileReader = new FileReader("LUPData.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);	

			while ((line = bufferedReader.readLine()) != null) {
				fileData.add(line);
			}

			fileReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in File Reading");
		}
	}

	void process() {

		int n = fileData.size() , i = 0;
		while(i < n) {
			String temp = fileData.get(i);
			temp = temp.replaceAll("( )+", " ");
			temp = temp.replaceAll("\\s", " ");
			String[] items = temp.split(" ");

			int mat[][] = new int[3][items.length+1];
			for(int k = 1 ;k<=items.length; k++)
				mat[0][k] = Integer.parseInt(items[k-1]);
			generateLUP(mat);
			System.out.println("\n\n");
			i++;
		}

	}


	void generateLUP(int mat[][]) {

		int max_sl=0;
		int max_prev_head = -1;
		int j = 9999;
		int max_lup_len = 0;
		mat[1][0] = 1;

		for(int i = 1 ; i<mat[0].length ; i++)
		{ 
			j = i-1;
			while(j>=0)
			{  if(max_sl <= mat[1][j] &&    //trying to get the maximum SL
					mat[0][j] < mat[0][i] //&& //trying to pick the numbers which are less than the current no
					)//max_prev_head < mat[0][j] ) //trying to pick the largest no from possible options
			{
				max_sl = mat[1][j];
				max_prev_head = mat[0][j];

			}
			j--;
			}

			mat[1][i] = max_sl + 1;
			mat[2][i] = max_prev_head;
			if(max_lup_len < max_sl + 1)
				max_lup_len = max_sl + 1;

			max_sl = 0;
			max_prev_head=0;
		}

		for (int a = 0; a< 3 ; a++) {
			for(int b = 0 ; b < mat[0].length ; b++)
				System.out.print(mat[a][b]+ "\t");
			System.out.println();
		}


		int cur_ind = findIndex(mat,max_lup_len,mat[0].length,1);
		System.out.print("LUP : "+mat[0][cur_ind] + " ");
		while(mat[0][cur_ind] != 0)
		{
			cur_ind = findIndex(mat,mat[2][cur_ind],cur_ind,0);
			if ( mat[0][cur_ind] != 0 )
				System.out.print(mat[0][cur_ind] + " ");
		}

	}	
	
	

	// Linear-search function to find the index of an element 
	public  int findIndex(int arr[][], int t, int curIndex , int rowID) 
	{ 

		// if array is Null 
		if (arr == null) { 
			return -1; 
		} 

		int i = curIndex-1; 

		// traverse in the array 
		while (i >= 0) { 

			// if the i-th element is t 
			// then return the index 
			if (arr[rowID][i] == t) { 
				return i; 
			} 
			else { 
				i--; 
			} 
		} 
		return -1; 
	} 

}