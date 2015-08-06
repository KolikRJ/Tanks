package usb.connected.core;

import javax.swing.JFrame;

public class main {

	public static void main(String... args) {
		JFrame f = new JFrame("Usb Connected");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new WindowsConnected());
		f.pack();
		f.setVisible(true);
		f.setResizable(true);
	}

}
