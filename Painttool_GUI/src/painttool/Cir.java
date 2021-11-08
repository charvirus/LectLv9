package painttool;

import java.awt.Color;

public class Cir {
	private int x, y, width, height, roundX, roundY;
	private Color c;

	public Cir(int x, int y, int width, int height, int roundX, int roundY, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.roundX = roundX;
		this.roundY = roundY;
		this.c = c;
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

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getRoundX() {
		return roundX;
	}

	public void setRoundX(int roundX) {
		this.roundX = roundX;
	}

	public int getRoundY() {
		return roundY;
	}

	public void setRoundY(int roundY) {
		this.roundY = roundY;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

}
