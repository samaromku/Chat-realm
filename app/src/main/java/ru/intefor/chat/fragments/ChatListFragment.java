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
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.activities.ChatListActivity;
import ru.intefor.chat.activities.ChatScreenActivity;
import ru.intefor.chat.adapters.ChatsAdapter;
import ru.intefor.chat.entities.Chat;
import ru.intefor.chat.storage.ChatDataBase;

public class ChatListFragment extends Fragment{

    private List<Chat> chats;
    private RecyclerView recyclerView;
    private ChatsAdapter adapter;
    ChatDataBase chatDB = new ChatDataBase();
    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
                Intent intent = new Intent(v.getContext(), ChatScreenActivity.class);
                intent.putExtra("chatId", chats.get(position).getId());
                intent.putExtra("userName", chats.get(position).getParticipant());
                startActivity(intent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        createArray();

        View v = inflater.inflate(R.layout.chats_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.chats_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        adapter = new ChatsAdapter(chats, clickListener);
        recyclerView.setAdapter(adapter);
        return v;
    }

    private void createArray(){
        chats = chatDB.getAll();
        chatDB.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
                chats = chatDB.getAll();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
