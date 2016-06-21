package whitebrains.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.google.common.io.Files;

public class ClientStart {

	public static void main(String args[]) throws IOException {

		System.out.println("Введите путь до файла:");
		String pathToFile = new Scanner(System.in).nextLine();

		File file = new File(pathToFile);
		System.out.println("Файл найден");

		Socket server = new Socket("192.168.152.77", 1111);
		System.out.println("Подключен к серверу");

		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		out.println(pathToFile.substring(8));

		Files.copy(file, server.getOutputStream());

		server.close();
		System.out.println("Файл " + pathToFile.substring(8) + " отправлен (" + file.length() + " байт)");

	}
}
