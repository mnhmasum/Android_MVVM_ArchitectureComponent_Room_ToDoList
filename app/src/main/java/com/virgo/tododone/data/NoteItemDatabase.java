package com.virgo.tododone.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by mac on 3/18/18.
 */

@Database(entities = {NoteItem.class}, version = 1)
public abstract class NoteItemDatabase extends RoomDatabase {
    public abstract NoteItemDao noteItemDao();
}
