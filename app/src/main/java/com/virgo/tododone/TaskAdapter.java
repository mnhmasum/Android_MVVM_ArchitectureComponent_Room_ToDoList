package com.virgo.tododone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import components.architecture.virgo.com.tododone.R;

/**
 * Created by nazmul on 1/4/18.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task_name, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewTaskName.setText(taskList.get(position).getTaskName());
        holder.textViewTime.setText(taskList.get(position).getTaskCreatedTime());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTaskName, textViewTime;

        public MyViewHolder(View view) {
            super(view);
            textViewTaskName = (TextView) view.findViewById(R.id.textViewTaskName);
            textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        }
    }
}
