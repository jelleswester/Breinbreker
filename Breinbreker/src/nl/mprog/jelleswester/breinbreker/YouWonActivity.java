package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class YouWonActivity extends ActionBarActivity {
	
	// declare highScoreName & highScoreTime
	String[] highScoreName;
	long[] highScoreTime;
	
	// declare positionInRank
	int positionInRank;
	
	// declare EditText
	EditText et;
	
	// declare newTime
	long newTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_won);
		
		// set saved game check variable to false
		Context mContext = getApplicationContext();
		GameSavings gs = new GameSavings(mContext);
		gs.setGameSaved(false);
		boolean test = gs.getGameSaved();
		System.out.println("game_saved_yw = " + test);
		
		// check whether time needs to come in highscore
		Object[] highScoreArray = gs.getHighScore();
		highScoreName = (String[]) highScoreArray[0];
		highScoreTime = (long[]) highScoreArray[1];
		
		// get elapsed time
	    newTime = gs.getElapsedTime();
	    
	    // convert time to string array containing hours, mins, secs
	    HighScoreController hs = new HighScoreController();
	    String[] time = hs.convertTime(newTime);
	    
	    // set elapsed time
	    TextView tv1 = (TextView) findViewById(R.id.textView2);
	    tv1.setText("Your time: " + time[0] + ":" + time[1] + ":" + time[2]);
	    
	    // declare newHighScore
	    boolean newHighScore = false;
	    
	    // check for new highscore
	    int[] highScoreCheck = hs.newHighScore(highScoreTime, newTime);
	    if (highScoreCheck[0] == 1) {
	    	newHighScore = true;
	    	positionInRank = highScoreCheck[1];
	    }
	    
	    // if new high score, ask for name
	    if (newHighScore) {

	    	// find relative layout
	    	RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout1);
	    	
	    	// new textView
	    	TextView tv2 = new TextView(mContext);
	    	String askName = "Submit your highscore:";
	    	tv2.setText(askName);
	    	RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
	    	        ViewGroup.LayoutParams.WRAP_CONTENT);
	    	lp1.addRule(RelativeLayout.BELOW, R.id.textView2);
	    	tv2.setLayoutParams(lp1);
	    	tv2.setId(123);
	    	rl.addView(tv2);
	    	
	    	// new editText
	    	et = new EditText(mContext);
	    	et.setHint("Name");
	    	int maxLength = 15;
	    	et.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(maxLength) });
	    	RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
	    	        ViewGroup.LayoutParams.WRAP_CONTENT);
	    	lp2.addRule(RelativeLayout.BELOW, 123);
	    	et.setLayoutParams(lp2);
	    	rl.addView(et);
	    	
	    	// new save button
	    	LinearLayout lnl = (LinearLayout) findViewById(R.id.linearLayout1);
	    	Button saveButton = new Button(mContext);
	    	saveButton.setText("save");
	    	saveButton.setOnClickListener(new OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View view) {
	    			
	    			// get newName
	    			String newName = et.getText().toString();
	    			
	    			// update highscores
	    			HighScoreController hs = new HighScoreController();
	    			Object[] newHighScores = hs.addHighScore(highScoreName, highScoreTime, positionInRank, newName, newTime);
	    			highScoreName = (String[]) newHighScores[0];
	    			highScoreTime = (long[])  newHighScores[1];
	    			
	    			// save new highscores
	    			Context mContext = getApplicationContext();
	    			GameSavings gs = new GameSavings(mContext);
	    			gs.setHighScore(highScoreName, highScoreTime);
	    			
	    			// open next activity
	    			startActivity(new Intent(YouWonActivity.this, HighScoreActivity.class));
	    	      	finish();
	    		}
	    	});
	    	LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
	    			ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
	    	saveButton.setLayoutParams(lp3);
	    	lnl.addView(saveButton);
	    }  
	}
	
	// activates when clicking on back button
   	public void backButton(View view) {
      	startActivity(new Intent(this, StartScreenActivity.class));
      	finish();
   	}
  	
  	@Override
	public void onBackPressed() {
  		startActivity(new Intent(this, StartScreenActivity.class));
      	finish();
	}
}
