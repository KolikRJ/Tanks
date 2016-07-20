package whitebrains.commands;

import java.io.DataOutputStream;
import java.io.IOException;

import whitebrains.file.CloudServerFiles;

public class LoginCommand {

	public String loginCommand(String[] options, DataOutputStream out) throws IOException {
		if (CloudServerFiles.getEqualsLoginAndPassword(options) != null) {
			out.writeBoolean(true);
			whitebrains.file.CloudServerFiles.addLineToLogFile(options[0] + " clients to connect to the server.");
			return options[0];
		} else {
			out.writeBoolean(false);
			return null;
		}
	}

}
