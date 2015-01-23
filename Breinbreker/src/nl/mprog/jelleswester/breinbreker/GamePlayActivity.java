package nl.mprog.jelleswester.breinbreker;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class GamePlayActivity extends ActionBarActivity {
	
	// declare game arrays
	String[] symbolsArray;
	String[] charactersArray;
	int[] numbersArray;
	int[] answersArray;
	
	// declare variables to record time
	long startTime;
	long timeElapsed;
	
	// create ArrayList numberpickers
	ArrayList<NumberPicker> numberPickers = new ArrayList<NumberPicker>();
	
	// create ArrayList doNotAlter (contains locations of disabled numberpickers)
	ArrayList<Integer> doNotAlter = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
		
		// get saved game from savings
		Context mContext = getApplicationContext();
	    GameSavings gs = new GameSavings(mContext);
	    boolean saved_game = gs.getGameSaved();
	    
		// declare object game arrays
		Object[] gameArrays;
		
		// check for saved game
		if (saved_game == true) {
			CreateGame game = new CreateGame(mContext);
			gameArrays = game.savedGame();
			timeElapsed = gs.getElapsedTime();
		}
		
		// else open a new game
		else {
			CreateGame game = new CreateGame(mContext);
			gameArrays = game.newGame();
			timeElapsed = 0;
		}
		
		// get game arrays
		symbolsArray = (String[]) gameArrays[0];
		numbersArray = (int[]) gameArrays[1];
		charactersArray = (String[]) gameArrays[2];
		answersArray = (int[]) gameArrays[3];
		
		// build the game
		buildGame(symbolsArray, charactersArray);
		
		// start counting the time
		startTime = System.currentTimeMillis();	
	}
	
	// method that builds the game using the gameArrays
	public void buildGame(String[] symbolsArray, String[] charactersArray) {
		
		// put characters in TextViews
		for (int i = 0; i < 9; i++) {
			
			// get the string from charactersArray
			String temp = charactersArray[i];
			
			// reverse the string and change into char array
			String reverse = new StringBuilder(new String(temp)).reverse().toString();
			char[] temp1 = reverse.toCharArray();
			int count = 0;
			for (int k = 3; k > -1; k--){
				
				// find textView by id
				String textViewID = "characterView" + i + "_" + k;
				int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
				TextView text = (TextView) findViewById(resID);
				
				// set character if there is any (string can be shorter than four)
				if (temp1.length > count) {
					text.setText(String.valueOf(temp1[count]));
				}
				else {
					text.setText(" ");
				}
				count += 1;
			}
		}
		
		// put symbols in TextViews (+-x:=)
		for (int j = 0; j < 12; j++) {
			String symbolViewID = "symbolView" + j;
			int resID = getResources().getIdentifier(symbolViewID, "id", "nl.mprog.jelleswester.breinbreker");
			TextView symbol = (TextView) findViewById(resID);
			symbol.setText(symbolsArray[j]);
		}
		
		// set the NumberPickers - loop through nine complete pickers
		for (int i = 0; i < 9; i++) {
			
			// get the right number from the answersArray to be displayed
			int number = answersArray[i];
			int length = String.valueOf(numbersArray[i]).length();
			int[] digits = new int[4];
			int power = 3;
			
			// store the digits of the answersArray seperately
			for (int k = 0; k < 4; k ++) {
				digits[k] = number / ((int)Math.pow(10,power));
				number = number - (digits[k] * (int)Math.pow(10,power));
				power -= 1;
			}
			
			// loop through the four pickers
			for (int j = 0; j < 4; j++) {
				
				// find NumberPicker by id
				String textViewID = "numberPicker" + i + "_" + j;
				int resID = getResources().getIdentifier(textViewID, "id", "nl.mprog.jelleswester.breinbreker");
				NumberPicker np = (NumberPicker) findViewById(resID);
				
				// set features NumberPicker - check if it needs to be disabled
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
				
				// when NumberPicker gets scrolled
				np.setOnScrollListener(new NumberPicker.OnScrollListener() {

			        @Override
			        public void onScrollStateChange(NumberPicker picker, int scrollState) {
			            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
			            	
			            	// change answersArray and numberPickers accordingly
			            	GameController game = new GameController();
			            	Object[] changedArrays = game.changeAnswer(numberPickers, answersArray, numbersArray, picker.getValue(), picker.getId(), doNotAlter);
			            	answersArray = (int[])changedArrays[0];
			            	numberPickers = (ArrayList<NumberPicker>)changedArrays[1];
							
							// check whether the game is finished
			            	if (game.wonGame(numbersArray,answersArray)) {
								startActivity(new Intent(GamePlayActivity.this, YouWonActivity.class));
					         	finish();
							}
			            }
			        }
			    });
				
				// add NumberPicker to numberPickers ArrayList
				numberPickers.add(np);
			}
		}
	}
  	
  	// activates when activity is ending
	@Override
	public void onPause() {
    	super.onPause();
    	
	    // update the elapsed time
	    timeElapsed = timeElapsed + (System.currentTimeMillis() - startTime);
	    
	    // save the game
	    Context mContext = getApplicationContext();
	    GameSavings gs = new GameSavings(mContext);
	    gs.saveGame(numbersArray, answersArray, charactersArray, symbolsArray, timeElapsed);
	}
  	
  	// activates when back button is pressed
  	public void backButton(View view) {
      	startActivity(new Intent(this, StartScreenActivity.class));
      	finish();
   	}
	
  	// activates when youWon button is pressed
  	public void youWonButton(View view) {
  		startActivity(new Intent(this, YouWonActivity.class));
     	finish();
  	}
  	
  	// activates when androids back button is pressed
  	@Override
	public void onBackPressed() {
	    startActivity(new Intent(this, StartScreenActivity.class));
	    finish();
	}
}
