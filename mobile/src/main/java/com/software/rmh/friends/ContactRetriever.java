package com.software.rmh.friends;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Class was created by Ryan Hoffman on 8/2/16. Retrieves all contact info from storage on the device.
 */

public class ContactRetriever {

	private Context context;

	private String CONTACT_ID = ContactsContract.Contacts._ID;
	private String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
	private String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	private Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	private String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
	private String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
	private Uri CONTACTS_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
	private ContentResolver contentResolver = context.getContentResolver();

	public ContactRetriever(Context context){
		this.context = context;
	}

	// Return all of the contacts as an Array of Contact objects.
	public ArrayList<Contact> getContacts(){
		ArrayList<Contact> contacts = new ArrayList<>();
		String[] projection = new String[]{CONTACT_ID, DISPLAY_NAME, HAS_PHONE_NUMBER};

		Cursor cursor = contentResolver.query(CONTACTS_CONTENT_URI, projection, null, null, null);

		while(cursor.moveToNext()){
			Contact contact = getContact(cursor);
			contacts.add(contact);
		}
		return contacts;
	}

	private Contact getContact(Cursor cursor){
		String id = cursor.getString(cursor.getColumnIndex(CONTACT_ID));
		String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

		Contact contact = new Contact(name, getPhoneNumber(cursor, id));
		return contact;
	}

	// I had a line of documentation here...but the method declaration/name said it all.
	private String getPhoneNumber(Cursor cursor, String id){
		String phoneNumber = "";
		int hasNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
		if(hasNumber > 0){
			Cursor contactCursor = contentResolver.query(PHONE_CONTENT_URI, null, PHONE_CONTACT_ID + " = ?", new String[]{id}, null);
			while(contactCursor.moveToNext()){
				phoneNumber = contactCursor.getString(cursor.getColumnIndex(PHONE_NUMBER));
			}
			contactCursor.close();
		}
		return phoneNumber;
	}

}
