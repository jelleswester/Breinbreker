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
	String[] symbolsArray;
	String[] charactersArray;
	int[] numbersArray;
	int[] answersArray = new int[9];
	
	// variables to record time
	long startTime;
	long timeElapsed = 0;
	
	//
	ArrayList<NumberPicker> numberPickers;
	
	//
	ArrayList<Integer> doNotAlter;
	
	
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
		symbolsArray = (String[]) gameArrays[0];
		numbersArray = (int[]) gameArrays[1];
		charactersArray = (String[]) gameArrays[2];
		answersArray = (int[]) gameArrays[3];
		
		// build the game
		buildGame(symbolsArray, charactersArray);
		
		// start counting the time
		startTime = System.currentTimeMillis();
		
		numberPickers = new ArrayList<NumberPicker>();
		doNotAlter = new ArrayList<Integer>();
		
		for (int y = 0; y < 9; y++) {
			System.out.println("n= "+ numbersArray[y]);
			System.out.println("a= "+ answersArray[y]);
		}
		
		for (int i = 0; i < 9; i++) {
			
			// get the right number from the answersArray to be displayed
			int number = answersArray[i];
			int length = String.valueOf(numbersArray[i]).length();
			int[] digits = new int[4];
			int power = 3;
			for (int k = 0; k < 4; k ++) {
				digits[k] = number / ((int)Math.pow(10,power));
				number = number - (digits[k] * (int)Math.pow(10,power));
				power -= 1;
			}
			
			for (int j = 0; j < 4; j++) {
				
				// find numberpicker by id
				String textViewID = "numberPicker" + i + "_" + j;
				int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
				NumberPicker np = (NumberPicker) findViewById(resID);
				
				// set features numberpicker
				if (length + j < 4) {
					np.setEnabled(false);
					np.setMinValue(0);
					np.setMaxValue(0);
					np.setValue(0);
					np.setId((i * 10) + j);
					doNotAlter.add((i * 10) + j);
				}
				else {
					np.setMinValue(0);
					np.setMaxValue(9);
					np.setValue(digits[j]);
					np.setId((i * 10) + j);
				}
				np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
				np.setWrapSelectorWheel(true);
				np.setOnScrollListener(new NumberPicker.OnScrollListener() {

			        @Override
			        public void onScrollStateChange(NumberPicker picker, int scrollState) {
			            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
			            	
			            	Object[] changedArrays = changeAnswer(numberPickers, answersArray, numbersArray, picker.getValue(), picker.getId(), doNotAlter);
			            	answersArray = (int[])changedArrays[0];
			            	numberPickers = (ArrayList<NumberPicker>)changedArrays[1];
							
							if (wonGame(numbersArray,answersArray)) {
								startActivity(new Intent(GamePlayActivity.this, YouWonActivity.class));
					         	finish();
							}
			            }
			        }
			    });
				numberPickers.add(np);
			}
		}		
	}
	
	// method that builds the game using the gameArrays
	public void buildGame(String[] symbolsArray, String[] charactersArray) {
		
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
			symbol.setText(symbolsArray[j]);
		}
	}
	
	public Object[] changeAnswer(ArrayList<NumberPicker> numberPickers, int[] answersArray, int[] numbersArray, int newVal, int id, ArrayList<Integer>  doNotAlter) {
		
		// declare tempAnswersArray, tempNumbersArray, tempNumberPickers
		int[] tempAnswersArray = answersArray;
		int[] tempNumbersArray = numbersArray;
		ArrayList<NumberPicker> tempNumberPickers = numberPickers;
		
		// find positionInArray and positionInNumber
		int positionInArray = id / 10;
		int positionInNumber;
		if (positionInArray == 0) {
			positionInNumber = id;
		}
		else {
			positionInNumber = id % (positionInArray * 10); 
		}
		
		// find out which character is changing
		int x = tempNumbersArray[positionInArray];
		int[] digits1 = new int[4];
		for (int i = 3; i > -1; i--) {
			digits1[i] = x % 10;
			x /= 10;
		}
		int characterThatChanges = digits1[positionInNumber];
		
		// find which locations changes need to be made
		List<Integer> locationsList = new ArrayList<Integer>();
		for (int j = 0; j < 9; j++) {
			int y = tempNumbersArray[j];
			int[] digits2 = new int[4];
			for (int k = 3; k > -1; k--) {
				digits2[k] = y % 10;
				y /= 10;
			}
			for (int m = 0; m < 4; m++) {
				int tempLocation = (j * 10) + m;
				if ((digits2[m] == characterThatChanges) && (doNotAlter.contains(tempLocation) == false)) {
					locationsList.add(tempLocation);
				}
			}
		}
		
		// make changes in tempAnswersArray
		for(int p = 0, q = locationsList.size(); p < q; p++) {
			
			int location = locationsList.get(p);
			
			// find positionInArray and positionInNumber
			int positionInArray1 = location / 10;
			int positionInNumber1;
			if (positionInArray1 == 0) {
				positionInNumber1 = location;
			}
			else {
				positionInNumber1 = location % (positionInArray1 * 10); 
			}
			
			// change the number
			int numberToBeChanged = tempAnswersArray[positionInArray1];
			int[] numberDigits = new int[4];
			for (int i = 3; i > -1; i--) {
				numberDigits[i] = numberToBeChanged % 10;
				numberToBeChanged /= 10;
			}
			
			numberDigits[positionInNumber1] = newVal;
			int replaceNumber = 0;
			int power = 3;
			for (int j = 0; j < 4; j++) {
				replaceNumber = replaceNumber + (numberDigits[j] * ((int)Math.pow(10, power)));
				power -= 1;
			}
			tempAnswersArray[positionInArray1] = replaceNumber;
			
		}
		
		// make changes in tempNumberPickers
		for (int b = 0, c = locationsList.size(); b < c; b++) {
			int temp = ((locationsList.get(b)/ 10) * 4) + (locationsList.get(b) % 10);
			NumberPicker np = tempNumberPickers.get(temp);
			np.setValue(newVal);
		}
		
		// return answersArray and numberPickers
		return new Object[]{tempAnswersArray, tempNumberPickers};
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
  	
  	public void onStop() {
    	super.onStop();
    	
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
    		sEdit.putString("symbol" + j, symbolsArray[j]);
    	}
	    
	    // save userInput
	    for (int k = 0; k < 9; k++) {
	    	sEdit.putInt("answer" + k, answersArray[k]);
	    }
	    
	    // commit saved game arrays
	    sEdit.commit();
  	}
}
