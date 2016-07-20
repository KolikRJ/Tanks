package whitebrains.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import whitebrains.core.MyDate;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class CloudServerFiles {

	private static File userFile = new File("Server/UserLogin.mycloud");
	private static File logFile = new File("Server/Log.mycloud");

	public static List<String> getUserFileLine() throws IOException {
		return Files.readLines(userFile, Charsets.UTF_8);
	}

	public static List<String> getLogFileLine() throws IOException {
		return Files.readLines(logFile, Charsets.UTF_8);
	}

	public static List<String> getCurrentUserFileLine(String login) {
		try {
			return Files.readLines(new File("Server/" + login + ".mycloud"), Charsets.UTF_8);
		} catch (IOException e) {
			return null;
		}
	}

	public static String[] getEqualsLogin(String[] options) throws IOException {

		String[] nameAndPassUser = null;

		for (String currentUser : getUserFileLine()) {
			String[] nameAndPassCurrentUser = currentUser.split(":");
			if (nameAndPassCurrentUser[0].equals(options[0]))
				nameAndPassUser = nameAndPassCurrentUser;
		}
		return nameAndPassUser;
	}

	public static String[] getEqualsLoginAndPassword(String[] options) throws IOException {

		String[] nameAndPassUser = null;

		for (String currentUser : getUserFileLine()) {
			String[] nameAndPassCurrentUser = currentUser.split(":");
			if (nameAndPassCurrentUser[0].equals(options[0]) && nameAndPassCurrentUser[1].equals(options[1]))
				nameAndPassUser = nameAndPassCurrentUser;
		}
		return nameAndPassUser;
	}

	public static void addLineToUserLoginFile(String[] options) throws IOException {
		for (String string : options) {
			Files.append(string + ":", userFile, Charsets.UTF_8);
		}
		Files.append("\n", userFile, Charsets.UTF_8);
	}

	public static void addLineToLogFile(String options) throws IOException {
		Files.append(MyDate.getData() + ": " + options + "\n", logFile, Charsets.UTF_8);
	}

	public static void addLineToUserFile(String login, String options) throws IOException {
		File userFile = new File("Server/" + login + ".mycloud");
		Files.append(options + "\n", userFile, Charsets.UTF_8);
	}

}
