package snake_GUI;

import javax.swing.JFrame;

class SnakePanel extends MyUtil {
	private Rect[][] map;
	private Rect[] snake;
	
	private int length;
	
	public SnakePanel() {
		
	}
}

public class Game extends JFrame {

	private SnakePanel panel = new SnakePanel();

	public Game() {
		super("Snake Game");
		setLayout(null);
		setBounds(50, 50, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
	}
}
