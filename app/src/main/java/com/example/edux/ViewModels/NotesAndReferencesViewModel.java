package com.example.edux.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edux.Repositories.AppRepository;
import com.example.edux.recyclerView.DataClasses.NotesAndReferences;

import java.util.ArrayList;
import java.util.List;

public class NotesAndReferencesViewModel extends AndroidViewModel {

    AppRepository repo;
    MutableLiveData<List<NotesAndReferences>> notesAndReferences;
    List<NotesAndReferences> notes;

    public NotesAndReferencesViewModel(Application application){
        super(application);
        repo= AppRepository.getInstance();
        notes = new ArrayList<>();

        repo.getLocalNotes(application, notes);

    }

    public void init(){

        if(notesAndReferences== null){
            notesAndReferences = new MutableLiveData<>();
            notesAndReferences.setValue(notes);

        }

    }

    public LiveData<List<NotesAndReferences>> getNotesAndReferences() {
        return notesAndReferences;
    }

}
