 package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseObject;


public class StartScreenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
	            WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);
        getActionBar().hide();
        
        // check whether game is saved
        Context mContext = getApplicationContext();
        GameSavings gs = new GameSavings(mContext);
        boolean saved_game = gs.getGameSaved();
        
        // disable continue button if necessary
        Button continueButton = (Button) findViewById(R.id.continueGameButton);
        continueButton.setEnabled(saved_game);
    }
    
    // activates when clicking on continue button
   	public void continueButton(View view) {
      	startActivity(new Intent(this, GamePlayActivity.class));
      	finish();
   	}
    
    // activates when clicking on start button
  	public void startButton(View view) {
  		
  		// set gameSaved on false
  		Context mContext = getApplicationContext();
     	GameSavings gs = new GameSavings(mContext);
     	gs.setGameSaved(false);
  		
  		// start GamePlayActivity
     	startActivity(new Intent(this, GamePlayActivity.class));
     	finish();
  	}
  	
  	// activates when clicking on high score button
   	public void highScoreButton(View view) {
      	startActivity(new Intent(this, HighScoreActivity.class));
      	finish();
   	}
}
