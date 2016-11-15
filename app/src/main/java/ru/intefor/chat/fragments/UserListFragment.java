package ru.intefor.chat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.intefor.chat.OnListItemClickListener;
import ru.intefor.chat.R;
import ru.intefor.chat.activities.ChatScreenActivity;
import ru.intefor.chat.activities.ProfileActivity;
import ru.intefor.chat.adapters.ContactsAdapter;
import ru.intefor.chat.entities.User;
import ru.intefor.chat.storage.UserDatabase;

public class UserListFragment extends Fragment{
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private UserDatabase userDB= new UserDatabase();
    private List<User> users;

    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            //Toast.makeText(v.getContext(), "Нажали на номер " + position, Toast.LENGTH_SHORT).show();
            openOptionMenu(v, position);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contacts_list, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.contacts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        users = new ArrayList<>();
        createFakeUsers();
        performUsers();

        System.out.println(userDB.getAll());
        adapter = new ContactsAdapter(users, clickListener);
        recyclerView.setAdapter(adapter);
        return v;
    }

    private void createFakeUsers() {
        ArrayList<User> newUsers = new ArrayList<>();

        newUsers.add(new User("15", "Вячеслав"));
        newUsers.add(new User("10", "Николай"));
        newUsers.add(new User("1234", "Александр"));
        newUsers.add(new User("54", "Святогор"));
        newUsers.add(new User("75", "Иван"));


        userDB.copyOrUpdate(newUsers);
    }

    private void performUsers() {
        users = userDB.getAll();
        userDB.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void openOptionMenu(final View v, final int position){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.user_option_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.action_message:
                        intent = new Intent(v.getContext(), ChatScreenActivity.class);
                        intent.putExtra("userName", users.get(position).getName());
                        startActivity(intent);
                        return true;
                    case R.id.action_profile:
                        intent = new Intent(v.getContext(), ProfileActivity.class);
                        intent.putExtra("userName", users.get(position).getName());
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
        popup.show();
    }
}
