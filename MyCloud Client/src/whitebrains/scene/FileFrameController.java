package whitebrains.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import whitebrains.pane.FilePane;
import whitebrains.core.*;

public class FileFrameController {

	@FXML
	private VBox vBox;
	@FXML
	private Button btn;

	public void btnAction(ActionEvent action) throws Exception {
		if (SendCommandsToServer.connectToServer())
			SendCommandsToServer.sendFileCommand("D://Soft/SNI.exe");
	}

	public void addPane(String path) throws Exception {
		if (vBox.getChildren().size() >= 20) {
			vBox.getChildren().remove(19);
			vBox.getChildren().add(0, new FilePane(path));
		} else
			vBox.getChildren().add(0, new FilePane(path));
	}

	public void setProgress(double progress) {
		FilePane pane = (FilePane) vBox.getChildren().get(0);
		pane.setProgress(progress);
	}

}
