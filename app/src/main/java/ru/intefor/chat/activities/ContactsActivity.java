package ru.intefor.chat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.adapters.ContactsAdapter;
import ru.intefor.chat.entities.User;
import ru.intefor.chat.storage.UserDatabase;

public class ContactsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private UserDatabase userDB;
    private List<User> users;

    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            Toast.makeText(ContactsActivity.this, "Нажали на номер " + position, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_list);
        userDB = new UserDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.contacts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users = new ArrayList<>();
        createFakeUsers();
        performUsers();

        System.out.println(userDB.getAll());
        adapter = new ContactsAdapter(users, clickListener);
        recyclerView.setAdapter(adapter);
    }


    private void createFakeUsers() {
        ArrayList<User> newUsers = new ArrayList<>();

        newUsers.add(new User("15", "Вячеслав"));
        newUsers.add(new User("10", "Николай"));
        newUsers.add(new User("1234", "Александр"));
        newUsers.add(new User("54", "Святогор"));
        newUsers.add(new User("75", "Иван"));


        userDB.copyOrUpdate(newUsers);
    }

    private void performUsers() {
        users = userDB.getAll();
        userDB.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
