package whitebrains.commands;

import java.io.DataOutputStream;
import java.io.IOException;

public class CloseCommand {

	public void closeCommand(DataOutputStream out) throws IOException{
		out.writeUTF(Commands.CLOSE);
	}
}
