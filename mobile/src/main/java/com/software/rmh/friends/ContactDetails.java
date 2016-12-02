package com.software.rmh.friends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joaquimley.faboptions.FabOptions;

public class ContactDetails extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

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

}
