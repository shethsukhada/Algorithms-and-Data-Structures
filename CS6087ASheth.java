import java.io.*;
import java.util.*;

public class CS6087ASheth {

	public static void main(String[] args) throws IOException{

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 7A - HashMap                                    ");
		System.out.println("*********************************************************************************");
		System.out.println();

		HashMapApl hm = new HashMapApl();	
		hm.readFile();
		hm.process();	

	}
}

class HashMapApl
{

	HashMap<String, String> myHashMap = new HashMap<>();

	void readFile() {
		try {

			String line;
			FileReader fileReader = new FileReader("inputData7A.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);	

			while ((line = bufferedReader.readLine()) != null) {
				//split the SSN and address into two parts
				String parts[] = 	 line.split(":");
                 //go on adding the key and value				
				myHashMap.put(parts[0], parts[1]);
			}

			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in File Reading");
		}
	}

	void process() throws IOException {

		String temp;
		System.out.println("Input a key to check if it exists");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		temp = reader.readLine();

		System.out.println(temp + " exists in the Hashmap : " + myHashMap.containsKey(temp));

		System.out.println("----------------------------------------------------------------------------------");

		//*********************

		System.out.println("Input a key to check its corresponding value");
		reader = new BufferedReader(new InputStreamReader(System.in));
		temp = reader.readLine();
		String temp2 = (String)myHashMap.get(temp);
		if(temp2 == null)
			System.out.println(temp + " does not exists in the Hashmap ");
		else
			System.out.println(temp2 + " is the value of key " + temp);

		System.out.println("----------------------------------------------------------------------------------");
		
		//*************************

		System.out.println("Input a key to be removed");
		reader = new BufferedReader(new InputStreamReader(System.in));
		temp = reader.readLine();

		if(  myHashMap.containsKey(temp) == true)
			System.out.println(temp + " and its value " + myHashMap.remove(temp) + " have been removed");
		else
			System.out.println("Key Does not exits.");

		System.out.println("----------------------------------------------------------------------------------");
		
		//***********************
		
		System.out.println("Hashmap using println");

		System.out.println(myHashMap);
		
		//**************************

		System.out.println("----------------------------------------------------------------------------------");
		
		@SuppressWarnings("rawtypes")
		Set myHashMapEntries = myHashMap.entrySet();
		Object[] keyValues = myHashMapEntries.toArray();

		System.out.println("Hashmap using entrySet");
		for(int i = 0 ; i < myHashMap.size() ; i++)
			System.out.println(keyValues[i]);

		System.out.println("----------------------------------------------------------------------------------");
		
		//***********************

	}
}

