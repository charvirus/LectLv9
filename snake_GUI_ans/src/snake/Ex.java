package snake;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

class RectEx {
	private int x, y, width, height;

	public RectEx(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

class ExPanel extends MyUtil {
	private RectEx rect = new RectEx(100, 100, 100, 100);
	private int gapX, gapY;

	public ExPanel() {

		setBounds(0, 0, 500, 500);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int xx = x - gapX;
		int yy = y - gapY;

		if (x >= rect.getX() && x <= rect.getX() + rect.getWidth() && y >= rect.getY()
				&& y <= rect.getY() + rect.getWidth()) {

			rect.setX(xx);
			rect.setY(yy);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.gapX = e.getX() - this.rect.getX();
		this.gapY = e.getY() - this.rect.getY();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
		repaint();
	}
}

class ExFrame extends JFrame {
	public ExFrame() {
		super("Drag");
		setLayout(null);
		setBounds(0, 0, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new ExPanel());

		setVisible(true);
		revalidate();
	}
}

public class Ex {

	public static void main(String[] args) {
		ExFrame frame = new ExFrame();
	}

}
