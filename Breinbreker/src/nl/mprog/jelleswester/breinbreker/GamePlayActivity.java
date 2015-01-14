package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class GamePlayActivity extends ActionBarActivity {

	boolean saved_game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
		
		// check for saved game
		Context mContext = getApplicationContext();
	    SharedPreferences mPrefs = mContext.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
	    SharedPreferences.Editor sEdit = mPrefs.edit();
		mPrefs.getBoolean("saved_game", false);
		
		// declare object temp
		Object[] temp;
		
		// yes? open saved game
		if (saved_game == true) {
			CreateGame game = new CreateGame(mContext);
			temp = game.savedGame();	
		}
		
		// no? open new game
		else {
			CreateGame game = new CreateGame(mContext);
			temp = game.newGame();
		}
		
		// get symbolsArray and numbersArray
		int[] symbolsArray = (int[]) temp[0];
		int[] numbersArray = (int[]) temp[1];
		String[] charactersArray = (String[]) temp[2];
		
		// call function built game
		buildGame();
		
		// put numbers in textViews
		for (int i = 0; i < 9; i++) {
			String textViewID = "textView" + i;
			int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
			TextView text = (TextView) findViewById(resID);
			text.setText(String.valueOf(numbersArray[i]));
		}
		
		// put signs in textViews
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
		
		// build table view
		
	}
	
	
	
	// method that builds the game using array input
	public void buildGame() {
		
	}
	
	// method that checks whether game is finished
	public boolean wonGame(int[] numbersArray, int[] answersArray) {
		
		// declare boolean game_won
		boolean game_won = true;
		
		// check whether all anwers are right
		for (int i = 0; i < 9; i++) {
			if (numbersArray[i] != answersArray[i]) {
				game_won = false;
			}
		}
		
		// return either true or false
		return game_won;
	}
	
	// activates when clicking on back button
   	public void backButton(View view) {
   		 	
      	Intent intent = new Intent(this, StartScreen.class);
      	startActivity(intent);
      	finish();
   	}
	
	// activates when clicking on you won button
  	public void youWonButton(View view) {
  		 	
     	Intent intent = new Intent(this, YouWon.class);
     	startActivity(intent);
     	finish();
  	}
  	
  	@Override
	public void onBackPressed() {
	    Intent intent = new Intent(this, StartScreen.class);
	    startActivity(intent);
	    finish();
	}
}
