package wordle;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordleUI extends JFrame {
	private Map<Character, CharLayer> tileMap;
	private Wordle wordle;
	private JPanel mainBoard;
	private VisibleChar[][] spaces; // md array of spaces
	private int currRow, currCol;
	private StringBuilder currentGuess;
	private Keyboard keyboard;

	public WordleUI() { // constructor
		wordle = new Wordle("teary");
		createTileMap();
		createComponents();
		currRow = currCol = 0;
		currentGuess = new StringBuilder();
	}
	
	private void createTileMap() {
		tileMap = new HashMap<>();
		tileMap.put(' ', CharLayer.BLANK);
		tileMap.put('a', CharLayer.A);
		tileMap.put('b', CharLayer.B);
		tileMap.put('c', CharLayer.C);
		tileMap.put('d', CharLayer.D);
		tileMap.put('e', CharLayer.E);
		tileMap.put('f', CharLayer.F);
		tileMap.put('g', CharLayer.G);
		tileMap.put('h', CharLayer.H);
		tileMap.put('i', CharLayer.I);
		tileMap.put('j', CharLayer.J);
		tileMap.put('k', CharLayer.K);
		tileMap.put('l', CharLayer.L);
		tileMap.put('m', CharLayer.M);
		tileMap.put('n', CharLayer.N);
		tileMap.put('o', CharLayer.O);
		tileMap.put('p', CharLayer.P);
		tileMap.put('q', CharLayer.Q);
		tileMap.put('r', CharLayer.R);
		tileMap.put('s', CharLayer.S);
		tileMap.put('t', CharLayer.T);
		tileMap.put('u', CharLayer.U);
		tileMap.put('v', CharLayer.V);
		tileMap.put('w', CharLayer.W);
		tileMap.put('x', CharLayer.X);
		tileMap.put('y', CharLayer.Y);
		tileMap.put('z', CharLayer.Z);
	}
	
	private void createComponents() {
		// main board (grid):
		mainBoard = new JPanel();
		mainBoard.setLayout(new GridLayout(Wordle.MAX_GUESSES, Wordle.WORD_LENGTH, 4, 4));
		
		spaces = new VisibleTile[Wordle.MAX_GUESSES][Wordle.WORD_LENGTH];
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[0].length; j++) {
				JLabel letterLabel = new JLabel(CharLayer.BLANK.getTileImage());
				letterLabel.setLayout(new BorderLayout());
				spaces[i][j] = new VisibleTile(CharLayer.BLANK, CharLayer.BLANK, letterLabel);
				mainBoard.add(letterLabel);
			}
		}
		
		// keyboard:
		keyboard = new Keyboard();
		
		setupKeyListeners();
		setLayout(new BorderLayout());
		add(mainBoard, BorderLayout.CENTER);
		add(keyboard, BorderLayout.SOUTH);
		setTitle("Emily's Wordle");
		pack();
	}
	
	private void setupKeyListeners() {
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = Character.toLowerCase(e.getKeyChar());
				if (!wordle.isGameOver()) {
					if (Character.isLetter(c) && !guessReady()) { // show the typed letter and add to guess
						spaces[currRow][currCol].setCharLayers(tileMap.get(' '), 
								tileMap.get(c));
						pack();
						repaint();
						currCol++;
						currentGuess.append(c);
					}
					else if (c == '\n' && guessReady()) { // submit guess
						submitGuess();
					}
					else if (c == '\b') { // delete pressed
						if (currCol > 0) {
							currCol--;
							currentGuess.deleteCharAt(currentGuess.length() - 1);
							spaces[currRow][currCol].setCharLayers(tileMap.get(' '), tileMap.get(' '));
							pack();
							repaint();
						}
					}
				}
			}
		});
	}
	
	private boolean guessReady() { // true if the current row is full of letters
		if ((currCol >= Wordle.WORD_LENGTH) && (currRow < Wordle.MAX_GUESSES)) { 
			// no more letters can be typed in this guess and not out of guesses
			return true;
		}
		else {
			return false;
		}
	}
	
	private void lose() {
		System.out.println("You lose!");
	}
	
	private void submitGuess() {
		String guessResult = wordle.guess(currentGuess.toString());
		System.out.println(guessResult);
		for (int i=0; i<guessResult.length(); i++) {
			char c = guessResult.charAt(i);
			if (c == '*') { // right letter, wrong place
				spaces[currRow][i].setCharLayers(CharLayer.YELLOW, 
						spaces[currRow][i].getForeground());
				System.out.println(spaces[currRow][i].getBackground());
				System.out.println(spaces[currRow][i].getForeground());
				System.out.println("currRow: " + currRow);
				System.out.println("i: " + i);
				pack();
				repaint();
			}
			else if (c == '!') { // right letter, right place
				spaces[currRow][i].setCharLayers(CharLayer.GREEN, 
						spaces[currRow][i].getForeground());
			}
			else { // letter not in word
				spaces[currRow][i].setCharLayers(CharLayer.GREY, 
						spaces[currRow][i].getForeground());
			}
		}
		currentGuess.delete(0, currentGuess.length()); // clear current guess
		currRow++;
		currCol = 0;
		if (wordle.isGameOver()) {
			System.out.println(wordle.getGameStatus());
		}
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