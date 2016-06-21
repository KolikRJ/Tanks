package mail.server.windows;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import mail.server.run.MailRunServer;

public class MailServerWindow extends JPanel {
	private static final long serialVersionUID = 1L;

	private MailRunServer server;
	{
		setPreferredSize(new Dimension(200, 300));
		setLayout(new GridLayout(8, 0));

		setServer(new MailRunServer(9696, this));

	}
	public MailRunServer getServer() {
		return server;
	}
	public void setServer(MailRunServer server) {
		this.server = server;
	}

}
