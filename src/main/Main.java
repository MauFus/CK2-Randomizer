package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import types.*;
import utility.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// Execution Variables
		boolean lessRandomMode = true;

		// Parameters
		File src_folder = new File("C:\\Program Files (x86)\\Crusader Kings II - Collection");
		File src_provinces = new File(src_folder.getAbsolutePath() + "\\history\\provinces");
		File src_diplomacy = new File(src_folder.getAbsolutePath() + "\\history\\diplomacy");
		// File src_wars = new File(src_folder.getAbsolutePath() +
		// "\\history\\wars");
		File src_titles = new File(src_folder.getAbsolutePath() + "\\history\\titles");
		File src_dynasties = new File(src_folder.getAbsolutePath() + "\\common\\dynasties\\00_dynasties.txt");
		File src_names = new File(src_folder.getAbsolutePath() + "\\common\\cultures\\00_cultures.txt");
		File src_landed = new File(src_folder.getAbsolutePath() + "\\common\\landed_titles\\landed_titles.txt");
		File src_flags = new File(src_folder.getAbsolutePath() + "\\gfx\\flags");

		File dst_mod = new File(
				"C:\\Users\\Mauro\\Documents\\Paradox Interactive\\Crusader Kings II\\mod\\CK2-Randomizer");
		File file_mod = new File(
				"C:\\Users\\Mauro\\Documents\\Paradox Interactive\\Crusader Kings II\\mod\\CK2-Randomizer.mod");
		File dst_republics = new File(dst_mod.getAbsolutePath() + "\\common\\landed_titles\\republic_duchies.txt");
		File dst_flags = new File(dst_mod.getAbsolutePath() + "\\gfx\\flags");
		File dst_localisation = new File(dst_mod.getAbsolutePath() + "\\localisation\\republic_names.csv");

		Random random = new Random();
		ArrayList<ArrayList<Long>> dynasties = new ArrayList<ArrayList<Long>>();
		ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>();

		// Initialize
		MiscUtility.initModFile(file_mod);
		MiscUtility.blankFiles(src_diplomacy, dst_mod);
		// MiscUtility.blankFiles(src_wars, dst_mod);
		TitleUtility.blankTitles(src_titles, dst_mod);
		// TitleUtility.blankTitles(new File(dst_mod + "\\history\\titles\\"), dst_mod);
		dynasties = MiscUtility.initDynasties(src_dynasties);
		names = MiscUtility.initNames(src_names);

		// Main Loop
		for (int k = 0; k < src_provinces.listFiles().length; k++) {
			File file_province = src_provinces.listFiles()[k];
			Log.log("Start on: " + file_province.getName());
			Statistics.countProvince();
			Holding holding = MiscUtility.randomizeHolding(random.nextInt(100));
			Culture culture = Culture.values()[random.nextInt(92)];
			String provinceResult = ProvinceUtility.randomizeProvince(lessRandomMode, file_province, dst_mod, holding, culture, random);
			Long dinastia = CharacterUtility.selectDynasty(dynasties, culture);
			// for (int h = 0; h < 50; h++)
			CharacterUtility.randomizeCharacter(k, new File(dst_mod + "\\history\\characters\\custom.txt"), culture,
					provinceResult.split(";")[1], dinastia, names.get(culture.ordinal()));
			TitleUtility.assignTitle(provinceResult.split(";")[0] + ".txt", new File(dst_mod + "\\history\\titles"),
					3000000 + k);
			// Repubbliche marinare
			if (holding.equals(Holding.city)
					&& ProvinceUtility.isCoastalProvince(Integer.parseInt(provinceResult.split(";")[2]))) {
				String titleName = provinceResult.split(";")[0].substring(2);
				TitleUtility.assignTitle("d_" + titleName + "_rep.txt", new File(dst_mod + "\\history\\titles"),
						3000000 + k);

				TitleUtility.assignDuchyToRepublic(src_landed, dst_republics, titleName, dst_localisation);
				// Copia la Flag
				File flag = new File(dst_flags + "\\d_" + titleName + "_rep.tga");
				Files.copy(Paths.get(src_flags + "\\c_" + titleName + ".tga"), Paths.get(flag.getAbsolutePath()),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}
		System.out.println("Fatto!");
	}
}
