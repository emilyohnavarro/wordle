package wordle;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Arrays;

public class Stats {

	private int numGamesPlayed;
	private int numWins;
	private int[] guessDistribution = new int[6]; // [0] = 1 guess, [1] = 2 guesses, [2] = 3 guesses, etc.

	public Stats(String dbPropsFilename) throws Exception {
		try (Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement();) {
			DatabaseMetaData dbm = conn.getMetaData();

			// check if "WordleStats" table is there:
			ResultSet tables = dbm.getTables(null, null, "WORDLESTATS", null);
			if (!tables.next()) { // table does not exist
				// create table:
				stat.execute("CREATE TABLE WordleStats (" + "Id INTEGER, " + "Timestamp VARCHAR(100), "
						+ "WinLoss INTEGER, " + "NumGuesses INTEGER)");
			} else { // table exists, so load stats from it
				loadStats(conn);
			}
		}
	}


	public int getNumGamesPlayed() {
		return numGamesPlayed;
	}


	public int getNumWins() {
		return numWins;
	}


	public int[] getGuessDistribution() {
		return Arrays.copyOf(guessDistribution, guessDistribution.length);
	}


	/**
	 * Updates the status with a won game
	 * 
	 * @param numGuesses how many guesses used in the won game
	 */
	public void gameWon(int numGuesses) {
		numGamesPlayed++;
		numWins++;
		guessDistribution[numGuesses - 1]++;
		updateStats(true, numGuesses);
	}


	public void gameLost() {
		numGamesPlayed++;
		updateStats(false, -1);
	}


	public void printStats() {
		System.out.println("Games played: " + numGamesPlayed);
		double percentWon = (double) numWins / (double) numGamesPlayed * 100.0;
		System.out.println("% won: " + Math.round(percentWon));
		System.out.println("Guess distribution:");
		for (int i = 0; i < guessDistribution.length; i++) {
			System.out.println((i + 1) + ": " + guessDistribution[i]);
		}
	}


	/**
	 * Loads stats from DB
	 * 
	 * @param conn
	 * @throws Exception
	 */
	private void loadStats(Connection conn) throws Exception {
		try (Statement stat = conn.createStatement();) {
			ResultSet games = stat.executeQuery("SELECT COUNT (*) FROM WordleStats");
			if (games.next()) { // not empty
				numGamesPlayed = games.getInt(1);
				ResultSet wins = stat.executeQuery("SELECT COUNT (*) FROM WordleStats WHERE WinLoss = 1");
				if (wins.next()) {
					numWins = wins.getInt(1);
					for (int i = 0; i < Wordle.MAX_GUESSES; i++) {
						String query = "SELECT COUNT (*) FROM WordleStats WHERE NumGuesses = ?";
						try (PreparedStatement pStat = conn.prepareStatement(query);) {
							pStat.setInt(1, (i + 1));
							ResultSet guess = pStat.executeQuery();
							if (guess.next()) {
								guessDistribution[i] = guess.getInt(1);
							}
						}
					}
				}
			}
		}
	}


	/**
	 * Updates the database by adding the stats from the game that just ended
	 * 
	 * @param won			whether it was a win or not
	 * @param numGuesses	how many guesses it took to win (-1 if loss)
	 */
	private void updateStats(boolean won, int numGuesses) {
		try (Connection conn = SimpleDataSource.getConnection();
				PreparedStatement stat = conn.prepareStatement(
						"INSERT INTO WordleStats VALUES (?, ?, ?, ?)"
						)) {

			stat.setInt(1, getNewID(conn)); // id
			stat.setString(2, Instant.now().toString()); // timestamp
			stat.setInt(3, won ? 1 : 0); // win/loss
			stat.setInt(4, numGuesses); // num guesses
			stat.executeUpdate();
		} 
		catch(SQLException e) {
			System.out.println(e);
		}
	}


	/**
	 * Gets a new ID for a row in the table, adding 1 to the max ID in the table, or 0 if no entries
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private static int getNewID(Connection conn) throws SQLException {
		int max = -1;
		try (Statement stat = conn.createStatement()) {
			ResultSet result = stat.executeQuery("SELECT max(Id) FROM WordleStats");
			if (result.next()) {

			}
			max = result.getInt(1);
		}
		return max + 1;
	}

}