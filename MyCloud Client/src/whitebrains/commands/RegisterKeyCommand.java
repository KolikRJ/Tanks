package whitebrains.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import whitebrains.core.ClientStart;

public class RegisterKeyCommand {

	public boolean registerKeyCommand(String key, String login, String password, DataInputStream in, DataOutputStream out) throws IOException {
		out.writeUTF(Commands.EQUALS_REGISTER_KEY + login + ":" + password + ":" + key);
		boolean request = in.readBoolean();
		if (request)
			ClientStart.setLoginScene();

		return request;
	}

}
