package whitebrains.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import whitebrains.core.ClientStart;

public class EqualsLoginCommand {

	public boolean equalsLoginCommand(String login, DataInputStream in, DataOutputStream out) throws IOException {

		out.writeUTF(Commands.EQUALS_REGISTER_USER + login);
		boolean request = in.readBoolean();
		if (request)
			ClientStart.setKeyScene();

		return request;
	}
}
