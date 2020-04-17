package cat.cattutorial.repostory;

import android.os.AsyncTask;

import java.util.List;

import cat.cattutorial.Contact;
import cat.cattutorial.db.AppDatabase;

public class AddContactRepository extends AsyncTask<Contact, Void, List<Contact>> {
    private AppDatabase db;
    private ContactRepository.ContactCallback contactCallback;

    public AddContactRepository(AppDatabase database, ContactRepository.ContactCallback callback) {
        db = database;
        contactCallback = callback;
    }


    @Override
    protected List<Contact> doInBackground(Contact... contacts) {
        for (Contact c : contacts) {
            db.contactDao().insertContact(c);
        }
        return db.contactDao().getContacts();
    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);
        contactCallback.getContactList(contacts);
    }
}
