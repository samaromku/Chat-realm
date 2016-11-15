package ru.intefor.chat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.adapters.ChatsAdapter;
import ru.intefor.chat.entities.Chat;
import ru.intefor.chat.storage.ChatDataBase;

public class ChatListActivity extends Activity {
    private List<Chat> chats;
    private RecyclerView recyclerView;
    private ChatsAdapter adapter;
    private ChatDataBase chatDB = new ChatDataBase();

    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            Toast.makeText(ChatListActivity.this, "Нажали на номер " + chats.get(position).getId(), Toast.LENGTH_SHORT).show();
            if(position==1){
                Intent intent = new Intent(ChatListActivity.this, ChatScreenActivity.class);
                startActivity(intent);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chats_list);
        allMethods();
    }

    public void allMethods(){
        createArray();
        chatDB.copyOrUpdate(chats);


        recyclerView = (RecyclerView) findViewById(R.id.chats_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        adapter = new ChatsAdapter(chats, clickListener);
        recyclerView.setAdapter(adapter);
    }


    private void createArray(){
        chats = chatDB.getAll();
    }
}
