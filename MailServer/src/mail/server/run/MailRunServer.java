package mail.server.run;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MailRunServer extends Thread {

	private ServerSocket server;
	public ArrayList<MailConnectedClient> clients = new ArrayList<MailConnectedClient>();
	private JPanel panel;
	public static int index = 0;

	public MailRunServer(int port, JPanel panel) {
		this.panel = panel;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	private void connectedClients() {
		try {
			clients.add(new MailConnectedClient(server.accept(), panel, index));
			index++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true)
			connectedClients();
	}

}
