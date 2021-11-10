package model;

import javax.swing.*;
import java.awt.*;

public class Map {
	public static final int ROAD = 1;
	public static final int WALL = 2;
	public static final int PLAYER = 3;
	public static final int BALL = 4;
	public static final int GOAL = 5;
	public static final int BALL_ENTERED = 6;

	private int state;
	private int x, y, w, h;

	private String fileName;
	private ImageIcon image;

	public Map(int state, int x, int y, int w, int h) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.fileName = String.format("images/tile%d.png", this.state);
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(temp);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		this.fileName = String.format("images/tile%d.png", this.state);
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(temp);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public String getFileName() {
		return fileName;
	}

	public ImageIcon getImage() {
		return image;
	}

}
