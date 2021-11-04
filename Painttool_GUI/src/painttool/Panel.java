package painttool;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Panel extends MyUtil {
	private int startX, startY;
	public JButton close = new JButton("Close");

	private Rect rect = null;
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
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 삼각형 그리기
		// drawPolygon(int [],int[],int)
		// (x좌표의 배열, y좌표의 배열, 꼭지점 개수)
		int[] xxx = { 100, 50, 150 };
		int[] yyy = { 100, 200, 200 };
		g.setColor(Color.blue);
		g.drawPolygon(xxx, yyy, 3);
		// 네모 그리기 (스레드)
		if (this.rect != null) {
			g.setColor(this.rect.getC());
			g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
		}
		requestFocusInWindow();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isShiftDown() == true) {
			this.shiftPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.shiftPressed = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.startX = e.getX();
		this.startY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int XX = 0, YY = 0, W = 0, H = 0;
		if (shiftPressed) {
			if (e.getX() > startX && e.getY() > startY) {
				XX = startX;
				YY = startY;
				W = e.getX() - XX;
				H = e.getX() - XX;
			} else if (e.getX() > startX && e.getY() < startY) {

				XX = startX;
				YY = e.getY();
				H = startY - e.getY();
				W = H;

			} else if (e.getX() < startX && e.getY() < startY) {
				H = startY - e.getY();
				W = H;
				XX = startX - W;
				YY = startY - H;
			} else if (e.getX() < startX && e.getY() > startY) {
				XX = e.getX();
				YY = startY;
				W = startX - e.getX();
				H = W;
			}

		} else {
			if (e.getX() > startX) {
				XX = startX;
				W = e.getX() - XX;
			} else {
				XX = e.getX();
				W = startX - e.getX();
			}

			if (e.getY() > startY) {
				YY = startY;
				H = e.getY() - YY;
			} else {
				YY = e.getY();
				H = startY - e.getY();
			}
		}
		this.rect = new Rect(XX, YY, W, H, Color.green);
	}



}
