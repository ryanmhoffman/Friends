package com.software.rmh.friends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joaquimley.faboptions.FabOptions;

public class ContactDetails extends AppCompatActivity {

	private TextView circleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		String name = getIntent().getStringExtra("NAME");
		String number = getIntent().getStringExtra("NUMBER");

		circleView = (TextView) findViewById(R.id.contentCircleView);

		setInitials(name);

		FabOptions fab = (FabOptions) findViewById(R.id.fabOptions);
		fab.setButtonsMenu(this, R.menu.menu);

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				switch(view.getId()){
					case R.id.menu_call:
						Toast.makeText(ContactDetails.this, "Pressed Call", Toast.LENGTH_SHORT).show();
						break;
					case R.id.menu_text:
						Toast.makeText(ContactDetails.this, "Pressed Text", Toast.LENGTH_SHORT).show();
						break;
					case R.id.menu_star:
						Toast.makeText(ContactDetails.this, "Pressed Favorite", Toast.LENGTH_SHORT).show();
						break;
					case R.id.menu_edit:
						Toast.makeText(ContactDetails.this, "Pressed Edit", Toast.LENGTH_SHORT).show();
						break;
					default:
						// No default case.
				}
			}
		});

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	// Extract the initials from the string and set them as the title in the CircleView
	private void setInitials(String name){
		String nameParts[] = name.split(" ");
		char firstInitial = nameParts[0].charAt(0);
		char secondInitial = nameParts[1].charAt(0);
		char thirdInitial = nameParts[2].charAt(0);
		String initials = "" + firstInitial + secondInitial + thirdInitial;
		if(circleView == null){
			Log.d("CircleView", "Why am I null?");
		} else {
			circleView.setText(initials);
		}
	}

}
