package com.example.stackoverflowusersapp.list_users;

import com.example.stackoverflowusersapp.model.User;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by rahul.raj on 19/09/2016.
 */
public class UserListPresenter implements UserListContract.Presenter {

    private Observable<List<User>> useListObservable;
    UserListContract.View view;

    Subscription subscription;

    public UserListPresenter(Observable<List<User>> useListObservable, UserListContract.View view) {
        this.useListObservable = useListObservable;
        this.view = view;
    }

    @Override
    public void loadUsers() {
        view.showProgress(true);
        subscription = useListObservable.subscribe(new Subscriber<List<User>>() {
            @Override
            public void onCompleted() {
                view.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                view.showErrorOnLoadingUsers(e.getMessage());
            }

            @Override
            public void onNext(List<User> users) {
                view.showUserList(users);
            }
        });
    }

    @Override
    public void stopPresenter() {
        if(subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}
