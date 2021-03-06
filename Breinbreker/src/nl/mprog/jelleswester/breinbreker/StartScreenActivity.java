/**
 * Jelles Wester
 * jelleswester@gmail.com
 * 10004531
 * 
 * StartScreenActivity:
 * This activity is the first activity of the app and it gives the user
 * the option to go to the GamePlayActivity or the HighScoreActivity.
 */

package nl.mprog.jelleswester.breinbreker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class StartScreenActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_start_screen);
		getActionBar().hide();

		// check whether game is saved
		Context mContext = getApplicationContext();
		GameSavings gs = new GameSavings(mContext);
		boolean savedGame = gs.getGameSaved();

		// if there is a saved game
		if (savedGame) {

			// create continueButton and find relative layout by id
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout1);
			Button continueButton = new Button(mContext);

			// set text and layout
			continueButton.setText("Continue Game");
			continueButton.setBackgroundResource(R.drawable.button_shape_2);
			continueButton.setTextColor(getResources().getColor(R.color.white));
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ABOVE, R.id.newGameButton);
			continueButton.setLayoutParams(lp);

			// set onClickListener
			continueButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View view) {

					// on click start GamePlayActivity
					startActivity(new Intent(StartScreenActivity.this,
							GamePlayActivity.class));
					finish();
				}
			});
			rl.addView(continueButton);
		}
	}

	// activates when clicking on new game button
	public void newGameButton(View view) {

		// set gameSaved on false
		Context mContext = getApplicationContext();
		GameSavings gs = new GameSavings(mContext);
		gs.setGameSaved(false);

		// start GamePlayActivity
		startActivity(new Intent(this, GamePlayActivity.class));
		finish();
	}

	// activates when clicking on high score button
	public void highScoreButton(View view) {

		// start HighScoreActivity
		Intent intent = new Intent(this, HighScoreActivity.class);
		intent.putExtra(HighScoreActivity.EXTRA_HIGH_SCORE_TYPE,
				HighScoreActivity.HIGH_SCORE_TYPE_OFFLINE);
		startActivity(intent);
		finish();
	}
}
