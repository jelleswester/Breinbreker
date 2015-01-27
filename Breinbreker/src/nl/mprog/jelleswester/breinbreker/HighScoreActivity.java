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
	
	String[] highScoreName;
	long[] highScoreTime;
	int numberOfScores;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(
	            WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_high_score);
		getActionBar().hide();
	    
		// get highscore arrays
		Context mContext = getApplicationContext();
		HighScoreController hs = new HighScoreController(mContext);
		GameSavings gs = new GameSavings(mContext);
		int typeOfHighScore = gs.getTypeOfHighScore();
		String textButton;
		System.out.println("check 1");
		
		if (typeOfHighScore == 0) {
			Object[] highScoreArrays = gs.getLocalHighScore();
			highScoreName = (String[]) highScoreArrays[0];
			highScoreTime = (long[]) highScoreArrays[1];
			numberOfScores = gs.getNumberOfScores();
			gs.setTypeOfHighScore(1);
			textButton = "Online";
		}
		else {
			System.out.println("check 2");
			hs.getOnlineHighScores(new OnLoadingFinishedListener() {

				@Override
				public void onLoadingFinished(Object[] items) {
					highScoreName = (String[]) items[0];
					highScoreTime = (long[]) items[1];
					numberOfScores = highScoreTime.length;
					System.out.println("check 3");
				}
		    });
			gs.setTypeOfHighScore(0);
			textButton = "My own";
			System.out.println("check 4");
		}
		
		Button button = (Button) findViewById(R.id.otherHighScoreButton);
		button.setText(textButton);
	    // set highscore time and name in textViews
		System.out.println("check 5");
		for (int j = 0; j < numberOfScores; j++) {
			System.out.println("check 7");
			// set number
			String numberView = "numberView" + j;
			int resID0 = getResources().getIdentifier(numberView, "id", "nl.mprog.jelleswester.breinbreker");
			TextView number = (TextView) findViewById(resID0);
			number.setText(Integer.toString(j + 1));
			
			// set name
			String nameView = "nameView" + j;
			int resID1 = getResources().getIdentifier(nameView, "id", "nl.mprog.jelleswester.breinbreker");
			TextView name = (TextView) findViewById(resID1);
			name.setText(highScoreName[j]);
			
			// set time
			String timeView = "timeView" + j;
			int resID2 = getResources().getIdentifier(timeView, "id", "nl.mprog.jelleswester.breinbreker");
	    	TextView time = (TextView) findViewById(resID2);
			String[] tempTime = hs.convertTime(highScoreTime[j]);
			time.setText(tempTime[0] + ":" + tempTime[1] + ":" + tempTime[2]);    	
	    }
		System.out.println("check 6");
	}
	
	// activates when clicking on other highscore button
  	public void otherHighScoreButton(View view) {
  		finish();
  		startActivity(new Intent(this, HighScoreActivity.class));
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
