package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class HighScoreActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_score);
	    
		// get highscore arrays
		Context mContext = getApplicationContext();
		GameSavings gs = new GameSavings(mContext);
		Object[] highScoreArrays = gs.getHighScore();
		String[] highScoreName = (String[]) highScoreArrays[0];
		long[] highScoreTime = (long[]) highScoreArrays[1];
		int numberOfScores = gs.getNumberOfScores();
	    
	    // set highscore time and name in textViews
	    HighScoreController hs = new HighScoreController();
		for (int j = 0; j < numberOfScores; j++) {
    		
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
	}
	
	// activates when clicking on back button
  	public void backButton(View view) {
  		 	
     	Intent intent = new Intent(this, StartScreenActivity.class);
     	startActivity(intent);
     	finish();
  	}
  	
  	@Override
	public void onBackPressed() {
	    Intent intent = new Intent(this, StartScreenActivity.class);
	    startActivity(intent);
	    finish();
	}
}
