package com.example.stackoverflowusersapp.list_users;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stackoverflowusersapp.R;
import com.example.stackoverflowusersapp.endpoint.StackOverflowServiceManager;
import com.example.stackoverflowusersapp.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class UserListActivity extends AppCompatActivity implements UserListContract.View{

    RecyclerView users;

    UserListAdapter adapter;

    ProgressDialog progress;

    UserListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progress = new ProgressDialog(this);
        users = (RecyclerView) findViewById(R.id.users);
        users.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserListAdapter(this, new ArrayList<>());
        users.setAdapter(adapter);

        presenter = new UserListPresenter(StackOverflowServiceManager.getInstance().
                                            getPopularUsersFromStackOverflow() /*repository*/, this/*view*/);
        presenter.loadUsers();
    }


    @Override
    public void showProgress(boolean show) {
        if(show) progress.show();
        else progress.dismiss();
    }

    @Override
    public void showUserList(List<User> users) {
        adapter.setUsers(users);
    }

    @Override
    public void showErrorOnLoadingUsers(String errorMsg) {

        Toast.makeText(UserListActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stopPresenter();
    }
}
