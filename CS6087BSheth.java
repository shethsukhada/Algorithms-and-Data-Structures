import java.io.*;
import java.math.*;
import java.util.*;

public class CS6087BSheth {

	public static void main(String[] args) throws IOException{

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 7B - Manual Hash table                          ");
		System.out.println("*********************************************************************************");
		System.out.println();

		Hashing hm = new Hashing();	
		hm.readFile();
		hm.process();	

	}
}


class Hashing{

	private List<String> fileData = new ArrayList<>();
	private int M;
	private String customHashTable[] ;
	
	
	void readFile() { 

		try {

			String line;
			FileReader fileReader = new FileReader("inputData7B.txt");
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

		M = fileData.size() * 2;	
		M = getNextPrime(M);

		customHashTable = new String[M];

		//build the hashtable
		for(int i = 0; i< fileData.size() ; i++)
		{
			customHashTable[getHashCode(fileData.get(i), 0 )] = fileData.get(i);	
		}

		/*for(int i = 0 ; i < M ; i++)
		{  if ( customHashTable[i] != null )
			System.out.println(i + "    " + customHashTable[i]);
		}
		 */

		//print as per the assignment 
		System.out.println("Number of elemenets is " + fileData.size() );
		System.out.println("Size of the hash table is " + M );

		System.out.println("Hascode for Boston " +  getHashCode("Boston", 1));
		System.out.println("Hascode for Brussels "  + getHashCode("Brussels", 1));
		System.out.println("Hascode for Pleasantville " +  getHashCode("Pleasantville", 1));



		String temp = "Boston";
		int code = getHashCode(temp, 1 );
		if( Objects.equals( customHashTable[code] , temp ) )
			System.out.println("Boston found at " + code);
		else
			System.out.println("Boston not found");


		temp = "Brussels";
		code = getHashCode(temp, 1 );
		if( Objects.equals(customHashTable[code] , temp ) )
			System.out.println("Brussels found at " + code);
		else
			System.out.println("Brussels not found");

		temp = "Shanghai";
		code = getHashCode(temp, 1 );
		if( Objects.equals( customHashTable[code] , temp ) )
			System.out.println("Shanghai found at " + code);
		else
			System.out.println("Shanghai not found");

	}


	public int getHashCode(String str , int get)
	{
		//get parameter is used to get the hashcode of the existing stored element. 
		//so whenever get = 1, search is not for null but for matching element is the hashtable
		
		long mask = 0x7ffffffffL;
		long hashcode;
		boolean comp;
		hashcode = ( str.hashCode()  & mask ) % M;

		comp = Objects.equals(customHashTable[(int)hashcode],str); //
		if(  customHashTable[(int)hashcode] == null || (  get == 1 && comp == true ) ) 
		{  
			//System.out.println(str + "   "  + hashcode);
			return (int)hashcode;

		}
			//handling of collision scenario. Using quadratic probing.
		else
		{
			int newHashcode, oldHashcode  = (int)hashcode , k = 1;
			boolean flag = false;

			while(flag != true){ 
				
				//quadratic probing
				newHashcode =	( oldHashcode + ( k * k ) ) % M ;

				//System.out.println(str + " looking alternate location for " + hashcode + "  "+ newHashcode);
				
				comp = Objects.equals(customHashTable[(int)newHashcode], str);
				
				if( ( customHashTable[(int)newHashcode] == null  ) ||
						( get == 1 && comp == true)  )
				{
					flag = true;
					return (int)newHashcode;
				}
				else
					k++;
			}

		}
		return 0;
	}

	
	//prime number to calculate size of the array
	static boolean checkPrime(long n) 
	{ 
		// Converting long to BigInteger 
		BigInteger b = new BigInteger(String.valueOf(n)); 

		return b.isProbablePrime(1); 
	} 

	// for size of the array
	public int getNextPrime(int n) 
	{ 
		boolean flag = false;
		while(flag != true)
		{ 
			flag = checkPrime(n);
			n++;
		} 

		return n-1;
	} 

}





