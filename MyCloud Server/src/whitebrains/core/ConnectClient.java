package whitebrains.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import whitebrains.commands.AcceptFileCommand;
import whitebrains.commands.Commands;
import whitebrains.commands.EqualsRegisterKeyCommand;
import whitebrains.commands.EqualsRegisterUserCommand;
import whitebrains.commands.LoginCommand;

public class ConnectClient implements Runnable {

	private Thread thread;
	private DataInputStream dis;
	private DataOutputStream dos;
	private DataInputStream fileDis;
	private DataOutputStream fileDos;
	private String command;
	private String[] options;
	private String login;
	private boolean isAlive;

	private LoginCommand loginCommand;
	private EqualsRegisterUserCommand registerUserCommand;
	private EqualsRegisterKeyCommand registerKeyCommand;
	private AcceptFileCommand fileAcceptCommand;

	public ConnectClient(Socket socket, Socket fileSocket, int id) throws IOException {

		command = "null";
		isAlive = true;

		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		fileDis = new DataInputStream(fileSocket.getInputStream());
		fileDos = new DataOutputStream(fileSocket.getOutputStream());

		loginCommand = new LoginCommand();
		registerUserCommand = new EqualsRegisterUserCommand();
		registerKeyCommand = new EqualsRegisterKeyCommand();
		fileAcceptCommand = new AcceptFileCommand();

		if (thread == null) {
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.setName("Thread client: " + id);
			thread.start();
		}

	}

	private String readClientCommand() throws IOException {
		if (!command.equals("close")) {
			String data = dis.readUTF();
			if (data != null) {
				String[] clientData = data.split(":");
				if (clientData.length != 0) {
					options = new String[clientData.length - 1];
					for (int i = 1; i < clientData.length; i++)
						options[i - 1] = clientData[i];
				}
				return clientData[0];
			}
		} else
			closeCommand();
		return "null";
	}

	private void closeCommand() throws IOException {
		dis.close();
		dos.close();
		fileDis.close();
		fileDos.close();
		isAlive = false;
		if (!login.equals(null))
			whitebrains.file.CloudServerFiles.addLineToLogFile("Client: " + login + " disconnect from the server.");

		Connecting.removeClient(this);
	}

	@Override
	public void run() {
		while (isAlive) {
			try {
				switch (command = readClientCommand()) {
				case Commands.LOGIN:
					login = loginCommand.loginCommand(options, dos);
					break;
				case Commands.EQUALS_REGISTER_USER:
					registerUserCommand.registerUserCommand(options, dos);
					break;
				case Commands.EQUALS_REGISTER_KEY:
					registerKeyCommand.registerKeyCommand(options, registerUserCommand.getKey(), dos);
					break;
				case Commands.ACCEPT_FILE:
					fileAcceptCommand.acceptFileCommand(options, login, dos, fileDis);
					break;
				}
			} catch (IOException e) {
				try {
					closeCommand();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
}
