package models;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Item {
	public static final int COFFEE = 0;
	public static final int TEA = 1;
	private int num;
	private int x, y, w, h;
	private int category; // 0 coffee 1 tea

	private String filename;
	private ImageIcon image;

	public Item(int num, int x, int y, int w, int h, int category, String filename) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.category = category;
		if(category == COFFEE) {
			if(num < 10) {
				this.filename = String.format("images/coffee0%d.png", num);				
			}else {
				this.filename = String.format("images/coffee%d.png", num);	
			}
		}else if(category == TEA) {
			if(num < 10) {
				this.filename = String.format("images/tea0%d.png", num);				
			}else {
				this.filename = String.format("images/tea%d.png", num);	
			}
		}
		Image temp = new ImageIcon(this.filename).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);;
		this.image = new ImageIcon(temp);
	}

	public int getNum() {
		return num;
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

	public int getCategory() {
		return category;
	}

	public String getFilename() {
		return filename;
	}

	public ImageIcon getImage() {
		return image;
	}

}
