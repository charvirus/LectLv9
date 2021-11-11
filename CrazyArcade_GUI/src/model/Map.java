package model;

import javax.swing.ImageIcon;

public class Map {
	public static final int PLAYER = 1;
	public static final int BOMB = 2;
	public static final int WALL = 3;
	public static final int ROAD = 4;
	public static final int ITEM = 5;
	private int state;
	private int x, y, w, h;
	private String names[] = {"bazzi","bomb","brick","grass","item"};
	private String fileName;
	private ImageIcon image;
	
	
	public Map(int state, int x, int y, int w, int h) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
