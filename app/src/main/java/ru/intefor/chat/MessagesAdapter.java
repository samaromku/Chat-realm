package ru.intefor.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder>{
    private ArrayList<Message> messages;
    private OnListItemClickListener clickListener;


    public MessagesAdapter(ArrayList<Message> messages, OnListItemClickListener clickListener){
        this.messages = messages;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.ViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView messageItem;

        public ViewHolder(View itemView) {
            super(itemView);
            messageItem = (TextView) itemView.findViewById(R.id.message);
            itemView.setOnClickListener(this);
        }

        public void bind(Message message){
            messageItem.setText(message.getBody());
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
}
