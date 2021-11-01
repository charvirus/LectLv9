package basic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.*;

class MyUtil extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

class Rect {
	private int x, y, width, height;

	public Rect(int x, int y, int width, int height) {
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

class PushPanel extends MyUtil {

	private int dir; // 0 Á¤Áö , ¿Þ ÇÏ ¿À À§
	private int xx;
	private int yy;
	private boolean check = false;
	private final int SIZE = 100;
	private Rect r1 = null;
	private Rect r2 = null;

	private JButton[] btn = new JButton[4];

	public PushPanel() {
		setLayout(null);
		setBounds(0, 0, 800, 800);
		setRect();
		setBtn();
		setFocusable(true);
		addKeyListener(this);
	}

	private void setBtn() {
		String[] text = { "¡ç", "¡é", "¡æ", "¡è" };

		int size = 50;
		int x = 700 - 200;
		int y = 700 - 100;
		for (int i = 0; i < btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x, y, size, size);
			this.btn[i].setText(text[i]);
			this.btn[i].addMouseListener(this);
			add(this.btn[i]);
			x += size;
			if (i == this.btn.length - 2) {
				x = 700 - 200 + size;
				y -= size;
			}
		}
	}

	private void setRect() {
		Random rn = new Random();
		int x = rn.nextInt(700 - 100) + 1;
		int y = rn.nextInt(700 - 100) + 1;

		this.r1 = new Rect(x, y, SIZE, SIZE);

		while (true) {
			x = rn.nextInt(700 - 100) + 1;
			y = rn.nextInt(700 - 100) + 1;

			// °ãÄ¡Áö ¾Ê°Ô ¹èÄ¡
			if ((x >= this.r1.getX() && x < this.r1.getX() + SIZE && y >= this.r1.getY()
					&& y < this.r1.getY() + SIZE)) {
				continue;
			}
			if ((x + SIZE >= this.r1.getX() && x + SIZE < this.r1.getX() + SIZE && y + SIZE >= this.r1.getY()
					&& y + SIZE < this.r1.getY() + SIZE)) {
				continue;
			}
			if ((x >= this.r1.getX() && x < this.r1.getX() + SIZE && y + SIZE >= this.r1.getY()
					&& y + SIZE < this.r1.getY() + SIZE)) {
				continue;
			}
			if ((x + SIZE >= this.r1.getX() && x + SIZE < this.r1.getX() + SIZE && y >= this.r1.getY()
					&& y < this.r1.getY() + SIZE)) {
				continue;
			}
			break;
		}
		this.r2 = new Rect(x, y, SIZE, SIZE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		update();
		// r1, r2
		g.setColor(Color.blue);
		if(check) {
			g.setColor(Color.red);
		}else{
			g.setColor(Color.blue);
		}
		g.drawRect(this.r1.getX(), this.r1.getY(), this.r1.getWidth(), this.r1.getHeight());
		g.setColor(Color.green);
		g.drawRect(this.r2.getX(), this.r2.getY(), this.r2.getWidth(), this.r2.getHeight());

		repaint();
	}

	private void update() {
		xx = this.r1.getX();
		yy = this.r1.getY();
		// ¿Þ
		if (this.dir == 1 && this.r1.getX() > 0) {
			xx--;
		}
		// ÇÏ
		else if (this.dir == 2 && this.r1.getY() + SIZE < 700) {
			yy++;
		}
		// ¿À
		else if (this.dir == 3 && this.r1.getX() + SIZE < 700) {
			xx++;
		}
		// »ó
		else if (this.dir == 4 && this.r1.getY() > 0) {
			yy--;
		}

		checkAnother();

		// ÁÂ
		if (this.dir == 1) {
			if (check && this.r2.getX() + SIZE > SIZE) {
				this.r2.setX(this.r2.getX() - 1);
				this.r1.setX(xx);
			} else if (!check && xx > 0) {
				this.r1.setX(xx);
			}

		}
		// ÇÏ
		else if (this.dir == 2) {
			if (check && this.r2.getY() < 700 - SIZE) {
				this.r2.setY(this.r2.getY() + 1);
				this.r1.setY(yy);
			}else if (!check && yy <700) {
				this.r1.setY(yy);
			}
		}
		// ¿ì
		else if (this.dir == 3) {
			if (check && this.r2.getX() < 700 - SIZE) {
				this.r2.setX(this.r2.getX() + 1);
				this.r1.setX(xx);
			}
			else if (!check && xx < 700) {
				this.r1.setX(xx);
			}
		}
		// »ó
		else if (this.dir == 4) {
			if (check && this.r2.getY() + SIZE > SIZE) {
				this.r2.setY(this.r2.getY() + 1);
				this.r1.setY(yy);
			}
			else if (!check && yy >0) {
				this.r1.setY(yy);
			}
		}
	}

	private void checkAnother() {
		check = false;
		if (this.dir == 1) {
			if (this.r2.getX() + SIZE == xx && this.r2.getY() >= this.r1.getY() - SIZE
					&& this.r2.getY() < this.r1.getY() + SIZE) {
				check = true;
			}
		}
		if (this.dir == 2) {
			if (this.r2.getY() == yy + SIZE && this.r2.getX() >= this.r1.getX() - SIZE
					&& this.r2.getX() < this.r1.getX() + SIZE) {
				check = true;
			}
		}
		if (this.dir == 3) {
			if (this.r2.getX() == xx + SIZE && this.r2.getY() >= this.r1.getY() - SIZE
					&& this.r2.getY() < this.r1.getY() + SIZE) {
				check = true;
			}
		}
		if (this.dir == 4) {
			if (this.r2.getY() + SIZE == yy && this.r2.getX() >= this.r1.getX() - SIZE
					&& this.r2.getX() < this.r1.getX() + SIZE) {
				check = true;
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this.btn[0]) {
			this.dir = 1;
		} else if (e.getSource() == this.btn[1]) {
			this.dir = 2;
		} else if (e.getSource() == this.btn[2]) {
			this.dir = 3;
		} else if (e.getSource() == this.btn[3]) {
			this.dir = 4;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.dir = 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT) {
			this.dir = 1;
		}
		else if(e.getKeyCode() == e.VK_DOWN) {
			this.dir = 2;
		}
		else if(e.getKeyCode() == e.VK_RIGHT) {
			this.dir = 3;
		}
		else if(e.getKeyCode() == e.VK_UP) {
			this.dir = 4;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = 0;
	}
}

class PushFrame extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int W = dm.width;
	public static final int H = dm.height;

	public final static int WIDTH = 750;
	public final static int HEIGHT = 750;

	private PushPanel board = new PushPanel();

	public PushFrame() {
		super("Nemopush");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(board);

		setVisible(true);
		revalidate();
	}

}

public class NemoPush {

	public static void main(String[] args) {
		PushFrame game = new PushFrame();
	}

}
