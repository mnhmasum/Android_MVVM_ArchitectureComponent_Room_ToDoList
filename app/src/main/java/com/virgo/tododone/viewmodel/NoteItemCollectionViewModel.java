/*
 * *
 *  * Copyright (C) 2017 Ryan Kay Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.virgo.tododone.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.virgo.tododone.data.NoteItem;
import com.virgo.tododone.data.NoteItemRepository;

import java.util.List;


/**
 * Created by R_KAY on 8/3/2017.
 */

public class NoteItemCollectionViewModel extends ViewModel {

    private NoteItemRepository repository;

    public NoteItemCollectionViewModel(NoteItemRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<NoteItem>> getListItems() {
        return repository.getNoteList();
    }

    public void deleteListItem(NoteItem listItem) {
        DeleteItemTask deleteItemTask = new DeleteItemTask();
        deleteItemTask.execute(listItem);
    }

    private class DeleteItemTask extends AsyncTask<NoteItem, Void, Void> {

        @Override
        protected Void doInBackground(NoteItem... item) {
            repository.deleteListItem(item[0]);
            return null;
        }
    }

}

