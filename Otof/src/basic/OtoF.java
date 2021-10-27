package basic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Random;

class AlertEnd extends JFrame {

	private JLabel text = new JLabel();

	public AlertEnd(int ms) {
		super("GAME CLEAR");
		setLayout(null);
		setBounds(Otf.W / 2 - 150, Otf.H / 2 - 100, 300, 200);
		setVisible(true);

		this.text.setBounds(0, 0, 300, 200);
		this.text.setText(String.format("±â·Ï : %d.%3d", ms / 1000, ms % 1000));
		this.text.setVerticalAlignment(JLabel.CENTER);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
	}
}

class Map extends JPanel implements ActionListener, Runnable {

	private final int SIZE = 5;
	private JButton[][] map = new JButton[SIZE][SIZE];
	private int[][] front = new int[SIZE][SIZE];
	private int[][] back = new int[SIZE][SIZE];
	private final int BUTTON = 60;
	private int gameNum = 1;

	JLabel timer = new JLabel();
	private int ms;
	private boolean isRun = false;

	private JButton reset = new JButton();

	public Map() {
		setLayout(null);
		setBounds(0, 0, Otf.WIDTH, Otf.HEIGHT);
		setTimer();
		setMap();
		setResetButton();
	}

	private void setResetButton() {
		this.reset.setBounds(Otf.WIDTH / 2 - 150, Otf.HEIGHT - 200, 300, 50);
		this.reset.setText("Reset");
		this.reset.addActionListener(this);
		add(this.reset);
	}

	private void setTimer() {
		this.timer.setBounds(0, 0, Otf.WIDTH, 200);
		this.timer.setText(String.format("%d.%3d", this.ms / 1000, this.ms % 1000));
		this.timer.setVerticalAlignment(JLabel.CENTER);
		this.timer.setHorizontalAlignment(JLabel.CENTER);

		add(this.timer);
	}

	private void setMap() {
		shuffle();
		int x = Otf.WIDTH / 2 - BUTTON * SIZE / 2;
		int y = Otf.HEIGHT / 2 - BUTTON * SIZE / 2;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				this.map[i][j] = new JButton();
				this.map[i][j].setBounds(x, y, BUTTON, BUTTON);
				this.map[i][j].setText(front[i][j] + "");
				this.map[i][j].addActionListener(this);
				this.map[i][j].setBackground(new Color(109, 152, 134));
				this.map[i][j].setFont(new Font("Arial", Font.BOLD, 15));
				this.map[i][j].setForeground(Color.white);

				add(this.map[i][j]);
				x += BUTTON;
			}
			y += BUTTON + 3;
			x = Otf.WIDTH / 2 - BUTTON * SIZE / 2;
		}
	}

	private void shuffle() {
		int n = 1;
		for (int i = 0; i < front.length; i++) {
			for (int j = 0; j < front[i].length; j++) {
				this.front[i][j] = n;
				this.back[i][j] = n + SIZE * SIZE;
				n++;
			}
		}

		Random ran = new Random();
		for (int i = 0; i < this.front.length; i++) {
			for (int j = 0; j < this.front[i].length; j++) {
				int rx = ran.nextInt(5);
				int ry = ran.nextInt(5);
				int temp = front[rx][ry];
				front[rx][ry] = front[i][j];
				front[i][j] = temp;

				rx = ran.nextInt(5);
				ry = ran.nextInt(5);
				temp = back[rx][ry];
				back[rx][ry] = back[i][j];
				back[i][j] = temp;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();

		if (target == this.reset) {
			resetMap();
		}
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				if (target == this.map[i][j] && this.front[i][j] == this.gameNum) {

					if (this.gameNum == 1) {
						this.isRun = true;
					}
					this.front[i][j] = this.back[i][j];
					this.back[i][j] = 0;
					if (this.front[i][j] == 0) {
						target.setText("");
						target.setBackground(Color.WHITE);

					} else {
						target.setText(this.front[i][j] + "");
						target.setBackground(new Color(217, 202, 179));
					}
					this.gameNum++;
				}
			}
		}
		if (this.gameNum > SIZE * SIZE * 2) {
			new AlertEnd(ms);
		}
	}

	private void resetMap() {
		shuffle();
		this.gameNum = 1;
		this.isRun = false;
		this.ms = 0;
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				this.map[i][j].setBackground(new Color(109, 152, 134));
				this.map[i][j].setText(this.front[i][j]+"");
			}
		}
		setTimer();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (this.isRun) {
				this.ms++;
				this.timer.setText(String.format("%d.%3d", this.ms / 1000, this.ms % 1000));
			}
		}
	}
}

class Otf extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int W = dm.width;
	public static final int H = dm.height;

	public final static int WIDTH = 500;
	public final static int HEIGHT = 800;
	Map map = new Map();

	public Otf() {
		super("1 to 50");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		add(map);
		revalidate();
		map.run();
	}
}

public class OtoF {

	public static void main(String[] args) {
		Otf ttt = new Otf();
	}

}
