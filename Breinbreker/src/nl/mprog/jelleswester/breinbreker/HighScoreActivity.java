package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class HighScoreActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_score);
		
		// open SharedPreferences
		Context mContext = getApplicationContext();
	    SharedPreferences mPrefs = mContext.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
	    
	    // get the highscore times and names
	    long[] highscoreTimes = new long[10];
	    String[] highscoreNames = new String[10];
	    for (int i = 0; i < 10; i++) {
	    	highscoreTimes[i] = mPrefs.getLong("time" + i, 100000);
	    	highscoreNames[i] = mPrefs.getString("name" + i, "bob");
	    }
	    
	    // set highscore time and name in textViews
	    for (int j = 0; j < 10; j++) {
    		String numberView = "numberView" + j;
	    	String nameView = "nameView" + j;
	    	String timeView = "timeView" + j;
	    	int resID0 = getResources().getIdentifier(numberView, "id", "nl.mprog.jelleswester.breinbreker");
	    	int resID1 = getResources().getIdentifier(nameView, "id", "nl.mprog.jelleswester.breinbreker");
			int resID2 = getResources().getIdentifier(timeView, "id", "nl.mprog.jelleswester.breinbreker");
			TextView number = (TextView) findViewById(resID0);
			TextView name = (TextView) findViewById(resID1);
			TextView time = (TextView) findViewById(resID2);
			number.setText(Integer.toString(j + 1));
			name.setText(highscoreNames[j]);
			time.setText(Long.toString(highscoreTimes[j]));    	
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