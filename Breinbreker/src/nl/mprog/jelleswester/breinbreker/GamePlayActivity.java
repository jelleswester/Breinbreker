package nl.mprog.jelleswester.breinbreker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class GamePlayActivity extends ActionBarActivity {
	
	// declare game arrays
	int[] symbolsArray;
	int[] numbersArray;
	String[] charactersArray;
	int[] answersArray = new int[9];
	
	// variables to record time
	long startTime;
	long timeElapsed = 0;
	
	//
	ArrayList<NumberPicker> numberPickers;
	
	
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
		answersArray = (int[]) gameArrays[3];
		
		// build the game
		buildGame(symbolsArray, charactersArray);
		
		// start counting the time
		startTime = System.currentTimeMillis();
		
		numberPickers = new ArrayList<NumberPicker>();
		
		for (int i = 0; i < 9; i++) {
			
			int number = answersArray[i];
			int[] digits = new int[4];
			int power = 3;
			for (int k = 0; k < 4; k ++) {
				digits[k] = number / ((int)Math.pow(10,power));
				number = number - (digits[k] * (int)Math.pow(10,power));
				power -= 1;
			}
			
			
			for (int j = 0; j < 4; j++) {
				String textViewID = "numberPicker" + i + "_" + j;
				int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
				NumberPicker np = (NumberPicker) findViewById(resID);
				if (i == 0 && j == 0) {
					System.out.println("eerst " +resID);
				}
				np.setMinValue(0);
				np.setMaxValue(9);
				np.setValue(digits[j]);
				np.setId((i * 10) + j);
				np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
				np.setWrapSelectorWheel(true);
				np.setOnValueChangedListener(new OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						answersArray = changeAnswer(answersArray, newVal, picker.getId());
						int[] locations = changeOtherValues(numbersArray, picker.getId());
						
						for (int b = 0, c = locations.length; b < c; b++) {
							
							int temp = ((locations[b]/ 10) * 4) + (locations[b] % 10);
							NumberPicker np1 = numberPickers.get(temp);
							np1.setValue(newVal);
							
							
						}
						
						if (wonGame(numbersArray,answersArray)) {
							startActivity(new Intent(GamePlayActivity.this, YouWonActivity.class));
				         	finish();
						}
					}
				});
				numberPickers.add(np);
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
	
	public int[] changeOtherValues(int[] numbersArray, int changed_position) {
		
		// declare tempNumbers and locationsList
		int[] tempNumbers = numbersArray;
		List<Integer> locationsList = new ArrayList<Integer>();
		
		// determine on which location the number has changed
		int positionInArray = changed_position / 10;
		int positionInNumber;
		if (positionInArray == 0) {
			positionInNumber = changed_position;
		}
		else {
			positionInNumber = changed_position % (positionInArray * 10);
		}
		
		// determine which number corresponds to the determined location
		int totalNumber = tempNumbers[positionInArray];
		int[] numberDigits = new int[4];
		for (int i = 3; i > -1; i--) {
			numberDigits[i] = totalNumber % 10;
			totalNumber /= 10;
		}

		int toCompareWith = numberDigits[positionInNumber];
		
		// compare numbers and save other locations that need to be changed
		for (int j = 0; j < 9; j++) {
			int tempNumber = tempNumbers[j];
			int[] tempDigits = new int[4];
			for (int k = 3; k > -1; k--) {
				tempDigits[k] = tempNumber % 10;
				totalNumber /= 10;
			}
			for (int m = 0; m < 4; m++) {
				if (tempDigits[m] == toCompareWith) {
					int tempLocation = (j * 10) + m;
					locationsList.add(tempLocation);
				}
			}
		}
		
		// convert locationsList to int array
		int[] returnLocations = new int[locationsList.size()];
		for (int n = 0, p = locationsList.size(); n < p; n++) {
			returnLocations[n] = locationsList.get(n);
			System.out.println(returnLocations[n]);
		}
		
		return returnLocations;
		
		
	}
	
	public int[] changeAnswer(int[] answersArray, int newVal, int changed_position) {
		
		// determine which number needs to be altered using position in array and in number
		int[] tempArray = answersArray;
		int positionInArray = changed_position / 10;
		int positionInNumber;
		if (positionInArray == 0) {
			positionInNumber = changed_position;
		}
		else {
			positionInNumber = changed_position % (positionInArray * 10);
		}
		
		// change the number
		int numberToBeChanged = tempArray[positionInArray];
		int[] numberDigits = new int[4];
		for (int i = 3; i > -1; i--) {
			numberDigits[i] = numberToBeChanged % 10;
			numberToBeChanged /= 10;
		}
		numberDigits[positionInNumber] = newVal;
		int replaceNumber = 0;
		int power = 3;
		for (int j = 0; j < 4; j++) {
			replaceNumber = replaceNumber + (numberDigits[j] * ((int)Math.pow(10, power)));
			power -= 1;
		}
		
		// replace the old number by the new number and return the array
		tempArray[positionInArray] = replaceNumber;
		return tempArray;
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
  		
  		startActivity(new Intent(this, YouWonActivity.class));
     	finish();
  		
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
