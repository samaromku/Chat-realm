package ru.intefor.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends Activity{
    private Button onExit;
    private Button onContacts;
    private Button onChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        onExit = (Button) findViewById(R.id.exit);
        onContacts = (Button) findViewById(R.id.contacts);
        onExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        onContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        onChats = (Button) findViewById(R.id.chats);
        onChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChatListActivity.class);
                startActivity(intent);
            }
        });

    }
}
