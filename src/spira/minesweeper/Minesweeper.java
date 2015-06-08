package spira.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Minesweeper extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int COL = 20;
	private static final int ROW = 20;
	private JButton[][] square;
	private Boolean mines[][];
	private JButton restart;
	private JLabel mineAmount;
	private int amount;
	private ArrayList<JButton> flags;
	private int MINES;
	private ImageIcon flag;
	private ImageIcon mine;

	public Minesweeper() throws MalformedURLException {

		setSize(900, 700);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(COL, ROW));

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 0));

		container.add(panel, BorderLayout.CENTER);
		container.add(panel2, BorderLayout.NORTH);

		restart = new JButton("Restart");
		flags = new ArrayList<JButton>();
		MINES = 40;
		amount = MINES;
		mineAmount = new JLabel("Mine Amount: " + amount, JLabel.CENTER);
		square = new JButton[ROW][COL];
		mines = new Boolean[ROW][COL];

		panel2.add(restart);
		panel2.add(mineAmount);

		flag = new ImageIcon(
				((new ImageIcon("flagIcon.png")).getImage()).getScaledInstance(
						20, 20, java.awt.Image.SCALE_SMOOTH));
		mine = new ImageIcon(
				(((new ImageIcon("bomb.png")).getImage()).getScaledInstance(20,
						20, java.awt.Image.SCALE_SMOOTH)));

		MouseAdapter mouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					JButton theButton = (JButton) e.getSource();
					if (theButton.isEnabled()) {

						if (flags.contains(theButton)) {
							flags.remove(theButton);
							theButton.setIcon(null);
							amount++;
							mineAmount.setText("Mine Amount: " + amount);
						} else {
							theButton.setIcon(flag);
							flags.add(theButton);
							amount--;
							mineAmount.setText("Mine Amount: " + amount);

						}

					}
				}
			}
		};
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource().equals(restart)) {
					restartGame();
				}
				for (int i = 0; i < ROW; i++) {
					for (int j = 0; j < COL; j++) {
						if (event.getSource().equals(square[i][j])) {
							if (mines[i][j]) {
								square[i][j].setIcon(mine);
								gameOver(square[i][j]);
							} else {
								int number = getNumbMinesAround(i, j);
								if (number != 0) {
									square[i][j].setIcon(null);
									square[i][j]
											.setText(String.valueOf(number));
									square[i][j].setEnabled(false);
								} else {
									Stack<JButton> clear = new Stack<JButton>();
									clear.push(square[i][j]);
									emptyOnes(clear);
								}
								wonGame();
							}
						}
					}
				}
			}

		};

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {

				final JButton button = new JButton();
				button.setOpaque(true);
				square[i][j] = button;
				button.addActionListener(listener);// interface
				button.addMouseListener(mouseListener);

				panel.add(square[i][j]);
			}
		}
		fillWithMines();
		restart.addActionListener(listener);
	}

	private void wonGame() {
		boolean won = true;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				int numb = getNumbMinesAround(i, j);
				if (square[i][j].isEnabled() && mines[i][j] == false) {
					won = false;
				} else if (!square[i][j].isEnabled()
						&& square[i][j].getText() != String.valueOf(numb)) {
					won = false;
				}
			}
		}
		if (won) {
			mineAmount.setText("You Won The Game!!!");
		}
	}

	private void emptyOnes(Stack<JButton> clear) {
		if (clear.isEmpty()) {
			return;
		}

		else {
			JButton button = clear.pop();
			for (int i = 0; i < ROW; i++) {
				for (int j = 0; j < COL; j++) {
					square[i][j].setIcon(null);
					if (square[i][j].equals(button)) {
						square[i][j].setEnabled(false);

						// traverse up
						if (i > 0 && square[i - 1][j].isEnabled()) {
							int numb = getNumbMinesAround(i - 1, j);
							if (numb != 0) {
								square[i - 1][j].setText(String.valueOf(numb));
								square[i - 1][j].setEnabled(false);
							} else {
								clear.push(square[i - 1][j]);
							}
						}
						// traverse up left
						if (i > 0 && j > 0 && square[i - 1][j - 1].isEnabled()) {
							int numb = getNumbMinesAround(i - 1, j - 1);
							if (numb != 0) {
								square[i - 1][j - 1].setText(String
										.valueOf(numb));
								square[i - 1][j - 1].setEnabled(false);
							} else {
								clear.push(square[i - 1][j - 1]);
							}
						}
						// traverse up right
						if (i > 0 && j < COL - 1
								&& square[i - 1][j + 1].isEnabled()) {
							int numb = getNumbMinesAround(i - 1, j + 1);
							if (numb != 0) {
								square[i - 1][j + 1].setText(String
										.valueOf(numb));
								square[i - 1][j + 1].setEnabled(false);
							} else {
								clear.push(square[i - 1][j + 1]);
							}
						}
						// traverse down
						if (i < ROW - 1 && square[i + 1][j].isEnabled()) {
							int numb = getNumbMinesAround(i + 1, j);
							if (numb != 0) {
								square[i + 1][j].setText(String.valueOf(numb));
								square[i + 1][j].setEnabled(false);
							} else {
								clear.push(square[i + 1][j]);
							}
						}
						// traverse down right
						if (i < ROW - 1 && j < COL - 1
								&& square[i + 1][j + 1].isEnabled()) {
							int numb = getNumbMinesAround(i + 1, j + 1);
							if (numb != 0) {
								square[i + 1][j + 1].setText(String
										.valueOf(numb));
								square[i + 1][j + 1].setEnabled(false);
							} else {
								clear.push(square[i + 1][j + 1]);
							}
						}
						// traverse down left
						if (i < ROW - 1 && j > 0
								&& square[i + 1][j - 1].isEnabled()) {
							int numb = getNumbMinesAround(i + 1, j - 1);
							if (numb != 0) {
								square[i + 1][j - 1].setText(String
										.valueOf(numb));
								square[i + 1][j - 1].setEnabled(false);
							} else {
								clear.push(square[i + 1][j - 1]);
							}
						}
						// traverse left
						if (j > 0 && square[i][j - 1].isEnabled()) {
							int numb = getNumbMinesAround(i, j - 1);
							if (numb != 0) {
								square[i][j - 1].setText(String.valueOf(numb));
								square[i][j - 1].setEnabled(false);
							} else {
								clear.push(square[i][j - 1]);
							}
						}

						// traverse right
						if (j < COL - 1 && square[i][j + 1].isEnabled()) {
							int numb = getNumbMinesAround(i, j + 1);
							if (numb != 0) {
								square[i][j + 1].setText(String.valueOf(numb));
								square[i][j + 1].setEnabled(false);
							} else {
								clear.push(square[i][j + 1]);
							}
						}
					}
					emptyOnes(clear);
				}
			}
		}

	}

	private void restartGame() {

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {

				square[i][j].setBackground(null);
				square[i][j].setEnabled(true);
				square[i][j].setText("");
				square[i][j].setIcon(null);
				square[i][j].setIcon(null);
			}
		}
		fillWithMines();
		amount = 40;
		mineAmount.setText("Mine Amount:" + amount);
	}

	private void gameOver(JButton button) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {

				if (square[i][j].equals(button)) {
					square[i][j].setBackground(Color.RED);
				} else if (mines[i][j]) {
					square[i][j].setIcon(mine);
				} else {
					int numb = getNumbMinesAround(i, j);
					if (numb != 0) {
						square[i][j].setText(String.valueOf(numb));
					}
					square[i][j].setIcon(null);
					square[i][j].setEnabled(false);
				}

			}
		}

		amount = 0;
		mineAmount.setText("YOU LOST!");
	}

	public void fillWithMines() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				mines[i][j] = false;
			}
		}
		Random random = new Random();
		for (int k = 0; k < MINES; k++) {
			int n = random.nextInt(ROW * COL);
			int y = n % COL;
			int x = Math.round(n / COL);
			if (!mines[x][y]) {
				mines[x][y] = true;
			} else {
				k--;
			}
		}
	}

	public int getNumbMinesAround(int i, int j) {
		int numAlive = 0;

		if (isMine(i - 1, j - 1)) {
			numAlive++;
		}
		if (isMine(i, j - 1)) {
			numAlive++;
		}
		if (isMine(i + 1, j - 1)) {
			numAlive++;
		}
		if (isMine(i - 1, j)) {
			numAlive++;
		}
		if (isMine(i + 1, j)) {
			numAlive++;
		}
		if (isMine(i - 1, j + 1)) {
			numAlive++;
		}
		if (isMine(i, j + 1)) {
			numAlive++;
		}
		if (isMine(i + 1, j + 1)) {
			numAlive++;
		}

		return numAlive;
	}

	private boolean isMine(int i, int j) {
		try {
			return mines[i][j];
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {

		try {
			Minesweeper frame = new Minesweeper();
			frame.setVisible(true);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
