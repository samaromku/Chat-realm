package ru.intefor.chat.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.entities.Message;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder1>{
    private List<Message> messages;
    private OnListItemClickListener clickListener;


    public MessagesAdapter(List<Message> messages, OnListItemClickListener clickListener){
        this.messages = messages;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
//        if(viewType == 0) {
//            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
//            System.out.println(viewType);
//
//        }else if(viewType == 1){
//            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_right, parent, false);
//        }
        return new ViewHolder1(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).getSender().equals("me")) {
            return 0;
        }
        else return 1;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView messageItem;

        public ViewHolder1(View itemView) {
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
