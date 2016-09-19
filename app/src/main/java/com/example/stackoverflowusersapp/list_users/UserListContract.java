package com.example.stackoverflowusersapp.list_users;

import com.example.stackoverflowusersapp.model.User;

import java.util.List;

/**
 * Created by rahul.raj on 19/09/2016.
 */
public interface UserListContract {

    interface View {

        void showProgress(boolean show);
        void showUserList(List<User> users);
        void showErrorOnLoadingUsers(String errorMsg);
    }

    interface Presenter {

        void loadUsers();
        void stopPresenter();
    }
}
