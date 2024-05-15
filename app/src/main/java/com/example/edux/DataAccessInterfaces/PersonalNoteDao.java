package com.example.edux.DataAccessInterfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;

import java.util.List;

@Dao
public interface PersonalNoteDao {

    @Insert
    void insert(PersonalNote note);

    @Update
    void update(PersonalNote note);

    @Delete
    void delete(PersonalNote delete);

    @Query("DELETE FROM personal_notes_table")
    void deleteAll();

    @Query("SELECT * FROM personal_notes_table")
    List<PersonalNote> getAllNotes();

}
