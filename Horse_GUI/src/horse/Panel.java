package horse;

import javax.swing.*;
import java.awt.*;

public class Panel extends MyUtil {
	public JButton start = new JButton("Start");
	public JButton reset = new JButton("Reset");
	private Image im[] = new Image[5];
	private JLabel image[] = new JLabel[5];
	private ImageIcon icon[] = new ImageIcon[5];

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 900, 700);
		setButton();
	}

	private void setButton() {
		this.start.setBounds(350, 500, 100, 50);
		start.addActionListener(this);
		add(this.start);
		this.reset.setBounds(460, 500, 100, 50);
		reset.addActionListener(this);
		add(this.reset);
	}

	private void setImage() {

	}
}
