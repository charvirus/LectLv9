package basic;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HeadTitle extends JLabel {
	public HeadTitle() {
		setBounds(MyFrame.W / 2 - 120 / 2, 10, 120, 50);
		setHorizontalAlignment(CENTER);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		setBorder(border);
		setText("틱택토");
		setFont(new Font("", Font.BOLD, 30));
		setVisible(true);
	}
}

class MyPanel extends JPanel implements ActionListener {
	JButton btnArr[][] = new JButton[3][3];
	int arr[][] = new int[3][3];
	int turn = 1;
	final int SIZE = 65;
	JButton winBtn = new JButton();
	JButton reBtn = new JButton();

	public MyPanel() {
		setLayout(null);
		this.setBackground(Color.GRAY);
		this.setBounds(0, 0, MyFrame.W, MyFrame.H);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				this.btnArr[i][j] = new JButton();
				this.btnArr[i][j].setBackground(Color.WHITE);
				this.btnArr[i][j].setText(i * 3 + j + 1 + "");
				this.btnArr[i][j].setBounds(MyFrame.W / 2 - (arr.length * SIZE) / 2 + j * SIZE,
						MyFrame.H / 2 - (arr.length * SIZE) / 2 - 10 + i * SIZE, SIZE, SIZE);
				this.btnArr[i][j].addActionListener(this);
				add(this.btnArr[i][j]);
				arr[i][j] = 0;
			}
		}

		winBtn.setText("승자는?");
		winBtn.setBounds(MyFrame.W / 4, 300, 100, 40);
		winBtn.setBackground(Color.PINK);
		winBtn.addActionListener(this);
		this.add(winBtn);

		reBtn.setText("재시작");
		reBtn.setBounds(MyFrame.W / 2 + 20, 300, 100, 40);
		reBtn.setBackground(Color.PINK);
		reBtn.addActionListener(this);
		this.add(reBtn);
	}

	int check() {
		// 가로
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][0] == turn && arr[i][1] == turn && arr[i][2] == turn) {
				return turn;
			}
		}
		// 세로
		for (int i = 0; i < arr.length; i++) {
			if (arr[0][i] == turn && arr[1][i] == turn && arr[2][i] == turn) {
				return turn;
			}
		}

		for (int i = 0; i < arr.length; i += 2) {
			if (arr[0][i] == turn && arr[1][1] == turn && arr[2][2 - i] == turn) {
				return turn;
			}
		}
		return 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (e.getSource() == this.btnArr[i][j]) {
					if (turn == 1) {
						btnArr[i][j].setText("O");
						btnArr[i][j].setBackground(new Color(0, 255, 200));
					} else {
						btnArr[i][j].setText("X");
						btnArr[i][j].setBackground(new Color(0, 200, 255));
					}
					arr[i][j] = turn;
					btnArr[i][j].setEnabled(false);

				}
			}
		}

		if (e.getSource() == reBtn) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					btnArr[i][j].setText(i * 3 + j + 1 + "");
					btnArr[i][j].setEnabled(true);
					btnArr[i][j].setBackground(Color.WHITE);
					winBtn.setBackground(Color.PINK);
					arr[i][j] = 0;
				}
			}
			winBtn.setText("승자는?");
			turn = 1;
		}

		if (check() == turn) {
			winBtn.setText("Player" + turn + "WIN!");
			if (turn == 1) {
				winBtn.setBackground(new Color(0, 255, 200));
			} else {
				winBtn.setBackground(new Color(0, 200, 255));
			}
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					btnArr[i][j].setEnabled(false);
				}
			}
		}
		turn = turn == 1 ? 2 : 1;
	}

}

class MyFrame extends JFrame {
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = dm.width;
	private int height = dm.height;
	public final static int W = 500;
	public final static int H = 400;

	public MyFrame(String title) {
		super(title);
		setLayout(null);
		this.setBounds(this.width / 2 - W / 2, this.height / 2 - H / 2, W, H);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		add(new HeadTitle());
		add(new MyPanel());
	}
}

public class TTT {

	public static void main(String[] args) {
		MyFrame frame = new MyFrame("TTT");
	}

}
