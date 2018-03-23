package com.virgo.tododone.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.virgo.tododone.data.NoteItem;
import com.virgo.tododone.data.NoteItemDatabase;
import com.virgo.tododone.data.NoteItemRepository;
import com.virgo.tododone.viewmodel.ApplicationViewModelFactory;
import com.virgo.tododone.viewmodel.AddNewListItemViewModel;
import com.virgo.tododone.viewmodel.NoteItemCollectionViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import components.architecture.virgo.com.tododone.R;

public class MainActivity extends AppCompatActivity {
    private List<NoteItem> noteItems = new ArrayList<>();
    private NoteItemAdapter mAdapter;

    private AddNewListItemViewModel newListItemViewModel;
    private NoteItemCollectionViewModel noteItemCollectionViewModel;

    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mAdapter = new NoteItemAdapter(this, noteItems);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        NoteItemDatabase database = Room.databaseBuilder(
                this,
                NoteItemDatabase.class,
                "ListItem.db"
        ).build();

        database.noteItemDao();

        final NoteItemRepository noteItemRepository = new NoteItemRepository(database.noteItemDao());

        viewModelFactory = new ApplicationViewModelFactory(noteItemRepository);


        noteItemCollectionViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(NoteItemCollectionViewModel.class);


        newListItemViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddNewListItemViewModel.class);


        final EditText editTextTaskName = (EditText) findViewById(R.id.editTextTaskName);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                NoteItem noteItem = new NoteItem(getDate(),
                                                 editTextTaskName.getText().toString(),
                                                 DateFormat.getDateInstance().format(new Date()));

                newListItemViewModel.addNewItemToDatabase(noteItem);

            }
        });


        noteItemCollectionViewModel.getListItems().observe(MainActivity.this, new Observer<List<NoteItem>>() {

            @Override
            public void onChanged(@Nullable List<NoteItem> noteItems) {
                MainActivity.this.noteItems.clear();
                editTextTaskName.setText("");

                MainActivity.this.noteItems.addAll(noteItems);
                mAdapter.notifyDataSetChanged();

            }

        });


    }


    public String getDate() {
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd/kk:mm:ss");
        return format.format(currentDate);
    }

    public void deleteData(NoteItem noteItem) {
        noteItemCollectionViewModel.deleteListItem(noteItem);
    }

}
