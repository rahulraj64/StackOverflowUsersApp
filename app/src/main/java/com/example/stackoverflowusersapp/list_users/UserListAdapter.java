package com.example.stackoverflowusersapp.list_users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stackoverflowusersapp.R;
import com.example.stackoverflowusersapp.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rahul.raj on 06/09/2016.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    List<User> users;
    Context context;

    public UserListAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cell_user, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        User item = users.get(position);
        holder.name.setText(item.getName());
        holder.reputation.setText(item.getReputation());
        Picasso.with(context).load(item.getPhoto()).into(holder.pic);
        holder.reputation.setText(item.getReputation());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {

        this.users = users;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView name, reputation;
        ImageView pic;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            reputation = (TextView) itemView.findViewById(R.id.reputation);
            pic = (ImageView) itemView.findViewById(R.id.pic);
        }
    }
}
