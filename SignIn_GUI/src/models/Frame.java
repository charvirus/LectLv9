package models;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.MainPanel;

public class Frame extends JFrame {
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int W = dm.width;
	public static final int H = dm.height;

	public static final int WHSIZE = 400;

	public Frame() {
		super("List");
		setBounds(W / 2 - WHSIZE / 2, H / 2 - WHSIZE / 2, WHSIZE, WHSIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new MainPanel());
		setVisible(true);
		revalidate();
	}
}
