package ru.intefor.chat.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.intefor.chat.R;
import ru.intefor.chat.adapters.ViewPagerAdapter;
import ru.intefor.chat.fragments.ChatListFragment;
import ru.intefor.chat.fragments.UserListFragment;

public class TabMainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       getSupportActionBar().setTitle(R.string.app_name);
//        Button getUserList = (Button) findViewById(R.id.action_user);
//        Button getChatList = (Button) findViewById(R.id.action_message);
//
//
//        getUserList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TabMainActivity.this, ContactsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        getChatList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TabMainActivity.this, ChatListActivity.class);
//                startActivity(intent);
//            }
//        });

        TabLayout tablayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ChatListFragment(), "Чаты");
        adapter.addFragment(new UserListFragment(), "Контакты");

        pager.setAdapter(adapter);
        tablayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_message:
                startActivity(new Intent(this, ChatListActivity.class));
                return true;

            case R.id.action_user:
                startActivity(new Intent(this, ContactsActivity.class));
                return true;

            case R.id.action_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
