package com.example.edux.recyclerView.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsFeed implements Parcelable {

    private String title, article;
    private int likes, comments;

    public NewsFeed(String title, String article, int likes, int comments) {
        this.title = title;
        this.article = article;
        this.likes = likes;
        this.comments = comments;
    }

    public NewsFeed(String title, String article) {
        this.title = title;
        this.article = article;
    }

    protected NewsFeed(Parcel in) {
        title = in.readString();
        article = in.readString();
        likes = in.readInt();
        comments = in.readInt();
    }

    public static final Creator<NewsFeed> CREATOR = new Creator<NewsFeed>() {
        @Override
        public NewsFeed createFromParcel(Parcel in) {
            return new NewsFeed(in);
        }

        @Override
        public NewsFeed[] newArray(int size) {
            return new NewsFeed[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(article);
        dest.writeInt(likes);
        dest.writeInt(comments);
    }
}
