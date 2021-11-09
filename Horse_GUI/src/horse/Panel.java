package horse;

import javax.swing.*;
import java.awt.*;

public class Panel extends MyUtil {
	public JButton start = new JButton("Start");
	public JButton reset = new JButton("Reset");
	private Horse horses[] = new Horse[5];
	private Image im = new ImageIcon("images/horse1.png").getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH);
	private JLabel image[] = new JLabel[5];
//	private JLabel image = new JLabel(new ImageIcon(im));
	private ImageIcon icon = new ImageIcon("images/horse1.png");

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 900, 700);
		setButton();
		setHorse();
		setImage();
		
	}

	private void setButton() {
		this.start.setBounds(350, 500, 100, 50);
		start.addActionListener(this);
		add(this.start);
		this.reset.setBounds(460, 500, 100, 50);
		reset.addActionListener(this);
		add(this.reset);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private void setHorse() {
		for (int i = 0; i < horses.length; i++) {
			ImageIcon imIcon = new ImageIcon("images/horse" + (i + 1) + ".png");
			horses[i] = new Horse(i, 100, 70 + i * 80, 150, 75, 0, "horse" + i + 1 + ".png", imIcon, 0);
		}
	}

	private void setImage() {
		for (int i = 0; i < horses.length; i++) {
			Image img = horses[i].getImage().getImage()
					.getScaledInstance(horses[i].getW(),horses[i].getH(),Image.SCALE_SMOOTH);
			image[i] = new JLabel(new ImageIcon(img));
			image[i].setBounds(horses[i].getX(),horses[i].getY(),horses[i].getW(),horses[i].getH());
			image[i].setVisible(true);
			add(image[i]);
		}
	}
}
