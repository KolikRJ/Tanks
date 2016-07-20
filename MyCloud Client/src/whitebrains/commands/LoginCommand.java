package whitebrains.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import whitebrains.core.ClientStart;

public class LoginCommand {

	public boolean loginCommand(String login, String password, DataInputStream in, DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN + login + ":" + password);
		boolean request = in.readBoolean();
		if (request)
			ClientStart.setKeyScene();

		return request;
	}
}
