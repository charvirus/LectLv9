package controller;

import models.MyUtil;

public class KioskPanel extends MyUtil {
	public KioskPanel() {
		setLayout(null);
		setBounds(50, 50, 500, 700);
		setButton();
		setFocusable(true);
		addKeyListener(this);
	}

	private void setButton() {
		
	}
}
