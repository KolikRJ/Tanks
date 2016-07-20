package whitebrains.scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import whitebrains.commands.LoginCommand;
import whitebrains.core.ClientStart;
import whitebrains.core.SendCommandsToServer;

public class LoginFrameController {

	@FXML
	private Button logBtn;

	@FXML
	private Button regBtn;

	@FXML
	private PasswordField passField;

	@FXML
	private TextField emailField;

	@FXML
	private Text messageText;

	@FXML
	private CheckBox rememberCheck;
	
	private LoginCommand loginCom;

	public void logBtnAction(ActionEvent event) {
		if (SendCommandsToServer.connectToServer())
			if (!emailField.getText().isEmpty() && !passField.getText().isEmpty())
				if (sendLogin(emailField.getText().toLowerCase(), passField.getText()))
					messageText.setText("");
				else
					messageText.setText("Такого пользователя не существует.");
			else
				messageText.setText("Заполните оба поля.");
		else
			messageText.setText("Нет связи с сервером.");
	}

	public void regBtnAction(ActionEvent event) {
		ClientStart.setRegisterScene();
	}

	private boolean sendLogin(String login, String pass) {
		if(loginCom == null)
			loginCom = new LoginCommand();
		boolean request = false;
		try {
			request = loginCom.loginCommand(login, pass, SendCommandsToServer.getDis(), SendCommandsToServer.getDos());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}
	
}
