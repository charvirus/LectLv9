package controller;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.Item;
import models.KioskFrame;

public class CoffeePanel extends JPanel implements ActionListener {

	private int WH = 500;

	private JButton btn16s[] = new JButton[16];
	private Item items[] = new Item[16];
	private TablePanel tableData = TablePanel.getInstance();
	private int btn16_WH = 100;
	private int btn16Start_X = 35;
	private int tBtn_H = 30;

	public CoffeePanel() {
		setLayout(null);
		setBounds(KioskFrame.WIDTH / 2 - WH / 2, tBtn_H * 3, WH, WH);
		set16Button();
		setPriceName();
	}

	private void setPriceName() {
		items[0].setPrice(1500);
		items[0].setName("아메리카노");
		items[1].setPrice(1500);
		items[1].setName("스페셜 아메리카노");
		items[2].setPrice(2000);
		items[2].setName("헤이즐넛 아메리카노");
		items[3].setPrice(2500);
		items[3].setName("유자 아메리카노");
		items[4].setPrice(2500);
		items[4].setName("카푸치노");
		items[5].setPrice(2500);
		items[5].setName("카페라떼");
		items[6].setPrice(3000);
		items[6].setName("헤이즐넛 라떼");
		items[7].setPrice(3000);
		items[7].setName("바닐라 라떼");
		items[8].setPrice(3000);
		items[8].setName("크리미 라떼");
		items[9].setPrice(3500);
		items[9].setName("헤이즐넛 크리미 라떼");
		items[10].setPrice(3500);
		items[10].setName("카페모카");
		items[11].setPrice(3500);
		items[11].setName("카라멜 마끼아또");
		items[12].setPrice(1500);
		items[12].setName("에소프레소");
		items[13].setPrice(2500);
		items[13].setName("더치커피");
		items[14].setPrice(3000);
		items[14].setName("더치 시나몬 라떼");
		items[15].setPrice(3000);
		items[15].setName("더치 코코넛 라떼");
	}

	private void set16Button() {
		int x = btn16Start_X;
		int y = tBtn_H;
		for (int i = 0; i < btn16s.length; i++) {
			items[i] = new Item(i + 1, x, y, btn16_WH, btn16_WH, 10);
			items[i].setCategory(Item.COFFEE);
			btn16s[i] = new JButton(items[i].getImage());
			btn16s[i].setBounds(items[i].getX(), items[i].getY(), items[i].getW(), items[i].getH());
			btn16s[i].addActionListener(this);
			add(btn16s[i]);

			x += btn16_WH + 10;
			if ((i + 1) % 4 == 0) {
				x = btn16Start_X;
				y += btn16_WH + 10;

			}
		}
	}

	public Item[] getCoffeeItemsList() {
		return items;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		Vector<Vector<String>> tempData = tableData.getData();
		boolean check = false;
		int idx = -1;
		for (int i = 0; i < items.length; i++) {
			if (target == btn16s[i]) {

				for (int j = 0; j < tempData.size(); j++) {
					if (items[i].getName().equals(tempData.get(j).get(0))) {
						check = true;
						idx = j;
					}
				}
				
				if (check) {
					int count = Integer.parseInt(tempData.get(idx).get(1));
					count++;
					tempData.get(idx).set(1,String.valueOf(count));
					TablePanel tp = TablePanel.getInstance();
					tp.update();
				} else {
					Vector<String> temp = new Vector<>();
					temp.add(items[i].getName());
					temp.add("1");
					temp.add(String.valueOf(items[i].getPrice()));
					TablePanel tp = TablePanel.getInstance();
					tp.addData(temp);
				}
			}
		}
	}
}
