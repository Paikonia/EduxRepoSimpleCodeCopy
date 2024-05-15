package com.example.edux.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.edux.InternetClasses.DataClasses.Posts;
import com.example.edux.Repositories.AppRepository;
import com.example.edux.Repositories.InternetRepository;
import com.example.edux.recyclerView.DataClasses.NewsFeed;

import java.util.ArrayList;
import java.util.List;

public class NewFeedViewModel extends AndroidViewModel {

    AppRepository appRepo;
    InternetRepository intRepo;
    private MutableLiveData<List<NewsFeed>> newsData;
    List<NewsFeed> nf;
    private MutableLiveData<List<Posts>> posts;

    public NewFeedViewModel(@NonNull Application application) {
        super(application);

        posts = new MutableLiveData<>();

        if(newsData == null){
            newsData = new MutableLiveData<>();
            appRepo = AppRepository.getInstance();
            intRepo = InternetRepository.getInstance(application);

            newsData.setValue(appRepo.getNews());
            nf = new ArrayList<>();
            appRepo.getLocalNews(application, nf);

            posts.setValue(intRepo.getOnlinePosts());

            if(nf.size()!= 0)
                newsData.setValue(nf);
            else
                newsData.setValue(new ArrayList<>());
        }




    }


    public LiveData<List<NewsFeed>> getNewsData() {
        return newsData;
    }

    public LiveData<List<Posts>> getPosts() {
        return posts;
    }
}
