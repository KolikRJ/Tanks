package whitebrains.commands;

import java.io.DataOutputStream;
import java.io.IOException;

import whitebrains.file.CloudServerFiles;

public class EqualsRegisterKeyCommand {

	public void registerKeyCommand(String[] options, String originalKey, DataOutputStream out) throws IOException {
		String key = options[2];
		if (key.equals(originalKey)) {
			CloudServerFiles.addLineToUserLoginFile(options);
			out.writeBoolean(true);
			whitebrains.file.CloudServerFiles.addLineToLogFile(options[0] + " clients register from the programm.");
		} else
			out.writeBoolean(false);
	}

}
