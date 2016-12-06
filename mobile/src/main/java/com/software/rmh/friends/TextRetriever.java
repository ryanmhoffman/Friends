package com.software.rmh.friends;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
/**
 * Created by RMH on 12/5/16.
 */

public class TextRetriever {

	private Context context;

	public TextRetriever(Context context){
		this.context = context;
	}

	public String getLastText(){
		String message = "";

		Cursor cursor = null;
		try {
			cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, "date desc limit 1");
			cursor.moveToFirst();
			do {
				for(int i = 0; i < cursor.getColumnCount(); i++){

				}
			} while(cursor.moveToNext());
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(cursor != null){
				cursor.close();
			}
		}

	}

}
