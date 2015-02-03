package spira.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife extends JFrame {
	private static final int COL = 20;
	private static final int ROW = 20;

	private static final long serialVersionUID = 1L;
	private JButton cells[][];
	private Boolean full[][];

	public GameOfLife() {
		setSize(800, 600);
		setTitle("game of life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel container = new JPanel();
		container.setLayout(new GridLayout(COL, ROW));

		Container container2 = getContentPane();
		// BorderLayout layout = new BorderLayout();
		container2.setLayout(new BorderLayout());

		JButton button2 = new JButton("press");
		container2.add(button2, BorderLayout.WEST);
		container2.add(container, BorderLayout.CENTER);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if (button.getBackground() == Color.GREEN) {
					button.setBackground(Color.BLACK);
				} else {
					button.setBackground(Color.GREEN);
				}
			}
		};

		Random random = new Random();

		cells = new JButton[COL][ROW];
		full = new Boolean[COL][ROW];

		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {

				final JButton button = new JButton();
				button.setOpaque(true);
				button.setBorderPainted(false);
				cells[i][j] = button;
				//full[i][j] = false;
				container.add(button);
				button.addActionListener(listener);// interface

				int n = random.nextInt(100);
				if (n < 30) {
					button.setBackground(Color.GREEN);
					full[i][j] = true;

				} else {
					button.setBackground(Color.BLACK);
					full[i][j] = false;

				}

			}
		}

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				nextGeneration();

			}
		});
	}

	public void nextGeneration() {

		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				int neighbors = getNumAliveNeighbors(i, j);
				switch (neighbors) {
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:

					full[i][j] = false;
					break;

				case 2:
				/*	if(cells[i][j].getBackground() == Color.BLACK){
						full[i][j] = false;
					}else{
						full[i][j] = true;
					}*/
					break;
				case 3:
					full[i][j] = true;
				}
			}
		}
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < ROW; j++) {
				if (full[i][j] == true) {
					cells[i][j].setBackground(Color.GREEN);
				} else {
					cells[i][j].setBackground(Color.BLACK);
				}
			}
		}

	}

	public int getNumAliveNeighbors(int i, int j) {
		int numAlive = 0;

		if (isAlive(i - 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i, j - 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i - 1, j)) {
			numAlive++;
		}
		if (isAlive(i + 1, j)) {
			numAlive++;
		}
		if (isAlive(i - 1, j + 1)) {
			numAlive++;
		}
		if (isAlive(i, j + 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j + 1)) {
			numAlive++;
		}

		return numAlive;
	}

	private boolean isAlive(int i, int j) {
		try {
			return cells[i][j].getBackground() == Color.GREEN;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		GameOfLife frame = new GameOfLife();
		frame.setVisible(true);
	}

}
