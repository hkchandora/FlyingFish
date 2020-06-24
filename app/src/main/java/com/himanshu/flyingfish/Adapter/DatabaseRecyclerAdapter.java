package com.himanshu.flyingfish.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.flyingfish.Model.UserScore;
import com.himanshu.flyingfish.R;

import java.util.ArrayList;

public class DatabaseRecyclerAdapter extends RecyclerView.Adapter<DatabaseRecyclerAdapter.DatabaseViewHolder> {

    ArrayList<UserScore> userScores;

    public DatabaseRecyclerAdapter(ArrayList<UserScore> userScores) {
        this.userScores = userScores;
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item, parent, false);
       return new DatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        UserScore userScore = userScores.get(position);

        holder.Date.setText("Date : " + userScore.getDate());
        holder.Time.setText("Time : " + userScore.getTime());
        holder.Id.setText(userScore.getId() + ") ");
        holder.Name.setText(userScore.getName());
        holder.Score.setText("Score : " + userScore.getScore());
        holder.Level.setText("Level : " + userScore.getLevel());
    }

    @Override
    public int getItemCount() {
        return userScores.size();
    }

    public static class DatabaseViewHolder extends RecyclerView.ViewHolder {

        TextView Date, Time, Id, Name, Score,Level;

        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);

            Date = itemView.findViewById(R.id.date);
            Time = itemView.findViewById(R.id.time);
            Id = itemView.findViewById(R.id.user_id);
            Name = itemView.findViewById(R.id.user_name);
            Score = itemView.findViewById(R.id.user_score);
            Level = itemView.findViewById(R.id.level);
        }
    }
}
