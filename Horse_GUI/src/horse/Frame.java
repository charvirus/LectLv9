package horse;

import java.awt.*;

import javax.swing.JFrame;

import controller.RacingPanel;


public class Frame extends JFrame  {

	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int W = dm.width;
	public static final int H = dm.height;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	private RacingPanel rp = new RacingPanel();


	public Frame() {
		super("Horse");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(rp);
		setVisible(true);
		revalidate();
		
		rp.run();
	}

}
