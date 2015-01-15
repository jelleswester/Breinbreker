package nl.mprog.jelleswester.breinbreker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class YouWonActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_won);
	}
	
	// activates when clicking on back button
   	public void backButton(View view) {
   		 	
      	Intent intent = new Intent(this, StartScreenActivity.class);
      	startActivity(intent);
      	finish();
   	}
	
	// activates when clicking on confirm button
  	public void confirmButton(View view) {
  		 	
     	Intent intent = new Intent(this, HighScoreActivity.class);
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
