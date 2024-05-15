package com.example.edux.recyclerView.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Examinations implements Parcelable {

    private final String title;
    private final String code;
    private final String description;
    private final String examUrl;

    public Examinations(String title, String code, String description, String examUrl) {
        this.title = title;
        this.code = code;
        this.description = description;
        this.examUrl = examUrl;
    }


    protected Examinations(Parcel in) {
        title = in.readString();
        code = in.readString();
        description = in.readString();
        examUrl = in.readString();
    }

    public static final Creator<Examinations> CREATOR = new Creator<Examinations>() {
        @Override
        public Examinations createFromParcel(Parcel in) {
            return new Examinations(in);
        }

        @Override
        public Examinations[] newArray(int size) {
            return new Examinations[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getExamUrl() {
        return examUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(code);
        dest.writeString(description);
        dest.writeString(examUrl);
    }
}
