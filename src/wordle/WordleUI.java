package wordle;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordleUI extends JFrame {
	private Map<Character, Tile> tileMap;
	private Wordle wordle;
	private JPanel mainBoard;
	private JLabel[][] spaces; // md array of spaces

	public WordleUI() { // constructor
		wordle = new Wordle("route");
		createTileMap();
		createComponents();
	}
	
	private void createTileMap() {
		tileMap = new HashMap<>();
		tileMap.put(' ', Tile.BLANK);
		tileMap.put('a', Tile.A);
		tileMap.put('b', Tile.B);
		tileMap.put('c', Tile.C);
		tileMap.put('d', Tile.D);
		tileMap.put('e', Tile.E);
		tileMap.put('f', Tile.F);
		tileMap.put('g', Tile.G);
		tileMap.put('h', Tile.H);
		tileMap.put('i', Tile.I);
		tileMap.put('j', Tile.J);
		tileMap.put('k', Tile.K);
		tileMap.put('l', Tile.L);
		tileMap.put('m', Tile.M);
		tileMap.put('n', Tile.N);
		tileMap.put('o', Tile.O);
		tileMap.put('p', Tile.P);
		tileMap.put('q', Tile.Q);
		tileMap.put('r', Tile.R);
		tileMap.put('s', Tile.S);
		tileMap.put('t', Tile.T);
		tileMap.put('u', Tile.U);
		tileMap.put('v', Tile.V);
		tileMap.put('w', Tile.W);
		tileMap.put('x', Tile.X);
		tileMap.put('y', Tile.Y);
		tileMap.put('z', Tile.Z);
	}
	
	private void createComponents() {
		mainBoard = new JPanel();
		mainBoard.setLayout(new GridLayout(Wordle.MAX_GUESSES, Wordle.WORD_LENGTH, 4, 4));
		
		spaces = new JLabel[Wordle.MAX_GUESSES][Wordle.WORD_LENGTH];
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[0].length; j++) {
				JLabel letterLabel = new JLabel(Tile.BLANK.getImage());
				letterLabel.setLayout(new BorderLayout());
				spaces[i][j] = letterLabel;
				mainBoard.add(spaces[i][j]);
			}
		}
		
		setupKeyListeners();
		add(mainBoard);
		setTitle("Wordle");
		pack();
	}
	
	private void setupKeyListeners() {
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println("char: " + c);
//				spaces[0][0].removeAll();
//				spaces[0][0].add(tileMap.get(' '));
//				spaces[0][0].add(tileMap.get(c));
				repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new WordleUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	

//	public void actionPerformed(ActionEvent evt) { // handles user actions
//		Object source = evt.getSource(); // get which component the action came from
//		if (source == okButton) {
//			if (engine.getCurrentSeqSize() == 4) {
//				engine.submitPegSeq();
//				for (int i = 0; i < 4; i++) {
//					rocketSpaces[engine.getCurrentRow() + 1][i].setIcon(engine.getCurrentRocketSeq(i).getImage());
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "Each guess must contain 4 aliens", "Invalid Input",
//						JOptionPane.ERROR_MESSAGE);
//			}
//			if (engine.getPlayerStatus() == GameEngine.WIN) {
//				win();
//			}
//			if (engine.getPlayerStatus() == GameEngine.LOSE) {
//				lose();
//			}
//		} else if (source == clearButton) {
//			if (engine.getCurrentSeqSize() != 0) {
//				engine.clearCurrentPegSeq();
//				for (int i = 0; i < 4; i++) {
//					spaces[engine.getCurrentRow()][i].setIcon(new Peg(Peg.EMPTY).getImage());
//				}
//			}
//		} else if (source == instructionsButton) {
//			JOptionPane.showMessageDialog(null,
//					("Four aliens have arranged themselves in a secret order and are hiding behind the sign marked GOAL."
//							+ '\n' + "There are " + (engine.getLevel() + 3) + " different possible colors of aliens."
//							+ '\n'
//							+ "There may be more than one alien of the same color, and there may be no alien of a particular color."
//							+ '\n'
//							+ "Try to guess the order in which the four aliens are arranged before you reach the end of the board."
//							+ '\n'
//							+ "After each guess, zero to four rockets will appear on the rocket panel on the left."
//							+ '\n'
//							+ "Each blue rocket means that you have placed an alien of the right color in the right position."
//							+ '\n'
//							+ "Each white rocket means that you have placed an alien of the right color in the wrong position."
//							+ '\n' + '\n' + "You are currently playing level " + engine.getLevel()));
//		} else if (source == levelButton) {
//			Object[] possibleValues = { 1, 2, 3, 4, 5 };
//
//			Object selectedValue = JOptionPane.showInputDialog(null, "Choose a level:", "Level",
//					JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
//			if (selectedValue != null) {
//				this.setVisible(false);
//				constructBoard(((Integer) selectedValue));
//			}
//		} else if (source == newGameButton) {
//			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?",
//					"Confirm New Game", JOptionPane.YES_NO_OPTION);
//			if (response == 0) {
//				constructBoard(engine.getLevel());
//			}
//		} else {
//			if (engine.getCurrentSeqSize() < 4) {
//				if (source == redButton) {
//					engine.addPegToSeq(Peg.RED);
//				} else if (source == yellowButton) {
//					engine.addPegToSeq(Peg.YELLOW);
//				} else if (source == greenButton) {
//					engine.addPegToSeq(Peg.GREEN);
//				} else if (source == purpleButton) {
//					engine.addPegToSeq(Peg.PURPLE);
//				} else if (source == blueButton) {
//					engine.addPegToSeq(Peg.BLUE);
//				} else if (source == orangeButton) {
//					engine.addPegToSeq(Peg.ORANGE);
//				} else if (source == pinkButton) {
//					engine.addPegToSeq(Peg.PINK);
//				} else if (source == aquaButton) {
//					engine.addPegToSeq(Peg.AQUA);
//				}
//				spaces[engine.getCurrentRow()][engine.getCurrentCol() - 1].setIcon(engine.getLastPeg().getImage());
//			}
//		}
//	}
//
//
//
//	private void win() {
//		showGoal();
//		JOptionPane.showMessageDialog(null, "Congratulations -- You win!");
//		disableButtons();
//	}
//
//	private void lose() {
//		showGoal();
//		JOptionPane.showMessageDialog(null, "Sorry -- You lose.");
//		disableButtons();
//	}
//
//	private void showGoal() {
//		g.setIcon(engine.getGoal().getSequence(0).getImage());
//		o.setIcon(engine.getGoal().getSequence(1).getImage());
//		a.setIcon(engine.getGoal().getSequence(2).getImage());
//		l.setIcon(engine.getGoal().getSequence(3).getImage());
//	}
//
//	private void disableButtons() {
//		for (JButton button : buttons) {
//			button.setEnabled(false);
//		}
//	}
}