package utility;

import java.io.*;
import java.util.ArrayList;

import types.*;

public class MiscUtility {

	/**
	 * Create a blank copy of all the files in a specified folder
	 * 
	 * @param src
	 *            - the source folder
	 * @param dst
	 *            - the destination folder
	 */
	public static void blankFiles(File src, File dst) {
		if (src != null) {
			for (File file : src.listFiles()) {
				try {
					File toWrite = new File(dst + "\\history\\" + src.getName() + "\\" + file.getName());
					toWrite.getParentFile().mkdirs();
					BufferedWriter writer = new BufferedWriter(new FileWriter(toWrite));
					writer.append("");
					writer.close();
					Log.info("File " + src.getName() + "\\" + file.getName() + " blanked");
				} catch (IOException e) {
					Log.error("Trouble during Blank operation for file " + src.getName() + "\\" + file.getName());
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Create the CK2 Mod file
	 * 
	 * @param fl_mod
	 *            - File destination
	 */
	public static void initModFile(File fl_mod) {
		Log.info("Creating CK2-Randomizer Mod file...");
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fl_mod));
			writer.append("name = \"CK2-Randomizer\"\n");
			writer.append("path = \"mod\\CK2-Randomizer\"\n");
			writer.append("user_dir = \"CK2-Randomizer\"\n");
			writer.append("replace_path = \"common\"\n");
			writer.append("replace_path = \"common\\landed_titles\"\n");
			writer.append("replace_path = \"events\"\n");
			writer.append("replace_path = \"history\\characters\"\n");
			writer.append("replace_path = \"history\\diplomacy\"\n");
			writer.append("replace_path = \"history\\provinces\"\n");
			writer.append("replace_path = \"history\\technology\"\n");
			writer.append("replace_path = \"history\\titles\"\n");
			writer.append("replace_path = \"history\\wars\"\n");
			writer.append("replace_path = \"gfx\\flags\"\n");
			writer.append("replace_path = \"localization\"\n");
			writer.close();
		} catch (IOException e) {
			Log.error("Creation of CK2-Randomizer Mod file unsuccessfull");
			e.printStackTrace();
		}
		Log.info("Created Successfully\n");
	}

	public static Holding randomizeHolding(int rand) {
		if (rand < 70)
			return Holding.castle;
		else if (rand >= 70 && rand < 95)
			return Holding.tribal;
		else
			return Holding.city;
	}

	/**
	 * It lists all the dynasties available in the game, splitted by culture
	 * 
	 * @param src_dynasties
	 *            - source dynasties file
	 * @return a list of list of dynasties
	 */
	public static ArrayList<ArrayList<Long>> initDynasties(File src_dynasties) {
		// Initialize a new arrayList of list
		ArrayList<ArrayList<Long>> dynasties = new ArrayList<ArrayList<Long>>();
		for (int i = 0; i < 92; i++)
			dynasties.add(new ArrayList<Long>());

		String line = "";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(src_dynasties));
			while ((line = reader.readLine()) != null) {
				Long id = 0l;
				if (line.split("=")[0].trim().matches("-?\\d+(\\.\\d+)?")) {
					id = Long.parseLong(line.split("=")[0].trim());
					while (!line.contains("culture")) {
						line = reader.readLine();
					}
					if (line.contains("=")) {
						Culture culture = Culture.valueOf(line.split("=")[1].split("#")[0].trim().replace("\"", ""));
						dynasties.get(culture.ordinal()).add(id);
						Statistics.countDynasties();
					}
				}
			}
			reader.close();
		} catch (NumberFormatException e) {
			Log.error("Error in parsing Dynasty code: " + line.split("=")[0].trim());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("I/O Error with file : " + src_dynasties.getName());
			e.printStackTrace();
		}
		Log.info("Number of dynasties retrieved: " + Statistics.getDynasties() + "\n");
		return dynasties;
	}

	/**
	 * It lists all the possible male names, splitted by culture
	 * 
	 * @param src_names
	 *            - names source file
	 * @return a list of list of names
	 */
	public static ArrayList<ArrayList<String>> initNames(File src_names) {
		// Initialize all the lists
		ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 92; i++)
			names.add(new ArrayList<String>());
		
		// Loop for each culture
		for (int j = 0; j < 92; j++) {
			String line;
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(src_names));
				String culture = Culture.values()[j].toString();
				while ((line = reader.readLine()) != null) {
					// Find specific culture
					if (line.trim().startsWith(culture + " = {") || line.contains("lappish")) {
						while (!(line = reader.readLine()).trim().startsWith("male_names = {")) {
							// do nothing
						}
						while (!(line = reader.readLine()).contains("}")) {
							// Skip commented names
							if (!line.startsWith("#")) {
								String splitted[] = line.split(" ");
								for (String string : splitted) {
									// Skip empty strings
									if (string.trim().length() > 1) {
										// Register only culture specific name
										names.get(j).add(string.split("_")[0].trim());
										Statistics.countNames();
									}
								}
							}
						}
					}
				}
				reader.close();
			} catch (IOException e) {
				Log.error("I/O Error with file : " + src_names.getName());
				e.printStackTrace();
			}

		}
		Log.info("Number of character names retrieved: " + Statistics.getNames() + "\n");
		return names;
	}
}
