package mail.server.core;

import javax.swing.JFrame;

public class mainServer {

	public static void main(String[] args) {
		JFrame f = new JFrame("MailServer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new MailServerWindows());
		f.setVisible(true);
		f.setResizable(false);
		f.pack();
	}

}
