package com.virgo.tododone;

import android.app.LauncherActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.virgo.tododone.data.NoteItem;
import com.virgo.tododone.data.NoteItemDao;
import com.virgo.tododone.data.NoteItemDatabase;
import com.virgo.tododone.data.NoteItemRepository;
import com.virgo.tododone.viewmodel.CustomViewModelFactory;
import com.virgo.tododone.viewmodel.NewListItemViewModel;
import com.virgo.tododone.viewmodel.NoteItemCollectionViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import components.architecture.virgo.com.tododone.R;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private List<Task> taskList = new ArrayList<>();
    private TaskAdapter mAdapter;

    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mAdapter = new TaskAdapter(taskList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        NoteItemDatabase database = Room.databaseBuilder(
                this,
                NoteItemDatabase.class,
                "ListItem.db"
        ).build();

        final NoteItemRepository noteItemRepository = new NoteItemRepository(database.noteItemDao());


        viewModelFactory = new CustomViewModelFactory(noteItemRepository);


        database.noteItemDao();


        /*final NoteItemCollectionViewModel noteItemCollectionViewModel =
                ViewModelProviders.of(this, viewModelFactory)
                        .get(NoteItemCollectionViewModel.class);*/

        final NoteItemCollectionViewModel noteItemCollectionViewModel = new NoteItemCollectionViewModel(noteItemRepository);

        final NewListItemViewModel newListItemViewModel = new NewListItemViewModel(noteItemRepository);


        final EditText editTextTaskName = (EditText) findViewById(R.id.editTextTaskName);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                NoteItem noteItem = new NoteItem(getDate(), editTextTaskName.getText().toString(),
                        DateFormat.getDateInstance().format(new Date()));

                newListItemViewModel.addNewItemToDatabase(noteItem);

            }
        });


        noteItemCollectionViewModel.getListItems().observe(MainActivity.this, new Observer<List<NoteItem>>() {

            @Override
            public void onChanged(@Nullable List<NoteItem> noteItems) {

                taskList.clear();
                editTextTaskName.setText("");

                for (NoteItem noteItem : noteItems) {
                    addTask(noteItem);
                }

                mAdapter.notifyDataSetChanged();

            }

        });


    }

    private void addTask(NoteItem noteItem) {
        Task task = new Task(noteItem.getNoteBody(), noteItem.getCreatedDate());
        taskList.add(task);
    }

    public String getDate() {

        Date currentDate = Calendar.getInstance().getTime();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd/kk:mm:ss");

        return format.format(currentDate);
    }

}
