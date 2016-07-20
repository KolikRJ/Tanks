package whitebrains.commands;

import whitebrains.core.Connecting;
import whitebrains.core.Console;

public class ConsoleCommands {

	public static final String EXIT = "exit";
	public static final String SIZE = "size";
	public static final String REMOVE = "remove";
	public static final String HELP = "help";

	public static String[] allCommands() {
		String[] str = new String[4];
		str[0] = EXIT;
		str[1] = SIZE;
		str[2] = REMOVE;
		str[3] = HELP;
		return str;
	}

	public static void removeCommand(String[] args) {
		if (args.length >= 2) {
			int index = Integer.parseInt(args[1]);
			if (index <= Connecting.getSize() && index >= 0) {
				Connecting.removeClientIndex(index - 1);
				Console.addText(" лиент с индесом: " + index + " отключен.");
			} else
				Console.addText(" лиента с индесом: " + index + " не существует");
		} else
			Console.addText("¬ведите индекс клиента после комманды, через пробел.");
	}

}
