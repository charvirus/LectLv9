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
	private ArrayList<Tri> tris = new ArrayList<>();
	private Rect rect = null;
	private Cir cir = null;
	private Tri tri = null;
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

		if (rect != null) {
			g.setColor(rect.getC());
			g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		} else if (cir != null) {
			g.setColor(cir.getC());
			g.drawRoundRect(cir.getX(), cir.getY(), cir.getWidth(), cir.getHeight(), cir.getRoundX(), cir.getRoundY());
		} else if (tri != null) {
			int xx[] = new int[3];
			int yy[] = new int[3];

			xx[0] = this.tri.getX();
			yy[0] = this.tri.getY();

			xx[1] = this.tri.getX() - this.tri.getWidth() / 2;
			yy[1] = this.tri.getY() + this.tri.getHeight();

			xx[2] = this.tri.getX() + this.tri.getWidth() / 2;
			yy[2] = this.tri.getY() + this.tri.getHeight();
			g.drawPolygon(xx, yy, 3);
		}

		for (Rect rect : this.rects) {
			g.setColor(rect.getC());
			g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
		for (Tri tri : this.tris) {
			int xx[] = new int[3];
			int yy[] = new int[3];

			xx[0] = tri.getX();
			yy[0] = tri.getY();

			xx[1] = tri.getX() - tri.getWidth() / 2;
			yy[1] = tri.getY() + tri.getHeight();

			xx[2] = tri.getX() + tri.getWidth() / 2;
			yy[2] = tri.getY() + tri.getHeight();

			g.setColor(tri.getC());
			g.drawPolygon(xx, yy, 3);
		}

		for (Cir cir : this.cirs) {
			g.setColor(cir.getC());
			g.drawRoundRect(cir.getX(), cir.getY(), cir.getWidth(), cir.getHeight(), cir.getRoundX(), cir.getRoundY());
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
		this.startX = e.getX();
		this.startY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		int XX = 0, YY = 0, W = 0, H = 0;

		if (shiftPressed) {

		} else {
			XX = startX;
			YY = startY;

			W = triCheck ? e.getX() - this.startX : Math.abs(e.getX() - this.startX);
			H = triCheck ? e.getY() - this.startY : Math.abs(e.getY() - this.startY);

			if (this.startX > e.getX() && W > 1) {
				XX = startX - W;
			}

			if (this.startY > e.getY() && H > 1) {
				YY = startY - H;
			}
		}
		if (rectCheck) {
			this.rect = new Rect(XX, YY, W, H, Color.red);
		} else if (cirCheck) {
			this.cir = new Cir(XX, YY, W, H, W, H, Color.red);
		} else if (triCheck) {
			this.tri = new Tri(XX, YY, W, H, Color.red);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (rectCheck) {
			rect.setC(Color.green);
			this.rects.add(rect);
			rect = null;
		} else if (triCheck) {
			tri.setC(Color.orange);
			this.tris.add(tri);
			tri = null;
		} else if (cirCheck) {
			cir.setC(Color.blue);
			this.cirs.add(cir);
			cir = null;
		}
	}

}
