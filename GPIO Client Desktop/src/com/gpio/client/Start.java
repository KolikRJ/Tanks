package com.gpio.client;

import javax.swing.JFrame;

public class Start {

	public static void main(String[] args) {
		JFrame f = new JFrame("GPIO Client");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new Client(f));
		f.setVisible(true);
		f.setResizable(false);
		f.pack();
	}

}
