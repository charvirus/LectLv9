package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private JButton purchase_Btn = new JButton("결제");
	private int tBtn_W = 85;
	private int tBtn_H = 30;
	private JLabel totalCountText = new JLabel("주문 수량");
	private JLabel totalPriceText = new JLabel("총 가격");
	private JLabel totalCount = new JLabel("0");
	private JLabel totalPrice = new JLabel("0");
	private int bottomLabel_CntX = 200;
	private int bottomLabel_CntY = 800;

	private static KioskFrame instance = new KioskFrame();

	public static KioskFrame getInstance() {
		return instance;
	}

	public KioskFrame() {
		super("Kiosk");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setContentPane(); Frame 자체를 바꾸는데 효율적
		setTopButton();
		setPurchaseButton();
		setBottomLabel();
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

	private void setPurchaseButton() {
		purchase_Btn.setBounds(KioskFrame.WIDTH / 2 - tBtn_W / 2, 910, tBtn_W, tBtn_H);
		purchase_Btn.addActionListener(this);
		add(purchase_Btn, 0);
	}

	private void setBottomLabel() {
		totalCountText.setBounds(KioskFrame.WIDTH / 2 - bottomLabel_CntX, bottomLabel_CntY, 150, 40);
		totalCountText.setFont(new Font("", Font.BOLD, 20));
		add(totalCountText, 0);
		totalCount.setBounds(KioskFrame.WIDTH / 2 - 75, bottomLabel_CntY, 150, 40);
		totalCount.setFont(new Font("", Font.BOLD, 20));
		add(totalCount, 0);

		totalPriceText.setBounds(KioskFrame.WIDTH / 2, bottomLabel_CntY, 150, 40);
		totalPriceText.setFont(new Font("", Font.BOLD, 20));
		add(totalPriceText, 0);
		totalPrice.setBounds(KioskFrame.WIDTH / 2 + 175, bottomLabel_CntY, 150, 40);
		totalPrice.setFont(new Font("", Font.BOLD, 20));
		add(totalPrice, 0);
	}

	public void updateBottomLabels() {
		TablePanel tp = TablePanel.getInstance();
		Vector<Vector<String>> data = tp.getData();
		System.out.println(data.size());
		int totalPrice = 0;
		int totalCount = 0;
		totalCount = data.size();
		for(int i = 0;i<data.size();i++) {
			totalPrice += Integer.parseInt(data.get(i).get(2));
		}
		totalCountText.setText(String.valueOf(totalCount));
		totalPriceText.setText(String.valueOf(totalPrice));
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
		} else if (e.getSource() == purchase_Btn) {

		}
	}

}
