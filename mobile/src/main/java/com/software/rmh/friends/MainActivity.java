package com.software.rmh.friends;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private RecyclerViewAdapter adapter;
	private DataRetriever retriever;
	private ArrayList<Contact> contacts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		retriever = new DataRetriever(this);
		contacts = retriever.retrieveContacts();

		initViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_credits) {
			startActivity(new Intent(this, Credits.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onPause() {
		super.onPause();
		recyclerView.setAdapter(null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		initViews();
	}

	private void initViews(){
		// Initializes the RecyclerView and sets the LayoutManager and Adapter.
		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new RecyclerViewAdapter(contacts, this);
		recyclerView.setAdapter(adapter);

		// Add a divider between each row in the RecyclerView.
		DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
		recyclerView.addItemDecoration(divider);

		// If the recyclerView is empty, tell them to go star some contacts.
		if(adapter.getItemCount() == 0){
			setIntroCardView();
		}
	}

	private void setIntroCardView(){
		final CardView cardView = (CardView) findViewById(R.id.introCardView);
		cardView.setVisibility(View.VISIBLE);

		Button button = (Button) findViewById(R.id.intro_button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI));
				cardView.setVisibility(View.GONE);
				onPause();
			}
		});
	}

}
