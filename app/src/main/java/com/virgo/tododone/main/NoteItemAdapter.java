package com.virgo.tododone.main;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.virgo.tododone.data.NoteItem;

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textViewTaskName.setText(noteItems.get(position).getNoteBody());
        holder.textViewTime.setText(noteItems.get(position).getCreatedDate());
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Do you want to delete now?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        ((MainActivity) context).deleteData(noteItems.get(position));

                        }
                    })
                    .setNegativeButton("NO", null).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return noteItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTaskName, textViewTime;
        public ImageView imageViewDelete;

        public MyViewHolder(View view) {
            super(view);
            textViewTaskName = (TextView) view.findViewById(R.id.textViewTaskName);
            textViewTime = (TextView) view.findViewById(R.id.textViewTime);
            imageViewDelete = (ImageView) view.findViewById(R.id.imageViewDelete);
        }
    }

    interface OnDeleteNote{
        void onDeleteNote();
    }
}
