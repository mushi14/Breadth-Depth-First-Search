import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		
		Hashtable java_table = new Hashtable();
		HashTable table = new HashTable(100);
		File file = new File(args[0]);
		int count = 0;

		// For inserting in my hash table
		try {
			Scanner scan = new Scanner(file);
			
			long start = System.currentTimeMillis();
			while (scan.hasNext() && count < 10000) {
				count++;
				Object combinations[] = scan.nextLine().split("\\s+");
				
				boolean exists = false;
				if (combinations.length > 1) {
					Object key = combinations[0];
					Object value = combinations[1];
					
					table.put(key, value);
				}
			}
			// Calculating stop time and printing the time elapsed
			long end = System.currentTimeMillis();
			System.out.println("Total time taken for inserting (using my hash table): " + (end - start));
			
		} catch (FileNotFoundException e) {
			System.out.println("Enter the sorted file name again.");
		}
		
		// Resetting count
		count = 0;
		
		// For inserting in the java hash table
		try {
			Scanner scan = new Scanner(file);
			
			long start = System.currentTimeMillis();
			while (scan.hasNext() && count < 10000) {
				count++;
				Object combinations[] = scan.nextLine().split("\\s+");
				
				if (combinations.length > 1) {
					Object key = combinations[0];
					Object value = combinations[1];
					
					java_table.put(key, value);
				}
			}
			// Calculating stop time and printing the time elapsed
			long end = System.currentTimeMillis();
			System.out.println("Total time taken for inserting (using java's hash table): " + (end - start));
			
		} catch (FileNotFoundException e) {
			System.out.println("Enter the sorted file name again.");
		}

		// For 
		ArrayList<Double> java_time = new ArrayList<Double>();
		Enumeration temp = java_table.keys();

		System.out.println();
    	// Calculating and printing time for getting a value using java hash table
		while (temp.hasMoreElements()) {
			double start = System.nanoTime();
			Object key = (Object) temp.nextElement();
		    table.get(key);
			double end = System.nanoTime();
			double convert = (double) (Math.round(((end - start) * (double) 0.000001) * 1000.0) / 1000.0);
			java_time.add(convert);
		}

		// Printing table into a file for visualizing the data
		table.PrintInFile();

		//  Find the times for my hash table. Commented because takes a long time to execute.
//		table.Graphing();
		
		// Puts the data in a txt file that is used to graph in excel.
//		table.DataInFile(java_time);
	}

}

// I know my code is very redundant at places and is inefficient in other, however, in the alloted time, I couldn't make it 
// more efficient. I finished, but I know I could've done much better and made this project much more appealing. Everything works,
// even with larger input, however, it takes a long time. I graphed in excel and will upload the file. Thank you
