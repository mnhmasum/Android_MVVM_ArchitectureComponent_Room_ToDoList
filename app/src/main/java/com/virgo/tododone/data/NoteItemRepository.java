package com.virgo.tododone.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by mac on 3/18/18.
 */

public class NoteItemRepository {
    private final NoteItemDao listItemDao;

    public NoteItemRepository(NoteItemDao noteItemDao) {
        listItemDao = noteItemDao;
    }

    public  LiveData<List<NoteItem>> getNoteList() {
        return listItemDao.getNoteList();
    }

    public Long createNewListItem(NoteItem listItem){
        return listItemDao.insertListItem(listItem);
    }
}
