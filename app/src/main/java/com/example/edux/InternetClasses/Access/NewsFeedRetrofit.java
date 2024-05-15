package com.example.edux.InternetClasses.Access;

import com.example.edux.InternetClasses.DataClasses.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsFeedRetrofit {

    @GET("posts")
    Call<List<Posts>> getOnlinePosts();



}
