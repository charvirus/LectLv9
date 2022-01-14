package snake;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

class Game extends MyUtil {
	private Rect[][] map = new Rect[10][10];
	private final int BUTTON = 50;
	private ArrayList<Rect> snake = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> yx = new ArrayList<>();

	private ArrayList<Rect> items = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> itemsYx = new ArrayList<ArrayList<Integer>>();

	private JButton[] btn = new JButton[4];
	private int length;
	private int dir = 0;
	private boolean growTail;
	private boolean canMove = true;
	private Color head = Color.red;
	private Color body = Color.green;

	public Game() {
		setLayout(null);
		setBounds(0, 0, 1000, 800);
		setHeader();
		setMap();
		setSnake();
		setItems();
		// setBtn();
		setFocusable(true);
		addKeyListener(this);
	}

	private void setItems() {
		Random rn = new Random();
		int n = rn.nextInt(10) + 5;

		for (int i = 0; i < n; i++) {
			int y = rn.nextInt(this.map.length);
			int x = rn.nextInt(this.map.length);

			// check
			boolean check = false;
			for (int j = 0; j < this.yx.size(); j++) {
				if (y == this.yx.get(j).get(0) && x == this.yx.get(j).get(1)) {
					check = true;
				}
			}

			if (check) {
				i--;
			} else {
				Rect temp = this.map[y][x];
				Rect item = new Rect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), Color.pink);

				this.items.add(item);

				ArrayList<Integer> pair = new ArrayList<>();
				pair.add(y);
				pair.add(x);
				this.itemsYx.add(pair);
			}
		}
	}

	private void setSnake() {
		for (int i = 0; i < 4; i++) {
			Rect temp = this.map[0][i];
			Rect nemo;
			if (i == 0) {
				nemo = new Rect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), head);

			} else {
				nemo = new Rect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), body);
			}
			this.snake.add(nemo);

			ArrayList<Integer> pair = new ArrayList<>();
			pair.add(0);
			pair.add(i);
			this.yx.add(pair);
		}
	}

	private void setBtn() {
		String[] text = { "¡ç", "¡é", "¡æ", "¡è" };
		int x = Game.WIDTH - 200;
		int y = Game.HEIGHT - 150;
		for (int i = 0; i < btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x, y, BUTTON, BUTTON);
			this.btn[i].setText(text[i]);
			this.btn[i].addMouseListener(this);
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
		head.setBounds(0, 0, SnakeGame.SIZE, 50);
		head.setFont(new Font("", Font.BOLD, 20));
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setVerticalAlignment(JLabel.CENTER);
		add(head);
	}

	private void setMap() {
		int start = SnakeGame.SIZE / 2 - BUTTON * 10 / 2;
		int x = start;
		int y = start;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				this.map[i][j] = new Rect(x, y, BUTTON, BUTTON, Color.black);
				this.map[i][j].setC(Color.black);
				x += BUTTON;
			}
			x = start;
			y += BUTTON;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// snake
		for (int i = 0; i < this.snake.size(); i++) {
			Rect nemo = this.snake.get(i);
			g.setColor(nemo.getC());
			g.fillRect(nemo.getX(), nemo.getY(), nemo.getWidth(), nemo.getHeight());

		}

		// map line
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Rect nemo = this.map[i][j];
				g.setColor(nemo.getC());
				g.drawRect(nemo.getX(), nemo.getY(), nemo.getWidth(), nemo.getHeight());
			}
		}

		for (int i = 0; i < this.items.size(); i++) {
			Rect nemo = this.items.get(i);
			g.setColor(nemo.getC());
			g.fillRoundRect(nemo.getX(), nemo.getY(), nemo.getWidth(), nemo.getHeight(), BUTTON, BUTTON);

		}

		repaint();

	}

	private void move() {
		int yy = this.yx.get(0).get(0);
		int xx = this.yx.get(0).get(1);
		if (this.dir == 1 && xx > 0) {
			xx--;
		} else if (this.dir == 2 && xx < this.map.length - 1) {
			xx++;
		} else if (this.dir == 3 && yy > 0) {
			yy--;
		} else if (this.dir == 4 && yy < this.map.length - 1) {
			yy++;
		}

		// check item
		for (int i = 0; i < this.itemsYx.size(); i++) {
			if (yy == this.itemsYx.get(i).get(0) && xx == this.itemsYx.get(i).get(1)) {
				this.items.remove(i);
				this.itemsYx.remove(i);
				growTail = true;
			}
		}

		boolean check = false;
		for (int i = 0; i < this.yx.size(); i++) {
			if (yy == this.yx.get(i).get(0) && xx == this.yx.get(i).get(1)) {
				check = true;
			}
		}
		if (!check || growTail) {

			// snake
			// body
			Rect tail = this.snake.get(this.snake.size() - 1);
			ArrayList<Integer> tailYx = this.yx.get(this.yx.size() - 1);

			for (int i = this.snake.size() - 1; i > 0; i--) {
				// yx
				Rect temp = this.snake.get(i - 1);
				temp.setC(body);
				this.snake.set(i, temp);
				this.yx.set(i, this.yx.get(i - 1));
			}
			Rect temp = this.map[yy][xx];
			Rect newHead = new Rect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), head);
			this.snake.set(0, newHead);
			ArrayList<Integer> pair = new ArrayList<Integer>();
			pair.add(yy);
			pair.add(xx);
			this.yx.set(0, pair);

			if (growTail) {
				this.snake.add(tail);
				this.yx.add(tailYx);
				growTail = false;
			}
		} else {
			canMove = false;
			JOptionPane.showMessageDialog(null, "»ç¸Á");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 37) {
			this.dir = 1;
		} else if (e.getKeyCode() == 39) {
			this.dir = 2;
		} else if (e.getKeyCode() == 38) {
			this.dir = 3;
		} else if (e.getKeyCode() == 40) {
			this.dir = 4;
		}
		if (canMove) {
			move();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = 0;
	}

}
