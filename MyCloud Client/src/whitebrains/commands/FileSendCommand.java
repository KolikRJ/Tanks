package whitebrains.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

public class FileSendCommand implements Runnable {

	private File sendFile;

	private DataInputStream in;
	private DataOutputStream out;
	private DataOutputStream fileOut;

	private Thread thread;

	private boolean isSendFile = false;

	public FileSendCommand(DataInputStream in, DataOutputStream out, DataOutputStream fileOut) {
		this.in = in;
		this.out = out;
		this.fileOut = fileOut;
	}

	public boolean sendFileCommand(String pathFile) {

		if (thread == null) {
			thread = new Thread(this);
			thread.setName("Thread send file");
			thread.setDaemon(true);
		}

		try {
			sendFile = new File(pathFile);
			out.writeUTF("accept-file:" + sendFile.length() + ":" + pathFile.substring(3));
			boolean request = in.readBoolean();
			if (request)
				thread.start();
			return request;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public void run() {
		try {
			Files.copy(sendFile, fileOut);
			isSendFile = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isSendFile() {
		return isSendFile;
	}

}
