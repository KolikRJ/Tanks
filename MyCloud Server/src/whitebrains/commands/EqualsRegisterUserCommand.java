package whitebrains.commands;

import java.io.DataOutputStream;
import java.io.IOException;

import whitebrains.file.CloudServerFiles;
import whitebrains.mail.SendMail;

public class EqualsRegisterUserCommand {

	private String key;
	private String[] options;

	public void registerUserCommand(String[] options, DataOutputStream out) throws IOException {
		this.options = options;
		if (CloudServerFiles.getEqualsLogin(options) == null) {
			if (generateAndSendRegisterKey())
				out.writeBoolean(true);
			else
				out.writeBoolean(false);
		} else {
			out.writeBoolean(false);
		}
	}

	private boolean generateAndSendRegisterKey() throws IOException {
		char[] keyChars = new char[6];
		for (int i = 0; i < keyChars.length; i++) {
			int chars = (int) (65 + Math.random() * 26);
			keyChars[i] = (char) chars;
		}
		key = new String(keyChars);
		whitebrains.file.CloudServerFiles.addLineToUserFile(options[0], "Registration user key:" + key);
		return SendMail.sendMailKey(key, options[0]);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		if (key.length() == 6)
			this.key = key;
		else
			System.out.println("Длина ключа должна быть 6 символов");
	}
}
