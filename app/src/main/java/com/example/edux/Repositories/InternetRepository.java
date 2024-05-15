package com.example.edux.Repositories;

import android.content.Context;
import android.widget.Toast;

import com.example.edux.InternetClasses.Access.NewsFeedRetrofit;
import com.example.edux.InternetClasses.DataClasses.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InternetRepository {


    private List<Posts> onlinePosts;
    private Retrofit retrofit;
    private NewsFeedRetrofit api;
    private static InternetRepository instance;

    private InternetRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(NewsFeedRetrofit.class);
    }

    public static InternetRepository getInstance(Context context) {

        if(instance==null){
            instance = new InternetRepository();
            instance.setOnlinePosts(context);
        }

        return instance;
    }

    private void setOnlinePosts(Context context){
        Call<List<Posts>> apiOnlinePosts = api.getOnlinePosts();
        apiOnlinePosts.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful()){
                    onlinePosts = response.body();
                }
                else {
                    onlinePosts = new ArrayList<>();
                    onlinePosts.add(new Posts("Failed", 0,"Failed to get data from the internet or some other error"));
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(context, "There was an error while getting the post.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Posts> getOnlinePosts() {

        return onlinePosts;
    }

    void updateListOfPosts(Context context){
        //this method will be implemented to update the posts whenever the user refreshes
        //It will be implemented when the server is ready

        Toast.makeText(context, "Posts are up to date", Toast.LENGTH_SHORT).show();

    }

}
