package com.example.edux.Repositories;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.edux.DataAccessInterfaces.PersonalNoteDao;
import com.example.edux.DatabaseClasses.DataBases.PersonalNoteDB;
import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepositories {

    private PersonalNoteDao dao;
    private MutableLiveData<List<PersonalNote>> p_notes;
    private PersonalNoteDB db;
    List<PersonalNote> l;

    private static DatabaseRepositories instance;



    private DatabaseRepositories(Context context) {
        db = PersonalNoteDB.getInstance(context);
        dao = db.PersonalNoteDao();
        if(dao == null)
            throw new NullPointerException("Dao is a null");
        p_notes = new MutableLiveData<>();
        AsyncTask.execute(()->{
            l = dao.getAllNotes();
        });


    }

    public static synchronized DatabaseRepositories getInstance(Context context) {

        if(instance ==null){
            instance = new DatabaseRepositories(context);
        }
        return instance;
    }

    public LiveData<List<PersonalNote>> getAllNotes() {

        if(l!=null)
            p_notes.setValue(l);
        if(p_notes.getValue() == null)
            p_notes.setValue(new ArrayList<>());

        return p_notes;
    }


    public void insert(PersonalNote note){
        AsyncTask.execute(()->{
            db.PersonalNoteDao().insert(note);
        });
    }

    public void update(PersonalNote note){
        AsyncTask.execute(()->{
            db.PersonalNoteDao().update(note);
        });
    }

    public void delete(PersonalNote note){
        AsyncTask.execute(()->{
            db.PersonalNoteDao().delete(note);
        });
    }

    public void deleteAllNotes(){
        AsyncTask.execute(()->{
            db.PersonalNoteDao().deleteAll();
        });
    }



}
