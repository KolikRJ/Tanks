package whitebrains.scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import whitebrains.commands.RegisterKeyCommand;
import whitebrains.core.ClientStart;
import whitebrains.core.SendCommandsToServer;

public class KeyFrameController {
	@FXML
	private Button cancelBtn;

	@FXML
	private Button regBtn;

	@FXML
	private TextField keyField;

	@FXML
	private Text messageText;

	private RegisterKeyCommand keyCom;

	public void cancelBtnAction(ActionEvent event) {
		ClientStart.setLoginScene();
	}

	public void regBtnAction(ActionEvent event) {
		if (keyField.getText().length() == 6) {
			if (SendCommandsToServer.connectToServer())
				if (SendRegisterKey(keyField.getText()))
					messageText.setText("");
				else
					messageText.setText("Ключ не совпадает.");

		} else
			messageText.setText("Ключ должен состоять из 6-ти символов");
	}

	private boolean SendRegisterKey(String key) {
		if (keyCom == null)
			keyCom = new RegisterKeyCommand();
		boolean request = false;
		try {
			request = keyCom.registerKeyCommand(key, SendCommandsToServer.getLogin(), SendCommandsToServer.getPass(), SendCommandsToServer.getDis(),
					SendCommandsToServer.getDos());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}
}
