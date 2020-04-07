package cat.cattutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContactActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initView();
        if (getIntent().hasExtra("contact")) {
            Contact contact = (Contact) getIntent().getSerializableExtra("contact");
            etName.setText(contact.getName());
            etNumber.setText(contact.getNumber());
        }
    }

    void initView() {
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = createContact(etName.getText().toString(), etNumber.getText().toString());
                if (contact != null) {
                    Intent intent = new Intent();
                    intent.putExtra("contact", contact);
                    intent.putExtra("pos",getIntent().getIntExtra("pos",0));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private Contact createContact(String name, String number) {
        boolean invalid = false;
        if (name == null || name.isEmpty()) {
            invalid = true;
            etName.setError("لا يمكن إضافة مستحدم بدون اسم");
        }
        if (number == null || number.isEmpty()) {
            invalid = true;
            etNumber.setError("لا يمكن إضافة مستخدم بدون رقم ");
        }
        if (invalid) return null;
        else return new Contact(name, number);
    }
}
