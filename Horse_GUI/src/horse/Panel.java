package horse;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Panel extends MyUtil {
	Random ran = new Random();
	public JButton start = new JButton("Start");
	public JButton reset = new JButton("Reset");
	private Horse horses[] = new Horse[5];
	private Image im = new ImageIcon("images/horse1.png").getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH);
	private JLabel image[] = new JLabel[5];
//	private JLabel image = new JLabel(new ImageIcon(im));
	private ImageIcon icon[] = new ImageIcon[5];

	private final int ENDX = 750;
	private int rank[] = new int[5];

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 900, 700);
		setButton();
		setHorse();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawLine(ENDX, 50, ENDX, 450);
		for (int i = 0; i < horses.length; i++) {
			g.drawImage(horses[i].getImage().getImage(), horses[i].getX(), horses[i].getY(), null);
		}
		repaint();
	}

	private void setButton() {
		this.start.setBounds(350, 500, 100, 50);
		start.addActionListener(this);
		add(this.start);
		this.reset.setBounds(460, 500, 100, 50);
		reset.addActionListener(this);
		add(this.reset);
	}

	private void setHorse() {
		for (int i = 0; i < horses.length; i++) {
			// 이미지 크기 변경
			ImageIcon imIcon = new ImageIcon("images/horse" + (i + 1) + ".png");
			Image img = imIcon.getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(img);
			horses[i] = new Horse(i, 100, 70 + i * 80, 150, 75, 0, "horse" + i + 1 + ".png", changeIcon, 0);
		}
	}

	private void stepByStep() {
		while (true) {
			boolean goal = false;
			for (int i = 0; i < horses.length; i++) {
				if (this.horses[i].getState() == Horse.RUN) {
					int jump = ran.nextInt(5);
					int xx = this.horses[i].getX() + jump;
					if (xx < this.ENDX - this.horses[i].getW() - 1) {
						this.horses[i].setX(xx);
					} else {
						if (goal) {
							i--;
							break;
						} else {

						}
					}
				}
			}
		}
	}
}
