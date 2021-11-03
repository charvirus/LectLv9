package painttool;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Panel extends MyUtil {
	private Rect rect = new Rect(0,0,0,0);

	public static final int SIZE = 800;

	public Panel() {
		setBounds(0, 0, SIZE, SIZE);
		addMouseMotionListener(this);
		addMouseListener(this);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		rect.setX(e.getX());
		rect.setY(e.getY());
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		rect.setWidth(e.getX()-rect.getX());
		rect.setHeight(e.getY()-rect.getY());		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		rect.setWidth(e.getX()-rect.getX());
		rect.setHeight(e.getY()-rect.getY());		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
		repaint();
	}

}
