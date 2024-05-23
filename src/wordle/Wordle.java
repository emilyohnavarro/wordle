package wordle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import io.github.cdimascio.dotenv.Dotenv;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Wordle {
	private boolean gameOver;
	private int numGuesses;
	private String target;
	private GameStatus status;
	private LetterStatus[] currentGuess; // keeps track of which letters in the target 
									// have already been accounted for in the result string
									// for the current guess
	private String apiKey;
	private Stats stats;
	
	public static final int WORD_LENGTH = 5;
	public static final int MAX_GUESSES = 6;
	public static final String INVALID_GUESS_RESULT = "INVALID";
	
	public enum GameStatus {
		IN_PROGRESS,
		WIN,
		LOSE
	}
	
	private enum LetterStatus {
		RIGHT_SPOT,
		WRONG_SPOT,
		NOT_COUNTED
	}
	
	public Wordle(String dbPropsFilename) throws Exception {
		loadApiKey();
		fetchTarget();
		initGame(dbPropsFilename);
	}
	
	private void loadApiKey() {
		Dotenv dotenv = Dotenv.load();
		apiKey = dotenv.get("API_KEY");
//		System.out.println("key: " + apiKey);
	}
	
	private void fetchTarget() {
		String word = "";
		boolean exceptionThrown = false;
		do {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.wordnik.com/v4/words.json/randomWord?hasDictionaryDef=true&includePartOfSpeech=noun%2Cadjective%2Cverb%2Cadverb%2Cnoun-plural&minCorpusCount=1000&minDictionaryCount=5&minLength=5&maxLength=5&api_key=" + apiKey))
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
//			System.out.println(response.body());
			JSONObject jsonObj = new JSONObject(response.body());
			
			try {
				word = (String)jsonObj.get("word");
//				System.out.println("word: " + word);
				exceptionThrown = false;
			} catch (JSONException e) {
				exceptionThrown = true;
			}
		}
		while (exceptionThrown || word.length() == 0 || Character.isUpperCase(word.charAt(0))); // ignore proper nouns
		target = word;
	}
	
	private void initGame(String dbPropsFilename) throws Exception {
		gameOver = false;
		numGuesses = 0;
		status = GameStatus.IN_PROGRESS;
		currentGuess = new LetterStatus[WORD_LENGTH];
		stats = new Stats(dbPropsFilename);
	}
	
	
	public String getTarget() {
		return target;
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
		if (!isValidWord(guess)) {
			return INVALID_GUESS_RESULT;
		}
		else {
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
//				System.out.println("numGuesses: " + numGuesses);
				stats.gameWon(numGuesses);
				endGame();
			}
			else if (numGuesses == MAX_GUESSES) {
				status = GameStatus.LOSE;
				stats.gameLost();
				endGame();
			}
			
			return new String(result);
		}
	}
	
	private void endGame() {
		gameOver = true;
		stats.printStats();
	}
	
	
	private void resetCurrentGuess() {
		for (int i=0; i<currentGuess.length; i++) {
			currentGuess[i] = LetterStatus.NOT_COUNTED;
		}
	}
	
	private boolean isValidWord(String word) {
		boolean valid;
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.wordnik.com/v4/word.json/" + word + "/definitions?limit=1&includeRelated=false&sourceDictionaries=all&useCanonical=false&includeTags=false&api_key=" + apiKey))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray(response.body());
		
		try {
			JSONObject jsonObj = jsonArray.getJSONObject(0);
			jsonObj.get("id");
			valid = true; // if the returned JSON object has an ID, that means it's a valid word
//			System.out.println("word: " + word);
		} catch (JSONException e) {
			valid = false; // if the returned JSON object does not have an ID, that means it's not a valid word
		}
//		System.out.println(response.body());
//		return (response.statusCode() == 200);
		return valid;
	}
}
