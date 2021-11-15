package controller;

import java.awt.Panel;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.KioskFrame;

public class TablePanel extends Panel {

	private TablePanel() {
		setLayout(null);
		setBounds(KioskFrame.WIDTH / 2 - WH / 2, tBtn_H * 4 + WH, WH, 200);
		setTable();
	}

	private static TablePanel instance = new TablePanel();

	public static TablePanel getInstance() {
		return instance;
	}

	Vector<Vector<String>> data = new Vector<>();
	Vector<String> colName = new Vector<>();

	JTable table = null;
	private int WH = 500;
	private int tBtn_H = 30;

	private void setTable() {
		colName.add("Name");
		colName.add("Price");
		table = new JTable(data, colName);
		table.setBounds(0, 0, WH, 200);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 0, WH, 200);
		js.setAutoscrolls(true);
		add(js);
	}

	public void addData(Vector<String> data) {
		this.data.add(data);
		table.revalidate();
		repaint();
		System.out.println(this.data.size());
	}

}
