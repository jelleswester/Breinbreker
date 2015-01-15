package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class GamePlayActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
		
		// open SharedPreferences
		Context mContext = getApplicationContext();
	    SharedPreferences mPrefs = mContext.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
	    
	    // get saved game check
		boolean saved_game = mPrefs.getBoolean("saved_game", false);
		
		// declare object gameArrays
		Object[] gameArrays;
		
		// check for saved game
		if (saved_game == true) {
			CreateGame game = new CreateGame(mContext);
			gameArrays = game.savedGame();	
		}
		
		// else open a new game
		else {
			CreateGame game = new CreateGame(mContext);
			gameArrays = game.newGame();
		}
		
		// get symbolsArray and numbersArray
		int[] symbolsArray = (int[]) gameArrays[0];
		int[] numbersArray = (int[]) gameArrays[1];
		String[] charactersArray = (String[]) gameArrays[2];
		
		// build the game
		buildGame(symbolsArray, charactersArray);		
	}
	
	
	
	// method that builds the game using the gameArrays
	public void buildGame(int[] symbolsArray, String[] charactersArray) {
		
		// put strings in textViews
		for (int i = 0; i < 9; i++) {
			String textViewID = "textView" + i;
			int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
			TextView text = (TextView) findViewById(resID);
			text.setText(charactersArray[i]);
		}
		
		// put symbols in textViews (+-x:=)
		for (int j = 0; j < 12; j++) {
			String symbolViewID = "symbolView" + j;
			int resID = getResources().getIdentifier(symbolViewID, "id", "nl.mprog.jelleswester.breinbreker");
			TextView symbol = (TextView) findViewById(resID);
			
			if (symbolsArray[j] == 1) {
				symbol.setText("+");
			}
			else if (symbolsArray[j] == 2) {
				symbol.setText("-");
			}
			else if (symbolsArray[j] == 3) {
				symbol.setText("x");
			}
			else if (symbolsArray[j] == 4) {
				symbol.setText(":");
			}
			else if (symbolsArray[j] == 5) {
				symbol.setText("=");
			}
		}
	}
	
	// method that checks whether game is finished
	public boolean wonGame(int[] numbersArray, int[] userInput) {
		
		// declare boolean game_won
		boolean game_won = true;
		
		// check whether all anwers are right
		for (int i = 0; i < 9; i++) {
			if (numbersArray[i] != userInput[i]) {
				game_won = false;
			}
		}
		
		// return either true or false
		return game_won;
	}
	
	// activates when clicking on back button
   	public void backButton(View view) {
   		
   		// start previous activity
      	startActivity(new Intent(this, StartScreenActivity.class));
      	finish();
   	}
	
	// activates when clicking on you won button
  	public void youWonButton(View view) {
     	
  		// start next activity
  		startActivity(new Intent(this, YouWonActivity.class));
     	finish();
  	}
  	
  	@Override
	public void onBackPressed() {
  		
  		// start previous activity
	    startActivity(new Intent(this, StartScreenActivity.class));
	    finish();
	}
}
