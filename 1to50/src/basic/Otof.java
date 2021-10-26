package basic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

//최상위 컨테이너 : JFrame
//컨테이너 : JPanel
//컴포넌트 : JButton, JLabel, JTextField...
//리스너 : 컴포넌트에 리스너를 add하면, 이벤트 발생 시 -> 캐치 ->처리

class Content extends JPanel implements ActionListener {
	Random ran = new Random();

	private JButton[][] buttonMap;
	private JButton reset = new JButton();
	private int numFrontMap[][];
	private int numBackMap[][];
	private int cnt = 1;

	public Content() {
		setLayout(null);
		setBounds(0, 0, 800, 800);
		setMap();
		setHeader();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		for (int i = 0; i < buttonMap.length; i++) {
			for (int j = 0; j < buttonMap[i].length; j++) {
				if (target == this.buttonMap[i][j] && this.cnt == this.numFrontMap[i][j]) {
					this.buttonMap[i][j].setText("" + this.numBackMap[i][j]);
					this.buttonMap[i][j].setBackground(new Color(255, 222, 250));
					this.cnt++;
				} else if (target == this.buttonMap[i][j] && this.cnt == this.numBackMap[i][j]) {
					this.buttonMap[i][j].setText("");
					this.buttonMap[i][j].setBackground(new Color(249, 243, 223));
					this.cnt++;
				}
			}
		}
		if (cnt == 51) {
			for (int i = 0; i < buttonMap.length; i++) {
				for (int j = 0; j < buttonMap[i].length; j++) {
					this.buttonMap[i][j].setBackground(new Color(249, 243, 223));
					this.buttonMap[i][j].setEnabled(false);
				}
			}
		}
	}

	private void setHeader() {
		JLabel head = new JLabel("1 to 50");
		head.setBounds(0, 0, 800, 100);
		head.setFont(new Font("", Font.BOLD, 30));
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setVerticalAlignment(JLabel.CENTER);
		add(head);

	}

	private void setMap() {
		buttonMap = new JButton[5][5];
		numFrontMap = new int[5][5];
		numBackMap = new int[5][5];
		int cnt = 1;
		int x = 200;
		int y = 200;
		final int SIZE = 75;

		for (int i = 0; i < this.numFrontMap.length; i++) {
			for (int j = 0; j < this.numFrontMap[i].length; j++) {
				numFrontMap[i][j] = cnt;
				cnt++;
			}
		}

		for (int i = 0; i < this.numBackMap.length; i++) {
			for (int j = 0; j < this.numBackMap[i].length; j++) {
				numBackMap[i][j] = cnt;
				cnt++;
			}
		}

		for (int i = 0; i < this.numFrontMap.length; i++) {
			for (int j = 0; j < this.numFrontMap[i].length; j++) {
				int rx = ran.nextInt(5);
				int ry = ran.nextInt(5);
				int temp = numFrontMap[rx][ry];
				numFrontMap[rx][ry] = numFrontMap[i][j];
				numFrontMap[i][j] = temp;
			}
		}

		for (int i = 0; i < this.numBackMap.length; i++) {
			for (int j = 0; j < this.numBackMap[i].length; j++) {
				int rx = ran.nextInt(5);
				int ry = ran.nextInt(5);
				int temp = numBackMap[rx][ry];
				numBackMap[rx][ry] = numBackMap[i][j];
				numBackMap[i][j] = temp;
			}
		}

		for (int i = 0; i < buttonMap.length; i++) {
			for (int j = 0; j < buttonMap[i].length; j++) {
				this.buttonMap[i][j] = new JButton();
				this.buttonMap[i][j].setBounds(x, y, SIZE, SIZE);
				this.buttonMap[i][j].setBackground(new Color(205, 242, 202));
				this.buttonMap[i][j].addActionListener(this);
				this.buttonMap[i][j].setText("" + numFrontMap[i][j]);
				add(this.buttonMap[i][j]);
				x += SIZE;
			}
			x = 200;
			y += SIZE;
		}
	}

}

class OtoFFrame extends JFrame {
	Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public final int W = dm.width;
	public final int H = dm.height;

	public OtoFFrame() {
		super("One to Fifty");

		setLayout(null);
		setBounds(W / 2 - 800 / 2, H / 2 - 800 / 2, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.white);
		add(new Content());
		setVisible(true);
		revalidate();
	}
}

public class Otof {
	public static void main(String[] args) {
		OtoFFrame run = new OtoFFrame();
//		Random ran = new Random();
//		int cnt = 1;
//		int numFrontMap[][] = new int[5][5];
//		int numBackMap[][] = new int[5][5];
//		for (int i = 0; i < numFrontMap.length; i++) {
//			for (int j = 0; j < numFrontMap[i].length; j++) {
//				numFrontMap[i][j] = cnt;
//				cnt++;
//			}
//		}
//
//		for (int i = 0; i < numBackMap.length; i++) {
//			for (int j = 0; j < numBackMap[i].length; j++) {
//				numBackMap[i][j] = cnt;
//				cnt++;
//			}
//		}
//
//		for (int i = 0; i < numFrontMap.length; i++) {
//			for (int j = 0; j < numFrontMap[i].length; j++) {
//				int rx = ran.nextInt(5);
//				int ry = ran.nextInt(5);
//				int temp = numFrontMap[rx][ry];
//				numFrontMap[rx][ry] = numFrontMap[i][j];
//				numFrontMap[i][j] = temp;
//			}
//		}
//
//		for (int i = 0; i < numBackMap.length; i++) {
//			for (int j = 0; j < numBackMap[i].length; j++) {
//				int rx = ran.nextInt(5);
//				int ry = ran.nextInt(5);
//				int temp = numBackMap[rx][ry];
//				numBackMap[rx][ry] = numBackMap[i][j];
//				numBackMap[i][j] = temp;
//			}
//		}
//		for (int i = 0; i < numFrontMap.length; i++) {
//			for (int j = 0; j < numFrontMap[i].length; j++) {
//				System.out.print(numFrontMap[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < numFrontMap.length; i++) {
//			for (int j = 0; j < numFrontMap[i].length; j++) {
//				System.out.print(numBackMap[i][j]+" ");
//			}
//			System.out.println();
//		}
	}

}
