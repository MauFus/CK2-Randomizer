package utility;

import java.io.*;

public class TitleUtility {

	public static void titlesStatistics(File folder) throws IOException {
		int totale = 0;
		int activeOption = 0;
		int b_tier = 0;
		int c_tier = 0;
		int d_tier = 0;
		int k_tier = 0;
		int e_tier = 0;

		if (folder != null) {
			for (File file : folder.listFiles()) {
				totale++;

				if (file.getName().startsWith("b_"))
					b_tier++;
				else if (file.getName().startsWith("c_"))
					c_tier++;
				else if (file.getName().startsWith("d_"))
					d_tier++;
				else if (file.getName().startsWith("k_"))
					k_tier++;
				else if (file.getName().startsWith("e_"))
					e_tier++;

				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.contains("active")) {
						System.out.println(file.getName());
						activeOption++;
						break;
					}
				}
				br.close();
			}
		}
		System.out.println("Titoli: " + totale);
		System.out.println("Titoli con attivazione: " + activeOption);
		System.out.println("Baronie: " + b_tier);
		System.out.println("Contee: " + c_tier);
		System.out.println("Ducati: " + d_tier);
		System.out.println("Regni: " + k_tier);
		System.out.println("Imperi: " + e_tier);
	}

	public static void assignTitle(String src, File dst, int id) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(dst + "\\" + src));
		writer.append("768.1.1 = {\n");
		writer.append("\tholder = " + id + "\n");
		writer.append("}\n");
		writer.close();
	}

	public static void blankTitles(File src, File dst) throws IOException {
		if (src != null) {
			for (File file : src.listFiles()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				BufferedWriter writer = new BufferedWriter(new FileWriter(dst + "\\history\\titles\\" + file.getName()));
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.contains("liege"))
						writer.append("\n");
					else if (line.contains("holder"))
						writer.append("holder = 0\n");
					else
						writer.append(line + "\n");

				}
				reader.close();
				writer.close();
			}
		}
	}

	public static void assignDuchyToRepublic(File src_landed, File dst_republics, String titleName,
			File dst_localisation) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(src_landed));
		BufferedWriter writer = new BufferedWriter(new FileWriter(dst_republics, true));
		BufferedWriter writer_loc = new BufferedWriter(new FileWriter(dst_localisation, true));
		String line = "";

		writer.append("\n\n" + "d_" + titleName + "_rep = {\n");

		while ((line = reader.readLine()) != null && !line.contains("c_" + titleName)) {
			// Do anything. Go on
		}
		if (line.contains("c_" + titleName)) {
			while ((line = reader.readLine()) != null && !line.contains("c_") && !line.contains("d_")
					&& !line.contains("k_") && !line.contains("e_")) {
				if (line.contains("color"))
					writer.append(line + "\n");
			}
		}
		writer.append("\tdynasty_title_names = no\n");
		writer.append("\tallow = {\n");
		writer.append("\t\talways = no\n");
		writer.append("\t\tis_republic = yes\n\t}\n}\n");

		String[] titleSplitted = titleName.split("_");
		String title = "";
		for (String string : titleSplitted) {
			String str = string.substring(0, 1).toUpperCase() + string.substring(1);
			title = title.concat(str) + " ";
		}
		title = title.substring(0, title.length() - 1);
		writer_loc.append("d_" + titleName + "_rep;" + title + ";" + title + ";" + title + ";;" + title
				+ ";;;;;;;;;x\n");

		reader.close();
		writer.close();
		writer_loc.close();
	}
}
