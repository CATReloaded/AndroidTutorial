package cat.cattutorial;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cat.cattutorial.db.AppDatabase;
import cat.cattutorial.repostory.AddContactRepository;
import cat.cattutorial.repostory.ContactRepository;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactAdapter adapter = new ContactAdapter();
    ArrayList<Contact> contacts = new ArrayList<>();
    FloatingActionButton fab;
    ContactRepository.ContactCallback callback = new ContactRepository.ContactCallback() {
        @Override
        public void getContactList(List<Contact> contacts) {
            adapter.submitList(contacts);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDatabase db = AppDatabase.getInstance(this);

        ContactRepository repo = new ContactRepository(db, callback);


        repo.execute();
        recyclerView = findViewById(R.id.rv);

        // NULL POINTER EXCEPTION
        recyclerView.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContactRepository addContact = new AddContactRepository(db, callback);
                addContact.execute(new Contact("MMMM", new Random().nextInt() + ""));
            }
        });

    }
}
