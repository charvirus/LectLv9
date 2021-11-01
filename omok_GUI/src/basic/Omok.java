package basic;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

class Nemo {
	boolean click;
	int x, y, width, height;
	int id = 0;

	public Nemo(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}

class Board extends JPanel implements MouseInputListener, ActionListener {
	private Nemo[][] map = new Nemo[10][10];
	private JButton line = new JButton();
	private JButton reset = new JButton();
	private final int BUTTON = 50;
	private int turn = 1;
	private boolean play = true;
	private boolean drawLine = false;

	public Board() {
		setLayout(null);
		setBounds(0, 0, 800, 800);
		addMouseListener(this);
		setMap();
		setLineButton();
		setResetButton();
	}

	private void setMap() {
		// �׸� �簢���� ���� ������ Nemo �迭�� Ȱ���ؼ� ����
		int x = 800 / 2 - BUTTON * 10 / 2;
		int y = 800 / 2 - BUTTON * 10 / 2 - 70;
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				this.map[i][j] = new Nemo(x, y, BUTTON, BUTTON);
				x += BUTTON;

			}
			x = 800 / 2 - BUTTON * 10 / 2;
			y += BUTTON;
		}
	}

	private void setLineButton() {
		this.line.setBounds(Omokgame.WIDTH / 2 - 200, Omokgame.HEIGHT - 150, 200, 50);
		this.line.setText("VIEW GUIDE");
		this.line.setVerticalAlignment(JLabel.CENTER);
		this.line.setHorizontalAlignment(JLabel.CENTER);
		this.line.addActionListener(this);
		add(this.line);
	}

	private void setResetButton() {
		this.reset.setBounds(Omokgame.WIDTH / 2 + 10, Omokgame.HEIGHT - 150, 200, 50);
		this.reset.setText("RESET");
		this.reset.setVerticalAlignment(JLabel.CENTER);
		this.reset.setHorizontalAlignment(JLabel.CENTER);
		this.reset.addActionListener(this);
		add(this.reset);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// �׸� �׸���
		// paintComponent() �޼ҵ尡 ���� ���� Graphics�� Ȱ��
		// drawRect(x,y,width,height) �޼ҵ带 ���
		// g.drawRect(100, 100, 100, 100);

		// map line
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				Nemo nemo = this.map[i][j];

				Nemo temp = new Nemo(nemo.x - nemo.width / 2, nemo.y - nemo.height / 2, nemo.width, nemo.height);
				g.setColor(Color.black);
				g.drawRect(temp.x, temp.y, temp.width, temp.height);

				if (i == this.map.length - 1) {
					g.drawRect(temp.x, temp.y + temp.height, temp.width, temp.height);
				}
				if (j == this.map.length - 1) {
					g.drawRect(temp.x + temp.width, temp.y, temp.width, temp.height);
				}
				if (i == this.map.length - 1 && j == this.map.length - 1) {
					g.drawRect(temp.x + temp.width, temp.y + temp.height, temp.width, temp.height);
				}

			}
		}

		// stone
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				// guide line
				Nemo nemo = this.map[i][j];
				if (this.drawLine) {
					g.setColor(Color.green);
					g.drawRect(nemo.x, nemo.y, nemo.width, nemo.height);
				}

				// stone
				if (nemo.id == 1) {
					g.setColor(Color.black);
					g.drawRoundRect(nemo.x + 10, nemo.y + 10, nemo.width - 20, nemo.height - 20, nemo.width,
							nemo.height);
					g.fillRoundRect(nemo.x + 10, nemo.y + 10, nemo.width - 20, nemo.height - 20, nemo.width,
							nemo.height);

				} else if (nemo.id == 2) {
					g.setColor(Color.white);
					g.drawRoundRect(nemo.x + 10, nemo.y + 10, nemo.width - 20, nemo.height - 20, nemo.width,
							nemo.height);
					g.fillRoundRect(nemo.x + 10, nemo.y + 10, nemo.width - 20, nemo.height - 20, nemo.width,
							nemo.height);
				}
			}
		}

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) { // Ŭ�� �߻���

		int x = e.getX();
		int y = e.getY();
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				Nemo stone = this.map[i][j];
				if (!stone.click && x >= stone.x && x <= stone.x + stone.width && y >= stone.y
						&& y <= stone.y + stone.height) {
					stone.click = true;
					stone.id = turn;
				}
			}
		}

		if (this.play) {

			if (check() == turn) {

				JOptionPane.showMessageDialog(null, String.format("Player %d ��!", turn));
				for (int i = 0; i < this.map.length; i++) {
					for (int j = 0; j < this.map[i].length; j++) {
						map[i][j].click = true;
					}
				}

			}
		} else {
			JOptionPane.showMessageDialog(null, String.format("������ �ٽ��ϼ���."));

		}
		turn = turn == 1 ? 2 : 1;
	}

	@Override
	public void mousePressed(MouseEvent e) { // Ŭ (������)

		int x = e.getX();
		int y = e.getY();
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				Nemo stone = this.map[i][j];
				if (stone.id != 0 && x >= stone.x && x <= stone.x + stone.width && y >= stone.y
						&& y <= stone.y + stone.height) {
					JOptionPane.showMessageDialog(null, String.format("�ٸ� ���� �������ּ���."));
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) { // �� (����)

	}

	@Override
	public void mouseEntered(MouseEvent e) { // listener �� add �� ���� ������ ���콺�� ������

	}

	@Override
	public void mouseExited(MouseEvent e) { // listener �� add �� ���� ������ ���콺�� ������

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		if (target == line) {
			this.drawLine = !this.drawLine;
		}
		if (target == reset) {
			this.play = true;
			setMap();
			this.turn = 1;
		}
	}

	int check() {
		// ����
		for (int i = 0; i < map.length; i++) {
			int cnt = 0;
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].id == turn) {
					cnt++;
				} else {
					cnt = 0;
				}
				if (cnt == 5) {
					System.out.println(turn);
					this.play = false;
					return turn;
				}
			}
		}
		// ����
		for (int i = 0; i < map.length; i++) {
			int cnt = 0;
			for (int j = 0; j < map[i].length; j++) {
				if (map[j][i].id == turn) {
					cnt++;
				} else {
					cnt = 0;
				}
				if (cnt == 5) {
					this.play = false;
					return turn;
				}
			}
		}
		// �밢�� \
		for (int i = 0; i < map.length - 4; i++) {
			for (int j = 0; j < map[i].length - 4; j++) {
				int cnt = 0;
				if (map[i][j].id == turn) {
					for (int k = 0; k < 5; k++) {
						if (map[i + k][j + k].id == turn) {
							cnt++;
						} else {
							cnt = 0;
						}
					}
				}
				if (cnt == 5) {
					this.play = false;
					return turn;
				}
			}
		}

		// �밢�� / , �߿�
		for (int i = map.length - 1; i >= map.length - 6; i--) {
			for (int j = 0; j <= map[i].length - 5; j++) {
				int cnt = 0;
				for (int k = 0; k < 5; k++) {
					if (map[i - k][j + k].id == turn) {
						cnt++;
					} else {
						cnt = 0;
					}
				}
				if (cnt == 5) {
					this.play = false;
					return turn;
				}

			}
		}

		return 0;
	}

}

class Omokgame extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int W = dm.width;
	public static final int H = dm.height;

	public final static int WIDTH = 800;
	public final static int HEIGHT = 800;

	private Board board = new Board();

	public Omokgame() {

		super("Omokgame");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(board);

		setVisible(true);
		revalidate();
	}
}

public class Omok {

	public static void main(String[] args) {
		Omokgame omok = new Omokgame();

	}

}
