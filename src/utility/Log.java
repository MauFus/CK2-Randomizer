package utility;

import java.io.*;

public class Log {

	static File logFile;
	static BufferedWriter logWriter;

	/**
	 * Create and initialize the log File
	 */
	public static void openLog() {
		logFile = new File(
				"C:\\Users\\Mauro\\Documents\\Paradox Interactive\\Crusader Kings II\\mod\\CK2-Randomizer\\log.txt");
		try {
			logWriter = new BufferedWriter(new FileWriter(logFile));
		} catch (IOException e) {
			// This error cannot be logged because we have no log file! xD
			// Execution will fail
			e.printStackTrace();
		}
		Log.write("Randomizer Execution - Log File");
		Log.write("-------------------------------\n");
		Log.write("Randomizer Execution - START");
	}

	/**
	 * Close the log file
	 */
	public static void closeLog() {
		try {
			Log.write("Randomizer Execution - FINISH");
			logWriter.close();
		} catch (IOException e) {
			// This error cannot be logged because we have problem on log file!
			// Execution will fail
			e.printStackTrace();
		}
	}

	/**
	 * Write simple row with no information
	 * @param log - row to be written on log file
	 */
	public static void write(String log) {
		try {
			logWriter.append(log + "\n");
		} catch (IOException e) {
			// This error cannot be logged because we have problem on log file!
			// Execution will fail
			e.printStackTrace();
		}
	}
	
	/**
	 * Write an Info Log
	 * @param log - row to be written on log file
	 */
	public static void info(String log) {
		Log.write("INFO: " + log);
	}
	
	/**
	 * Write an Error Log
	 * @param log - row to be written on log file
	 */
	public static void error(String log) {
		Log.write("ERROR: " + log);
	}
}
