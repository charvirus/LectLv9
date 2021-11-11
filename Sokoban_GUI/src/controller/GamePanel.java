package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;

import model.Frame;
import model.Map;
import model.MyUtil;

public class GamePanel extends MyUtil {
	private Random random = new Random();
	private boolean check = false;
	private boolean boxNear = false;
	private int boxNum = -1;
	private int ballCnt = 0;
	private int checkCnt = 0;
	private final int WHSIZE = 500;
	private final int SIZE = 10;
	private final int BLOCKSIZE = WHSIZE / SIZE;
	private Map[][] map = new Map[SIZE][SIZE];
	private int ball[][] = null;
	private int goal[][] = null;
	private int pY, pX;
	private int dir = 0; // 1 위(38) 2 왼쪽(37) 3 아래(40) 4 오른쪽(39)

	public GamePanel() {
		setLayout(null);
		setBounds(Frame.WIDTH / 2 - WHSIZE / 2, Frame.HEIGHT / 2 - WHSIZE / 2, WHSIZE, WHSIZE);
		setMap();
		setBallCnt();
		setWall();
		setBall();
		setGoal();
		setPlayer();
		setFocusable(true);
		addKeyListener(this);
	}

	private void setBallCnt() {

		ballCnt = Integer.parseInt(JOptionPane.showInputDialog("박스의 수를 입력해주세요"));

	}

	private void setMap() {
		int XX = Frame.WIDTH / 2 - WHSIZE / 2;
		int YY = Frame.HEIGHT / 2 - WHSIZE / 2;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Map(Map.ROAD, XX, YY, BLOCKSIZE, BLOCKSIZE);
				XX += BLOCKSIZE;
			}
			XX = Frame.WIDTH / 2 - WHSIZE / 2;
			YY += BLOCKSIZE;
		}
	}

	private void setWall() {
		int walls = random.nextInt(SIZE - 3) + 3;
		for (int i = 0; i < walls; i++) {
			int wallY = random.nextInt(SIZE);
			int wallX = random.nextInt(SIZE);
			Map m = this.map[wallY][wallX];
			if (m.getState() == Map.ROAD) {
				m.setState(Map.WALL);

			} else {
				i--;
			}

		}
	}

	private void setGoal() {
		goal = new int[ballCnt][2];

		for (int j = 0; j < ballCnt; j++) {

			while (true) {
				int y = random.nextInt(SIZE);
				int x = random.nextInt(SIZE);

				if (map[y][x].getState() == Map.ROAD) {
					map[y][x].setState(Map.GOAL);
					goal[j][0] = y;
					goal[j][1] = x;

					break;
				}
			}
		}
	}

	private void setBall() {
		ball = new int[ballCnt][2];
		for (int j = 0; j < ballCnt; j++) {
			while (true) {
				int y = random.nextInt(SIZE - 2) + 1;
				int x = random.nextInt(SIZE - 2) + 1;
				if (map[y][x].getState() == Map.ROAD) {
					int check = 0;
					for (int i = -1; i <= 1; i++) {
						if (y + i >= 0 && y + i < SIZE && map[y + i][x].getState() != Map.ROAD) {
							check++;
						}
						if (x + i >= 0 && x + i < SIZE && map[y][x + i].getState() != Map.ROAD) {
							check++;
						}
					}
					if (check <= 1) {
						this.map[y][x].setState(Map.BALL);
						ball[j][0] = y;
						ball[j][1] = x;
						break;
					}
				}
			}
		}
	}

	private void setPlayer() {
		while (true) {
			int y = random.nextInt(SIZE);
			int x = random.nextInt(SIZE);
			if (map[y][x].getState() == Map.ROAD) {
				pY = y;
				pX = x;
				map[y][x].setState(Map.PLAYER);
				break;
			}

		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Map m = this.map[i][j];
				g.drawImage(m.getImage().getImage(), m.getX(), m.getY(), null);
			}
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 40) {
			this.dir = 1;
		} else if (e.getKeyCode() == 37) {
			this.dir = 2;
		} else if (e.getKeyCode() == 38) {
			this.dir = 3;
		} else if (e.getKeyCode() == 39) {
			this.dir = 4;
		}
		move();
	}

	private void move() {
		check = false;
		int yy = pY;
		int xx = pX;
		int byy = -1;
		int bxx = -1;
		// 1 위(38) 2 왼쪽(37) 3 아래(40) 4 오른쪽(39)
		if (dir == 1) {
			if (0 <= yy && yy < SIZE - 1) {
				yy++;
			}
		} else if (dir == 2) {
			if (0 < xx && xx < SIZE) {
				xx--;
			}
		} else if (dir == 3) {
			if (0 < yy && yy < SIZE) {
				yy--;
			}
		} else if (dir == 4) {
			if (0 <= xx && xx < SIZE - 1) {
				xx++;
			}
		}

		if (yy < 0 || yy > SIZE - 1 || xx < 0 || xx > SIZE - 1 || map[yy][xx].getState() == Map.WALL) {
			check = true;
		}
		for (int i = 0; i < ballCnt; i++) {
			if (yy == ball[i][0] && xx == ball[i][1]) {
				boxNear = true;
				boxNum = i;
			}
		}
		if (boxNear) {
			byy = ball[boxNum][0];
			bxx = ball[boxNum][1];
			if (dir == 1) {	
				byy++;
			} else if (dir == 2) {
				bxx--;
			} else if (dir == 3) {
				byy--;
			} else if (dir == 4) {
				bxx++;
			}
		}
		if (this.map[yy][xx].getState() == Map.BALL) {
			if (byy < 0 || byy > SIZE - 1 || bxx < 0 || bxx > SIZE - 1 || map[byy][bxx].getState() == Map.WALL) {
				check = true;
			} else if (map[byy][bxx].getState() == Map.GOAL) {
				map[pY][pX].setState(Map.ROAD);
				pY = yy;
				pX = xx;
				map[pY][pX].setState(Map.PLAYER);
				map[byy][bxx].setState(Map.BALL_ENTERED);
				check = true;
				checkCnt++;
				ball[boxNum][0] = byy;
				ball[boxNum][1] = bxx;

			}
			if (!check) {
				map[byy][bxx].setState(Map.BALL);
				ball[boxNum][0] = byy;
				ball[boxNum][1] = bxx;
			}
		} else if (this.map[yy][xx].getState() == Map.BALL_ENTERED) {
			if (byy < 0 || byy > SIZE - 1 || bxx < 0 || bxx > SIZE - 1 || map[byy][bxx].getState() == Map.WALL) {
				check = true;
			} else if (map[byy][bxx].getState() == Map.ROAD) {
				map[pY][pX].setState(Map.ROAD);
				pY = yy;
				pX = xx;
				map[pY][pX].setState(Map.PLAYER);
				map[byy][bxx].setState(Map.BALL);
				checkCnt--;
			}
			if (!check) {
				map[byy][bxx].setState(Map.BALL);
				ball[boxNum][0] = byy;
				ball[boxNum][1] = bxx;
			}
		}

		if (!check) {
			map[pY][pX].setState(Map.ROAD);
			for (int i = 0; i < ballCnt; i++) {
				if (map[goal[i][0]][goal[i][1]].getState() == Map.ROAD) {
					map[goal[i][0]][goal[i][1]].setState(Map.GOAL);
				}
			}
			pY = yy;
			pX = xx;
			map[pY][pX].setState(Map.PLAYER);
		}
		if (checkCnt == ballCnt) {

			JOptionPane.showMessageDialog(null, "Goal");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = 0;
	}

}
