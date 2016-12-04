package com.software.rmh.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Custom Adapter created by Ryan Hoffman on 8/3/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

	private ArrayList<Contact> contacts;

	public RecyclerViewAdapter(ArrayList<Contact> contacts){
		this.contacts = contacts;
	}

	public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		public TextView contactName;
		public TextView contactNumber;
		public RecyclerViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			contactName = (TextView) itemView.findViewById(R.id.contactName);
			contactNumber = (TextView) itemView.findViewById(R.id.contactNumber);
		}

		@Override
		public void onClick(View v) {
			// Open the ContactDetails screen
			Intent intent = new Intent(v.getContext(), ContactDetails.class);
			Bundle bundle = new Bundle();
			bundle.putString("NAME", contactName.getText().toString());
			bundle.putString("NUMBER", contactNumber.getText().toString());
			intent.putExtras(bundle);
			v.getContext().startActivity(intent);
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
		}
	}

	@Override
	public int getItemCount() {
		if(contacts == null){
			return 0;
		}
		return contacts.size();
	}
}
