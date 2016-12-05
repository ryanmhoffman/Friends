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


	public ContactRetriever(Context context){
		this.context = context;
	}

	// Return all of the contacts as an Array of Contact objects.
	public ArrayList<Contact> getContacts(){

		Cursor cursor = null;
		try {
			cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
			int contactIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
			int nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
			int phoneNumberIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			cursor.moveToFirst();
			do {
				String idContact = cursor.getString(contactIdIdx);
				String name = cursor.getString(nameIdx);
				String phoneNumber = cursor.getString(phoneNumberIdx);
				Contact contact = new Contact(name, phoneNumber);
				contacts.add(contact);
			} while (cursor.moveToNext());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}

		//new ContactRetrieverTask().execute(this);
		return contacts;
	}

}
