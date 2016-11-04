package ru.intefor.chat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.adapters.ChatsAdapter;
import ru.intefor.chat.entities.Chat;

public class ProfileActivity extends Activity{
    private ArrayList<Chat> chats;
    private RecyclerView recyclerView;
    private ChatsAdapter adapter;
    private FloatingActionButton profileWrite;


    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            //Toast.makeText(ChatListActivity.this, "Нажали на номер " + position, Toast.LENGTH_SHORT).show();
            if(position==1){
                Intent intent = new Intent(ProfileActivity.this, ChatScreenActivity.class);
                startActivity(intent);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        profileWrite = (FloatingActionButton) findViewById(R.id.button_profile_write);
        recyclerView = (RecyclerView) findViewById(R.id.chats_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        createArray();

        profileWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });
        adapter = new ChatsAdapter(chats, clickListener);
        recyclerView.setAdapter(adapter);
    }
    private void createArray(){
        chats = new ArrayList<>();
        chats.add(new Chat("Покемоны", "Кто убил кролика роджера?", 1477314702373L));
        chats.add(new Chat("Работа", "Почему у меня такая маленькая зарплата((", 1477314702373L));
        chats.add(new Chat("Вопросы", "и?", 1477314833021L));
        chats.add(new Chat("Собрание", "в 15-00", 1477314846987L));
        chats.add(new Chat("Ыра", "это имя чтоли?", 1477314860760L));
    }
}
