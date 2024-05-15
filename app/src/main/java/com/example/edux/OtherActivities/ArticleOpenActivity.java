package com.example.edux.OtherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.edux.InternetClasses.DataClasses.Posts;
import com.example.edux.R;
import com.example.edux.recyclerView.DataClasses.NewsFeed;

public class ArticleOpenActivity extends AppCompatActivity {

    NewsFeed news;
    TextView article, title;
    Posts post;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_open_activity);

        Toolbar toolbar = findViewById(R.id.article_open_toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        article = findViewById(R.id.article_open_article);

        Intent intent = getIntent();

        post = intent.getParcelableExtra("Post");
        news = intent.getParcelableExtra("Article");

        if(news != null){
            toolbar.setTitle(news.getTitle());

        }else if(post!=null){
            toolbar.setTitle(post.getTitle());

        }
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (post == null) {
            article.setText(news.getArticle());
        } else {
            article.setText(post.getBody());
        }
    }
}
