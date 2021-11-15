package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.CoffeePanel;
import controller.TablePanel;
import controller.TeaPanel;

public class KioskFrame extends JFrame implements ActionListener {

	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int W = dm.width;
	public static final int H = dm.height;

	public static final int WIDTH = 600;
	public static final int HEIGHT = 1000;

	private CoffeePanel coffeePanel = new CoffeePanel();
	private TeaPanel teaPanel = new TeaPanel();
	private TablePanel tablePanel = TablePanel.getInstance();
	private JButton coffee_Btn = new JButton("커피");
	private JButton tea_Btn = new JButton("차/에이드");
	private int tBtn_W = 85;
	private int tBtn_H = 30;

	public KioskFrame() {
		super("Kiosk");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setContentPane(); Frame 자체를 바꾸는데 효율적
		setTopButton();
		add(coffeePanel);
		add(tablePanel);
		setVisible(true);
		revalidate();
	}

	private void setTopButton() {
		coffee_Btn.setBounds(WIDTH / 2 - tBtn_W - 2, tBtn_H, tBtn_W, tBtn_H);
		coffee_Btn.addActionListener(this);
		add(coffee_Btn, 0);
		tea_Btn.setBounds(WIDTH / 2 + 2, tBtn_H, tBtn_W, tBtn_H);
		tea_Btn.addActionListener(this);
		add(tea_Btn, 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == coffee_Btn) {
			remove(teaPanel);
			add(coffeePanel);
			revalidate();
			repaint();
		} else if (e.getSource() == tea_Btn) {
			remove(coffeePanel);
			add(teaPanel);
			revalidate();
			repaint();
		}
	}

}
