package com.virgo.tododone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.virgo.tododone.data.NoteItem;
import com.virgo.tododone.main.MainActivity;

import java.util.List;

import components.architecture.virgo.com.tododone.R;

/**
 * Created by nazmul on 1/4/18.
 */

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.MyViewHolder> {
    private List<NoteItem> noteItems;
    private Context context;

    public NoteItemAdapter(Context context, List<NoteItem> taskList) {
        this.context = context;
        this.noteItems = taskList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task_name, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textViewTaskName.setText(noteItems.get(position).getNoteBody());
        holder.textViewTime.setText(noteItems.get(position).getCreatedDate());
        holder.textViewTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).deleteData(noteItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTaskName, textViewTime;

        public MyViewHolder(View view) {
            super(view);
            textViewTaskName = (TextView) view.findViewById(R.id.textViewTaskName);
            textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        }
    }

    interface OnDeleteNote{
        void onDeleteNote();
    }
}
