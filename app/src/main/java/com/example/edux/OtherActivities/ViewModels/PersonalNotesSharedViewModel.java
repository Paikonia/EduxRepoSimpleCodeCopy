package com.example.edux.OtherActivities.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;
import com.example.edux.Repositories.DatabaseRepositories;

import java.util.List;

public class PersonalNotesSharedViewModel extends AndroidViewModel {

    DatabaseRepositories repo;

    PersonalNote note;
    LiveData<List<PersonalNote>> pnote;

    public PersonalNotesSharedViewModel(@NonNull Application application) {
        super(application);
        repo = DatabaseRepositories.getInstance(application);
        pnote = repo.getAllNotes();
    }

    public PersonalNote getNote() {
        return note;
    }

    public LiveData<List<PersonalNote>> getPersonalNote() {
        return pnote;
    }

    public void setNote(PersonalNote note) {
        this.note = note;
    }

    public void insertNote(PersonalNote p_note){
        repo.insert(p_note);
    }

    public void deleteNote(PersonalNote p_note){
        repo.delete(p_note);
    }

    public void updateNote(PersonalNote p_note){
        repo.update(p_note);
    }

    public LiveData<List<PersonalNote>> getAllNotes() {
        return repo.getAllNotes();
    }
}
