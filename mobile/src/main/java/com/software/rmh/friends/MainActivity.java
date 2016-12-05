package com.software.rmh.friends;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private FloatingActionButton fab;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private RecyclerViewAdapter adapter;
	private ContactRetriever retriever;
	private ArrayList<Contact> dummyContacts = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		retriever = new ContactRetriever(this);

		//makeDummyContacts();

		initViews();

    }

	private void makeDummyContacts(){
		for(int i = 0; i < 15; i++) {
			Contact contact = new Contact(i + " Ryan Hoffman", i + "330-453-6061");
			dummyContacts.add(contact);
		}
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	private void initViews(){
		// Initializes the RecyclerView
		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new RecyclerViewAdapter(retriever.getContacts());
		recyclerView.setAdapter(adapter);

		DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
		recyclerView.addItemDecoration(divider);

		// Initializes the FloatingActionButton
		fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Intent intent = new Intent(MainActivity.this, ContactDetails.class);
				//startActivity(intent);
			}
		});
	}
}
