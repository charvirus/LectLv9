package controller;

import javax.swing.*;

import horse.Frame;
import horse.Horse;
import horse.MyUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RacingPanel extends MyUtil implements Runnable {
	private Random random = new Random();
	private int ms;
	private boolean isRun;
	private JLabel timer = new JLabel();

	private JButton reset = new JButton("Start");
	private final int SIZE = 5;
	private Horse[] horses = new Horse[SIZE];

	private int rank;

	public RacingPanel() {
		setLayout(null);
		setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
		setTimer();
		setButton();
		setGame();
	}

	private void setGame() {
		this.rank = 1;
		this.ms = 0;
		int x = 30;
		int y = 120;

		for (int i = 0; i < SIZE; i++) {
			this.horses[i] = new Horse(i + 1, x, y, 120, 90, String.format("images/horse%d.png", i + 1));
			y += 100;
		}
	}

	private void setButton() {
		this.reset.setBounds(30, 50, 100, 50);
		this.reset.addActionListener(this);
		add(reset);
	}

	private void setTimer() {
		timer.setBounds(135, 50, 100, 50);
		add(timer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();

			if (target == this.reset) {
				this.isRun = !this.isRun;
				this.reset.setText(this.isRun ? "Reset" : "Start");

				if (!this.isRun) {
					this.timer.setText("Ready");
					setGame();
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// horses
		g.drawLine(30, 120, Frame.WIDTH - 30, 120);
		for (int i = 0; i < SIZE; i++) {
			Horse h = this.horses[i];
			g.drawImage(h.getImage().getImage(), h.getX(), h.getY(), null);
			g.drawLine(30, h.getY() + h.getH(), Frame.WIDTH - 30, h.getY() + h.getH());
			// rank
			if (h.getState() == h.GOAL) {
				g.setFont(new Font("", Font.PLAIN, 20));
				g.drawString(h.getRecord(), Frame.WIDTH - 200, h.getY() + h.getH() / 2);
				g.setFont(new Font("", Font.BOLD, 20));
				g.drawString(h.getRank() + "등", Frame.WIDTH - 200 - 20, h.getY() + h.getH() / 2);

			}
		}
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		update();

		repaint();
	}

	private void update() {

		boolean goal = false;

		if (this.isRun) {
			// run
			for (int i = 0; i < SIZE; i++) {
				Horse h = this.horses[i];

				int jump = random.nextInt(10) * 3+3;
				int tempX = h.getX() + jump;

				if (h.getState() == h.RUN) {

					if (tempX >= Frame.WIDTH - h.getW() - 30 && !goal) {
						h.setState(h.GOAL);
						h.setRecord(String.format("%4d.%3d", this.ms / 1000, this.ms % 1000));
						h.setRank(rank);
						this.rank++;
						goal = !goal;
					} else if (tempX >= Frame.WIDTH - h.getW() - 30) {
						i--;
						// 되돌아가는 말이 발생할 경우 -> ms 홀딩하는 변수를 활용
						
						break;
					}

					h.setX(tempX);
				}

			}
		}
	}

	@Override
	public void run() {
		while (true) {
			if (isRun) {
				this.ms++;
				this.timer.setText(String.format("%3d.%3d", this.ms / 1000, this.ms % 1000));
			}

			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
