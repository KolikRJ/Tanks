package usb.connected.core;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import usb.connected.window.Cell;

public class WindowsConnected extends Container implements Runnable, MouseMotionListener, MouseInputListener {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 240 * 2;
	public static final int HEIGHT = 320 * 2;

	private Thread thread;

	private ArrayList<Cell> cells;

	public WindowsConnected() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridLayout(5, 5, 5, 5));
		cells = new ArrayList<Cell>();
		for (int i = 0; i < 25; i++) {
			cells.add(new Cell(i + 1));
			add(cells.get(i));
		}
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
			addMouseMotionListener(this);
			addMouseListener(this);
		}
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(60);
				for (Cell cell : cells)
					cell.update();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (Cell cell : cells)
			cell.mouseMoved(e.getPoint());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (Cell cell : cells)
			cell.mouseClicked(e.getPoint());

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
