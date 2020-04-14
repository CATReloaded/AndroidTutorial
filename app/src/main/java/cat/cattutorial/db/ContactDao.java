package cat.cattutorial.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cat.cattutorial.Contact;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contacts")
    List<Contact> getContacts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContact(Contact contact);

    @Delete
    void delete(Contact contact);
}
