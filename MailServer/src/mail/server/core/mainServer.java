package mail.server.core;

import javax.swing.JFrame;

import mail.server.windows.MailServerWindow;

public class mainServer {

	public static void main(String[] args) {
		JFrame f = new JFrame("MailServer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new MailServerWindow());
		f.setVisible(true);
		f.setResizable(false);
		f.pack();
	}

}
