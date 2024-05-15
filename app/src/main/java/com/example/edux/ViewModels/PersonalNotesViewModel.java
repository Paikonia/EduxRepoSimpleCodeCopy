package com.example.edux.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edux.DatabaseClasses.DataBases.PersonalNoteDB;
import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;
import com.example.edux.Repositories.DatabaseRepositories;

import java.util.ArrayList;
import java.util.List;


public class PersonalNotesViewModel extends AndroidViewModel {

    MutableLiveData<List<PersonalNote>> notes;
    DatabaseRepositories dbRepo;

    public PersonalNotesViewModel(@NonNull Application application) {
        super(application);

        //dbRepo = DatabaseRepositories.getInstance(application);
        notes =  new MutableLiveData<>();
        notes.setValue(new ArrayList<>());

        notes.getValue().add(new PersonalNote("First note", "Appinfo", "This is the first note that has not been implemented correctly"));

    }


    public LiveData<List<PersonalNote>> getNotes() {
        if(notes == null)
            throw new NullPointerException("The Live data notes are null.");
        return notes;
    }

    public void insertNote(PersonalNote note){
        dbRepo.insert(note);
    }


    public void updateNote(PersonalNote note){
        dbRepo.update(note);
    }

    public void deleteNote(PersonalNote note){
        dbRepo.delete(note);
    }

    public void deleteAllNotes(){
        dbRepo.deleteAllNotes();

    }

}
