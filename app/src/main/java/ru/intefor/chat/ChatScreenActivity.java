package ru.intefor.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatScreenActivity extends Activity{
    private ArrayList<Message> messages;
    private RecyclerView messageList;
    private MessagesAdapter adapter;
    private Button sendButton;
    private EditText editMessage;



    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            Toast.makeText(ChatScreenActivity.this, "Нажали на номер " + position, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        fillMessages();
        sendButton = (Button) findViewById(R.id.send_message);
        editMessage = (EditText) findViewById(R.id.edit_message);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages.add(new Message("me", DateFormatter.getDate(), editMessage.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });

        messageList = (RecyclerView) findViewById(R.id.message_list);
        messageList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagesAdapter(messages, clickListener);
        messageList.setAdapter(adapter);
    }

    private void fillMessages(){
        messages = new ArrayList<>();
        messages.add(new Message("me", 123, "whoat???"));
        messages.add(new Message("me", 123, "whoatsdf???"));
        messages.add(new Message("me", 123, "whoatgfhf???"));
        messages.add(new Message("me", 123, "whoatgh???"));
        messages.add(new Message("me", 123, "whoatjhkjh???"));
        messages.add(new Message("me", 123, "whoatfyhf???"));
    }
}
