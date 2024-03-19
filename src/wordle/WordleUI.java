package wordle;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
		wordle = new Wordle("since");
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
	
	private void lose() {
		System.out.println("You lose!");
	}
	
	private void submitGuess() {
		String guessResult = wordle.guess(currentGuess.toString());
//		System.out.println(guessResult);
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
			System.out.println(wordle.getGameStatus());
		}
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new WordleUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.wordnik.com/v4/words.json/randomWord?hasDictionaryDef=true&includePartOfSpeech=noun%2Cadjective%2Cverb%2Cadverb%2Cnoun-plural&minCorpusCount=1000&minDictionaryCount=5&minLength=5&maxLength=5&api_key=8lb16cjwsd42tng7lopt6oopvh8ul2exzg6l80upupffo8ihj"))
//				.header("X-RapidAPI-Host", "jokes-by-api-ninjas.p.rapidapi.com")
//				.header("X-RapidAPI-Key", "x-rapidapi-key")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
	}
}