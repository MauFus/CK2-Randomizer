package utility;

import java.io.*;

public class Log {

	public static void write(String log) throws IOException {
		File logFile = new File(
				"C:\\Users\\Mauro\\Documents\\Paradox Interactive\\Crusader Kings II\\mod\\CK2-Randomizer\\log.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
		writer.append(log + "\n");
		writer.close();
		return;
	}
}
