package com.virgo.tododone.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;

/**
 * Created by mac on 3/18/18.
 */

@Entity
public class NoteItem {
    @NonNull
    public String getNoteId() {
        return noteId;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    @PrimaryKey
    @NonNull
    private String noteId;
    private String noteBody;
    private String createdDate;

    public NoteItem(@NonNull String noteId, String noteBody, String createdDate) {
        this.noteId = noteId;
        this.noteBody = noteBody;
        this.createdDate = createdDate;
    }
}
