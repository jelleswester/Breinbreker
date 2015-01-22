package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.SharedPreferences;

public class GameSavings {
	
	Context c;
	SharedPreferences mPrefs;
	SharedPreferences.Editor sEdit;
	public GameSavings (Context context) {
		c = context;
		mPrefs = c.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
		sEdit = mPrefs.edit();
	}
	
	// method that saves the saved_game check variable
	public void setGameSaved(boolean saved_game) {
		sEdit.putBoolean("saved_game", saved_game);
		sEdit.commit();
	}
	
	// method that saves all game arrays and the elapsed time
	public void saveGame(int[] numbersArray, int[] answersArray, String[] charactersArray, String[] symbolsArray, long timeElapsed) {
	    
	    // set saved_game to true
	    sEdit.putBoolean("saved_game", true);
	    
	    // save the elapsed time
	    sEdit.putLong("time_elapsed", timeElapsed);
	    
	    // save numbersArray and charactersArray
    	for(int i = 0; i < 9; i++)
    	{
    	    sEdit.putInt("number" + i, numbersArray[i]); 
    	    sEdit.putString("character" + i, charactersArray[i]);
    	}
	    
    	// save symbolsArray
    	for (int j = 0; j < 12; j++) {
    		sEdit.putString("symbol" + j, symbolsArray[j]);
    	}
	    
	    // save userInput
	    for (int k = 0; k < 9; k++) {
	    	sEdit.putInt("answer" + k, answersArray[k]);
	    }
	    
	    // commit saved game arrays
	    sEdit.commit();
	}
	
	// method that saves the highscore arrays
	public void setHighScore(String[] highScoreName, long[] highScoreTime) {
		
		// save highScoreTime & highScoreName
  		for (int i = 0, n = highScoreTime.length; i < n; i++) {
  			sEdit.putLong("time" + i, highScoreTime[i]);
  			sEdit.putString("name" + i, highScoreName[i]);
  		}
  		
  		// store number of highscores
  		sEdit.putInt("number_high_scores", highScoreTime.length);
  		
  		// commit saved high score arrays
  		sEdit.commit();
	}
	
	// method that gets the saved_game check variable
	public boolean getGameSaved() {
		boolean saved_game = mPrefs.getBoolean("saved_game", false);
		return saved_game;
	}
	
	// method that gets the answersArray
	public int[] getAnswersArray() {
		
		// declare and get answersArray
		int[] answersArray = new int[9];
		for (int i = 0; i < 9; i++) {
			answersArray[i] = mPrefs.getInt("answer" + i, 0);
		}
		
		// return array
		return answersArray;
	}
	
	// method that gets the numbersArray
	public int[] getNumbersArray() {
		
		// declare and get numbersArray
		int[] numbersArray = new int[9];
		for (int i = 0; i < 9; i++) {
			numbersArray[i] = mPrefs.getInt("number" + i, 0);
		}
		
		// return array
		return numbersArray;
	}
	
	// method that gets the charactersArray
	public String[] getCharactersArray() {
		
		// declare and get numbersArray
		String[] charactersArray = new String[9];
		for (int i = 0; i < 9; i++) {
			charactersArray[i] = mPrefs.getString("character" + i, "A");
		}
		
		// return array
		return charactersArray;
	}
	
	// method that gets the symbolsArray
	public String[] getSymbolsArray() {
		
		// declare and get numbersArray
		String[] symbolsArray = new String[12];
		for (int i = 0; i < 12; i++) {
			symbolsArray[i] = mPrefs.getString("symbol" + i, "+");
		}
		
		// return array
		return symbolsArray;
	}
	
	// method that gets the elapsed time
	public long getElapsedTime() {
		long elapsedTime = mPrefs.getLong("time_elapsed", 0);
		return elapsedTime;
	}
	
	// method that gets the highscore arrays
	public Object[] getHighScore() {
		
		// get number of highscores stored
		int numberOfScores = mPrefs.getInt("number_high_scores", 0);
		
		// declare highScoreName & highScoreTime
		String[] highScoreName = new String[numberOfScores];
		long[] highScoreTime = new long[numberOfScores];
		
		// get the highScores
		for (int i = 0, n = numberOfScores; i < n; i++) {
	    	highScoreTime[i] = mPrefs.getLong("time" + i, 1000000);
	    	highScoreName[i] = mPrefs.getString("name" + i, "anonymous");
	    }
		
		// return highscore arrays
		return new Object[]{highScoreName, highScoreTime};
	}
	
	// method that gets the number of high scores
	public int getNumberOfScores() {
		int numberOfScores = mPrefs.getInt("number_high_scores", 0);
		return numberOfScores;
	}
}
