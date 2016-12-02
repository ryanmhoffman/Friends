package com.software.rmh.friends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
