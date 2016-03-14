package mail.server.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MailServer extends Thread {

	private Socket client;
	private DataInputStream in;
	private PrintWriter out;

	public MailServer(Socket socket) {
		client = socket;
		try {
			in = new DataInputStream(client.getInputStream());
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDaemon(true);
		setPriority(NORM_PRIORITY);
		start();
	}

	public boolean serverClosed() {
		try {
			out.println("close");
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void update() {
		try {
			System.out.println(in.readLine());
		} catch (Exception e) {
			if (serverClosed()){
				this.stop();
				System.out.println("Клиент отключился");
			}
		}
	}

	public void run() {
		while (true) {
			update();
		}
	}

}
