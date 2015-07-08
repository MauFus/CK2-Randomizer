package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import types.*;
import utility.*;

public class Main {

	public static void main(String[] args) throws IOException {

		// TODO - Put in a configuration file
		File src_folder = new File("C:\\Program Files (x86)\\Crusader Kings II - Collection");
		File dst_folder = new File("C:\\Users\\Mauro\\Documents\\Paradox Interactive\\Crusader Kings II\\mod");

		// Parameters
		File src_provinces = new File(src_folder.getAbsolutePath() + "\\history\\provinces");
		File src_diplomacy = new File(src_folder.getAbsolutePath() + "\\history\\diplomacy");
		File src_characters = new File(src_folder.getAbsolutePath() + "\\history\\characters");
		File src_tech = new File(src_folder.getAbsolutePath() + "\\history\\technology");
		File src_wars = new File(src_folder.getAbsolutePath() + "\\history\\wars");
		File src_titles = new File(src_folder.getAbsolutePath() + "\\history\\titles");
		File src_dynasties = new File(src_folder.getAbsolutePath() + "\\common\\dynasties\\00_dynasties.txt");
		File src_names = new File(src_folder.getAbsolutePath() + "\\common\\cultures\\00_cultures.txt");
		File src_landed = new File(src_folder.getAbsolutePath() + "\\common\\landed_titles\\landed_titles.txt");
		File src_flags = new File(src_folder.getAbsolutePath() + "\\gfx\\flags");

		File dst_mod = new File(dst_folder.getAbsolutePath() + "\\CK2-Randomizer");
		File file_mod = new File(dst_folder.getAbsolutePath() + "\\CK2-Randomizer.mod");
		File dst_characters = new File(dst_mod.getAbsolutePath() + "\\history\\characters\\");
		File dst_titles = new File(dst_mod.getAbsolutePath() + "\\history\\titles\\");
		File dst_republics = new File(dst_mod.getAbsolutePath() + "\\common\\landed_titles\\republic_duchies.txt");
		File dst_flags = new File(dst_mod.getAbsolutePath() + "\\gfx\\flags");
		File dst_republic_names = new File(dst_mod.getAbsolutePath() + "\\localisation\\republic_names.csv");

		// Execution Variables
		boolean lessRandomMode = true;
		Random random = new Random();
		ArrayList<ArrayList<Long>> dynasties = new ArrayList<ArrayList<Long>>();
		ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>();

		// Randomizer Main Execution
		// Creation of Mod file
		Log.openLog();
		MiscUtility.initModFile(file_mod);

		// Blank Diplomacy
		Log.info("Start blanking historical Diplomacy");
		MiscUtility.blankFiles(src_diplomacy, dst_mod);
		Log.info("Diplomacy blanked\n");

		// Blank Wars
		Log.info("Start blanking historical Wars");
		MiscUtility.blankFiles(src_wars, dst_mod);
		Log.info("Wars blanked\n");

		// Blank Tech
		Log.info("Start blanking historical Technology");
		MiscUtility.blankFiles(src_tech, dst_mod);
		Log.info("Technology blanked\n");

		// Blank Characters
		Log.info("Start blanking historical Characters");
		for (File file : dst_characters.listFiles())
			file.delete();
		MiscUtility.blankFiles(src_characters, dst_mod);
		Log.info("Characters blanked\n");

		// Blank Titles
		Log.info("Start blanking historical title assignation");
		TitleUtility.blankTitles(src_titles, dst_titles);
		Log.info("All the title de-assigned\n");

		// Retrieve possible Dynasties
		Log.info("Retrieving dynasties...");
		dynasties = MiscUtility.initDynasties(src_dynasties);

		// Retrieve possible Character names
		Log.info("Retrieving character names...");
		names = MiscUtility.initNames(src_names);

		// Main Loop - An execution for each Province
		Log.info("Start of Provincies Randomization");
		for (int k = 0; k < src_provinces.listFiles().length; k++) {
			// Select the province file
			File file_province = src_provinces.listFiles()[k];
			Log.info("Processing: " + file_province.getName());
			Statistics.countProvince();
			// Select a random type of holding
			Holding holding = MiscUtility.randomizeHolding(random.nextInt(100));
			// Select a random culture (Not LessRandomMode)
			Culture culture = Culture.values()[random.nextInt(92)];
			// Randomize the province
			String provinceResult = ProvinceUtility.randomizeProvince(lessRandomMode, file_province, dst_mod, holding,
					culture, random);
			Long dinastia = CharacterUtility.selectDynasty(dynasties, Culture.valueOf(provinceResult.split(";")[3]));
			// Create the character for the province
			CharacterUtility.randomizeCharacter(1000 + k, new File(dst_mod + "\\history\\characters\\custom.txt"),
					Culture.valueOf(provinceResult.split(";")[3]), provinceResult.split(";")[1], dinastia,
					names.get(Culture.valueOf(provinceResult.split(";")[3]).ordinal()));
			TitleUtility.assignTitle(provinceResult.split(";")[0] + ".txt", new File(dst_mod + "\\history\\titles"),
					1000 + k);
			// Repubbliche marinare
			if (holding.equals(Holding.city)
					&& ProvinceUtility.isCoastalProvince(Integer.parseInt(provinceResult.split(";")[2]))) {
				String titleName = provinceResult.split(";")[0].substring(2);
				TitleUtility.assignTitle("d_" + titleName + "_rep.txt", new File(dst_mod + "\\history\\titles"),
						1000 + k);

				TitleUtility.assignDuchyToRepublic(src_landed, dst_republics, titleName, dst_republic_names);
				// Copia la Flag
				File flag = new File(dst_flags + "\\d_" + titleName + "_rep.tga");
				Files.copy(Paths.get(src_flags + "\\c_" + titleName + ".tga"), Paths.get(flag.getAbsolutePath()),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}
		// Write Statistics in log File
		Log.info("Holding Distribution");
		for (int i = 0; i < 4; i++)
			Log.info("Province with holding " + Holding.values()[i].toString() + ": " + Statistics.getHoldingNumber(i));

		Log.write("");
		Log.info("Culture Distribution");
		for (int i = 0; i < 92; i++)
			Log.info("Province with culture " + Culture.values()[i].toString() + ": " + Statistics.getCultureNumber(i));

		Log.write("");
		Log.info("Religion Distribution");
		for (int i = 0; i < 41; i++)
			Log.info("Province with religion " + GlobalReligion.values()[i].toString() + ": "
					+ Statistics.getReligionNumber(i));
		Log.closeLog();
	}
}
