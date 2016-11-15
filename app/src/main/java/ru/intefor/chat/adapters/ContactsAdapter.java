package ru.intefor.chat.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.entities.User;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{
    private List<User> users;
    private OnListItemClickListener clickListener;

    public ContactsAdapter(List<User> users, OnListItemClickListener clickListener){
        this.users = users;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        //ImageView avatar;
        TextView avatar;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            //avatar = (ImageView)itemView.findViewById(R.id.avatar);
            avatar = (TextView) itemView.findViewById(R.id.first_letter);
            name = (TextView)itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        public void bind(User user){
            //avatar.setImageResource(R.mipmap.ic_launcher);
            name.setText(user.getName());
            char[] str = user.getName().toCharArray();
            avatar.setText(String.valueOf(str[0]));
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
