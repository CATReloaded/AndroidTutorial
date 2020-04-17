package cat.cattutorial.repostory;

import android.os.AsyncTask;
import android.util.Log;


import java.util.List;

import cat.cattutorial.Contact;
import cat.cattutorial.db.AppDatabase;

public class ContactRepository extends AsyncTask<Void, Void, List<Contact>> {
    private AppDatabase db;

    public ContactRepository(AppDatabase database, ContactCallback callback) {
        db = database;
        this.callback = callback;
    }

    private ContactCallback callback;

    public interface ContactCallback {
        void getContactList(List<Contact> contacts);
    }

    @Override
    protected List<Contact> doInBackground(Void... voids) {
        List<Contact> list = db.contactDao().getContacts();
        Log.d("TAG", " Do In Background list length : " + list.size());
        return list;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("TAG", "onPreExecute ... ");
    }

    @Override
    protected void onPostExecute(List<Contact> contact) {
        super.onPostExecute(contact);
        callback.getContactList(contact);
        Log.d("TAG", " onPostExecute list length : " + contact.size());
    }
}
