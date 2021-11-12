package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import models.Frame;
import models.MyUtil;
import models.SignInFrame;
import models.Users;

public class MainPanel extends MyUtil {
	private int WHSIZE = 400;
	private JButton signIn = new JButton("Sign In");
	private JButton login = new JButton("Login");
	private static JLabel dataField = new JLabel();

	private static ArrayList<ArrayList<String>> usersList = new ArrayList<>();

	public MainPanel() {
		setLayout(null);
		setBounds(0, 0, WHSIZE, WHSIZE);
		setBtn();
		setLabel();
		setFocusable(true);
		addKeyListener(this);
	}

	private void setLabel() {
		dataField.setBounds(0, 20, WHSIZE, WHSIZE);
		dataField.setVerticalAlignment(JLabel.TOP);
		dataField.setHorizontalAlignment(JLabel.CENTER);
		add(dataField);
	}

	private void setBtn() {
		this.signIn.setBounds(Frame.WHSIZE / 2 - 100, Frame.WHSIZE - 100, 100, 50);
		this.signIn.addActionListener(this);
		add(signIn);
		this.login.setBounds(Frame.WHSIZE / 2 + 5, Frame.WHSIZE - 100, 100, 50);
		this.login.addActionListener(this);
		add(login);
	}

	private void setSigninPopUp() {
		new SignInFrame();
	}
	
	public static int getUsersSize() {
		return usersList.size();
	}
	
	public static ArrayList<String> getUser(int i) {
		return usersList.get(i);
	}
	
	public static void setUser(ArrayList<String> user) {
		usersList.add(user);
	}
	
	public static void setData(String data) {
		dataField.setText(data);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();

		if (target == signIn) {
			setSigninPopUp();
		} else if (target == login) {
			
			
		}
	}
}
