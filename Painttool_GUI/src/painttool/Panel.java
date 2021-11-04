package painttool;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Panel extends MyUtil {
	private int startX, startY;
	public JButton close = new JButton("Close");

	private Rect rect = new Rect(0, 0, 0, 0);
	private boolean shiftPressed = false;

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 900, 700);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		addKeyListener(this);

		setButton();
	}

	private void setButton() {
		this.close.setBounds(750, 600, 100, 50);

		add(this.close);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isShiftDown() == true) {
//			shiftPressed = true;
//		} else {
//			shiftPressed = false;
			System.out.println(this.rect.getX() + "," + this.rect.getY() + "," + this.rect.getWidth() + ","
					+ this.rect.getHeight());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		rect.setX(e.getX());
		rect.setY(e.getY());

		startX = e.getX();
		startY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (shiftPressed) {
			rect.setWidth(e.getX() - rect.getX());
			rect.setHeight(e.getX() - rect.getX());
		} else {
			if (e.getX() > startX) {
				rect.setX(startX);
				rect.setWidth(e.getX() - rect.getX());
			} else {
				rect.setX(e.getX());
				rect.setWidth(startX - e.getX());
			}

			if (e.getY() > startY) {
				rect.setY(startY);
				rect.setHeight(e.getY() - rect.getY());
			} else {
				rect.setY(e.getY());
				rect.setHeight(startY - e.getY());
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getX() > startX) {
			rect.setX(startX);
			rect.setWidth(e.getX() - rect.getX());
		} else {
			rect.setX(e.getX());
			rect.setWidth(startX - e.getX());
		}

		if (e.getY() > startY) {
			rect.setY(startY);
			rect.setHeight(e.getY() - rect.getY());
		} else {
			rect.setY(e.getY());
			rect.setHeight(startY - e.getY());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
		requestFocusInWindow();
		repaint();
	}

}
