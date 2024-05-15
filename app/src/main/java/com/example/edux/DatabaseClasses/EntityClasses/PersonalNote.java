package com.example.edux.DatabaseClasses.EntityClasses;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personal_notes_table")
public class PersonalNote implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "subject")
    private String subject;

    @ColumnInfo(name = "content")
    private String content;

    public PersonalNote(String title, String subject, String content) {
        this.title = title;
        this.subject = subject;
        this.content = content;
    }


    protected PersonalNote(Parcel in) {
        id = in.readInt();
        title = in.readString();
        subject = in.readString();
        content = in.readString();
    }

    public static final Creator<PersonalNote> CREATOR = new Creator<PersonalNote>() {
        @Override
        public PersonalNote createFromParcel(Parcel in) {
            return new PersonalNote(in);
        }

        @Override
        public PersonalNote[] newArray(int size) {
            return new PersonalNote[size];
        }
    };

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(subject);
        dest.writeString(content);
    }
}


