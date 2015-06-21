package utility;

import java.io.*;
import java.util.ArrayList;

import types.*;

public class MiscUtility {

	public static void blankFiles(File src, File dst) throws IOException {
		if (src != null) {
			for (File file : src.listFiles()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(dst + "\\" + src.getName() + "\\"
						+ file.getName()));
				writer.append("");
				writer.close();
			}
		}
	}

	public static void doubleFile(File file) throws IOException {
		if (file != null) {
			String line = "";
			String toAppend = "";
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			while ((line = reader.readLine()) != null) {
				toAppend = toAppend.concat(line + "\n");
			}
			writer.append(toAppend);
			reader.close();
			writer.close();

		}
	}

	public static void initModFile(File fl_mod) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fl_mod));
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
	}

	public static Holding randomizeHolding(int rand) {
		if (rand < 70)
			return Holding.castle;
		else if (rand >= 70 && rand < 95)
			return Holding.tribal;
		else
			return Holding.city;
	}

	public static ArrayList<ArrayList<Long>> initDynasties(File src_dynasties) throws IOException {
		ArrayList<ArrayList<Long>> dynasties = new ArrayList<ArrayList<Long>>();
		for (int i = 0; i < 92; i++)
			dynasties.add(new ArrayList<Long>());
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(src_dynasties));
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
				}
			}
		}
		reader.close();
		return dynasties;

	}

	public static ArrayList<ArrayList<String>> initNames(File src_names) throws IOException {
		ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 92; i++)
			names.add(new ArrayList<String>());
		for (int j = 0; j < 92; j++) {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(src_names));
			String culture = Culture.values()[j].toString();
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith(culture + " = {") || line.contains("lappish")) {
					while (!(line = reader.readLine()).trim().startsWith("male_names = {")) {
						// do nothing
					}
					while (!(line = reader.readLine()).contains("}")) {
						String splitted[] = line.split(" ");
						for (String string : splitted) {
							names.get(j).add(string.split("_")[0].trim());
						}
					}
				}
			}
			reader.close();
		}
		return names;
	}
}