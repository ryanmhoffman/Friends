package com.software.rmh.friends;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
/**
 * Created by rhoffman on 8/2/16. Retrieves all contact info from storage on the device.
 */

public class ContactRetriever {

	private Context context;

	private String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
	private String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	private Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;

	public ContactRetriever(Context context){
		this.context = context;
	}

}
