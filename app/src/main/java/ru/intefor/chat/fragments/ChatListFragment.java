package ru.intefor.chat.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.activities.ChatScreenActivity;
import ru.intefor.chat.adapters.ChatsAdapter;
import ru.intefor.chat.entities.Chat;

public class ChatListFragment extends Fragment{

    private ArrayList<Chat> chats;
    private RecyclerView recyclerView;
    private ChatsAdapter adapter;
    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            //Toast.makeText(ChatListActivity.this, "Нажали на номер " + position, Toast.LENGTH_SHORT).show();
            if(position==1){
                Intent intent = new Intent(v.getContext(), ChatScreenActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chats_list, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.chats_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        createArray();

        adapter = new ChatsAdapter(chats, clickListener);
        recyclerView.setAdapter(adapter);

        return v;
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
