package com.software.rmh.friends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
/**
 * Custom Adapter created by Ryan Hoffman on 8/3/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

	private ArrayList<Contact> contacts;
	private static Context context;

	public RecyclerViewAdapter(ArrayList<Contact> contacts, Context context){
		this.contacts = contacts;
		this.context = context;
	}

	public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		public TextView contactName;
		public TextView contactNumber;
		public ImageView contactPhoto;
		public RecyclerViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			contactName = (TextView) itemView.findViewById(R.id.contactName);
			contactNumber = (TextView) itemView.findViewById(R.id.contactNumber);
			contactPhoto = (ImageView) itemView.findViewById(R.id.circleView);
		}

		@Override
		public void onClick(View v) {
			// Open the ContactDetails screen
			Intent intent = new Intent(v.getContext(), ContactDetails.class);
			Bundle bundle = new Bundle();
			bundle.putString("NAME", contactName.getText().toString());
			bundle.putString("NUMBER", contactNumber.getText().toString());
			intent.putExtras(bundle);
			ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, contactName, "NAME");
			v.getContext().startActivity(intent, options.toBundle());
		}
	}



	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Inflate the layout and return the view.
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cell, parent, false);
		return new RecyclerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {
		if(contacts.get(position) != null){
			holder.contactName.setText(contacts.get(position).getNAME());
			holder.contactNumber.setText(contacts.get(position).getNUMBER());
			holder.contactPhoto.setBackground(getRandomColor());
		}
	}

	@Override
	public int getItemCount() {
		if(contacts == null){
			return 0;
		}
		return contacts.size();
	}

	private Drawable getRandomColor(){
		Random r = new Random();
		int low = 1;
		int high = 6;
		int result = r.nextInt(high - low) + low;
		switch(result){
			case 1:
				return ContextCompat.getDrawable(context, R.drawable.shape_circle_green);
			case 2:
				return ContextCompat.getDrawable(context, R.drawable.shape_circle_blue);
			case 3:
				return ContextCompat.getDrawable(context, R.drawable.shape_circle_red);
			case 4:
				return ContextCompat.getDrawable(context, R.drawable.shape_circle_orange);
			case 5:
				return ContextCompat.getDrawable(context, R.drawable.shape_circle_yellow);
			case 6:
				return ContextCompat.getDrawable(context, R.drawable.shape_circle_purple);
		}
		return ContextCompat.getDrawable(context, R.drawable.shape_circle_green);
	}
}
