package com.example.edux.Repositories;

import android.content.Context;

import com.example.edux.GetLocalData;
import com.example.edux.recyclerView.DataClasses.Examinations;
import com.example.edux.recyclerView.DataClasses.NotesAndReferences;
import com.example.edux.recyclerView.DataClasses.NewsFeed;

import java.util.ArrayList;
import java.util.List;

public class AppRepository {

    private static AppRepository instance;
    private List<NewsFeed> news;
    private List<NotesAndReferences> notesAndReferences;
    private List<Examinations> exam;

    private static int ni = 0, nn =0;

    private AppRepository(){
        news = new ArrayList<>();
        notesAndReferences = new ArrayList<>();
        exam = new ArrayList<>();

        initExams();
    }

    public static AppRepository getInstance() {
        if(instance==null){
            instance = new AppRepository();
        }
        return instance;
    }

    void initExams(){

        Examinations e1 = new Examinations("Biology Revision", "Paper code for Biology", "Some biology revision Questions", "URL1");
        Examinations e2 = new Examinations("Physics Paper", "Paper code for Biology", "Some random physics paper with the given name", "URL2");
        Examinations e3 = new Examinations("Pure mathematics examination", "Paper code for Biology", "Pure math examination", "URL3");
        exam.add(e1);
        exam.add(e2);
        exam.add(e3);
    }


    public List<Examinations> getExam() {

        return exam;
    }

    public List<NewsFeed> getNews() {
        return news;
    }

    public List<NotesAndReferences> getNotesAndReferences() {
        return notesAndReferences;
    }


    public void getLocalNews(Context context, List<NewsFeed> news){
        GetLocalData.initialiseNews(context, news);
    }

    public void getLocalNotes(Context context, List<NotesAndReferences> list){
        GetLocalData.initialiseNotesAndReferences(context, list);
    }

}
