/**
 * Jelles Wester
 * jelleswester@gmail.com
 * 10004531
 * 
 * HighScoreActivity:
 * This activity shows the top ten of the high scores either local or online
 * to the user.
 */

package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class HighScoreActivity extends ActionBarActivity {

	public static final String EXTRA_HIGH_SCORE_TYPE = "typeOfHighScore";
	public static final int HIGH_SCORE_TYPE_OFFLINE = 0;
	public static final int HIGH_SCORE_TYPE_ONLINE = 1;

	// declare highScoreNames and highScoreTimes
	String[] highScoreNames;
	long[] highScoreTimes;

	// declare numberOfScores
	int numberOfScores;

	// current highscore displayed
	int currentHighScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_high_score);
		getActionBar().hide();

		// get highscore arrays
		Context mContext = getApplicationContext();
		HighScoreController hs = new HighScoreController(mContext);
		int typeOfHighScore = getIntent().getIntExtra(EXTRA_HIGH_SCORE_TYPE,
				HIGH_SCORE_TYPE_OFFLINE);
		String textButton;

		if (typeOfHighScore == HIGH_SCORE_TYPE_OFFLINE) {
			GameSavings gs = new GameSavings(mContext);
			Object[] highScoreArrays = gs.getLocalHighScore();
			highScoreNames = (String[]) highScoreArrays[0];
			highScoreTimes = (long[]) highScoreArrays[1];
			numberOfScores = gs.getNumberOfScores();
			textButton = "Online";
			setTextViews();
			currentHighScore = HIGH_SCORE_TYPE_OFFLINE;
		} else {
			hs.getOnlineHighScores(new OnLoadingFinishedListener() {

				@Override
				public void onLoadingFinished(Object[] items) {
					highScoreNames = (String[]) items[0];
					highScoreTimes = (long[]) items[1];
					numberOfScores = highScoreTimes.length;
					setTextViews();
				}
			});
			textButton = "My own";
			currentHighScore = HIGH_SCORE_TYPE_ONLINE;
		}

		Button button = (Button) findViewById(R.id.otherHighScoreButton);
		button.setText(textButton);

	}

	// method that sets the highscore arrays in the textViews
	public void setTextViews() {

		// set highscore time and name in textViews
		for (int j = 0; j < numberOfScores; j++) {

			// set number
			String numberView = "numberView" + j;
			int resID0 = getResources().getIdentifier(numberView, "id",
					"nl.mprog.jelleswester.breinbreker");
			TextView number = (TextView) findViewById(resID0);
			number.setText(Integer.toString(j + 1));

			// set name
			String nameView = "nameView" + j;
			int resID1 = getResources().getIdentifier(nameView, "id",
					"nl.mprog.jelleswester.breinbreker");
			TextView name = (TextView) findViewById(resID1);
			name.setText(highScoreNames[j]);

			// set time
			String timeView = "timeView" + j;
			int resID2 = getResources().getIdentifier(timeView, "id",
					"nl.mprog.jelleswester.breinbreker");
			TextView time = (TextView) findViewById(resID2);
			HighScoreController hs = new HighScoreController(
					getApplicationContext());
			String[] tempTime = hs.convertTime(highScoreTimes[j]);
			time.setText(tempTime[0] + ":" + tempTime[1] + ":" + tempTime[2]);
		}
	}

	// activates when clicking on other highscore button
	public void otherHighScoreButton(View view) {

		// start HighScoreActivity
		Intent intent = new Intent(this, HighScoreActivity.class);
		if (currentHighScore == HIGH_SCORE_TYPE_OFFLINE) {
			intent.putExtra(HighScoreActivity.EXTRA_HIGH_SCORE_TYPE,
					HighScoreActivity.HIGH_SCORE_TYPE_ONLINE);
		} else {
			intent.putExtra(HighScoreActivity.EXTRA_HIGH_SCORE_TYPE,
					HighScoreActivity.HIGH_SCORE_TYPE_OFFLINE);

		}
		finish();
		startActivity(intent);
	}

	// activates when clicking on back button
	public void backButton(View view) {

		// set type of highscore back to local (default)
		GameSavings gs = new GameSavings(getApplicationContext());
		gs.setTypeOfHighScore(0);

		// start StartScreenActivity
		startActivity(new Intent(this, StartScreenActivity.class));
		finish();
	}

	@Override
	public void onBackPressed() {

		// set type of highscore back to local (default)
		GameSavings gs = new GameSavings(getApplicationContext());
		gs.setTypeOfHighScore(0);

		// start StartScreenActivity
		startActivity(new Intent(this, StartScreenActivity.class));
		finish();
	}
}
