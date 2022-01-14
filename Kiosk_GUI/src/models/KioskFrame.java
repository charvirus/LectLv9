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
import javax.swing.JOptionPane;

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
	private static KioskFrame instance = new KioskFrame(); // ???????????????????????????????

	public static KioskFrame getInstance() {
		return new KioskFrame();
	}

	public KioskFrame() {
		super("Kiosk");
		setLayout(null);
		setBounds(W / 2 - WIDTH / 2, H / 2 - HEIGHT / 2, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setContentPane(); Frame 자체를 바꾸는데 효율적
		setTopButton();
		setPurchaseButton();
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
		purchase_Btn.setBounds(KioskFrame.WIDTH / 2 - tBtn_W / 2, 880, tBtn_W, tBtn_H);
		purchase_Btn.addActionListener(this);
		add(purchase_Btn, 0);
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
			Vector<Vector<String>> datas = tablePanel.getData();
			Item coffeeItems[] = coffeePanel.getCoffeeItemsList();
			Item teaItems[] = teaPanel.getTeaItemsList();
			int resetCountArr[] = new int[16];
			for (int i = 0; i < coffeeItems.length; i++) {
				for (int j = 0; j < datas.size(); j++) {
					if (coffeeItems[i].getCategory() == Integer.parseInt(datas.get(j).get(4))
							&& coffeeItems[i].getNum() == Integer.parseInt(datas.get(j).get(3))) {
						coffeeItems[i].setRemain(coffeeItems[i].getRemain()
								- Integer.parseInt(datas.get(j).get(1)));
					}
				}
			}
			
			coffeePanel.setCount(resetCountArr);
			
			for (int i = 0; i < teaItems.length; i++) {
				for (int j = 0; j < datas.size(); j++) {
					if (teaItems[i].getCategory() == Integer.parseInt(datas.get(j).get(4))
							&& teaItems[i].getNum() == Integer.parseInt(datas.get(j).get(3))) {
						teaItems[i].setRemain(teaItems[i].getRemain()
								- Integer.parseInt(datas.get(j).get(1)));
					}
				}
			}
			teaPanel.setCount(resetCountArr);
			
			tablePanel.resetTable();
			JOptionPane.showMessageDialog(null, String.format("%d원을 결제하셨습니다.",tablePanel.getTotalPrice_i()));
			tablePanel.revalidate();
			tablePanel.repaint();
		}
	}

}
