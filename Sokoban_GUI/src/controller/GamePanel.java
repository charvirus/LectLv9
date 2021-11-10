package controller;

import java.awt.Graphics;
import java.util.Random;

import model.Frame;
import model.Map;
import model.MyUtil;

public class GamePanel extends MyUtil {
	private Random random = new Random();

	private final int WHSIZE = 500;
	private final int SIZE = 10;
	private final int BLOCKSIZE = WHSIZE / SIZE;
	private Map[][] map = new Map[SIZE][SIZE];

	public GamePanel() {
		setLayout(null);
		setBounds(Frame.WIDTH / 2 - WHSIZE / 2, Frame.HEIGHT / 2 - WHSIZE / 2, WHSIZE, WHSIZE);
		setMap();
		setWall();

	}

	private void setMap() {
		int XX = Frame.WIDTH / 2 - WHSIZE / 2;
		int YY = Frame.HEIGHT / 2 - WHSIZE / 2;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Map(XX, YY, BLOCKSIZE, BLOCKSIZE, Map.EMPTY, String.format("images/tile%d.png", 1));
				XX += BLOCKSIZE;
			}
			XX = Frame.WIDTH / 2 - WHSIZE / 2;
			YY += BLOCKSIZE;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Map m = this.map[i][j];
				System.out.println(m.getFilename());
				g.drawImage(m.getImage().getImage(), m.getX(), m.getY(), null);

			}
		}
		repaint();
	}

	private void setWall() {
		int walls = random.nextInt(SIZE) + 1;
		for (int i = 0; i < walls; i++) {
			int wallY = random.nextInt(SIZE);
			int wallX = random.nextInt(SIZE);
			Map m = this.map[wallY][wallX];
			if (m.getStatus() == Map.EMPTY) {
				m.setStatus(Map.WALL);
				m.setFilename(String.format("images/tile%d.png", 2));
			}

		}
	}
}
