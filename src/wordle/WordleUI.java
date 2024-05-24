package wordle;

import javax.swing.*;

import wordle.Wordle.GameStatus;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;

public class WordleUI extends JFrame {
	private Map<Character, CharLayer> tileMap;
	private Wordle wordle;
	private JPanel mainBoard;
	private VisibleChar[][] spaces; // md array of spaces
	private int currRow, currCol;
	private StringBuilder currentGuess;
	private Keyboard keyboard;

	public WordleUI(String dbPropsFilename) throws Exception { // constructor
		wordle = new Wordle(dbPropsFilename);
		tileMap = CharLayer.charLayerMap;
		createComponents();
		currRow = currCol = 0;
		currentGuess = new StringBuilder();
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
	
	private void submitGuess() {
		String guessResult = wordle.guess(currentGuess.toString());
//		System.out.println(guessResult);
		if (guessResult.equals(Wordle.INVALID_GUESS_RESULT)) {
			JOptionPane.showMessageDialog(this, ("Invalid word"), 
					"Warning", JOptionPane.WARNING_MESSAGE);
		}
		else {
			for (int i=0; i<guessResult.length(); i++) {
				char resultChar = guessResult.charAt(i);
				char guessChar = currentGuess.charAt(i);
				VisibleKey key = keyboard.keyMap.get(guessChar);
				if (resultChar == '*') { // right letter, wrong place
					
					// set tile background:
					spaces[currRow][i].setCharLayers(CharLayer.YELLOW, 
							spaces[currRow][i].getForeground());
					
					// set key background:
					key.setCharLayers(CharLayer.YELLOW, key.getForeground());
					
	//				System.out.println(spaces[currRow][i].getBackground());
	//				System.out.println(spaces[currRow][i].getForeground());
	//				System.out.println("currRow: " + currRow);
	//				System.out.println("i: " + i);
					pack();
					repaint();
				}
				else if (resultChar == '!') { // right letter, right place
					// set tile background:
					spaces[currRow][i].setCharLayers(CharLayer.GREEN, 
							spaces[currRow][i].getForeground());
					
					// set key background:
					key.setCharLayers(CharLayer.GREEN, key.getForeground());
				}
				else { // letter not in word
					// set tile background:
					spaces[currRow][i].setCharLayers(CharLayer.GREY, 
							spaces[currRow][i].getForeground());
					
					// set key background:
					key.setCharLayers(CharLayer.GREY, key.getForeground());
				}
			}
			currentGuess.delete(0, currentGuess.length()); // clear current guess
			currRow++;
			currCol = 0;
			if (wordle.isGameOver()) {
				GameStatus gameStatus = wordle.getGameStatus();
				String message = (gameStatus == GameStatus.WIN) ? 
						gameStatus.toString() : 
							(wordle.getGameStatus() + 
									". The word was " + wordle.getTarget().toUpperCase());
				JOptionPane.showMessageDialog(this, ("YOU " + message + '\n' + wordle.getStats()), 
						"Game over", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println(
					"Usage: java -classpath driver_class_path" + File.pathSeparator + ". TestDB propertiesFile");
			return;
		}
		SimpleDataSource.init(args[0]);
		JFrame frame = new WordleUI(args[0]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}