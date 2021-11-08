package horse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Frame extends JFrame implements ActionListener {

	Panel pan = new Panel();

	public Frame() {
		super("Horse");
		setLayout(null);
		setBounds(0, 0, 900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(pan);
		setVisible(true);
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
