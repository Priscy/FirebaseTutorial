package com.example.priscila.firebasetutorial;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Priscila on 06/07/2018.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.CustomViewHolder> {
    private List<UserShow> users;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvEmail, tvAge;

        public CustomViewHolder(View view) {
            super(view);
            tvName= view.findViewById(R.id.tvName);
            tvEmail=view.findViewById(R.id.tvEmail);
            tvAge=view.findViewById(R.id.tvAge);
        }
    }

    public RecyclerviewAdapter(List<UserShow> users) {
        this.users = users;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        UserShow user = users.get(position);
        holder.tvName.setText(user.getName());
        holder.tvEmail.setText(user.getEmail());
        //Log.d("Priscila", user.getName());
       // holder.tvAge.setText(Integer.valueOf(user.getAge()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
