package com.software.rmh.friends;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.joaquimley.faboptions.FabOptions;

public class ContactDetails extends AppCompatActivity {

	private TextView circleView, nameText, lastText;
	private FabOptions fab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		setWindowTransitionAnimations();

		// Retrieve the Extra data from the intent.
		String name = getIntent().getStringExtra("NAME");
		final String number = getIntent().getStringExtra("NUMBER");

		circleView = (TextView) findViewById(R.id.contentCircleView);

		nameText = (TextView) findViewById(R.id.nameTextView);
		nameText.setText(name);

		lastText = (TextView) findViewById(R.id.lastTextTV);
		lastText.setText(new DataRetriever(this).retrieveTexts(number));

		setInitials(name);

		fab = (FabOptions) findViewById(R.id.fabOptions);
		animateFAB();
		fab.setButtonsMenu(this, R.menu.menu);

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				switch(view.getId()) {
					case R.id.menu_call:
						Intent call = new Intent(Intent.ACTION_DIAL);
						call.setData(Uri.parse("tel:" + number));
						startActivity(call);
						break;
					case R.id.menu_text:
						Intent text = new Intent(Intent.ACTION_VIEW);
						text.setData(Uri.parse("sms:" + number));
						startActivity(text);
						break;
					default:
						// No default case needed.
				}
			}
		});

		// Enable the back button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * Set the initial(s) of the contact inside the circular TextView. If there is only one name and it is
	 * less than 5 characters long the whole thing will be displayed in the circle, any more than five and
	 * it will only display the first initial. If there are two names then two initials are shown, and if
	 * there are three names then all three initials are shown in the circle.
	 *
	 * @param name String containing the contact name retrieved from the Intent.
	 */
	private void setInitials(String name){
		String nameParts[] = name.split(" ");
		int len = nameParts.length;
		// There will always be at least 1 initial.
		char firstInitial = nameParts[0].charAt(0);
		String initials;

		if(len == 3){
			// Sets second and third initials if they exist.
			char secondInitial = nameParts[1].charAt(0);
			char thirdInitial = nameParts[2].charAt(0);
			initials = "" + firstInitial + secondInitial + thirdInitial;
			circleView.setText(initials);
		} else if(len == 2){
			// Sets the second initial if it exists.
			char secondInitial = nameParts[1].charAt(0);
			initials = "" + firstInitial + secondInitial;
			circleView.setText(initials);
		} else {
			if(name.length() <= 5){
				// Set the whole name.
				initials = name;
			} else {
				// if name.length() > 5 use the first initial only.
				initials = "" + firstInitial;
			}
			circleView.setText(initials);
		}

	}

	private void setWindowTransitionAnimations(){
		Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
		getWindow().setEnterTransition(fade);
	}

	private void animateFAB(){
		if(fab != null){
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.expand_in);
			fab.startAnimation(animation);
		}
	}

}
