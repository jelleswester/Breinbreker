package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class YouWonActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_won);
	}
	
	// activates when clicking on back button
   	public void backButton(View view) {
   		
   		// start start screen activity
      	Intent intent = new Intent(this, StartScreenActivity.class);
      	startActivity(intent);
      	finish();
   	}
	
	// activates when clicking on confirm button
  	public void confirmButton(View view) {
  		
  		// get the name for the highscore
  		EditText editText = (EditText) findViewById(R.id.editText1);
		String new_name = editText.getText().toString();
		
		// open SharedPreferences
		Context mContext = getApplicationContext();
	    SharedPreferences mPrefs = mContext.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
	    SharedPreferences.Editor sEdit = mPrefs.edit();
	    
	    // get highscores from SharedPreferences
	    long[] highscoreTime = new long[10];
	    String[] highscoreName = new String[10];
	    for (int i = 0; i < 10; i++) {
	    	highscoreTime[i] = mPrefs.getLong("time" + i, 1000000);
	    	highscoreName[i] = mPrefs.getString("name" + i, "bob");
	    }
	    
	    // compare new highscore and add if necessary
	    long new_time = mPrefs.getLong("time_elapsed", 0);
	    int position_score = 99;
	    
	    for (int j = 9; j > -1; j--) {
	   
	    	if (new_time < highscoreTime[j]) {
	    		position_score = j;
	    	}
	    }
	    
	    long[] highscoreTimeNew = new long[11];
	    String[] highscoreNameNew = new String[11];
	    boolean new_score_inserted = false;
	    if (position_score != 99) {
	    	for (int k = 0; k < 10; k++) {
	    		
	    		if (k == position_score) {
	    			highscoreTimeNew[k] = new_time;
	    			highscoreNameNew[k] = new_name;
	    			new_score_inserted = true;
	    		}
	    		else if (new_score_inserted == true) {
	    			highscoreTimeNew[k] = highscoreTime[k - 1];
	    			highscoreNameNew[k] = highscoreName[k - 1];
	    		}
	    		else {
	    			highscoreTimeNew[k] = highscoreTime[k];
	    			highscoreNameNew[k] = highscoreName[k];
	    		}
	    	}
	    }
	    
	    // save highscores in SharedPreferences
  		for (int n = 0; n < 10; n++) {
  			sEdit.putLong("time" + n, highscoreTimeNew[n]);
  			sEdit.putString("name" + n, highscoreNameNew[n]);
  		}
  		sEdit.commit();
	    
		// start next activity
     	Intent intent = new Intent(this, HighScoreActivity.class);
     	startActivity(intent);
     	finish();
  	}
  	
  	@Override
	public void onBackPressed() {
	    
  		// start start screen activity
  		Intent intent = new Intent(this, StartScreenActivity.class);
	    startActivity(intent);
	    finish();
	}
}
