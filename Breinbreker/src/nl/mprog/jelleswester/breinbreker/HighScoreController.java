/**
 * Jelles Wester
 * jelleswester@gmail.com
 * 10004531
 * 
 * HighScoreController:
 * This class offers methods to check whether a new score is a highscore, to set
 * a new local and online highscore, to get the online highscores and to convert 
 * time from miliseconds to hours, minutes and seconds.
 */

package nl.mprog.jelleswester.breinbreker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class HighScoreController {

	Context c;

	public HighScoreController(Context context) {
		c = context;
	}

	// method that checks for new highscore and return position in rank
	public int[] newHighScore(long[] highScoreTime, long newTime) {

		// declare positionInRank and newHighScore
		int positionInRank = 0;
		int newHighScore = 0;

		// loop through current highScoreTime array
		for (int j = 0, n = highScoreTime.length; j < n; j++) {
			if (highScoreTime.length == 0) {
				newHighScore = 1;
				positionInRank = j;
				break;
			} else if (newTime < highScoreTime[j]) {
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

		// return newHighScore and positionInRank
		return new int[] { newHighScore, positionInRank };
	}

	// method that adds a new local highscore
	public Object[] addLocalHighScore(String[] highScoreName,
			long[] highScoreTime, int positionInRank, String newName,
			long newTime) {

		// check for the number of highscores
		int length;
		if (highScoreName.length < 10) {
			length = highScoreName.length + 1;
		} else {
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
			} else if (newScoreInserted) {
				tempNameArray[i] = highScoreName[i - 1];
				tempTimeArray[i] = highScoreTime[i - 1];
			} else {
				tempNameArray[i] = highScoreName[i];
				tempTimeArray[i] = highScoreTime[i];
			}
		}

		// return new highScoreName and highScoreTime
		return new Object[] { tempNameArray, tempTimeArray };
	}

	// method that adds a new score to online saved highscores
	public void addOnlineHighScore(long newTime, String newName) {

		Parse.initialize(c, "RP0VGv7DKp9CKbxwbsKrus6jTqhAmMfLMwqvMTUp",
				"JyXtiOn8o8uFkHckJdECIAa1RJLVY6zFuEYzd8AT");
		ParseObject gameScore = new ParseObject("HighScore");
		gameScore.put("score", newTime);
		gameScore.put("name", newName);
		gameScore.saveInBackground();
	}

	// method that gets the top ten of highscores saved online
	public void getOnlineHighScores(final OnLoadingFinishedListener listener) {

		// declare names and times
		final ArrayList<String> names = new ArrayList<String>();
		final ArrayList<Long> times = new ArrayList<Long>();

		// retrieve names and times from online saved highscores
		Parse.initialize(c, "RP0VGv7DKp9CKbxwbsKrus6jTqhAmMfLMwqvMTUp",
				"JyXtiOn8o8uFkHckJdECIAa1RJLVY6zFuEYzd8AT");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("HighScore");
		query.orderByAscending("score");
		query.setLimit(10);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {
				if (e == null) {
					for (int i = 0; i < scoreList.size(); i++) {
						ParseObject temp = scoreList.get(i);
						names.add(temp.getString("name"));
						times.add(temp.getLong("score"));
					}
					String[] highScoreName = names.toArray(new String[names
							.size()]);
					long[] highScoreTime = new long[times.size()];

					for (int j = 0; j < times.size(); j++) {
						highScoreTime[j] = (long) times.get(j);
					}

					listener.onLoadingFinished(new Object[] { highScoreName,
							highScoreTime });
				}
			}
		});
	}

	// method that converts integer time in string array with hours, mins & secs
	public String[] convertTime(long newTime) {

		// calculate hours, mins, sec & msec
		int[] intTime = new int[3];
		String[] stringTime = new String[3];
		intTime[2] = (int) (newTime / 1000) % 60;
		intTime[1] = (int) ((newTime / (1000 * 60)) % 60);
		intTime[0] = (int) ((newTime / (1000 * 60 * 60)) % 24);

		// add to stringTime and add a zero if needed
		for (int i = 0; i < 3; i++) {
			if (String.valueOf(intTime[i]).length() == 1) {
				stringTime[i] = "0" + String.valueOf(intTime[i]);
			} else {
				stringTime[i] = String.valueOf(intTime[i]);
			}
		}

		// return array
		return stringTime;
	}
}
