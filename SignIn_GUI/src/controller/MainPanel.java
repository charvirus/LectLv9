package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import models.Frame;
import models.MyUtil;
import models.SignInFrame;

public class MainPanel extends MyUtil {
	private String fileName = "../SignIn_GUI.txt";
	private SignInFrame sif = null;
	private int cnt = 0;
	private int WHSIZE = 400;
	private JButton signIn = new JButton("Sign In");
	private JButton login = new JButton("Login");
	public static JButton add = new JButton("Add");
	private static JLabel dataField = new JLabel();

	private static Vector<Vector<String>> usersList = new Vector<>();
	private Vector<String> colname = new Vector<>();
	JTable table = null;
	
	
	public MainPanel() {
		setLayout(null);
		setBounds(0, 0, WHSIZE, WHSIZE);
		setBtn();
	//	setLabel();
		setTable();
		// readFile();
		setFocusable(true);
		addKeyListener(this);
	}

	private void setTable() {
		// JTable(Vector<?>,Vector<?>)
		// 1) 실데이터
		// 2) 컬럼이름
		this.colname.add("id");
		this.colname.add("pw");
		this.colname.add("name");
		
		this.table = new JTable(usersList,colname);
		this.table.setBounds(20,20,430,430);
		this.table.setGridColor(Color.black);
		this.table.setBorder(new LineBorder(Color.red));
		this.table.setCellEditor(null);
		add(this.table);
		
	}

	private void readFile() {
		File file = new File(fileName);
		if (file.exists()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String data = br.readLine();
				while (data != null) {
					cnt++;
					data += br.readLine();
				}
				fr.close();
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String data = br.readLine();
				while (data != null) {
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void setLabel() {
		dataField.setBounds(0, 20, WHSIZE, WHSIZE);
		dataField.setVerticalAlignment(JLabel.TOP);
		dataField.setHorizontalAlignment(JLabel.CENTER);
		add(dataField);
	}

	private void setBtn() {
		this.signIn.setBounds(Frame.WHSIZE / 2 - 100, Frame.WHSIZE - 100, 100, 30);
		this.signIn.addActionListener(this);
		add(signIn,0);
		this.login.setBounds(Frame.WHSIZE / 2 + 5, Frame.WHSIZE - 100, 100, 30);
		this.login.addActionListener(this);
		add(login,0);
	}

	private void setSigninPopUp() {
		sif = new SignInFrame();
	}

	public static int getUsersSize() {
		return usersList.size();
	}

	public static Vector<String> getUser(int i) {
		return usersList.get(i);
	}

	public static void setUser(Vector<String> user) {
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

		} else if(target == add) {
			String id = sif.inputId.getText();
			String pw = sif.inputPw.getText();
			String name = sif.inputName.getText();
			if (!id.equals("") && !pw.equals("") && !name.equals("")) {
				sif.adduser(id,pw,name);
				String data = "<html>";
				for (int i = 0; i < MainPanel.getUsersSize(); i++) {
					for (int j = 0; j < MainPanel.getUser(i).size(); j++) {
						data += MainPanel.getUser(i).get(j);
						if (j != MainPanel.getUser(i).size() - 1) {
							data += " ";
						}
					}
					data += "<br>";
				}
				data += "</html>";
				MainPanel.setData(data);
				sif.dispose();
				revalidate();
				repaint();
			} else {
				JOptionPane.showMessageDialog(null, "회원정보를 다시 확인해주세요");
			}
		}
	}
}
