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


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactAdapter adapter = new ContactAdapter();
    ArrayList<Contact> contacts = new ArrayList<>();
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDatabase db = AppDatabase.getInstance(this);
        List<Contact> dbContacts = db.contactDao().getContacts();

        adapter.submitList(dbContacts);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.contactDao().insertContact(new Contact("Mohamed" + new Random().nextFloat(), new Random().nextInt()+""));
                List<Contact> dbContacts = db.contactDao().getContacts();
                adapter.submitList(dbContacts);
            }
        });

    }

}
