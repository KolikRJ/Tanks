package mail.client.core;

import java.awt.Dimension;

import javax.swing.JPanel;

public class MailClientCore extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private MailSendClient send;

	public MailClientCore() {
		setPreferredSize(new Dimension(200, 200));
		setFocusable(true);
		requestFocus();

		send = new MailSendClient();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				if (send.isConnected)
					send.update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
