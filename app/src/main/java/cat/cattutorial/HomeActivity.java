package cat.cattutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    RecyclerView rv;
    final int ADD_REQUEST_CODE = 1;
    final int EDIT_REQUEST_CODE = 2;
    ArrayList<Contact> contactList = new ArrayList<>();

    ContactAdapterNew.OnContactClicked o = new ContactAdapterNew.OnContactClicked() {
        @Override
        public void c(Contact contact, int position) {
            Intent intent = new Intent(HomeActivity.this, AddContactActivity.class);
            intent.putExtra("contact", contact);
            intent.putExtra("pos", position);
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }
    };

    ContactAdapterNew adapter = new ContactAdapterNew(o);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        adapter.contacts = contactList;
    }


    void initViews() {
        rv = findViewById(R.id.rv);
        rv.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddContactActivity.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            contactList.add((Contact) data.getSerializableExtra("contact"));
            adapter.notifyDataSetChanged();
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            contactList.set(data.getIntExtra("pos", 0), (Contact) data.getSerializableExtra("contact"));
            adapter.notifyDataSetChanged();
        }
    }
}
