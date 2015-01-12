 package nl.mprog.jelleswester.breinbreker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class StartScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }
    
    // activates when clicking on start button
  	public void startButton(View view) {
  		 	
     	Intent intent = new Intent(this, GamePlay.class);
     	startActivity(intent);
     	finish();
  	}
  	
  	// activates when clicking on high score button
   	public void highScoreButton(View view) {
   		 	
      	Intent intent = new Intent(this, HighScore.class);
      	startActivity(intent);
      	finish();
   	}
}
