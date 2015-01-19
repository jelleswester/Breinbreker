package nl.mprog.jelleswester.breinbreker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlayActivity extends ActionBarActivity {
	
	// declare game arrays
	int[] symbolsArray;
	int[] numbersArray;
	String[] charactersArray;
	int[] answersArray = new int[9];
	
	// variables to record time
	long startTime;
	long timeElapsed = 0;
	
	
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
			timeElapsed = mPrefs.getLong("time_elapsed", 0);
		}
		
		// else open a new game
		else {
			CreateGame game = new CreateGame(mContext);
			gameArrays = game.newGame();
		}
		
		// get symbolsArray and numbersArray
		symbolsArray = (int[]) gameArrays[0];
		numbersArray = (int[]) gameArrays[1];
		charactersArray = (String[]) gameArrays[2];
		
		for (int z = 0; z < 9; z++) {
			System.out.println(numbersArray[z]);
		}
		
		// build the game
		buildGame(symbolsArray, charactersArray);
		
		// start counting the time
		startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				String textViewID = "numberPicker" + i + "_" + j;
				int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
				NumberPicker np = (NumberPicker) findViewById(resID);
				np.setMinValue(0);
				np.setMaxValue(9);
				np.setId((i * 10) + j);
				np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
				np.setWrapSelectorWheel(true);
				
				np.setOnValueChangedListener(new OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						answersArray = changeAnswer(answersArray, newVal, picker.getId());
						for (int z = 0; z < 9; z++) {
							System.out.println(answersArray[z]);
						}
						if (wonGame(numbersArray,answersArray) == true) {
							// open next activity
							Context context = getApplicationContext();
				    		CharSequence text = "You won biatch";
				    		int duration = Toast.LENGTH_SHORT;
				    		Toast toast = Toast.makeText(context, text, duration);
				    		toast.show();
						}
					}
				});
			}
		}
				
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
	
	public int[] changeAnswer(int[] answersArray, int newVal, int changed_position) {
		
		int [] temp = answersArray;
		int position1 = changed_position / 10;
		int position2;
		if (position1 == 0) {
			position2 = changed_position;
		}
		else {
			position2 = changed_position % position1;
		}
		int temp1 = answersArray[position1];
		int[] digits1 = new int[4];
		int count = 0;
		while (temp1 > 0) {
			digits1[count] = (temp1 % 10);
			temp1 = temp1 / 10;
			count += 1;
		}
		digits1[position2] = newVal;
		int changed_number = 0;
		int count2 = 3;
		for (int i = 0; i < 4; i++) {
			changed_number = changed_number + (digits1[i] * ((int)Math.pow(10, count2)));
			count2 = count2 - 1;
		}
		temp[position1] = changed_number;
		return temp;
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
  		
  		int game_won_count = 0;
    	for (int i = 0; i < 9; i++) {
			String textViewID = "editText" + i;
			int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
			EditText editText = (EditText) findViewById(resID);
			String string = editText.getText().toString();
			
			if (string.length() == 0) {
				answersArray[i] = 0;
				game_won_count += 1;
			}
			else {
				answersArray[i] = Integer.parseInt(string);
			}
		}
    	
    	if (wonGame(numbersArray, answersArray) == true) {
    		// start next activity
      		startActivity(new Intent(this, YouWonActivity.class));
         	finish();
    	}
    	else if (game_won_count == 8) {
    		// start next activity
      		startActivity(new Intent(this, YouWonActivity.class));
         	finish();
    	}
    	else {
    		Context context = getApplicationContext();
    		CharSequence text = "Incorrect answer :(!";
    		int duration = Toast.LENGTH_SHORT;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
    	}
  		
  	}
  	
  	@Override
	public void onBackPressed() {
  		
  		// start previous activity
	    startActivity(new Intent(this, StartScreenActivity.class));
	    finish();
	}
  	
  	public void onPause() {
    	super.onPause();
    	
    	// open SharedPreferences
		Context mContext = getApplicationContext();
	    SharedPreferences mPrefs = mContext.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
	    SharedPreferences.Editor sEdit = mPrefs.edit();
	    
	    // check the time
	    timeElapsed = timeElapsed + (System.currentTimeMillis() - startTime);
	    
	    // set saved_game to true
	    sEdit.putBoolean("saved_game", true);
	    
	    // save the time
	    sEdit.putLong("time_elapsed", timeElapsed);
	    
	    // save numbersArray and charactersArray
    	for(int i = 0; i < 9; i++)
    	{
    	    sEdit.putInt("number" + i, numbersArray[i]); 
    	    sEdit.putString("character" + i, charactersArray[i]);
    	}
	    
    	// save symbolsArray
    	for (int j = 0; j < 12; j++) {
    		sEdit.putInt("symbol" + j, symbolsArray[j]);
    	}
	    
	    // save userInput
	    for (int k = 0; k < 9; k++) {
	    	sEdit.putInt("answer" + k, answersArray[k]);
	    }
	    
	    // commit saved game arrays
	    sEdit.commit();
  	}
}
