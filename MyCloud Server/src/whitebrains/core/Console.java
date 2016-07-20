package whitebrains.core;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import whitebrains.commands.*;

public class Console {
	private static VBox consoleBox;
	private static TextArea console;
	private static TextField send;

	static {
		consoleBox = new VBox();
		console = new TextArea();
		console.setEditable(false);
		send = new TextField();
		send.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER) && !send.getText().isEmpty()) {
					String command = send.getText();
					String args[] = command.split(" ");
					switch (args[0]) {
					case ConsoleCommands.EXIT:
						System.exit(0);
						break;
					case ConsoleCommands.SIZE:
						addText(Connecting.getSize() + " Clients");
						break;
					case ConsoleCommands.HELP:
						for (int i = 0; i < ConsoleCommands.allCommands().length; i++)
							addText(ConsoleCommands.allCommands()[i]);
						break;
					case ConsoleCommands.REMOVE:
						ConsoleCommands.removeCommand(args);
						break;
					default:
						addText(" оманда \"" + command + "\" не €вл€етс€ внутренней командой.");
						break;
					}
					send.clear();
				}

			}
		});
		consoleBox.getChildren().addAll(console, send);
	}

	public static void addText(String text) {
		console.appendText("Server: " + text + "\n");
	}

	public static Scene getScene() {
		return new Scene(consoleBox, 400, 190);
	}

}
