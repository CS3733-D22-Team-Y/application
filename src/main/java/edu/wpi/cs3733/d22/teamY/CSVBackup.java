package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.StringArrayConv;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVBackup {

	private CSVBackup() {}

	// Function that reads in CSV file for the corresponding entry type and
	// adds them to the DBManager.
	// Also returns all its values as an ArrayList of entries.
	public static ArrayList<? extends StringArrayConv> loadFromCSV(EntryType type) {
		System.out.println("Loading from " + type.getEntryClass().getSimpleName());
		return loadHelper(type, type.getEntryClass());
	}

	// Function that reads in CSV file for the corresponding class and
	// adds them to the DBManager.
	// Also returns all its values as an ArrayList of entries.
	public static <T extends StringArrayConv> ArrayList<T> loadFromCSV(Class<T> entryClass) {
		EntryType type = EntryType.getFromClass(entryClass);
		if (type == null) {
			System.out.println(
					"CSV read failed: Class \"" + entryClass.getName() + "\" is not a valid entry type.");
			return null;
		}

		return loadHelper(type, entryClass);
	}

	@SuppressWarnings("unchecked")
	private static <T extends StringArrayConv> ArrayList<T> loadHelper(
			EntryType type, Class<T> entryClass) {
		// ArrayList created to store values from CSV output
		ArrayList<String> csvOutputs = new ArrayList<>();
		ArrayList<T> output = new ArrayList<>();

		// try-catch in order to avoid File not Found error
		try {

			// creates scanner object
			Scanner sc = new Scanner(new File(type.getCsvInputLocation() + ".csv"));

			while (sc.hasNextLine()) // returns a boolean value
			{
				String s = sc.nextLine(); // takes line of input with commas

				String[] vals = s.split(","); // divides the CSV data by commas

				// adds all values into csvOutputs
				csvOutputs.addAll(Arrays.asList(vals));
			}
			sc.close(); // closes scanner
		} catch (java.io.FileNotFoundException e) {
			System.out.println("CSV read failed: File not found."); // accounts for File not Found
			return null;
		}

		int size = type.getColumns();
		Constructor<? extends StringArrayConv> cons;

		// adds each location value into the ArrayList
		try {
			cons = entryClass.getConstructor();
		} catch (NoSuchMethodException e) {
			System.out.println(
					"CSV read failed: Class \"" + entryClass.getName() + "\" has no valid constructor.");
			return null;
		}

		for (int i = size; i < csvOutputs.size(); i += size) {
			try {
				String[] sa = csvOutputs.subList(i, i + size).toArray(String[]::new);
				StringArrayConv conv = cons.newInstance();
				conv.fromStringArray(sa);

				output.add((T) conv);
			} catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
				System.out.println(
						"CSV read failed: Could not instantiate class \"" + entryClass.getName() + "\".");
				return null;
			}
		}

		DBManager.saveList(output);

		return output;
	}

	// Function that backs up all entries of the specified class
	// from the DBManager to the proper CSV output file.
	public static <T extends StringArrayConv> void saveToCSV(Class<T> entryClass) {
		EntryType type = EntryType.getFromClass(entryClass);
		if (type == null) {
			System.out.println(
					"CSV write failed: Class \"" + entryClass.getName() + "\" is not a valid entry type.");
			return;
		}

		saveToCSV(type);
	}

	// Function that backs up all entries of the specified type
	// from the DBManager to the proper CSV output file.
	public static void saveToCSV(EntryType type) {
		File csvFile = new File(type.getCsvOutputLocation() + ".csv");
		List<StringArrayConv> list;
		FileWriter fileWriter;

		try {
			fileWriter = new FileWriter(csvFile);
			list = DBManager.getAll(type.getEntryClass());
		} catch (IOException e) {
			System.out.println("CSV write failed: Could not open file.");
			return;
		}

		if (list == null) {
			System.out.println("CSV write failed: Null list retrieved.");
			return;
		}

		String[][] stringMatrix = new String[list.size()][type.getColumns()];
		for (int i = 0; i < list.size(); i++) {
			stringMatrix[i] = list.get(i).toStringArray();
		}

		for (String[] data : stringMatrix) {
			try {
				StringBuilder line = new StringBuilder();
				for (int i = 0; i < data.length; i++) {
					// line.append("\"");
					line.append(data[i].replaceAll("\"", "\"\""));
					// line.append("\"");
					if (i != data.length - 1) {
						line.append(',');
					}
				}
				line.append("\n");
				fileWriter.write(line.toString());
			} catch (IOException e) {
				System.out.println("CSV write failed: File write was unsuccessful.");
				tryClose(fileWriter);
				return;
			}
		}

		tryClose(fileWriter);
	}

	private static void tryClose(FileWriter fw) {
		try {
			fw.close();
		} catch (IOException e) {
			System.out.println("Could not close file.");
		}
	}
}
