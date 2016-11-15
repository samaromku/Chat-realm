package ru.intefor.chat.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.intefor.chat.DateFormatter;
import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.adapters.MessagesAdapter;
import ru.intefor.chat.entities.Chat;
import ru.intefor.chat.entities.Message;
import ru.intefor.chat.storage.ChatDataBase;
import ru.intefor.chat.storage.MessageDataBase;

public class ChatScreenActivity extends Activity{
    private List<Message> messages;
    private RecyclerView messageList;
    private MessagesAdapter adapter;
    private EditText editMessage;
    private MessageDataBase messageDB;
    private ChatDataBase chatDB = new ChatDataBase();


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
        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");
        final String chatId = intent.getStringExtra("chatId");
        String chatIdTrue = "";

        if(userName!=null) {
            if(chatId!=null) {
                chatIdTrue = chatId;
            }
            else{
                if(chatDB.getAllByParticipant(userName).size()>0) {
                    chatIdTrue = chatDB.getAllByParticipant(userName).get(0).getId();
                }else {
                    chatIdTrue = String.valueOf((int) (Math.random() * 300));
                    chatDB.copyOrUpdate(new Chat(chatIdTrue, userName, userName, "", DateFormatter.getDate()));
                }
            }
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


            toolbar.setTitle(userName);
            toolbar.setTitleTextColor(Color.WHITE);

        messageDB = new MessageDataBase();
       // messages = messageDB.getAll();
        messages = messageDB.getAllByChatId(chatIdTrue);


        Button sendButton = (Button) findViewById(R.id.send_message);
        editMessage = (EditText) findViewById(R.id.edit_message);
        final String finalChatIdTrue = chatIdTrue;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editMessage.getText().toString();
//                if(editMessage.getText().toString().startsWith("@sender@")){
//                    String[] str = editMessage.getText().toString().split("@");
//                    messageDB.copyOrUpdate(new Message(str[0], DateFormatter.getDate(), editMessage.getText().toString()));
//                }
                messageDB.copyOrUpdate(new Message("me", finalChatIdTrue, DateFormatter.getDate(), s));
                adapter.notifyDataSetChanged();
                editMessage.setText("");
                messageList.smoothScrollToPosition(adapter.getItemCount());
                messageDB.copyOrUpdate(messages);
                chatDB.copyOrUpdate(new Chat(finalChatIdTrue, userName, userName, s, DateFormatter.getDate()));
            }
        });
        messageList = (RecyclerView) findViewById(R.id.message_list);
        messageList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagesAdapter(messages, clickListener);
        messageList.setAdapter(adapter);
        messageList.smoothScrollToPosition(adapter.getItemCount());
    }
}
