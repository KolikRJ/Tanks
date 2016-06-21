package mail.client.core;

import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MailSendClient {

	private Socket client;
	private PrintWriter out;
	private DataInputStream in;

	public boolean isConnected = false;

	public MailSendClient() {
		try {
			client = new Socket("localhost", 9696);
			in = new DataInputStream(client.getInputStream());
			out = new PrintWriter(client.getOutputStream(), true);
			isConnected = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		out.println("Kolik: asd");
		out.println("Kolik: Hoaasdasd");
		out.println("Kolik: Hoaasrtrgffdasd");
	}

}
