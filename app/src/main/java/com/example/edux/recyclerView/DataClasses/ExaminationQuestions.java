package com.example.edux.recyclerView.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class ExaminationQuestions implements Parcelable {

    private final int questionNumber;
    private final String Question;
    private final String subscript;
    private final String imageResource;

    public ExaminationQuestions(int questionNumber, String question, String subscript, String imageResource) {
        this.questionNumber = questionNumber;
        Question = question;
        this.subscript = subscript;
        this.imageResource = imageResource;
    }

    protected ExaminationQuestions(Parcel in) {
        questionNumber = in.readInt();
        Question = in.readString();
        subscript = in.readString();
        imageResource = in.readString();
    }

    public static final Creator<ExaminationQuestions> CREATOR = new Creator<ExaminationQuestions>() {
        @Override
        public ExaminationQuestions createFromParcel(Parcel in) {
            return new ExaminationQuestions(in);
        }

        @Override
        public ExaminationQuestions[] newArray(int size) {
            return new ExaminationQuestions[size];
        }
    };

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestion() {
        return Question;
    }

    public String getSubscript() {
        return subscript;
    }

    public String getImageResource() {
        return imageResource;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionNumber);
        dest.writeString(Question);
        dest.writeString(subscript);
        dest.writeString(imageResource);
    }
}
