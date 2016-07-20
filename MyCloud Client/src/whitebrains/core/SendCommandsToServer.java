package whitebrains.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.NumberFormat;

import whitebrains.scene.FileFrameController;

import com.google.common.io.Files;

public class SendCommandsToServer {

	private static Socket socket;
	private static DataInputStream dis;
	private static DataOutputStream dos;

	private static Socket fileSocket;
	private static DataInputStream fileDis;
	private static DataOutputStream fileDos;

	private static String login;
	private static String pass;

	private static FileFrameController fileFrame;

	static {
		fileFrame = new FileFrameController();
	}

	public static boolean connectToServer() {
		if (socket == null)
			try {
				socket = new Socket("localhost", 55690);
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());

				fileSocket = new Socket("localhost", 55691);
				fileDis = new DataInputStream(fileSocket.getInputStream());
				fileDos = new DataOutputStream(fileSocket.getOutputStream());
				return true;
			} catch (IOException e) {
				return false;
			}
		else
			return true;
	}

	public static DataInputStream getDis() {
		return dis;
	}

	public static DataOutputStream getDos() {
		return dos;
	}

	public static DataInputStream getFileDis() {
		return fileDis;
	}

	public static DataOutputStream getFileDos() {
		return fileDos;
	}

	public static String getLogin() {
		return login;
	}

	public static String getPass() {
		return pass;
	}

	public static void setLogin(String login) {
		SendCommandsToServer.login = login;
	}

	public static void setPass(String pass) {
		SendCommandsToServer.pass = pass;
	}

}
