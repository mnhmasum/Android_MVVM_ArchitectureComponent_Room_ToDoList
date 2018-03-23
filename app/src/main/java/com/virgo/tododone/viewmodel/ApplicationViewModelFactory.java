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

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.virgo.tododone.data.NoteItemRepository;


/**
 * Created by masum on 8/17/2017.
 */
public class ApplicationViewModelFactory implements ViewModelProvider.Factory {
    private final NoteItemRepository repository;

    public ApplicationViewModelFactory(NoteItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NoteItemCollectionViewModel.class))
            return (T) new NoteItemCollectionViewModel(repository);

        else if (modelClass.isAssignableFrom(AddNewListItemViewModel.class))
            return (T) new AddNewListItemViewModel(repository);

        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
