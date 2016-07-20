package whitebrains.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.common.io.Files;

public class AcceptFileCommand {

	public void acceptFileCommand(String[] options, String login, DataOutputStream out, DataInputStream fileIn) throws IOException {
		File acceptFile = new File("D://MyCloudServer/" + login + "/" + options[1]);
		Files.createParentDirs(acceptFile);

		FileOutputStream fos = new FileOutputStream(acceptFile, true);
		DataOutputStream fileDos = new DataOutputStream(fos);

		long fileSize = Long.parseLong(options[0]);
		byte[] arr;
		int byteRead = 0;
		long byteSize = 0;
		out.writeBoolean(true);
		out.writeLong(byteRead);
		while (byteSize < fileSize) {
			arr = new byte[1024 * 1024];
			byteRead = fileIn.read(arr, 0, arr.length);
			fileDos.write(arr, 0, byteRead);
			byteSize += byteRead;
			System.out.println(fileSize + " " + byteSize);
			out.writeLong(byteRead);
		}
		fileDos.flush();
		fileDos.close();
		fos.close();

		out.writeLong(-1);
	}
}
