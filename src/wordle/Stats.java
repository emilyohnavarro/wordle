package wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Stats {

	private int numGamesPlayed;
	private int numWins;
	private int[] guessDistribution = new int[6]; // [0] = 1 guess, [1] = 2 guesses, [2] = 3 guesses, etc.

	private final String statsFilename = "res/stats.txt";

	public Stats() {
		File statsFile = new File(statsFilename);
		if (statsFile.exists()) {
			loadFile();
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
	 * @param numGuesses	how many guesses used in the won game
	 */
	public void gameWon(int numGuesses) {
		numGamesPlayed++;
		numWins++;
		guessDistribution[numGuesses-1]++;
//		System.out.println("numGuesses-1=" + (numGuesses-1));
//		System.out.println("guessDistribution[numGuesses-1]: " + guessDistribution[numGuesses-1]);
//		for (int guessNum : guessDistribution) {
//			System.out.println("(gameWon)guessNum: " + guessDistribution[guessNum]);
//		}
		updateFile();
	}
	
	
	public void gameLost() {
		numGamesPlayed++;
		updateFile();
	}
	
	
	public void printStats() {
		System.out.println("Games played: " +  numGamesPlayed);
		double percentWon = (double)numWins / (double)numGamesPlayed * 100.0;
		System.out.println("% won: " + Math.round(percentWon));
		System.out.println("Guess distribution:");
		for (int i=0; i<guessDistribution.length; i++) {
			System.out.println((i + 1) + ": " + guessDistribution[i]);
		}
	}


	private void loadFile() {
		try (Scanner infile = new Scanner(new File(statsFilename))) {
			while (infile.hasNext()) {

				numGamesPlayed = infile.nextInt();
//				System.out.println("numGamesPlayed: " + numGamesPlayed);
				numWins = infile.nextInt();
//				System.out.println("numWins: " + numWins);
				infile.nextLine(); // consume the newline at the end of the line
				String line = infile.nextLine();
//				System.out.println("line: " + line);
				
				Scanner lineScan = new Scanner(line);
				int index = 0;
				while (lineScan.hasNextInt()) {
					guessDistribution[index] = lineScan.nextInt();
					index++;
				}
//				for (int guessNum : guessDistribution) {
//					System.out.println("guessNum: " + guessDistribution[guessNum]);
//				}
				lineScan.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + statsFilename + " not found");
		}
	}

	
	private void updateFile() {
		try (FileWriter fw = new FileWriter(statsFilename)) {
			PrintWriter pw = new PrintWriter(fw);
			pw.println(numGamesPlayed);
			pw.println(numWins);
			for (int guessNum : guessDistribution) {
//				System.out.println("guessNum: " + guessDistribution[guessNum]);
				pw.print(guessNum + " ");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}