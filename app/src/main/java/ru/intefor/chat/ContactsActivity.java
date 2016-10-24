package ru.intefor.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private ArrayList<User> users;

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

        recyclerView = (RecyclerView) findViewById(R.id.contacts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            users.add(new User("Пользователь № " + i));
        }

        adapter = new ContactsAdapter(users, clickListener);
        recyclerView.setAdapter(adapter);
    }



}
