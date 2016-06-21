package whitebrains.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {

	public static void main(String[] args) throws IOException {

		int size = 999999999;
		int bytes;
		int current = 0;
		
		String pathToFile = "d:/inputFile.exe";

		System.out.println("Сервер запущен");
		ServerSocket server = new ServerSocket(1111);
		System.out.println("Ожидание подключения");
		Socket client = server.accept();
		System.out.println("Подключен, начата передача файла");

		File file = new File(pathToFile);
		byte[] buffer = new byte[size];
		InputStream in = client.getInputStream();
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bytes = in.read(buffer, 0, buffer.length);
		current = bytes;

		while (bytes > -1) {
			bytes = in.read(buffer, current, (buffer.length - current));
			if (bytes >= 0)
				current += bytes;
		}

		bos.write(buffer, 0, current);
		System.out.println("Файл " + pathToFile + " загружен (" + current + " байт)");

		in.close();
		bos.close();
		fos.close();
		client.close();
		server.close();

	}

}
