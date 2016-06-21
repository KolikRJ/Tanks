package mail.server.run;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class MailConnectedClient extends Thread {

	private DataInputStream in;
	private PrintWriter out;

	private Box box;
	private JLabel name;
	private JLabel sizeDisk;

	private JPanel panel;
	private int index;

	public MailConnectedClient(Socket client, JPanel panel, int index) throws IOException {
		this.panel = panel;
		this.index = index;
		in = new DataInputStream(client.getInputStream());
		out = new PrintWriter(client.getOutputStream(), true);

		panel.add(mailPanel());

		setDaemon(true);
		setPriority(NORM_PRIORITY);
		start();
	}

	public Box mailPanel() {

		box = Box.createVerticalBox();
		name = new JLabel();
		sizeDisk = new JLabel();

		box.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		box.add(name);
		box.add(sizeDisk);
		return box;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			String temp = in.readLine();
			name.setText(temp.substring(0, temp.indexOf(":")));
			while (true) {
				Thread.sleep(1000);
				temp = in.readLine();
				sizeDisk.setText(temp.substring(temp.indexOf(":") + 1, temp.length()));
			}
		} catch (Exception e) {
			name.setText("Disconected");
			sizeDisk.setText("null");
		}

	}

}
