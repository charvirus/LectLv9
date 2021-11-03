package painttool;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame implements MouseListener {
	JButton close = new JButton();

	public Frame() {
		super("Paint Tool");
		setLayout(null);
		setBounds(0, 0, Panel.SIZE, Panel.SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		close.setText("Close");
		close.setBounds(Panel.SIZE - 150, Panel.SIZE - 120, 100, 50);
		close.addMouseListener(this);
		add(close);
		add(new Panel());
		setVisible(true);
		revalidate();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if ((JButton) e.getSource() == close) {
			this.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
