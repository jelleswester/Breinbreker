package nl.mprog.jelleswester.breinbreker;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GamePlay extends ActionBarActivity {

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
		int[][] temp;
		
		// yes? open saved game
		if (saved_game == true) {
			temp = savedGame();	
		}
		
		// no? open new game
		else {
			temp = newGame();
		}
		
		// get symbolsArray and numbersArray
		int[] symbolsArray = temp[0];
		int[] numbersArray = temp[1];
		
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
	
	// method that opens saved game array
	public int[][] savedGame() {
		
		Context mContext = getApplicationContext();
	    SharedPreferences mPrefs = mContext.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
	    SharedPreferences.Editor sEdit = mPrefs.edit();
		
		// declare symbolArray & game_numbers
		int[] symbolArray = new int[12];
		int[] game_numbers = new int[9];
		
		for (int i = 0; i < 12; i++) {
			symbolArray[i] = mPrefs.getInt("symbol" + i, 1);
		}
		
		for (int j = 0; j < 9; j++) {
			game_numbers[j] = mPrefs.getInt("game_number" + j, 0);
		}
		
		return new int[][]{symbolArray, game_numbers};
		
		
	}
	
	// method that creates a new game array
	public int[][] newGame() {
		
		// pick random number from 1-10
		Random rand = new Random();
//		int variant = rand.nextInt(9);
		int variant = 9;
		
		// declare symbolArray
		int[] symbolArray = new int[12];
		
		// declare game_numbers
		int[] game_numbers = new int[9];
		
		if (variant == 100){
			int[] signsArray = {1,5,1,1,1,1,5,5,5,5,1,5};
			symbolArray = signsArray;
			game_numbers[0] = 1;
			game_numbers[1] = 1;
			game_numbers[2] = 2;
			game_numbers[3] = 1;
			game_numbers[4] = 1;
			game_numbers[5] = 2;
			game_numbers[6] = 2;
			game_numbers[7] = 2;
			game_numbers[8] = 4;
		}
		
		// variant 0
		else if (variant == 0) {
			
			int[] signsArray = {1,5,1,1,1,1,5,5,5,5,1,5};
			symbolArray = signsArray;
			
			// declare int array numbers
			int[] numbers = new int[4];
			
			// declare right_puzzle
			boolean right_puzzle = false;
			
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				
				game_numbers[0] = numbers[0];
				game_numbers[1] = numbers[1];
				game_numbers[2] = numbers[0] + numbers[1];
				game_numbers[3] = numbers[2];
				game_numbers[4] = numbers[3];
				game_numbers[5] = numbers[2] + numbers[3];
				game_numbers[6] = numbers[0] + numbers[2];
				game_numbers[7] = numbers[1] + numbers[3];
				game_numbers[8] = game_numbers[6] + game_numbers[7];
				
				if ((game_numbers[0] + game_numbers[1] > 9999)) {
					right_puzzle = false;
				}
				else if ((game_numbers[0] + game_numbers[3]) > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[3] + game_numbers[4]) > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[6] + game_numbers[7]) > 9999) {
					right_puzzle = false;
				}
			}
		}
			
		// variant 1
		else if (variant == 1) {
			
			int[] signsArray = {1,5,1,2,2,2,5,5,5,5,2,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				right_puzzle = true;
				
				for (int i = 0; i < 4; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				numbers[2] = numbers[1];
				
				if ((numbers[0] + numbers[1] > 9999)) {
					right_puzzle = false;
				}
				else if ((numbers[0] + numbers[2]) > 9999) {
					right_puzzle = false;
				}
				else if ((numbers[1] - numbers[3]) < 0) {
					right_puzzle = false;
				}
				else if ((numbers[2] - numbers[3]) < 0) {
					right_puzzle = false;
				}
				else if (((numbers[0] + numbers[2]) - (numbers[1] - numbers[3])) < 0){
					right_puzzle = false;
				}
				else if (((numbers[0] + numbers[1]) - (numbers[2] - numbers[3])) < 0){
					right_puzzle = false;
				}
			}
			
			game_numbers[0] = numbers[0];
			game_numbers[1] = numbers[1];
			game_numbers[2] = numbers[0] + numbers[1];
			game_numbers[3] = numbers[2];
			game_numbers[4] = numbers[3];
			game_numbers[5] = numbers[2] - numbers[3];
			game_numbers[6] = numbers[0] + numbers[2];
			game_numbers[7] = numbers[1] - numbers[3];
			game_numbers[8] = game_numbers[6] - game_numbers[7];
			
		}
		
		// variant 2
		else if (variant == 2) {
			
			int[] signsArray = {1,5,1,1,2,2,5,5,5,5,2,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				
				right_puzzle = true;
				
				for (int i = 0; i < 3; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				
				if (numbers[1] > numbers[2]) {
					numbers[3] = numbers[1] - numbers[2];
				}
				else {
					numbers[3] = numbers[2] - numbers[1];
				}
				
				if ((numbers[0] + numbers[1]) > 9999) {
					right_puzzle = false;
				}
				else if ((numbers[0] + numbers[2]) > 9999) {
					right_puzzle = false;
				}
				else if ((numbers[1] + numbers[3]) > 9999) {
					right_puzzle = false;
				}
				else if ((numbers[2] - numbers[3]) < 0) {
					right_puzzle = false;
				}
				else if (((numbers[0] + numbers[2]) - (numbers[1] + numbers[3])) < 0 ) {
					right_puzzle = false;
				}
				else if (((numbers[0] + numbers[1]) - (numbers[2] - numbers[3])) < 0) {
					right_puzzle = false;
				}
			}
			
			game_numbers[0] = numbers[0];
			game_numbers[1] = numbers[1];
			game_numbers[2] = numbers[0] + numbers[1];
			game_numbers[3] = numbers[2];
			game_numbers[4] = numbers[3];
			game_numbers[5] = numbers[2] - numbers[3];
			game_numbers[6] = numbers[0] + numbers[2];
			game_numbers[7] = numbers[1] + numbers[3];
			game_numbers[8] = game_numbers[6] - game_numbers[7];
		}
		
		// variant 3
		else if (variant == 3) {
			
			int[] signsArray = {3,5,3,1,2,1,5,5,5,5,1,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				
				right_puzzle = true;
				
				numbers[0] = rand.nextInt(9998) + 1;
				if ((numbers[0] % 2) == 0) {
					numbers[0] = numbers[0] - 1;
				}
				numbers[1] = rand.nextInt(9998) + 1;
				numbers[2] = rand.nextInt(9998) + 1;
				
				if (((numbers[1]*(numbers[0] - 1)) > (numbers[2]*(numbers[0] - 1))) && 
						((((numbers[1]*(numbers[0] - 1)) - (numbers[2]*(numbers[0] - 1))) % 2)) == 0) {
					numbers[3] = ((numbers[1]*(numbers[0] - 1)) - (numbers[2]*(numbers[0] - 1)))/2;
					
					if ((numbers[0] * numbers[1]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[0] * numbers[2]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[1] + numbers[3]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[2] + numbers[3]) > 9999) {
						right_puzzle = false;
					}
					else if (((numbers[0] * numbers[2]) + (numbers[1] + numbers[3])) > 9999) {
						right_puzzle = false;
					}
					else if (((numbers[0] * numbers[1]) - (numbers[2] + numbers[3])) < 0) {
						right_puzzle = false;
					}
					
					game_numbers[0] = numbers[0];
					game_numbers[1] = numbers[1];
					game_numbers[2] = numbers[0] * numbers[1];
					game_numbers[3] = numbers[2];
					game_numbers[4] = numbers[3];
					game_numbers[5] = numbers[2] + numbers[3];
					game_numbers[6] = numbers[0] * numbers[2];
					game_numbers[7] = numbers[1] + numbers[3];
					game_numbers[8] = game_numbers[6] + game_numbers[7];
				}
				else {
					right_puzzle = false;
				}
			}
		}
		
		// variant 4
		else if (variant == 4) {
			int[] signsArray = {3,5,3,1,1,2,5,5,5,5,1,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				
				right_puzzle = true;
				
				for (int i = 0; i < 3; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				
				if ((((numbers[0] - 1)*(numbers[1] - numbers[2])) % 2 == 0) && ((numbers[1] - numbers[2]) > 0)) {
					numbers[3] = ((numbers[0] - 1)*(numbers[1] - numbers[2])) / 2;
					
					if ((numbers[0] * numbers[1]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[0] * numbers[2]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[1] + numbers[3]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[2] - numbers[3]) < 0) {
						right_puzzle = false;
					}
					else if (((numbers[0] * numbers[2]) + (numbers[1] + numbers[3])) > 9999) {
						right_puzzle = false;
					}
					else if (((numbers[0] * numbers[1]) + (numbers[2] - numbers[3])) > 9999) {
						right_puzzle = false;
					}
				}
				else {
					right_puzzle = false;
				}
			}
			
			game_numbers[0] = numbers[0];
			game_numbers[1] = numbers[1];
			game_numbers[2] = numbers[0] * numbers[1];
			game_numbers[3] = numbers[2];
			game_numbers[4] = numbers[3];
			game_numbers[5] = numbers[2] - numbers[3];
			game_numbers[6] = numbers[0] * numbers[2];
			game_numbers[7] = numbers[1] + numbers[3];
			game_numbers[8] = game_numbers[6] + game_numbers[7];
		}
		
		// variant 5
		else if (variant == 5) {
			int[] signsArray = {3,5,1,3,2,2,5,5,5,5,1,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				
				right_puzzle = true;
				
				for (int i = 0; i < 3; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				
				if (numbers[1] % 2 == 0) {
					numbers[1] = numbers[1] - 1;
				}
				System.out.println("punt 1");
				
				if ((numbers[0] - numbers[3]) > 0) {
					numbers[3] = (numbers[1] - 1) * (numbers[0] - numbers[3]);
					
					System.out.println("punt 2");
					if ((numbers[0] * numbers[1]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[0] + numbers[2]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[1] * numbers[3]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[2] - numbers[3]) < 0) {
						right_puzzle = false;
					}
					else if (((numbers[0] + numbers[2]) + (numbers[1] * numbers[3])) > 9999) {
						right_puzzle = false;
					}
					else if (((numbers[0] * numbers[1]) - (numbers[2] - numbers[3])) < 0) {
						right_puzzle = false;
					}
				}
				else {
					right_puzzle = false;
				}
			}
			
			game_numbers[0] = numbers[0];
			game_numbers[1] = numbers[1];
			game_numbers[2] = numbers[0] * numbers[1];
			game_numbers[3] = numbers[2];
			game_numbers[4] = numbers[3];
			game_numbers[5] = numbers[2] - numbers[3];
			game_numbers[6] = numbers[0] + numbers[2];
			game_numbers[7] = numbers[1] * numbers[3];
			game_numbers[8] = game_numbers[6] + game_numbers[7];
		}
		
		// variant 6
		else if (variant == 6) {
			int[] signsArray = {3,5,1,2,1,2,5,5,5,5,3,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				
				right_puzzle = true;
				
				for (int i = 0; i < 3; i++) {
					numbers[i] = numbers[i] = rand.nextInt(9998) + 1;
				}
				
				if ((numbers[2] * (numbers[1] - 1)) % (numbers[0] + numbers[2] + 1) == 0) {
					numbers[3] = (numbers[2] * (numbers[1] - 1)) / (numbers[0] + numbers[2] + 1);
					
					if ((numbers[0] * numbers[1]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[0] + numbers[2]) > 9999) {
						right_puzzle = false;
					}
					else if ((numbers[1] - numbers[3]) < 0) {
						right_puzzle = false;
					}
					else if ((numbers[2] - numbers[3]) < 0) {
						right_puzzle = false;
					}
					else if (((numbers[0] + numbers[2]) * (numbers[1] - numbers[3])) > 9999) {
						right_puzzle = false;
					}
					else if (((numbers[0] * numbers[1]) + (numbers[2] - numbers[3])) > 9999) {
						right_puzzle = false;
					}
				}
				else {
					right_puzzle = false;
				}
				
			}
			
			game_numbers[0] = numbers[0];
			game_numbers[1] = numbers[1];
			game_numbers[2] = numbers[0] * numbers[1];
			game_numbers[3] = numbers[2];
			game_numbers[4] = numbers[3];
			game_numbers[5] = numbers[2] - numbers[3];
			game_numbers[6] = numbers[0] + numbers[2];
			game_numbers[7] = numbers[1] - numbers[3];
			game_numbers[8] = game_numbers[6] * game_numbers[7];
		}
		
		// variant 7
		else if (variant == 7) {
			int[] signsArray = {4,5,2,3,1,1,5,5,5,5,2,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				right_puzzle = true;
				
				for (int i = 0; i < 3; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				
				if ((numbers[2] % 2) != 0) {
					numbers[2] = numbers[2] + 1;
				}
				
				game_numbers[0] = numbers[0] * numbers[1];
				game_numbers[1] = numbers[0];
				game_numbers[2] = numbers[1];
				game_numbers[4] = numbers[2];
				game_numbers[5] = ((game_numbers[2] - game_numbers[4]) * (game_numbers[1] - 1)) / 2;
				game_numbers[3] = game_numbers[5] - game_numbers[4];
				game_numbers[6] = game_numbers[0] - game_numbers[3];
				game_numbers[7] = game_numbers[1] * game_numbers[4];
				game_numbers[8] = game_numbers[6] - game_numbers[7];
				
				if (game_numbers[0] > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[0] - game_numbers[3]) < 0) {
					right_puzzle = false;
				}
				else if ((game_numbers[1] * game_numbers[4]) > 9999) {
					right_puzzle = false;
				}
				else if ((numbers[3] + numbers[4]) > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[2] + game_numbers[5]) > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[6] - game_numbers[7]) < 0) {
					right_puzzle = false;
				}
			}	
		}
		
		// variant 8
		else if (variant == 8) {
			int[] signsArray = {4,5,2,3,1,2,5,5,5,5,2,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				right_puzzle = true;
				
				for (int i = 0; i < 3; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				
				if (numbers[0] % 2 == 0) {
					numbers[0] = numbers[0] + 1;
				}
				
				game_numbers[0] = numbers[0] * numbers[1];
				game_numbers[1] = numbers[0];
				game_numbers[2] = numbers[1];
				game_numbers[4] = numbers[2];
				game_numbers[5] = ((numbers[1] * (numbers[0] - 1)) - (numbers[2] * (numbers[0] + 1))) / 2;
				game_numbers[3] = game_numbers[4] + game_numbers[5];
				game_numbers[6] = game_numbers[0] - game_numbers[3];
				game_numbers[7] = game_numbers[1] * game_numbers[4];
				game_numbers[8] = game_numbers[6] - game_numbers[7];
				
				if ((game_numbers[0] > game_numbers[1]) || ((game_numbers[0] % game_numbers[1]) != 0)) {
					right_puzzle = false;
				}
				else if ((game_numbers[0] - game_numbers[3]) < 0) {
					right_puzzle = false;
				}
				else if ((game_numbers[1] * game_numbers[4]) > 9999) {
					right_puzzle = false;
				}
				else if ((numbers[3] - numbers[4]) < 0) {
					right_puzzle = false;
				}
				else if ((game_numbers[2] + game_numbers[5]) > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[6] - game_numbers[7]) < 0) {
					right_puzzle = false;
				}
			}
		}
		
		// variant 9
		else if (variant == 9) {
			int[] signsArray = {4,5,2,1,1,1,5,5,5,5,2,5};
			symbolArray = signsArray;
			
			boolean right_puzzle = false;
			
			int[] numbers = new int[4];
			
			while (right_puzzle == false) {
				right_puzzle = true;
				
				for (int i = 0; i < 3; i++) {
					numbers[i] = rand.nextInt(9998) + 1;
				}
				
				if ((numbers[0] % 2) != 0) {
					numbers[0] = numbers[0] + 1;
				}
				
				if ((numbers[1] % 2) != 0) {
					numbers[1] = numbers[1] + 1;
				}
				
				game_numbers[0] = numbers[0] * numbers[1];
				game_numbers[1] = numbers[0];
				game_numbers[2] = numbers[1];
				game_numbers[3] = numbers[2];
				game_numbers[5] = ((game_numbers[1] * (game_numbers[2] - 1)) - game_numbers[2]) / 2;
				game_numbers[4] = game_numbers[5] - game_numbers[3];
				game_numbers[6] = game_numbers[0] - game_numbers[3];
				game_numbers[7] = game_numbers[1] + game_numbers[4];
				game_numbers[8] = game_numbers[6] - game_numbers[7];
				
				if (game_numbers[0] > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[0] - game_numbers[3]) < 0) {
					right_puzzle = false;
				}
				else if ((game_numbers[1] + game_numbers[4]) > 9999) {
					right_puzzle = false;
				}
				else if ((numbers[3] + numbers[4]) > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[2] + game_numbers[5]) > 9999) {
					right_puzzle = false;
				}
				else if ((game_numbers[6] - game_numbers[7]) < 0) {
					right_puzzle = false;
				}
			}
		}
		
		// return symbolArray and game_numbers
		return new int[][]{symbolArray, game_numbers};
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
