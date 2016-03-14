package mail.client.core;

import javax.swing.JFrame;

public class mainClient {

	public static void main(String[] args) {

		JFrame f = new JFrame("MailClient");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new MailClientCore());
		f.setVisible(true);
		f.setResizable(false);
		f.pack();

	}

}
