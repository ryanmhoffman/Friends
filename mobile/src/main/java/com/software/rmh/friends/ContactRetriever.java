package com.software.rmh.friends;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

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

		new ContactRetrieverTask().execute(this);
		return contacts;
	}

	private void setContacts(ArrayList<Contact> retrievedContacts){
		contacts = retrievedContacts;
	}

	private class ContactRetrieverTask extends AsyncTask<ContactRetriever, Long, ArrayList<Contact>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Do nothing
		}

		@Override
		protected ArrayList<Contact> doInBackground(ContactRetriever... params) {
			ArrayList<Contact> allContacts = new ArrayList<>();

			ContentResolver resolver = context.getContentResolver();

			Uri raw = Uri.parse("content://com.android.contacts/raw_contacts");
			Uri data = Uri.parse("content://com.android.contacts/data");

			Cursor cursor = resolver.query(raw, new String[]{"contact_id"}, null, null, null);

			while(cursor.moveToNext()){

				String contact_id = cursor.getString(0);

				if(contact_id != null){

					Cursor c = resolver.query(data, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{contact_id}, null);
					Contact contact = new Contact();

					while(c.moveToNext()){

						String data1 = c.getString(0);
						String mimetype = c.getString(1);

						if(mimetype.equals("vnd.android.cursor.item/phone_v2")){
							contact.setNUMBER(data1);
						} else if(mimetype.equals("vnd.android.cursor.item/name")){
							contact.setNAME(data1);
						}
					}
					allContacts.add(contact);
					c.close();
				}
			}
			cursor.close();
			return allContacts;
		}

		@Override
		protected void onPostExecute(ArrayList<Contact> contacts) {
			super.onPostExecute(contacts);
			setContacts(contacts);
		}

	}

}
