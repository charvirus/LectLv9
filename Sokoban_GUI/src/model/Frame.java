package model;

import java.awt.*;

import javax.swing.JFrame;

import controller.GamePanel;

public class Frame extends JFrame {
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int W = dm.width;
	public static final int H = dm.height;

	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	
	public Frame() {
		super("Sokoban");
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new GamePanel());
		setVisible(true);
		revalidate();
		
	}
}
