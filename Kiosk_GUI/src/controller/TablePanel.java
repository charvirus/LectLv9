package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.KioskFrame;

public class TablePanel extends Panel {

	private TablePanel() {
		setLayout(null);
		setBounds(KioskFrame.WIDTH / 2 - WH / 2, tBtn_H * 3 + WH, WH, 300);
		setTable();
		setBottomLabel();
	}

	private static TablePanel instance = new TablePanel();

	public static TablePanel getInstance() {
		return instance;
	}

	Vector<Vector<String>> data = new Vector<>();
	Vector<String> colName = new Vector<>();
	KioskFrame kf = null;
	JTable table = null;
	private int WH = 500;
	private int tBtn_H = 30;
	private JLabel totalCountText = new JLabel("주문 수량");
	private JLabel totalPriceText = new JLabel("총 가격");
	private JLabel totalCount = new JLabel();
	private JLabel totalPrice = new JLabel();

	private int bottomLabel_CntX = 100;
	private int bottomLabel_CntY = 200;

	private int totalcount_i = 0;
	private int totalPrice_i = 0;

	private void setBottomLabel() {
		totalCountText.setBounds(0, bottomLabel_CntY, 150, 40);
		totalCountText.setFont(new Font("", Font.BOLD, 20));
		add(totalCountText, 0);

		totalCount.setBounds(bottomLabel_CntX, bottomLabel_CntY, 150, 40);
		totalCount.setFont(new Font("", Font.BOLD, 20));
		totalCount.setText("0");
		add(totalCount, 0);

		totalPriceText.setBounds(WH / 2, bottomLabel_CntY, 150, 40);
		totalPriceText.setFont(new Font("", Font.BOLD, 20));
		add(totalPriceText, 0);

		totalPrice.setBounds(WH / 2 + 150, bottomLabel_CntY, 150, 40);
		totalPrice.setFont(new Font("", Font.BOLD, 20));
		totalPrice.setText("0");
		add(totalPrice, 0);
	}

	private void setTable() {
		colName.add("Name");
		colName.add("Count");
		colName.add("Price");
		table = new JTable(data, colName);
		table.setBounds(0, 0, WH, 200);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 0, WH, 200);
		js.setAutoscrolls(true);
		add(js);
	}

	private void updateBottomLabels() {
		TablePanel tp = TablePanel.getInstance();
		Vector<Vector<String>> data = tp.getData();

		totalcount_i = data.size();
		for (int i = 0; i < data.size(); i++) {
			totalPrice_i += Integer.parseInt(data.get(i).get(2));
		}

		this.totalCount.setText(totalcount_i + "");
		this.totalCount.revalidate();
		this.totalCount.repaint();

		this.totalPrice.setText(totalPrice_i + "");
		this.totalPrice.revalidate();
		this.totalPrice.repaint();

		this.revalidate();
		this.repaint();
	}

	public void update() {
		updateBottomLabels();
		table.revalidate();
		table.repaint();
	}

	public void resetTable() {
		data.clear();
	}

	public void addData(Vector<String> data) {
		this.data.add(data);

	}

	public Vector<Vector<String>> getData() {
		return data;
	}

	public void setData(Vector<Vector<String>> data) {
		this.data = data;
	}

	public int getTotalcount_i() {
		return totalcount_i;
	}

	public int getTotalPrice_i() {
		return totalPrice_i;
	}

}
