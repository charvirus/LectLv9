package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

// 최상위 컨테이너 : JFrame
// 컨테이너 : JPanel
// 컴포넌트 : JButton, JLabel, JTextField...
// 리스너 : 컴포넌트에 리스너를 add하면, 이벤트 발생 시 -> 캐치 ->처리

class Map extends JPanel implements ActionListener {
	private final int SIZE = 10;
	private JButton[][] map = new JButton[SIZE][SIZE];
	private JButton line = new JButton();
	private JButton reset = new JButton();
	private int[][] intMap = new int[SIZE][SIZE];
	private final int BUTTON = 45;
	private int turn = 1;

	public Map() {
		setLayout(null);
		setBounds(0, 0, FRock.WIDTH, FRock.HEIGHT);
		setMap();
		setLineButton();
		setResetButton();
	}

	private void setMap() {
		int x = FRock.WIDTH / 2 - BUTTON * SIZE / 2;
		int y = FRock.HEIGHT / 2 - BUTTON * SIZE / 2;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				this.map[i][j] = new JButton();
				this.map[i][j].setBounds(x, y, BUTTON, BUTTON);
				this.map[i][j].setBackground(Color.white);
				this.map[i][j].addActionListener(this);
				add(this.map[i][j]);
				x += BUTTON;
			}
			y += BUTTON - 1;
			x = FRock.WIDTH / 2 - BUTTON * SIZE / 2;
		}
	}

	private void setLineButton() {
		this.line.setBounds(FRock.WIDTH / 2 - 200, FRock.HEIGHT - 150, 200, 50);
		this.line.setText("VIEW GUIDE");
		this.line.setVerticalAlignment(JLabel.CENTER);
		this.line.setHorizontalAlignment(JLabel.CENTER);
		this.reset.addActionListener(this);
		add(this.line);
	}

	private void setResetButton() {
		this.reset.setBounds(FRock.WIDTH / 2 + 10, FRock.HEIGHT - 150, 200, 50);
		this.reset.setText("RESET");
		this.reset.setVerticalAlignment(JLabel.CENTER);
		this.reset.setHorizontalAlignment(JLabel.CENTER);
		this.reset.addActionListener(this);
		add(this.reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (target == map[i][j]) {
					if (turn == 1) {
						map[i][j].setText("●");
					} else {
						map[i][j].setText("○");
					}
					map[i][j].setFont(new Font("Arial", Font.BOLD, 15));
					map[i][j].setEnabled(false);
					intMap[i][j] = turn;
				}
			}
		}

		if (target == reset) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j].setText("");
					map[i][j].setFont(new Font("Arial", Font.BOLD, 15));
					intMap[i][j] = 0;
					map[i][j].setEnabled(true);
					turn = 2;
				}
			}
		}

		if (check() == turn) {
			JOptionPane.showMessageDialog(null, String.format("Player %d Win!", turn));
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j].setEnabled(false);
				}
			}
		}
		turn = turn == 1 ? 2 : 1;
	}

	int check() {
		for (int i = 0; i < map.length; i++) {
			int cnt = 0;
			for (int j = 0; j < map[i].length; j++) {
				if (intMap[i][j] == turn) {
					cnt++;
				} else {
					cnt = 0;
				}
				if (cnt == 5) {
					return turn;
				}
			}
		}
		for (int i = 0; i < map.length; i++) {
			int cnt = 0;
			for (int j = 0; j < map[i].length; j++) {
				if (intMap[j][i] == turn) {
					cnt++;
				} else {
					cnt = 0;
				}
				if (cnt == 5) {
					return turn;
				}
			}
		}
		for (int i = 0; i < map.length - 5; i++) {
			int cnt = 0;
			for (int j = 0; j < map[i].length - 5; j++) {
				if (intMap[i + j][i + j] == turn) {
					cnt++;
				} else {
					cnt = 0;
				}
				if (cnt == 5) {
					return turn;
				}
			}
		}

		for (int i = map.length - 1; i >= map.length - 5; i--) {
			for (int j = 0; j < map[i].length - 5; j++) {
				int cnt = 0;
				if (intMap[i][j] == turn) {
					for (int k = 0; k < 5; k++) {
						if (intMap[i - k][j + k] == turn) {
							cnt++;
						} else {
							cnt = 0;
						}
					}
				}
				if (cnt == 5) {
					return turn;
				}
			}
		}

		return 0;
	}

}

class FRock extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int W = dm.width;
	public static final int H = dm.height;

	public final static int WIDTH = 800;
	public final static int HEIGHT = 800;

	Map map = new Map();

	public FRock() {
		super("1 to 50");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		add(map);
		revalidate();
	}
}

public class ORock {

	public static void main(String[] args) {
		FRock omoc = new FRock();
	}

}
