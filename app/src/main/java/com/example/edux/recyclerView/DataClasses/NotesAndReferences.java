package com.example.edux.recyclerView.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesAndReferences implements Parcelable {
    String title, subject, references, data;

    public NotesAndReferences(String title, String subject, String references, String data) {
        this.title = title;
        this.subject = subject;
        this.references = references;
        this.data = data;
    }

    protected NotesAndReferences(Parcel in) {
        title = in.readString();
        subject = in.readString();
        references = in.readString();
        data = in.readString();
    }

    public static final Creator<NotesAndReferences> CREATOR = new Creator<NotesAndReferences>() {
        @Override
        public NotesAndReferences createFromParcel(Parcel in) {
            return new NotesAndReferences(in);
        }

        @Override
        public NotesAndReferences[] newArray(int size) {
            return new NotesAndReferences[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getReferences() {
        return references;
    }

    public String getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(subject);
        dest.writeString(references);
        dest.writeString(data);

    }
}
