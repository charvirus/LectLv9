package models;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.MainPanel;

import java.awt.event.*;
import java.util.ArrayList;

public class SignInFrame extends JFrame implements ActionListener {

	public final int WHSIZE = 300;
	JButton btnAdd = new JButton("ADD");
	private JLabel popUpId = new JLabel("ID : ");
	private JLabel popUpPw = new JLabel("PW : ");
	private JLabel popUpName = new JLabel("NAME : ");
	JTextField inputId = new JTextField();
	JTextField inputPw = new JTextField();
	JTextField inputName = new JTextField();

	public SignInFrame() {

		super("SignIn Pop Up");
		setLayout(null);
		setBounds(Frame.W / 2 - WHSIZE / 2, Frame.H / 2 - WHSIZE / 2, WHSIZE, WHSIZE);
		addText();
		addTextFields();
		addButton();
		setVisible(true);
		revalidate();
	}

	private void addText() {
		this.popUpId.setBounds(30, 20, 50, 30);
		this.popUpPw.setBounds(30, 60, 50, 30);
		this.popUpName.setBounds(30, 100, 50, 30);
		add(popUpId);
		add(popUpPw);
		add(popUpName);
	}

	private void addTextFields() {
		this.inputId.setBounds(90, 20, 100, 30);
		this.inputPw.setBounds(90, 60, 100, 30);
		this.inputName.setBounds(90, 100, 100, 30);
		add(inputId);
		add(inputPw);
		add(inputName);
	}

	private void addButton() {
		this.btnAdd.setBounds(WHSIZE / 2 - 50, WHSIZE - 100, 80, 30);
		this.btnAdd.addActionListener(this);
		add(btnAdd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		if (target == btnAdd) {
			String id = inputId.getText();
			String pw = inputPw.getText();
			String name = inputName.getText();
			ArrayList<String> temp = new ArrayList<>();
			temp.add(id);
			temp.add(pw);
			temp.add(name);
			MainPanel.setUser(temp);
			
			String data = "<html>";
			for (int i = 0; i < MainPanel.getUsersSize(); i++) {
				for (int j = 0; j < MainPanel.getUser(i).size(); j++) {
					data += MainPanel.getUser(i).get(j) +" ";
				}
				data += "<br>";
			}
			data += "</html>";
			MainPanel.setData(data);
			this.dispose();
		}
	}

}
