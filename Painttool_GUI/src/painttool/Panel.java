package painttool;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class Panel extends MyUtil {
	private int startX, startY;
	public JButton close = new JButton("Close");
	public JButton rectButton = new JButton("□");
	public JButton triButton = new JButton("△");
	public JButton cirButton = new JButton("○");
	private ArrayList<Rect> rects = new ArrayList<>();
	private ArrayList<Cir> cirs = new ArrayList<>();
	private Rect rect = null;
	private Cir cir = null;
	int figureCount = 0;
	private boolean shiftPressed = false;
	private boolean rectCheck = true;
	private boolean triCheck = false;
	private boolean cirCheck = false;

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
		this.rectButton.setBounds(50, 50, 50, 50);
		this.triButton.setBounds(50, 110, 50, 50);
		this.cirButton.setBounds(50, 170, 50, 50);
		rectButton.addActionListener(this);
		rectButton.setEnabled(false);
		triButton.addActionListener(this);
		cirButton.addActionListener(this);
		add(this.close);
		add(this.rectButton);
		add(this.triButton);
		add(this.cirButton);
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
		for (int i = 0; i < rects.size(); i++) {
			g.setColor(this.rects.get(i).getC());
			g.drawRect(this.rects.get(i).getX(), this.rects.get(i).getY(), this.rects.get(i).getWidth(),
					this.rects.get(i).getHeight());
		}
		for (int i = 0; i < cirs.size(); i++) {
			g.setColor(this.cirs.get(i).getC());
			g.drawRoundRect(this.cirs.get(i).getX(), this.cirs.get(i).getY(), this.cirs.get(i).getWidth(),
					this.cirs.get(i).getHeight(), this.cirs.get(i).getRoundX(), this.cirs.get(i).getRoundY());
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
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == rectButton) {
			rectButton.setEnabled(false);
			triButton.setEnabled(true);
			cirButton.setEnabled(true);
			rectCheck = true;
			triCheck = false;
			cirCheck = false;
		} else if (button == triButton) {
			rectButton.setEnabled(true);
			triButton.setEnabled(false);
			cirButton.setEnabled(true);
			rectCheck = false;
			triCheck = true;
			cirCheck = false;
		} else if (button == cirButton) {
			rectButton.setEnabled(true);
			triButton.setEnabled(true);
			cirButton.setEnabled(false);
			rectCheck = false;
			triCheck = false;
			cirCheck = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (rectCheck) {
			figureCount = rects.size();
			this.rects.add(new Rect(0, 0, 0, 0, Color.red));
		} else if (triCheck) {

		} else if (cirCheck) {
			figureCount = cirs.size();
			this.cirs.add(new Cir(0, 0, 0, 0, 0, 0, Color.red));
		}
		this.startX = e.getX();
		this.startY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (rectCheck) {
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
			this.rects.set(figureCount, new Rect(XX, YY, W, H, Color.red));
		} else if (triCheck) {

		} else if (cirCheck) {
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
			this.cirs.set(figureCount, new Cir(XX, YY, W, H, W, H, Color.red));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (rectCheck) {
			this.rects.get(figureCount).setC(Color.green);
		} else if (triCheck) {

		} else if (cirCheck) {
			this.cirs.get(figureCount).setC(Color.blue);
		}
	}

}
