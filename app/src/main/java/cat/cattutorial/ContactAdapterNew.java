package cat.cattutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapterNew extends RecyclerView.Adapter<ContactAdapterNew.ContactViewHolder> {

    public ContactAdapterNew(OnContactClicked onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    private OnContactClicked onItemClickedListener;
    public ArrayList<Contact> contacts = new ArrayList<>();

    interface OnContactClicked {
        void c(Contact contact, int position);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_new, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        holder.onBind(contacts.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.c(contacts.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }

        void onBind(Contact contact) {
            tvName.setText(contact.getName());
            tvNumber.setText(contact.getNumber());
        }
    }
}
