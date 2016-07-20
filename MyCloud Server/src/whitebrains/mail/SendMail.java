package whitebrains.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private static Message message;
	private static Session session;

	static {
		final String username = "KolikRJ@gmail.com";
		final String password = "WinterFest14";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

	}

	public static boolean sendMailKey(String key, String email) {
		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress("KolikRJ@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			message.setSubject("Регистрация в программе MyCloud");
			message.setText("Здравствуйте. \nВаш ключ: " + key
					+ "\nесли вы получили это письмо случайно, просто проигнорируйте его. \n Всего доброго.");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

}
