package nl.mprog.jelleswester.breinbreker;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
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
		
		// declare symbolArray & numbersArray
		int[] symbolArray = new int[12];
		int[] numbersArray = new int[9];
		
		for (int i = 0; i < 12; i++) {
			symbolArray[i] = mPrefs.getInt("symbol" + i, 1);
		}
		
		for (int j = 0; j < 9; j++) {
			numbersArray[j] = mPrefs.getInt("game_number" + j, 0);
		}
		
		return new int[][]{symbolArray, numbersArray};
		
		
	}
	
	// method that creates a new game array
	public int[][] newGame() {
		
		// declare int[] symbolsArray and int[] numbersArray
		int[] symbolsArray = new int[12];
		int[] numbersArray = new int[9];
		
		// declare int[] temp_number
		int[] temp_number = new int[4];
		
		// declare boolean right_puzzle
		boolean right_puzzle = false;
		
		// pick random number between 0 and 9
		Random rand = new Random();
//		int variant = rand.nextInt(18);
		int variant = 18;
		
		int count = 0;
		
		if (variant == 0) {
			
			// create symbolsArray
			symbolsArray = new int[]{1,5,1,1,1,1,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] + temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] + temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 1) {
			
			// create symbolsArray
			symbolsArray = new int[]{2,5,2,2,2,2,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] - temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] - temp_number[2];
				numbersArray[7] = temp_number[1] - temp_number[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 2) {
			
			// create symbolsArray
			symbolsArray = new int[]{1,5,2,1,2,2,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] + temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] - temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 3) {
			
			// create symbolsArray
			symbolsArray = new int[]{1,5,1,2,1,2,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] + temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] - temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 4) {
			
			// create symbolsArray
			symbolsArray = new int[]{2,5,1,1,1,2,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] - temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 5) {
			
			// create symbolsArray
			symbolsArray = new int[]{2,5,1,2,1,1,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] - temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] + temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] - temp_number[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 6) {
			
			// create symbolsArray
			symbolsArray = new int[]{1,5,2,2,2,1,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] + temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] + temp_number[3];
				numbersArray[6] = temp_number[0] - temp_number[2];
				numbersArray[7] = temp_number[1] - temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 7) {
			
			// create symbolsArray
			symbolsArray = new int[]{2,5,2,1,2,1,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false){ 
			
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] - temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] + temp_number[3];
				numbersArray[6] = temp_number[0] - temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 8) {
			
			// create symbolsArray
			symbolsArray = new int[]{1,5,1,2,2,2,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				temp_number[2] = temp_number[1];
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] + temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] - temp_number[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 9) {
			
			// create symbolsArray
			symbolsArray = new int[]{2,5,2,1,1,1,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				temp_number[2] = temp_number[1];
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] - temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] + temp_number[3];
				numbersArray[6] = temp_number[0] - temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 10) {
			
			// create symbolsArray
			symbolsArray = new int[]{1,5,1,1,2,2,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 3 random integers between 1 and 9999
				for (int i = 0; i < 3; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				temp_number[3] = temp_number[2] - temp_number[1];
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] + temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 11) {
			
			// create symbolsArray
			symbolsArray = new int[]{2,5,2,2,1,1,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 3 random integers between 1 and 9999
				for (int i = 0; i < 3; i++) {
					temp_number[i] = rand.nextInt(9998) + 1;
				}
				temp_number[3] = temp_number[1] - temp_number[2];
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] - temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] + temp_number[3];
				numbersArray[6] = temp_number[0] - temp_number[2];
				numbersArray[7] = temp_number[1] - temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 12) {
			
			// create symbolsArray
			symbolsArray = new int[]{3,5,3,1,2,1,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 suitable random integers between 1 and 9999
				boolean condition = false;
				while (condition == false) {
					temp_number[0] = 2;
					while ((temp_number[0] % 2) == 0) {
						temp_number[0] = rand.nextInt(9996) + 2;
					}
					temp_number[1] = rand.nextInt(9998) + 1;
					temp_number[2] = rand.nextInt(9998) + 1;
					if ((temp_number[1] * (temp_number[0] - 1)) > (temp_number[2] * (temp_number[0] + 1))) {
						temp_number[3] = ((temp_number[1] * (temp_number[0] - 1)) - (temp_number[2] * (temp_number[0] + 1))) / 2;
						condition = true;
					}
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] * temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] + temp_number[3];
				numbersArray[6] = temp_number[0] * temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 13) {
			
			// create symbolsArray
			symbolsArray = new int[]{3,5,3,1,1,2,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 suitable random integers between 1 and 9999
				temp_number[0] = 2;
				while ((temp_number[0] % 2) == 0) {
					temp_number[0] = rand.nextInt(9996) + 2;
				}
				temp_number[1] = rand.nextInt(9998) + 1;
				temp_number[2] = rand.nextInt(9998) + 1;
				temp_number[3] =((temp_number[0] - 1)*(temp_number[1] - temp_number[2])) / 2;
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] * temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] * temp_number[2];
				numbersArray[7] = temp_number[1] + temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 14) {
			
			// create symbolsArray
			symbolsArray = new int[]{3,5,1,3,2,2,5,5,5,5,1,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 suitable random integers between 1 and 9999
				temp_number[0] = rand.nextInt(9998) + 1;
				temp_number[1] = 2;
				while ((temp_number[1] % 2) == 0) {
					temp_number[1] = rand.nextInt(9996) + 2;
				}
				temp_number[3] = rand.nextInt(9998) + 1;
				temp_number[2] = ((temp_number[1] - 1) * (temp_number[0] - temp_number[3])) / 2;
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] * temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] * temp_number[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}	
		}
		
		else if (variant == 15) {
			
			// create symbolsArray
			symbolsArray = new int[]{3,5,1,2,1,2,5,5,5,5,3,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 suitable random integers between 1 and 9999
				boolean condition = false;
				while (condition == false) {
					for (int i = 0; i < 3; i++) {
						temp_number[i] = temp_number[i] = rand.nextInt(500) + 3;
					}
					if ((temp_number[2] * (temp_number[1] - 1)) % (temp_number[0] + temp_number[2] - 1) == 0) {
						condition = true;
						temp_number[3] = (temp_number[2] * (temp_number[1] - 1)) / (temp_number[0] + temp_number[2] - 1);
					}
				}
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0];
				numbersArray[1] = temp_number[1];
				numbersArray[2] = temp_number[0] * temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[4] = temp_number[3];
				numbersArray[5] = temp_number[2] - temp_number[3];
				numbersArray[6] = temp_number[0] + temp_number[2];
				numbersArray[7] = temp_number[1] - temp_number[3];
				numbersArray[8] = numbersArray[6] * numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 16) {
			
			// create symbolsArray
			symbolsArray = new int[]{4,5,2,3,1,1,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 suitable random integers between 1 and 9999				
				temp_number[0] = 2;
				while ((temp_number[0] % 2) == 0) {
					temp_number[0] = rand.nextInt(9996) + 2;
				}
				temp_number[1] = rand.nextInt(9998) + 1;
				temp_number[2] = rand.nextInt(9998) + 1;
				temp_number[3] = ((temp_number[1] - temp_number[2]) * (temp_number[0] - 1)) / 2;
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0] * temp_number[1];
				numbersArray[1] = temp_number[0];
				numbersArray[2] = temp_number[1];
				numbersArray[4] = temp_number[2];
				numbersArray[5] = temp_number[3];
				numbersArray[3] = numbersArray[5] - numbersArray[4];
				numbersArray[6] = numbersArray[0] - numbersArray[3];
				numbersArray[7] = numbersArray[1] * numbersArray[4];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}	
		}
		
		else if (variant == 17) {
			
			// create symbolsArray
			symbolsArray = new int[]{4,5,2,3,1,2,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 suitable random integers between 1 and 9999
				temp_number[0] = 2;
				while ((temp_number[0] % 2) == 0) {
					temp_number[0] = rand.nextInt(9996) + 2;
				}
				temp_number[1] = rand.nextInt(9998) + 1;
				temp_number[2] = rand.nextInt(9998) + 1;
				temp_number[3] = ((temp_number[1] * (temp_number[0] - 1)) - (temp_number[2] * (temp_number[0] + 1))) / 2;
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0] * temp_number[1];
				numbersArray[1] = temp_number[0];
				numbersArray[2] = temp_number[1];
				numbersArray[4] = temp_number[2];
				numbersArray[5] = temp_number[3];
				numbersArray[3] = numbersArray[4] + numbersArray[5];
				numbersArray[6] = numbersArray[0] - numbersArray[3];
				numbersArray[7] = numbersArray[1] * numbersArray[4];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		else if (variant == 18) {
			
			// create symbolsArray
			symbolsArray = new int[]{4,5,2,1,1,1,5,5,5,5,2,5};
			
			// create numbersArray
			while (right_puzzle == false) {
				
				// set right_puzzle to true
				right_puzzle = true;
				
				// pick 4 suitable random integers between 1 and 9999
				temp_number[0] = 1;
				while ((temp_number[0] % 2) != 0) {
					temp_number[0] = rand.nextInt(9998) + 1;
				}
				temp_number[1] = 1;
				while ((temp_number[1] % 2) != 0) {
					temp_number[1] = rand.nextInt(9998) + 1;
				}
				temp_number[2] = rand.nextInt(9998) + 1;
				temp_number[3] = ((temp_number[0] * (temp_number[1] - 1)) - temp_number[1]) / 2;
				
				// set all numbers puzzle
				numbersArray[0] = temp_number[0] * temp_number[1];
				numbersArray[1] = temp_number[0];
				numbersArray[2] = temp_number[1];
				numbersArray[3] = temp_number[2];
				numbersArray[5] = temp_number[3];
				numbersArray[4] = numbersArray[5] - numbersArray[3];
				numbersArray[6] = numbersArray[0] - numbersArray[3];
				numbersArray[7] = numbersArray[1] + numbersArray[4];
				numbersArray[8] = numbersArray[6] - numbersArray[7];
				
				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						right_puzzle = false;
					}
				}
			}
		}
		
		// return symbolsArray and numbersArray
		return new int[][]{symbolsArray, numbersArray};
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
