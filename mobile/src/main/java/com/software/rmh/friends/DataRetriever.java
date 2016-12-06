package com.software.rmh.friends;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
/**
 * Created by RMH on 12/5/16.
 */

public class DataRetriever {

	private Context context;

	private ArrayList<Contact> contacts = new ArrayList<>();

	public DataRetriever(Context context){
		this.context = context;
	}

	/**
	 * Using a Cursor and ContentResolver, retrieve a list of all contacts and turn them into Contact objects.
	 * Then add the Contact objects to an ArrayList and return it.
	 *
	 * @return ArrayList\<Contact\>
	 */
	public ArrayList<Contact> retrieveContacts(){
		Cursor cursor = null;
		try {
			// Initialize the cursor and run the query for all contacts.
			cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "starred=?", new String[] {"1"}, null);

			// Set the indices to pull from when retrieving info from the cursor.
			int contactIdId = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
			int nameId = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
			int phoneNumberId = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

			// Start at number 1.
			cursor.moveToFirst();
			do {
				// Set my local variables equal to the values retrieved during the query.
				String idContact = cursor.getString(contactIdId);
				String name = cursor.getString(nameId);
				String phoneNumber = cursor.getString(phoneNumberId);

				// Create a new Contact with the name and number just retrieved.
				Contact contact = new Contact(name, phoneNumber);
				contacts.add(contact);

				// Keep looping as long as there are still values.
			} while (cursor.moveToNext());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// Once there are no more values and the loop breaks, close the cursor.
			if (cursor != null) {
				cursor.close();
			}
		}
		return contacts;
	}

	public String retrieveTexts(){
		String message = "";

		Cursor cursor = null;
		try {
			// Initialize the cursor and run the query for the last received text message from the contact.
			cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, "address='3304173189'", null, "date desc limit 1");

			// Start at 1.
			cursor.moveToFirst();
			do {
				// Set message equal to the "body" of the last received text.
				message += cursor.getString(cursor.getColumnIndex("body"));
				Log.d("LAST TEXT", cursor.getString(cursor.getColumnIndex("body")));
			// There should only be 1 value... might remove this later.
			} while(cursor.moveToNext());
		} catch(Exception e){
			e.printStackTrace();
		} finally {

			// Once there are no more values and the loop breaks, close the cursor.
			if(cursor != null){
				cursor.close();
			}

			// If no message was retrieved and message is still blank, then set the error message.
			if(message.equals("")){
				message = "No messages :(";
			}
		}
		return message;
	}

}
