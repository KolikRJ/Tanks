package whitebrains.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Connecting implements Runnable {

	private Thread thread;
	private ServerSocket server;
	private ServerSocket fileServer;
	private static ArrayList<ConnectClient> clients = new ArrayList<ConnectClient>();

	public void start() {
		if (thread == null)
			try {
				thread = new Thread(this);
				server = new ServerSocket(55690);
				fileServer = new ServerSocket(55691);
				thread.setDaemon(true);
				thread.setName("Thread listener clients");
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void run() {
		while (true)
			try {
				clients.add(new ConnectClient(server.accept(), fileServer.accept(), clients.size()));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static int getSize() {
		return clients.size();
	}

	public static void removeClient(Object object) {
		clients.remove(object);
	}

	public static void removeClientIndex(int id) {
		clients.remove(id);
	}
}
