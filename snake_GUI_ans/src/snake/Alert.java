package snake;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Alert extends JFrame{
	private JLabel text = new JLabel();
	
	public Alert() {
		super("Game Clear");
		setBounds(SnakeGame.W/2-150,SnakeGame.H/2-100,300,200);
		text.setBounds(0,0,300,200);
		text.setText("¸ö¿¡ ºÎµúÇô »ç¸Á");
		text.setFont(new Font("",Font.BOLD,20));
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		setVisible(true);
		revalidate();
	}
}
