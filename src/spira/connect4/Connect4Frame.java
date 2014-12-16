package spira.connect4;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Connect4Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public Connect4Frame() {
		setSize(400, 300);
		setTitle("Connect4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new GridLayout(6, 7));
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if (button.getBackground().equals(Color.PINK)) {
					button.setBackground(Color.YELLOW);
				} else {
					button.setBackground(Color.PINK);

				}
			}
		};
		for (int i = 0; i < 6 * 7; i++) {
			final JButton button = new JButton();
			container.add(button);
			button.addActionListener(listener);// interface
		}
	}

	public static void main(String[] args) {
		Connect4Frame frame = new Connect4Frame();
		frame.setVisible(true);
	}
}
