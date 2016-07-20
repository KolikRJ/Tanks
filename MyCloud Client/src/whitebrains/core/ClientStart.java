package whitebrains.core;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientStart extends Application {

	private static Scene scene;
	private static Stage stage;

	private static Parent login;
	private static Parent register;
	private static Parent key;
	private static Parent files;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		ClientStart.stage = stage;
		stage.setTitle("My Cloud Client");

		login = FXMLLoader.load(getClass().getResource("/whitebrains/scene/LoginFrame.fxml"));
		register = FXMLLoader.load(getClass().getResource("/whitebrains/scene/RegisterFrame.fxml"));
		key = FXMLLoader.load(getClass().getResource("/whitebrains/scene/KeyFrame.fxml"));
		files = FXMLLoader.load(getClass().getResource("/whitebrains/scene/FilesFrame.fxml"));
		scene = new Scene(login);
		stage.setScene(scene);
		// stage.setScene(LoginScene.getScene());
		// stage.setOnCloseRequest(e -> {
		// SendCommandsToServer.closeCommand();
		// System.exit(0);
		// });
		stage.setResizable(false);
		stage.show();

	}

	public static void setLoginScene() {
		scene.setRoot(login);
		stage.setHeight(377);
	}

	public static void setRegisterScene() {
		scene.setRoot(register);
		stage.setHeight(377);
	}

	public static void setKeyScene() {
		scene.setRoot(key);
		stage.setHeight(205);
	}

}
