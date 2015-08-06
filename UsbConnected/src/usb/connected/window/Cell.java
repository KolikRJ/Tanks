package usb.connected.window;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import usb.connected.device.ConnectedPort;

public class Cell extends JPanel {

	private static final long serialVersionUID = 1L;

	private boolean isRemember;

	private JLabel title;

	private ConnectedPort port;

	public Cell(int number) {
		Border border = new TitledBorder("#" + number);
		setBorder(border);

		title = new JLabel("<html>Данная ячейка<br>не запомнена<html>");
		add(title);
	}

	public Rectangle getRect() {
		return new Rectangle(getX(), getY(), getWidth() - 5, getHeight() - 5);
	}

	public void mouseMoved(Point e) {
		if (!isRemember)
			if (getRect().contains(e))
				title.setText("<html>Нажмите чтобы<br>запомнить<html>");
			else
				title.setText("<html>Данная ячейка<br>не запомнена<html>");
	}

	public void mouseClicked(Point e) {
		if (getRect().contains(e)) {
			if (!isRemember)
				remember();
			else
				try {
					Runtime.getRuntime().exec("explorer.exe " + port.getChars() + ":\\");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
	}

	private void remember() {
		isRemember = true;
		title.setText("<html>Подождите<html>");
	}

	public void update() {
		if (isRemember) {
			port = new ConnectedPort("Port_#0006.Hub_#0004");
			port.getConnectedPort();
			title.setText(port.toString());
		}
	}
}
