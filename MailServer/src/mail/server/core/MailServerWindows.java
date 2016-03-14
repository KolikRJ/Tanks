package mail.server.core;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MailServerWindows extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton startServer;
	private ServerSocket server;
	
	public static ArrayList<MailServer> CLIENTS = new ArrayList<MailServer>();

	public MailServerWindows() {
		setPreferredSize(new Dimension(200, 200));
		setFocusable(true);
		requestFocus();

		startServer = new JButton("Start Server");
		startServer.addActionListener(this);
		add(startServer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start Server")) {
			startServer.setText("Close Server");
			try {
				server = new ServerSocket(9696, 0, InetAddress.getByName("localhost"));
				System.out.println("Сервер запущен");
				while (true) {
					CLIENTS.add(new MailServer(server.accept()));
					System.out.println("Клиент подключен");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
