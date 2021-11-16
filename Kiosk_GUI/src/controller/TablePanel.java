package controller;

import java.awt.Panel;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.KioskFrame;

public class TablePanel extends Panel {

	private TablePanel() {
		setLayout(null);
		setBounds(KioskFrame.WIDTH / 2 - WH / 2, tBtn_H * 3 + WH, WH, 200);
		setTable();
	}

	private static TablePanel instance = new TablePanel();

	public static TablePanel getInstance() {
		return instance;
	}

	Vector<Vector<String>> data = new Vector<>();
	Vector<String> colName = new Vector<>();
	KioskFrame kf = KioskFrame.getInstance();
	JTable table = null;
	private int WH = 500;
	private int tBtn_H = 30;

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

	public void addData(Vector<String> data) {
		this.data.add(data);
	}
	public void update() {
		kf.updateBottomLabels();
		table.revalidate();
		repaint();
	}

	

	public Vector<Vector<String>> getData() {
		return data;
	}

	public void setData(Vector<Vector<String>> data) {
		this.data = data;
	}

}
