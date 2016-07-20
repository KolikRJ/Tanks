package whitebrains.core;

import javafx.application.Application;
import javafx.stage.Stage;

public class ServerStart extends Application {

	Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		 window = stage;
		 window.setTitle("MyCloud Server");
		
		 Connecting start = new Connecting();
		 start.start();
		
		 window.setScene(Console.getScene());
		 window.centerOnScreen();
		 window.setResizable(false);
		 window.show();
	}

}
