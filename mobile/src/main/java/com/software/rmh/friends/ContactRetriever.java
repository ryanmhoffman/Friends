package com.software.rmh.friends;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Class was created by Ryan Hoffman on 8/2/16. Retrieves all contact info from storage on the device.
 */

public class ContactRetriever {

	private Context context;
	private ArrayList<Contact> contacts = new ArrayList<>();

	// Constructor method. Context is required to run the query.
	public ContactRetriever(Context context){
		this.context = context;
	}

	/**
	 * Using a Cursor and ContentResolver, retrieve a list of all contacts and turn them into Contact objects.
	 * Then add the Contact objects to an ArrayList and return it.
	 *
	 * @return ArrayList<Contact>
	 */
	public ArrayList<Contact> getContacts(){

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

}
