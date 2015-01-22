package nl.mprog.jelleswester.breinbreker;

public class HighScoreController {
	
	public int[] newHighScore(long[] highScoreTime, long newTime) {
		
		// declare positionInRank & newHighScore
		int positionInRank = 0;
		int newHighScore = 0;
		
		// loop through current highScoreTime array
	    for (int j = 0, n = highScoreTime.length; j < n; j++) {
	    	if (highScoreTime.length == 0) {
	    		newHighScore = 1;
	    		positionInRank = j;
	    		break;
	    	}
	    	else if (newTime < highScoreTime[j]) {
	    		newHighScore = 1;
	    		positionInRank = j;
	    		break;
	    	}
	    }
	    
	    // check whether highscore is not full and new score is lowest score
	    if ((highScoreTime.length < 10) && (newHighScore == 0)) {
	    	positionInRank = highScoreTime.length;
	    	newHighScore = 1;
	    }
	    
	    // return newHighScore & positionInRank
	    return new int[]{newHighScore, positionInRank};
	}
	
	// method that add a new highscore
	public Object[] addHighScore(String[] highScoreName, long[] highScoreTime, int positionInRank, String newName, long newTime) {
		
		// check for the number of highscores
		int length;
		if (highScoreName.length < 10) {
			length = highScoreName.length + 1;
		}
		else {
			length = 10;
		}
		
		// declare tempNameArray & tempTimeArray
		String[] tempNameArray = new String[length];
		long[] tempTimeArray = new long[length];
		
		// add new score to rank
		boolean newScoreInserted = false;
		for (int i = 0, n = length; i < n; i++) {
			if (i == positionInRank) {
				tempNameArray[i] = newName;
				tempTimeArray[i] = newTime;
				newScoreInserted = true;
			}
			else if (newScoreInserted) {
				tempNameArray[i] = highScoreName[i - 1];
				tempTimeArray[i] = highScoreTime[i - 1];
			}
			else {
				tempNameArray[i] = highScoreName[i];
				tempTimeArray[i] = highScoreTime[i];
			}
		}
		
		// return new highScoreName and highScoreTime
		return new Object[]{tempNameArray, tempTimeArray};
	}
	
	// method that converts integer time in string array with hours, mins & secs
	public String[] convertTime(long newTime) {
		
		// calculate hours, mins, sec & msec
	    int[] intTime = new int[3];
	    String[] stringTime = new String[3];
	    intTime[2] = (int) (newTime / 1000) % 60 ;
	    intTime[1] = (int) ((newTime / (1000*60)) % 60);
	    intTime[0] = (int) ((newTime / (1000*60*60)) % 24);
	    
	    // add to stringTime and add a zero if needed
	    for (int i = 0; i < 3; i++) {
	    	if (String.valueOf(intTime[i]).length() == 1) {
	    		stringTime[i] = "0" + String.valueOf(intTime[i]);	    		
	    	}
	    	else {
	    		stringTime[i] = String.valueOf(intTime[i]);
	    	}
	    }
	    
	    // return array
	    return stringTime;
	}
}
