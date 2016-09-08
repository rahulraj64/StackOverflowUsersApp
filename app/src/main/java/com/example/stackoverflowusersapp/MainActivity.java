package com.example.stackoverflowusersapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    RecyclerView users;

    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        users = (RecyclerView) findViewById(R.id.users);
        users.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(this, new ArrayList<>());
        users.setAdapter(adapter);
        fetchStackOverflowUsers();
    }


    private void fetchStackOverflowUsers() {
        StackOverflowServiceManager.getInstance().getPopularUsersFromStackOverflow().subscribe(new Subscriber<List<User>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Completed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                System.out.println("e = " + e.getMessage());
            }

            @Override
            public void onNext(List<User> users) {
                System.out.println("users = " + users);
                adapter.setUsers(users);

            }
        });

    }
}
