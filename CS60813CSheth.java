import java.util.Date;

public class CS60813CSheth {

	public static void main(String args[])
	{

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("         Assignment 13C - Sudoku 9*9 Grid Using 3*3 Magic Square                 ");
		System.out.println("*********************************************************************************");
		System.out.println("2nd,3rd and 4th Magic Square obtained by rotating first one clockwise            ");
		System.out.println("*********************************************************************************");

		System.out.println();		

		int n = 3;
		Sudoku sdk = new Sudoku();
		int[][] magicSq =  sdk.generateMagicSq(3);
		System.out.println("\nSudoku Set 1\n");
		sdk.generateSudoku(magicSq);

		magicSq = sdk.rotateMagicSq(magicSq);
		System.out.println("\nSudoku Set 2\n");
		sdk.generateSudoku(magicSq);

		magicSq = sdk.rotateMagicSq(magicSq);
		System.out.println("\nSudoku Set 3\n");
		sdk.generateSudoku(magicSq);

		magicSq = sdk.rotateMagicSq(magicSq);
		System.out.println("\nSudoku Set 4\n");
		sdk.generateSudoku(magicSq);

	}

}

class Sudoku{

	private static int n = 9;

	int[][] rotateMagicSq(int magicSq[][]){
		// Observed the pattern of new positions of elements
		// and accordingly designed the i,j loop

		int x = -1 , y = 0;
		int temp[][] = new int[3][3];

		//copying the magic sq
		for(int i = 0 ; i < 3 ; i++)
			for(int j = 0 ; j < 3 ; j++)
				temp[i][j] = magicSq[i][j];

		//rotating clockwise
		for(int i = 0 ; i < 3 ; i++)
		{   x++; y = 0 ;
		for(int j = 2 ; j >= 0 ; j -- )
		{
			magicSq[x][y++] = temp[j][i];

		}
		}

		return magicSq;
	}


	void generateSudoku(int[][] magicSq) {

		// steps - 

		// rowshift - first develop the 
		//            top grid row of sudoku ( block 1 , 2 and 3 )
		// columnshift - then develope the 4th onwards block using 
		//            the block above it

		// for every shift pass the starting row and column of the reference block


		int[][] sudoku = new int[n][n];
		int i = 0 , j = 0 ;

		//block1
		for( i = 0 ; i < 3 ; i ++)
			for( j  = 0 ; j < 3 ; j++)
				sudoku[i][j] = magicSq[i][j];

		//block 2
		i = 0 ; j = 0;
		sudoku = rowShift(i,j,sudoku);
		//block3
		i = 0 ; j = 3;
		sudoku = rowShift(i,j,sudoku);

		//block4
		i = 0 ; j = 0 ;
		sudoku = columnShift(i,j,sudoku);
		//block5
		i = 0 ; j = 3 ;
		sudoku = columnShift(i,j,sudoku);
		//block6
		i = 0 ; j = 6 ;
		sudoku = columnShift(i,j,sudoku);

		//block7
		i = 3 ; j = 0 ;
		sudoku = columnShift(i,j,sudoku);
		//block8
		i = 3 ; j = 3 ;
		sudoku = columnShift(i,j,sudoku);
		//block9
		i = 3 ; j = 6 ;
		sudoku = columnShift(i,j,sudoku);

		//printing the solution

		System.out.println("---------------------------------------------------------------------");

		for(int x = 0 ; x < n ; x++) {
			for(int y = 0 ; y < n ; y++) {
				if(y!=0 && (y+1)%3 ==0)
					System.out.print(sudoku[x][y] + "  | \t");
				else
					System.out.print(sudoku[x][y] + "\t");
			}
			System.out.println("");
			if(x!=0 &&(x+1)%3==0)
				System.out.println("---------------------------------------------------------------------");
		}
	}

	// for first block row of sudoku
	int[][] rowShift(int oldi , int oldj, int sudoku[][] )
	{ 
		int newi = 1;
		int newj = oldj + 3;
		int backupj = oldj;

		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++)
				sudoku[newi][newj++] = sudoku[oldi][oldj++];

			oldi++;
			oldj= backupj;
			newi =  ( ++newi ) % 3;
			newj = oldj + 3;
		}

		return sudoku;

	}


	// for 4th onward blocks of sudoku
	int[][] columnShift(int oldi , int oldj, int sudoku[][] )
	{

		int newi = oldi+3;
		int newj = oldj+1;
		int backupi = oldi ; 
		int backupj = oldj;

		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++)
				sudoku[newi++][newj] = sudoku[oldi++][oldj];

			oldi = backupi ; newi = oldi + 3;

			oldj++;			
			newj = ( (++newj) % ( backupj + 3) ) ;
			if(newj == 0 )
				newj = newj + backupj;
		}

		return sudoku;

	}


	//generating magic square using algorithm discussed in the class
	int[][] generateMagicSq(int n){
		int[][] magicSq = new int[n][n];

		int i = 0 , j = n/2 , k = 1;

		magicSq[0][j] = k++;

		while(k<=n*n) {

			i--; j++;

			if(i<0 && j>n-1)
			{
				i = i + 2;
				j--;

			}

			if(i<0)
				i = n-1;
			if(j>n-1)
				j=0;

			if(magicSq[i][j]!=0)
			{
				i = i + 2;
				j--;			


			}

			magicSq[i][j] = k++;


		}

		return magicSq;
	}
}
