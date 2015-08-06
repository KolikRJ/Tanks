package usb.connected.device;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StreamString {

	private static FileWriter writeFile = null;
	private static File file = new File("temp/temp.txt");
	private static Scanner scan;

	public static ArrayList<String> getArrString() {

		ArrayList<String> arr = new ArrayList<String>();

		try {
			scan = new Scanner(new File("temp/temp.txt"));

			for (int i = 0; i < 30; i++) {
				if (!scan.hasNextLine())
					break;
				arr.add(scan.nextLine());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				scan.close();
			} catch (Exception e2) {
			}
		}
		return arr;
	}

	public static void stringAdd(String temp) {
		try {
			writeFile = new FileWriter(file, true);
			writeFile.append(temp + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writeFile != null)
				try {
					writeFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
