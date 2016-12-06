package com.software.rmh.friends;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
/**
 * Created by RMH on 12/5/16.
 */

public class DataRetriever {

	Context context;

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
			int contactIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
			int nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
			int phoneNumberIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

			// Start at number 1.
			cursor.moveToFirst();
			do {
				// Set my local variables equal to the values retrieved during the query.
				String idContact = cursor.getString(contactIdIdx);
				String name = cursor.getString(nameIdx);
				String phoneNumber = cursor.getString(phoneNumberIdx);

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
