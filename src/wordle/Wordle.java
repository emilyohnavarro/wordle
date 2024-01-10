package wordle;

import java.util.Scanner;

public class Wordle {
	private boolean gameOver;
	private int numGuesses;
	private String target;
	private GameStatus status;
	private LetterStatus[] currentGuess; // keeps track of which letters in the target 
									// have already been accounted for in the result string
									// for the current guess
	
	private final int WORD_LENGTH = 5;
	private final int MAX_GUESSES = 6;
	
	private enum GameStatus {
		IN_PROGRESS,
		WIN,
		LOSE
	}
	
	private enum LetterStatus {
		RIGHT_SPOT,
		WRONG_SPOT,
		NOT_COUNTED
	}
	
	public Wordle(String target) {
		gameOver = false;
		numGuesses = 0;
		this.target = target;
		status = GameStatus.IN_PROGRESS;
		currentGuess = new LetterStatus[WORD_LENGTH];
	}
	
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	
	public GameStatus getGameStatus() {
		return status;
	}
	
	
	/**
	 * Accepts a guess at the target word and returns a String with:
	 * 
	 * a '_' in each spot where the letter is not contained in the word
	 * at all,
	 * 
	 * a '!' in each spot where the right letter is in the right spot
	 * 
	 * a '*' in each spot where the right letter is in the wrong spot
	 * 
	 * @param guess	the guess at the target word
	 * 
	 * @return	a string representing the result
	 */
	public String guess(String guess) {
		resetCurrentGuess();
		char[] result = new char[WORD_LENGTH];
		for (int i=0; i<result.length; i++) {
			result[i] = '_';
		}
		
		// first pass through to look for right letter in right spot:
		for (int i=0; i<guess.length(); i++) {
			if (guess.charAt(i) == target.charAt(i)) { // right letter, right spot
				result[i] = '!';
				currentGuess[i] = LetterStatus.RIGHT_SPOT;
			}
		}
		
//		System.out.println(result);
//		for (LetterStatus g : currentGuess) {
//			System.out.print(g + " ");
//		}
		
		// second pass to look for right letter in wrong spot:
		for (int i=0; i<guess.length(); i++) {
			goThruTarget: for (int j=0; j<target.length(); j++) {
				if ((guess.charAt(i) == target.charAt(j)) &&
						(currentGuess[i] != LetterStatus.RIGHT_SPOT) &&
						(currentGuess[j] == LetterStatus.NOT_COUNTED)) {
					result[i] = '*';
					currentGuess[j] = LetterStatus.WRONG_SPOT;
					break goThruTarget;
				}
			}
		}
		
		numGuesses++;
		
		// check if game is over:
		boolean win = true;
		for (char c : result) {
			if (c == '_' || c == '*') {
				gameOver = false;
				win = false;
				break;
			}
		}
		if (win) {
			status = GameStatus.WIN;
			gameOver = true;
		}
		else if (numGuesses == MAX_GUESSES) {
			status = GameStatus.LOSE;
			gameOver = true;
		}

		StringBuilder resultStr = new StringBuilder();
		for (char c : result) {
			resultStr.append(c);
			resultStr.append(' ');
		}
		resultStr.deleteCharAt(resultStr.length() - 1);
		return resultStr.toString();
	}
	
	
	private void resetCurrentGuess() {
		for (int i=0; i<currentGuess.length; i++) {
			currentGuess[i] = LetterStatus.NOT_COUNTED;
		}
	}
	
	
	public static void main(String[] args) {
		Wordle game = new Wordle("threw");
		Scanner in = new Scanner(System.in);
		while (!game.isGameOver()) {
			System.out.print("Enter your guess: ");
			System.out.println(game.guess(in.next()));
		}
		System.out.println("GAME OVER. YOU " + game.getGameStatus());
	}
}
