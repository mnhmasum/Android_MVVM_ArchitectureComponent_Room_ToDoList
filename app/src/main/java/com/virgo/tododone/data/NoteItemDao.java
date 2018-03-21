package com.virgo.tododone.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mac on 3/18/18.
 */

@Dao
public interface NoteItemDao {

    @Insert(onConflict = REPLACE)
    Long insertListItem(NoteItem noteItem);

    @Query("SELECT * FROM NoteItem")
    LiveData<List<NoteItem>> getNoteList();


    @Delete
    void deleteListItem(NoteItem noteItem);
}
