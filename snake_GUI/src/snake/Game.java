package snake;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;

class SnakePanel extends MyUtil {
	private Rect[][] map = new Rect[10][10];
	private final int BUTTON = 50;
	private Rect[] snake;
	private JButton[] btn = new JButton[4];
	private int length; 
	private int dir = 0;
	public SnakePanel() {
		setLayout(null);
		setBounds(0, 0, 1000, 800);
		setHeader();
		setMap();
		setSnake();
		setBtn();

	}

	private void setBtn() {
		String[] text = { "ก็", "ก้", "กๆ", "ก่" };
		int x = Game.WIDTH - 200;
		int y = Game.HEIGHT - 150;
		for (int i = 0; i < btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x, y, BUTTON, BUTTON);
			this.btn[i].setText(text[i]);
			this.btn[i].addActionListener(this);
			add(this.btn[i]);
			x += BUTTON;
			if (i == this.btn.length - 2) {
				x = Game.WIDTH - 200 + BUTTON;
				y -= BUTTON;
			}
		}
	}

	private void setHeader() {
		JLabel head = new JLabel("Snake Game");
		head.setBounds(0, 0, 1000, 50);
		head.setFont(new Font("", Font.BOLD, 20));
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setVerticalAlignment(JLabel.CENTER);
		add(head);
	}

	private void setMap() {
		int x = 1000 / 2 - BUTTON * 10 / 2 - 100;
		int y = 800 / 2 - BUTTON * 10 / 2;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				this.map[i][j] = new Rect(x, y, BUTTON, BUTTON);
				x += BUTTON;
			}
			y += BUTTON;
			x = 1000 / 2 - BUTTON * 10 / 2 - 100;
		}
	}

	private void setSnake() {
		snake = new Rect[4];
		for (int i = 0; i < snake.length; i++) {
			this.snake[i] = new Rect((3 + i) * BUTTON, BUTTON, BUTTON, BUTTON);
			if (i == 0) {
				snake[i].setC(Color.green);
			} else {
				snake[i].setC(Color.cyan);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// map line
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Rect nemo = this.map[i][j];
				Rect temp = new Rect(nemo.getX() - nemo.getWidth() / 2, nemo.getY() - nemo.getHeight() / 2,
						nemo.getWidth(), nemo.getHeight());
				g.setColor(Color.black);
				g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());

				if (i == this.map.length - 1) {
					g.drawRect(temp.getX(), temp.getY() + temp.getHeight(), temp.getWidth(), temp.getHeight());
				}
				if (j == this.map.length - 1) {
					g.drawRect(temp.getX() + temp.getWidth(), temp.getY(), temp.getWidth(), temp.getHeight());
				}
				if (i == this.map.length - 1 && j == this.map.length - 1) {
					g.drawRect(temp.getX() + temp.getWidth(), temp.getY() + temp.getHeight(), temp.getWidth(),
							temp.getHeight());
				}
			}
		}

		for (int i = 0; i < snake.length; i++) {
			g.setColor(snake[i].getC());
			g.drawRect(snake[i].getX() - snake[i].getWidth() / 2, snake[i].getY() - snake[i].getHeight() / 2,
					snake[i].getWidth(), snake[i].getHeight());
			g.fillRect(snake[i].getX() - snake[i].getWidth() / 2, snake[i].getY() - snake[i].getHeight() / 2,
					snake[i].getWidth(), snake[i].getHeight());
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btn[0]) {
			this.dir = 1;
		}else if ( e.getSource() == this.btn[1]) {
			this.dir = 2;
		}else if ( e.getSource() == this.btn[2]) {
			this.dir = 3;
		}else if ( e.getSource() == this.btn[3]) {
			this.dir = 4;
		}
	}
}

public class Game extends JFrame {

	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int W = dm.width;
	public static final int H = dm.height;

	public final static int WIDTH = 1000;
	public final static int HEIGHT = 800;

	private SnakePanel panel = new SnakePanel();

	public Game() {
		super("Snake Game");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		revalidate();
	}
}
