import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class HashTable {

	protected ArrayList<KeyValue>[] hashTable;
	protected int size;
	protected ArrayList<KeyValue> temp = new ArrayList<KeyValue>();
	protected ArrayList<Double> my_time = new ArrayList<Double>();
	protected int loadFactor;


	// Constructor
	public HashTable(int initialCapacity) {
		size = initialCapacity;
		hashTable = (ArrayList<KeyValue>[])new ArrayList[size];
	}

	// Dynamically sizing the hash table
	public void dynamicSizing() {
		ArrayList<KeyValue>[] new_hashTable = (ArrayList<KeyValue>[])new ArrayList[size * 2];
		System.arraycopy(hashTable, 0, new_hashTable, 0, hashTable.length);
		hashTable = new_hashTable;
	}

	// Putting the key/value in hash table
	public void put(Object key, Object value) {
		// Using ascii values
		String convert = String.valueOf(key);
		char[] k = convert.toCharArray();
		int ascii = (int) k[0];
		int index = ascii % hashTable.length;
		int holder = ascii % hashTable.length;
		loadFactor = (int) (10000 * 0.75);

		Hashing(key, value, index, holder);
	}

	// Hashing the function recursively
	public void Hashing(Object key, Object value, int index, int holder) {
		if (index == hashTable.length) {
			index = 0; 
		}

		if (index == (holder - 1)) {
			dynamicSizing();
		}

		if (hashTable[index] == null) {
			ArrayList<KeyValue> buckets = new ArrayList<KeyValue>(100);
			KeyValue b = new KeyValue(key, value);
			buckets.add(b);
			hashTable[index] = buckets;
		} else {
			ArrayList<KeyValue> buckets = hashTable[index];
			if (buckets.size() < loadFactor) {
				KeyValue b = new KeyValue(key, value);
				buckets.add(b);
			} else {
				Hashing(key, value, index + 1, holder);
			}
		}
	}
	
	// Method for getting the value back from the key
	public Object get(Object key) {
		String convert = String.valueOf(key);
		char[] k = convert.toCharArray();
		int ascii = (int) k[0];
		int index = ascii % size;

		ArrayList<KeyValue> values = new ArrayList<KeyValue>();

		int i = index;
		while (i < hashTable.length) {
			ArrayList<KeyValue> buckets = hashTable[i];
			if (buckets != null) {
				for (int j = 0; j < buckets.size(); j++) {
					String k1 = String.valueOf(buckets.get(j).getKey());
					if (k1.equals(key)) {
						values.add(buckets.get(j));
					}
				}
			}
			i++;
		}
		
		i = 0;
		while (i < index) {
			ArrayList<KeyValue> buckets = hashTable[i];
			if (buckets != null) {
				for (int j = 0; j < buckets.size(); j++) {
					String k1 = String.valueOf(buckets.get(j).getKey());
					if (k1.equals(key)) {
						values.add(buckets.get(j));
					}
				}
			}
			i++;
		}

		if (values.isEmpty()) {
			return null;
		} else {
			temp.add(values.get(values.size() - 1));
			return values.get(values.size() - 1).getValue();
		}
	}


	// THIS CODE WORKS. IT'S JUST COMMENTED OUT BECAUSE IT TAKES ALOT OF TIME WHEN RUNNING WITH A BIG INPUT

/*	public void Graphing() {
		for (int i = 0; i < hashTable.length; i++) {
			ArrayList<KeyValue> buckets = hashTable[i];
			if (buckets != null) {
				for (int j = 0; j < buckets.size(); j++) {
					this.get(buckets.get(j).getKey());
				}
			}
		}
		ArrayList<KeyValue> no_duplicate = new ArrayList<KeyValue>();;

		for (int i = 0; i < temp.size(); i++) {
			if (!no_duplicate.contains(temp.get(i))) {
				no_duplicate.add(temp.get(i));
			}
		}
		
		temp.clear();
		temp = no_duplicate;
		int count = 0;
		
		for (int i = 0; i < temp.size(); i++) {
			count++;
			double start = System.nanoTime();
			System.out.println(temp.get(i).getValue());
			double end = System.nanoTime();
			double convert = (double) (Math.round(((end - start) * (double) 0.000001) * 1000.0) / 1000.0);
			my_time.add(convert);

		}
		
		System.out.println();
		System.out.println();
		
	}
	*/

	// Printing for visualization purposes
	public void PrintInFile() {
		try {
			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter("test.txt"));
			for (int i = 0; i < hashTable.length; i++) {
				ArrayList<KeyValue> buckets = hashTable[i];
				if (buckets != null) {
					for (int j = 0; j < buckets.size(); j++) {
						outputWriter.write(buckets.get(j).toString() + " , ");
					}
				}
			    outputWriter.newLine();
			}
			outputWriter.flush();  
			outputWriter.close();	
		} catch (IOException e) {
			System.out.println("Enter the sorted file name again.");
		}
	}


	// Storing time for every key in a file for graphing purposes
	public void DataInFile(ArrayList<Double> java_time) {
		int j = 0;
		try {
			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter("data.txt"));	
			for (int i = 0; i < my_time.size(); i++) {
				while (j < java_time.size()) {
					outputWriter.write((my_time.get(i) * 1000) + ", " + (java_time.get(j) * 1000));
					j++;
					break;
				}
				outputWriter.newLine();
			}
			outputWriter.flush();  
			outputWriter.close();	
		} catch (IOException e) {
			System.out.println("Enter the sorted file name again.");
		}
	}
}