package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import types.Culture;

public class CharacterUtility {

	public static void randomizeCharacter(int id, File dst, Culture culture, String religion,
			Long dinastia, ArrayList<String> names) throws IOException {
		Random rand = new Random();
		BufferedWriter writer = new BufferedWriter(new FileWriter(dst, true));
		writer.append(id + "= {\n");
		writer.append("\tname=" + names.get(rand.nextInt(names.size())) + "\n");
		writer.append("\tdynasty=" + dinastia + "\n");
		writer.append("\treligion = \"" + religion + "\"\n");
		writer.append("\tculture = \"" + culture + "\"\n");
		int age = 724 + rand.nextInt(30);
		writer.append("\t" + age + "." + (1 + rand.nextInt(12)) + "." + (1 + rand.nextInt(28)) + " = {\n");
		writer.append("\t\tbirth=yes\n");
		writer.append("\t}\n}\n\n");
		writer.close();
	}

	public static Long selectDynasty(ArrayList<ArrayList<Long>> dynasties, Culture cult) {
		Random rand = new Random();
		Long dinastia = null;
		ArrayList<Long> cultureDynasties = null;
		if (!(cultureDynasties = dynasties.get(cult.ordinal())).isEmpty()) {
			int n = rand.nextInt(cultureDynasties.size());
			dinastia = cultureDynasties.get(n);
			cultureDynasties.remove(n);
		} else
			dinastia = selectDynasty(dynasties, Culture.values()[(cult.ordinal() + 1) % 92]);
		return dinastia;
	}
}
