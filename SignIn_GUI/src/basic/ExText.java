package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

class JoinFrame extends JFrame {

	JLabel idLabel = new JLabel("id");
	JLabel pwLabel = new JLabel("pw");
	JLabel nameLabel = new JLabel("name");
	JTextField idField = new JTextField();
	JTextField pwField = new JTextField();
	JTextField nameField = new JTextField();

	public JoinFrame() {
		setLayout(null);
		setBounds(200, 200, 200, 200);

		setLabel();

		setVisible(true);
		revalidate();
	}

	private void setLabel() {
		idLabel.setBounds(10, 20, 30, 20);
		idLabel.setText("id : ");
		add(idLabel);

		this.idField.setBounds(50, 20, 100, 20);
		this.idField.setText("id");
		add(this.idField);

		pwLabel.setBounds(10, 50, 30, 20);
		pwLabel.setText("pw : ");
		add(pwLabel);

		this.pwField.setBounds(50, 50, 100, 20);
		this.pwField.setText("pw");
		add(this.pwField);

		nameLabel.setBounds(10, 80, 30, 20);
		nameLabel.setText("name : ");
		add(nameLabel);

		this.nameField.setBounds(50, 80, 100, 20);
		this.nameField.setText("name");
		add(this.nameField);
	}
}

public class ExText extends JFrame implements ActionListener, KeyListener, MouseListener {

	JLabel dataField = new JLabel(String.format("<html>%s<br>%d</html>", "test", 123));

	JTextField text = new JTextField();
	JTextArea text2 = new JTextArea();

	JButton login = new JButton("login");
	JButton join = new JButton("join");

	Vector<Vector<String>> users = new Vector<>();
	Vector<String> colName = new Vector<>();
	JTable table = null;

	JoinFrame joinFrame = null;

	public ExText() {
		setLayout(null);
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 텍스트 박스의 처리
		// JTextField ( 줄바꿈이 안됨 . )
//		this.text.setBounds(20,40,300,50);
//		text.addKeyListener(this);
//		add(this.text);
//		
//		// JTextArea ( 줄바꿈이 가능 . )
//		this.text2.setBounds(20,100,300,50);
//		text2.addKeyListener(this);
//		add(this.text2);
		setButton();
		// setDataField();
		setTable();

		setVisible(true);
		revalidate();

	}

	private void setTable() {
		// JTable 생성자 만드는법
		// JTable(Vector<?> , Vector<?>)
		// 1) 실데이터
		// 2) 컬럼이름

		this.colName.add("id");
		this.colName.add("pw");
		this.colName.add("name");

		this.table = new JTable(users, colName);
		this.table.setBounds(20, 20, 460, 430);
		this.table.setGridColor(Color.black);
		this.table.setBorder(new LineBorder(Color.red));

		this.table.setCellEditor(null);
		this.table.setDragEnabled(true);
		this.table.setCellSelectionEnabled(true);
//		add(this.table);

		JScrollPane js = new JScrollPane(table);
		js.setBounds(20, 20, 460, 430);
		js.setAutoscrolls(true); // 필요 시에 -> 스크롤바가 등장
		add(js);
	}

	private void setDataField() {
		this.dataField.setBounds(0, 0, 500, 500);
		this.dataField.setVerticalAlignment(JLabel.TOP);
		add(this.dataField);
	}

	private void setButton() {
		this.join.setBounds(500 / 2 - 100 / 2, 400, 100, 40);
		this.join.addActionListener(this);
		add(this.join, 0);
	}

	public static void main(String[] args) {

		new ExText();

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER) {
			// 검증 후 회원가입 처리
			String tempId = this.joinFrame.idField.getText();
			String tempPw = this.joinFrame.pwField.getText();
			String tempName = this.joinFrame.nameField.getText();
			String id = tempId.equals("") || tempId.equals("id") ? null : tempId;
			String pw = tempPw.equals("") || tempPw.equals("pw") ? null : tempPw;
			String name = tempName.equals("") || tempName.equals("name") ? null : tempName;

			if (id != null && pw != null && name != null) {
				addUser(id, pw, name);
				JOptionPane.showMessageDialog(null, "회원가입 성공 ! ");
				this.joinFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "회원정보를 다시 확인하세요.");
			}
		}
	}

	private void addUser(String id, String pw, String name) {
		// Vector<String> : User
		Vector<String> user = new Vector<>();
		user.add(id);
		user.add(pw);
		user.add(name);
		this.users.add(user);

		revalidate();
		repaint();

		// 1 . JLabel로 데이터 전체 출력
		// updateDataField();
	}

	// 2. JPanel 위에서 -> JComponent 의 paintComponent() 오버라이딩 ->
	// g.drawString() 메소드를 통해 데이터 전체 출력 .

	/*
	 * 전역 멤버 int startX = 30; int startY = 50;
	 * 
	 * public void paintComonent(Grahic g) {
	 * 
	 * int x = startX; int y = startY; for(int i=0; i<this.users.size(); i++) {
	 * Vector<String> u = this.users.get(i);
	 * g.drawString(String.format("%s/%s(%s)",u.get(0),u.get(1),u.get(2), x , y); y
	 * += 13; }
	 * 
	 * }
	 */

	private void updateDataField() {
		String data = "<html>";
		for (int i = 0; i < this.users.size(); i++) {
			data += this.users.get(i).get(0) + "/";
			data += this.users.get(i).get(1) + "(";
			data += this.users.get(i).get(2) + ")<br>";
		}
		data += "</html>";

		this.dataField.setText(data);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.join) {
//			new JoinFrame();
			this.joinFrame = new JoinFrame();
			this.joinFrame.idField.addKeyListener(this);
			this.joinFrame.idField.addMouseListener(this);
			this.joinFrame.pwField.addKeyListener(this);
			this.joinFrame.pwField.addMouseListener(this);
			this.joinFrame.nameField.addKeyListener(this);
			this.joinFrame.nameField.addMouseListener(this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this.joinFrame.idField) {
			this.joinFrame.idField.setText("");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}