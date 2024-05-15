package com.example.edux.DatabaseClasses.DataBases;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextPaint;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.edux.DataAccessInterfaces.PersonalNoteDao;
import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;

@Database(entities = {PersonalNote.class}, version = 1, exportSchema = false)
public abstract class PersonalNoteDB extends RoomDatabase{

    private static PersonalNoteDB instance;

    public abstract PersonalNoteDao PersonalNoteDao();

    public static synchronized PersonalNoteDB getInstance(Context m) {
        if(instance == null){

            instance = Room.databaseBuilder(m.getApplicationContext(),
                    PersonalNoteDB.class, "personal_note_database")
                    .fallbackToDestructiveMigration()
                    .build();
            Toast.makeText(m, m.getApplicationInfo().dataDir, Toast.LENGTH_SHORT).show();

        }
        return instance;
    }

}
