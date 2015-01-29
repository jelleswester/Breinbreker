/**
 * Jelles Wester
 * jelleswester@gmail.com
 * 10004531
 * 
 * CreateGame:
 * This class offer methods to open a saved game, create a new game, randomly convert
 * numbers to characters and convert numbers to symbols (+,-,x,:,=).
 */

package nl.mprog.jelleswester.breinbreker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;

public class CreateGame {

	Context c;

	public CreateGame(Context context) {
		c = context;
	}

	// method that opens saved game arrays
	public Object[] savedGame() {

		// open GameSavings
		GameSavings gs = new GameSavings(c);

		// get symbolsArray
		String[] symbolsArray = gs.getSymbolsArray();

		// get numbersArray
		int[] numbersArray = gs.getNumbersArray();

		// get charactersArray
		String[] charactersArray = gs.getCharactersArray();

		// get answersArray
		int[] answersArray = gs.getAnswersArray();

		// get hintNumber
		long[] hintNumber = new long[1];
		hintNumber[0] = gs.getHintNumber();

		// get givenHints ArrayList
		ArrayList<Integer> givenHints = gs.getGivenHints();

		// return all
		return new Object[] { symbolsArray, numbersArray, charactersArray,
				answersArray, hintNumber, givenHints };

	}

	// method that creates a new game
	public Object[] newGame() {

		// declare int[] signsArray,int[] numbersArray and String[]
		// charactersArray
		int[] signsArray = new int[12];
		int[] numbersArray = new int[9];
		String[] charactersArray = new String[9];
		int[] answersArray = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		// declare int[] tempNumber
		int[] tempNumber = new int[4];

		// declare boolean rightPuzzle
		boolean rightPuzzle = false;

		// pick random number between 0 and 18
		Random rand = new Random();
		int variant = rand.nextInt(18);

		// proceed to the right variant
		if (variant == 0) {

			// create signsArray
			signsArray = new int[] { 1, 5, 1, 1, 1, 1, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] + tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] + tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 1) {

			// create signsArray
			signsArray = new int[] { 2, 5, 2, 2, 2, 2, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] - tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] - tempNumber[2];
				numbersArray[7] = tempNumber[1] - tempNumber[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 2) {

			// create signsArray
			signsArray = new int[] { 1, 5, 2, 1, 2, 2, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] + tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] - tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 3) {

			// create signsArray
			signsArray = new int[] { 1, 5, 1, 2, 1, 2, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] + tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] - tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 4) {

			// create signsArray
			signsArray = new int[] { 2, 5, 1, 1, 1, 2, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] - tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 5) {

			// create signsArray
			signsArray = new int[] { 2, 5, 1, 2, 1, 1, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] - tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] + tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] - tempNumber[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 6) {

			// create signsArray
			signsArray = new int[] { 1, 5, 2, 2, 2, 1, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] + tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] + tempNumber[3];
				numbersArray[6] = tempNumber[0] - tempNumber[2];
				numbersArray[7] = tempNumber[1] - tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 7) {

			// create signsArray
			signsArray = new int[] { 2, 5, 2, 1, 2, 1, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] - tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] + tempNumber[3];
				numbersArray[6] = tempNumber[0] - tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 8) {

			// create signsArray
			signsArray = new int[] { 1, 5, 1, 2, 2, 2, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}
				tempNumber[2] = tempNumber[1];

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] + tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] - tempNumber[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 9) {

			// create signsArray
			signsArray = new int[] { 2, 5, 2, 1, 1, 1, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 random integers between 1 and 9999
				for (int i = 0; i < 4; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}
				tempNumber[2] = tempNumber[1];

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] - tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] + tempNumber[3];
				numbersArray[6] = tempNumber[0] - tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 10) {

			// create signsArray
			signsArray = new int[] { 1, 5, 1, 1, 2, 2, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 3 random integers between 1 and 9999
				for (int i = 0; i < 3; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}
				tempNumber[3] = tempNumber[2] - tempNumber[1];

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] + tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 11) {

			// create signsArray
			signsArray = new int[] { 2, 5, 2, 2, 1, 1, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 3 random integers between 1 and 9999
				for (int i = 0; i < 3; i++) {
					tempNumber[i] = rand.nextInt(9998) + 1;
				}
				tempNumber[3] = tempNumber[1] - tempNumber[2];

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] - tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] + tempNumber[3];
				numbersArray[6] = tempNumber[0] - tempNumber[2];
				numbersArray[7] = tempNumber[1] - tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 12) {

			// create signsArray
			signsArray = new int[] { 3, 5, 3, 1, 2, 1, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 suitable random integers between 1 and 9999
				boolean condition = false;
				while (condition == false) {
					tempNumber[0] = 2;
					while ((tempNumber[0] % 2) == 0) {
						tempNumber[0] = rand.nextInt(9996) + 2;
					}
					tempNumber[1] = rand.nextInt(9998) + 1;
					tempNumber[2] = rand.nextInt(9998) + 1;
					if ((tempNumber[1] * (tempNumber[0] - 1)) > (tempNumber[2] * (tempNumber[0] + 1))) {
						tempNumber[3] = ((tempNumber[1] * (tempNumber[0] - 1)) - (tempNumber[2] * (tempNumber[0] + 1))) / 2;
						condition = true;
					}
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] * tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] + tempNumber[3];
				numbersArray[6] = tempNumber[0] * tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 13) {

			// create signsArray
			signsArray = new int[] { 3, 5, 3, 1, 1, 2, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 suitable random integers between 1 and 9999
				tempNumber[0] = 2;
				while ((tempNumber[0] % 2) == 0) {
					tempNumber[0] = rand.nextInt(9996) + 2;
				}
				tempNumber[1] = rand.nextInt(9998) + 1;
				tempNumber[2] = rand.nextInt(9998) + 1;
				tempNumber[3] = ((tempNumber[0] - 1) * (tempNumber[1] - tempNumber[2])) / 2;

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] * tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] * tempNumber[2];
				numbersArray[7] = tempNumber[1] + tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 14) {

			// create signsArray
			signsArray = new int[] { 3, 5, 1, 3, 2, 2, 5, 5, 5, 5, 1, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 suitable random integers between 1 and 9999
				tempNumber[0] = rand.nextInt(9998) + 1;
				tempNumber[1] = 2;
				while ((tempNumber[1] % 2) == 0) {
					tempNumber[1] = rand.nextInt(9996) + 2;
				}
				tempNumber[3] = rand.nextInt(9998) + 1;
				tempNumber[2] = ((tempNumber[1] - 1) * (tempNumber[0] - tempNumber[3])) / 2;

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] * tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] * tempNumber[3];
				numbersArray[8] = numbersArray[6] + numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 15) {

			// create signsArray
			signsArray = new int[] { 3, 5, 1, 2, 1, 2, 5, 5, 5, 5, 3, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 suitable random integers between 1 and 9999
				boolean condition = false;
				while (condition == false) {
					for (int i = 0; i < 3; i++) {
						tempNumber[i] = tempNumber[i] = rand.nextInt(500) + 3;
					}
					if ((tempNumber[2] * (tempNumber[1] - 1))
							% (tempNumber[0] + tempNumber[2] - 1) == 0) {
						condition = true;
						tempNumber[3] = (tempNumber[2] * (tempNumber[1] - 1))
								/ (tempNumber[0] + tempNumber[2] - 1);
					}
				}

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0];
				numbersArray[1] = tempNumber[1];
				numbersArray[2] = tempNumber[0] * tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[4] = tempNumber[3];
				numbersArray[5] = tempNumber[2] - tempNumber[3];
				numbersArray[6] = tempNumber[0] + tempNumber[2];
				numbersArray[7] = tempNumber[1] - tempNumber[3];
				numbersArray[8] = numbersArray[6] * numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 16) {

			// create signsArray
			signsArray = new int[] { 4, 5, 2, 3, 1, 1, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 suitable random integers between 1 and 9999
				tempNumber[0] = 2;
				while ((tempNumber[0] % 2) == 0) {
					tempNumber[0] = rand.nextInt(9996) + 2;
				}
				tempNumber[1] = rand.nextInt(9998) + 1;
				tempNumber[2] = rand.nextInt(9998) + 1;
				tempNumber[3] = ((tempNumber[1] - tempNumber[2]) * (tempNumber[0] - 1)) / 2;

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0] * tempNumber[1];
				numbersArray[1] = tempNumber[0];
				numbersArray[2] = tempNumber[1];
				numbersArray[4] = tempNumber[2];
				numbersArray[5] = tempNumber[3];
				numbersArray[3] = numbersArray[5] - numbersArray[4];
				numbersArray[6] = numbersArray[0] - numbersArray[3];
				numbersArray[7] = numbersArray[1] * numbersArray[4];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 17) {

			// create signsArray
			signsArray = new int[] { 4, 5, 2, 3, 1, 2, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 suitable random integers between 1 and 9999
				tempNumber[0] = 2;
				while ((tempNumber[0] % 2) == 0) {
					tempNumber[0] = rand.nextInt(9996) + 2;
				}
				tempNumber[1] = rand.nextInt(9998) + 1;
				tempNumber[2] = rand.nextInt(9998) + 1;
				tempNumber[3] = ((tempNumber[1] * (tempNumber[0] - 1)) - (tempNumber[2] * (tempNumber[0] + 1))) / 2;

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0] * tempNumber[1];
				numbersArray[1] = tempNumber[0];
				numbersArray[2] = tempNumber[1];
				numbersArray[4] = tempNumber[2];
				numbersArray[5] = tempNumber[3];
				numbersArray[3] = numbersArray[4] + numbersArray[5];
				numbersArray[6] = numbersArray[0] - numbersArray[3];
				numbersArray[7] = numbersArray[1] * numbersArray[4];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		else if (variant == 18) {

			// create signsArray
			signsArray = new int[] { 4, 5, 2, 1, 1, 1, 5, 5, 5, 5, 2, 5 };

			// create numbersArray
			while (rightPuzzle == false) {

				// set rightPuzzle to true
				rightPuzzle = true;

				// pick 4 suitable random integers between 1 and 9999
				tempNumber[0] = 1;
				while ((tempNumber[0] % 2) != 0) {
					tempNumber[0] = rand.nextInt(9998) + 1;
				}
				tempNumber[1] = 1;
				while ((tempNumber[1] % 2) != 0) {
					tempNumber[1] = rand.nextInt(9998) + 1;
				}
				tempNumber[2] = rand.nextInt(9998) + 1;
				tempNumber[3] = ((tempNumber[0] * (tempNumber[1] - 1)) - tempNumber[1]) / 2;

				// set all numbers puzzle
				numbersArray[0] = tempNumber[0] * tempNumber[1];
				numbersArray[1] = tempNumber[0];
				numbersArray[2] = tempNumber[1];
				numbersArray[3] = tempNumber[2];
				numbersArray[5] = tempNumber[3];
				numbersArray[4] = numbersArray[5] - numbersArray[3];
				numbersArray[6] = numbersArray[0] - numbersArray[3];
				numbersArray[7] = numbersArray[1] + numbersArray[4];
				numbersArray[8] = numbersArray[6] - numbersArray[7];

				// check whether all numbers are between 1 and 9999
				for (int j = 0; j < 9; j++) {
					if ((numbersArray[j] < 1) || (numbersArray[j] > 9999)) {
						rightPuzzle = false;
					}
				}
			}
		}

		// create symbolsArray
		String[] symbolsArray = createSymbols(signsArray);

		// create charactersArray
		charactersArray = createCharacters(numbersArray);

		// create hintNumber & givenHints
		long[] hintNumber = new long[1];
		hintNumber[0] = 0;
		ArrayList<Integer> givenHints = new ArrayList<Integer>();

		// return all
		return new Object[] { symbolsArray, numbersArray, charactersArray,
				answersArray, hintNumber, givenHints };
	}

	// method that converts numbersArray randomly to an charactersArray
	public String[] createCharacters(int[] numbersArray) {

		// declare charactersArray
		String[] charactersArray = new String[9];

		// create mapNumbers and mapChars
		int[] mapNumbers = new int[10];
		char[] mapChars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K' };

		// shuffle the numbers of map numbers 0-9
		List<Integer> tempList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			tempList.add(i);
		}
		Collections.shuffle(tempList);
		for (int i = 0; i < 10; i++) {
			mapNumbers[i] = tempList.get(i);
		}

		// loop through numbersArray
		for (int i = 0; i < 9; i++) {

			// transform each number into a string
			String temp = String.valueOf(numbersArray[i]);
			StringBuilder temp1 = new StringBuilder();

			// loop through the characters of the string
			for (int j = 0, k = temp.length(); j < k; j++) {

				char c = temp.charAt(j);

				// change number to character
				for (int l = 0; l < 10; l++) {
					if (Character.getNumericValue(c) == mapNumbers[l]) {
						temp1.append(mapChars[l]);
					}
				}
			}

			String temp2 = temp1.toString();
			charactersArray[i] = temp2;
		}

		// return charactersArray
		return charactersArray;
	}

	// method that converts numbers to symbols
	public String[] createSymbols(int[] symbolsArray) {

		// declare tempArray and returnArray
		int[] tempArray = symbolsArray;
		String[] returnArray = new String[12];

		// create returnArray
		for (int i = 0; i < 12; i++) {
			switch (tempArray[i]) {
			case 1:
				returnArray[i] = "+";
				break;
			case 2:
				returnArray[i] = "-";
				break;
			case 3:
				returnArray[i] = "x";
				break;
			case 4:
				returnArray[i] = ":";
				break;
			case 5:
				returnArray[i] = "=";
				break;
			}
		}

		// return returnArray
		return returnArray;
	}

	// checks whether a certain integer is in a certain integer array
	public boolean contains(final int[] array, final int key) {
		for (final int i : array) {
			if (i == key) {
				return true;
			}
		}
		return false;
	}
}
