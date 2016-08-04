package com.software.rmh.friends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * Custom Adapter created by Ryan Hoffman on 8/3/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

	public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		public RecyclerViewHolder(View itemView) {
			super(itemView);
		}

		@Override
		public void onClick(View v) {
			// Do nothing for now.
		}
	}

}
