package nl.mprog.jelleswester.breinbreker;

import java.util.ArrayList;
import java.util.List;

import android.widget.NumberPicker;

public class GameController {
	
	// method that updates answersArray and numberPickers when NumberPicker was scrolled 
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
		
		// check whether all answers are right
		for (int i = 0; i < 9; i++) {
			if (numbersArray[i] != userInput[i]) {
				game_won = false;
			}
		}
		
		// return either true or false
		return game_won;
	}
}
