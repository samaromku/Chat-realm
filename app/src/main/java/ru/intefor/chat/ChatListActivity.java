package ru.intefor.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class ChatListActivity extends Activity {
    private ArrayList<Chat> chats;
    private RecyclerView recyclerView;
    private ChatsAdapter adapter;

    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            //Toast.makeText(ChatListActivity.this, "Нажали на номер " + position, Toast.LENGTH_SHORT).show();
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
        recyclerView = (RecyclerView) findViewById(R.id.chats_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        createArray();

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
