package com.example.edux.InternetClasses.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class Posts implements Parcelable {

    String title;
    int id;
    String body;

    public Posts(String title, int id, String body) {
        this.title = title;
        this.id = id;
        this.body = body;
    }

    protected Posts(Parcel in) {
        title = in.readString();
        id = in.readInt();
        body = in.readString();
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(id);
        dest.writeString(body);
    }
}
