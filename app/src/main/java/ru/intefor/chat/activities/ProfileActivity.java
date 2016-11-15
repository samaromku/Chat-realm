package ru.intefor.chat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.adapters.ChatsAdapter;
import ru.intefor.chat.entities.Chat;
import ru.intefor.chat.storage.ChatDataBase;

public class ProfileActivity extends Activity{
    private FloatingActionButton profileWrite;
    private CollapsingToolbarLayout toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar.setTitle(userName);



        profileWrite = (FloatingActionButton) findViewById(R.id.button_profile_write);

        profileWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });
    }

}
