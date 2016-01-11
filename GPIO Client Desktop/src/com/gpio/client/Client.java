package com.gpio.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address; 
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JPanel implements ActionListener {

	private JButton btn;

	private Socket server;
	private PrintWriter out;

	private JButton address;
	private String ipAddress;

	public Client(JFrame f) {
		f.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				if(address.getText().equals("Отключится"))
				disconect();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		setPreferredSize(new Dimension(100, 100));
		setFocusable(true);
		requestFocus();
		init();

	}

	public void init() {
		btn = new JButton("on");
		address = new JButton("Поиск");

		btn.addActionListener(this);
		address.addActionListener(this);

		add(btn);
		add(address);
	}

	private boolean connect(int i) throws Exception {

		String ip = Inet4Address.getLocalHost().getHostAddress().substring(0, 10);

		server = new Socket();
		server.connect(new InetSocketAddress(ip + i, 3536), 1);
		if (server.isConnected()) {
			server.close();
			server = null;
			address.setText(ip + i);
			ipAddress = ip + i;
			return true;
		}
		return false;
	}

	private void disconect() {
		out.println("close");
		out.close();
		try {
			server.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		server = null;
		address.setText("Поиск");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("on") && address.getText().equals("Отключиться")) {
			out.println("on");
			btn.setText("off");
		}

		if (e.getActionCommand().equals("off") && address.getText().equals("Отключиться")) {
			out.println("off");
			btn.setText("on");
		}

		if (e.getActionCommand().equals("Поиск")) {
			for (int i = 0; i < 254; i++) {
				try {
					if (connect(i))
						break;
				} catch (Exception e1) {
				}
			}
		}

		if (e.getActionCommand().equals("Отключиться")) {
			disconect();
		}

		if (e.getActionCommand().equals(ipAddress)) {
			try {
				server = new Socket(address.getText(), 3535);
				out = new PrintWriter(server.getOutputStream(), true);
				address.setText("Отключиться");
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
