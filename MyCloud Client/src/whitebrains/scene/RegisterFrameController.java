package whitebrains.scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import whitebrains.commands.EqualsLoginCommand;
import whitebrains.core.ClientStart;
import whitebrains.core.SendCommandsToServer;

public class RegisterFrameController {
	@FXML
	private Button backBtn;

	@FXML
	private Button regBtn;

	@FXML
	private PasswordField passField;

	@FXML
	private PasswordField rePassField;

	@FXML
	private TextField emailField;

	@FXML
	private Text messageText;

	private EqualsLoginCommand loginCommand;

	public void backBtnAction(ActionEvent event) {
		ClientStart.setLoginScene();
	}

	public void regBtnAction(ActionEvent event) {
		if (!emailField.getText().isEmpty() && !passField.getText().isEmpty() && !rePassField.getText().isEmpty())
			if (passField.getText().equals(rePassField.getText())) {
				if (passField.getText().length() >= 5) {
					if (SendCommandsToServer.connectToServer()) {
						if (SendRegisterUser(emailField.getText().toLowerCase(), passField.getText()))
							messageText.setText("");
						else
							messageText.setText("Данный пользователь уже зарегистрирован.");
					} else
						messageText.setText("Сервер не доступен.");
				} else
					messageText.setText("Пароль должен быть не менее 5 символов.");
			} else
				messageText.setText("Пароли не совпадают.");
		else
			messageText.setText("Заполните все поля.");
	}

	private boolean SendRegisterUser(String login, String pass) {
		if (loginCommand == null)
			loginCommand = new EqualsLoginCommand();
		boolean request = false;
		try {
			request = loginCommand.equalsLoginCommand(login, SendCommandsToServer.getDis(), SendCommandsToServer.getDos());
			if (request) {
				SendCommandsToServer.setLogin(login);
				SendCommandsToServer.setPass(pass);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}
}
