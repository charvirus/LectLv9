package snake;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SnakeGame extends JFrame {

	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int W = dm.width;
	public static final int H = dm.height;

	public final static int SIZE = 800;

	private Game panel = new Game();

	public SnakeGame() {
		super("Snake Game");
		setLayout(null);
		setBounds(W / 2 - SIZE / 2, H / 2 - SIZE / 2, SIZE, SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		revalidate();
	}
}
