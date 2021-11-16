package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.Item;
import models.KioskFrame;

public class TeaPanel extends JPanel implements ActionListener {
	private int WH = 500;

	private JButton btn16s[] = new JButton[16];
	private Item items[] = new Item[16];
	private TablePanel tableData = TablePanel.getInstance();
	private int btn16_WH = 100;
	private int btn16Start_X = 35;
	private int tBtn_H = 30;
	private int count[] = new int[16];

	public TeaPanel() {
		setLayout(null);
		setBounds(KioskFrame.WIDTH / 2 - WH / 2, tBtn_H * 3, WH, WH);
		set16Button();
		setPriceName();
	}

	private void setPriceName() {
		items[0].setPrice(3500);
		items[0].setName("��� ����Ƽ");
		items[1].setPrice(3500);
		items[1].setName("��� ����Ƽ");
		items[2].setPrice(3500);
		items[2].setName("��� �ڸ�Ƽ");
		items[3].setPrice(2500);
		items[3].setName("��׷���");
		items[4].setPrice(2500);
		items[4].setName("��ȭ��");
		items[5].setPrice(2500);
		items[5].setName("��Ʈ����Ƽ");
		items[6].setPrice(2500);
		items[6].setName("�ɸ���");
		items[7].setPrice(2500);
		items[7].setName("���۹�Ʈ");
		items[8].setPrice(3500);
		items[8].setName("û���� ���̵�");
		items[9].setPrice(2500);
		items[9].setName("������");
		items[10].setPrice(2500);
		items[10].setName("���̺���");
		items[11].setPrice(2500);
		items[11].setName("������");
		items[12].setPrice(2500);
		items[12].setName("����Ŀ��");
		items[13].setPrice(3000);
		items[13].setName("������ ���̽�Ƽ");
		items[14].setPrice(3500);
		items[14].setName("��� ���� ���̵�");
		items[15].setPrice(3500);
		items[15].setName("�ڸ� ���̵�");
	}

	private void set16Button() {
		int x = btn16Start_X;
		int y = tBtn_H;
		for (int i = 0; i < btn16s.length; i++) {
			items[i] = new Item(i + 1, x, y, btn16_WH, btn16_WH, 10);
			items[i].setCategory(Item.TEA);
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

	public Item[] getTeaItemsList() {
		return items;
	}

	public int[] getCount() {
		return count;
	}

	public void setCount(int[] count) {
		this.count = count;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		Vector<Vector<String>> tempData = tableData.getData();
		boolean check = false;
		int idx = -1;
		TablePanel tp = TablePanel.getInstance();

		for (int i = 0; i < items.length; i++) {
			if (target == btn16s[i]) {
				if (count[i] < items[i].getRemain()) {
					for (int j = 0; j < tempData.size(); j++) {
						if (items[i].getName().equals(tempData.get(j).get(0))) {
							check = true;
							idx = j;
						}
					}
					if (check) {
						int curCount = Integer.parseInt(tempData.get(idx).get(1));
						curCount++;
						count[i]++;
						int curPrice = Integer.parseInt(tempData.get(idx).get(2));
						curPrice += items[i].getPrice();
						tempData.get(idx).set(1, String.valueOf(curCount));
						tempData.get(idx).set(2, String.valueOf(curPrice));
					} else {
						Vector<String> temp = new Vector<>();
						temp.add(items[i].getName());
						temp.add("1");
						count[i] = 1;
						temp.add(String.valueOf(items[i].getPrice()));
						tp.addData(temp);
					}
					tp.update();
				} else {
					JOptionPane.showMessageDialog(null, "��� �����մϴ�");
				}
			}
		}

	}
}
