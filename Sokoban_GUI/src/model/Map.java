package model;

import javax.swing.*;
import java.awt.*;

public class Map {
	public static final int PLAYER = 2;
	public static final int BALL = 3;
	public static final int GOAL = 7;
	public static final int WALL = 9;
	public static final int EMPTY = 0;
	private int x, y, w, h;
	private int status = EMPTY;
	private String filename;
	private ImageIcon image;

	public Map(int x, int y, int w, int h, int status, String filename) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.status = status;
		this.filename = filename;
		this.image = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
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

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ImageIcon getImage() {
		return image;
	}

	
	
	
}
