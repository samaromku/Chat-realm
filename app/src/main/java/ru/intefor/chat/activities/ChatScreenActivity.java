package ru.intefor.chat.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import ru.intefor.chat.entities.Message;
import ru.intefor.chat.storage.MessageDataBase;

public class ChatScreenActivity extends Activity{
    private List<Message> messages;
    private RecyclerView messageList;
    private MessagesAdapter adapter;
    private Button sendButton;
    private EditText editMessage;
    private MessageDataBase messageDB;



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
        messageDB = new MessageDataBase();
        messages = messageDB.getAll();



        sendButton = (Button) findViewById(R.id.send_message);
        editMessage = (EditText) findViewById(R.id.edit_message);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(editMessage.getText().toString().startsWith("@sender@")){
//                    String[] str = editMessage.getText().toString().split("@");
//                    messageDB.copyOrUpdate(new Message(str[0], DateFormatter.getDate(), editMessage.getText().toString()));
//                }
                messageDB.copyOrUpdate(new Message("me", DateFormatter.getDate(), editMessage.getText().toString()));
                adapter.notifyDataSetChanged();
                editMessage.setText("");
                messageList.smoothScrollToPosition(adapter.getItemCount());
                messageDB.copyOrUpdate(messages);
            }
        });
        messageList = (RecyclerView) findViewById(R.id.message_list);
        messageList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagesAdapter(messages, clickListener);
        messageList.setAdapter(adapter);
        messageList.smoothScrollToPosition(adapter.getItemCount());
    }
}
