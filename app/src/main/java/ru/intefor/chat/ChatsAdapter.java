package ru.intefor.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {
    private ArrayList<Chat> chats;
    private OnListItemClickListener clickListener;

    public ChatsAdapter(ArrayList<Chat> chats, OnListItemClickListener clickListener) {
        this.chats = chats;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(chats.get(position));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView body;
        TextView updated;
        TextView firsLetter;


        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_chat);
            body = (TextView) itemView.findViewById(R.id.last_message);
            updated = (TextView) itemView.findViewById(R.id.updated);
            firsLetter = (TextView) itemView.findViewById(R.id.first_letter);
            itemView.setOnClickListener(this);
        }

        public void bind(Chat chat){
            title.setText(chat.getTitle());
            body.setText(chat.getLastMessage());
            updated.setText(DateFormatter.dateStr(chat.getUpdated()));
            char[] str = chat.getTitle().toCharArray();
            firsLetter.setText(String.valueOf(str[0]));
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

}
