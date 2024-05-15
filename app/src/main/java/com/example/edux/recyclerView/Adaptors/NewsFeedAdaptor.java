package com.example.edux.recyclerView.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.InternetClasses.DataClasses.Posts;
import com.example.edux.R;
import com.example.edux.recyclerView.DataClasses.NewsFeed;

import java.util.List;

public class NewsFeedAdaptor extends RecyclerView.Adapter<NewsFeedAdaptor.NewsFeedHolder> {

    public final String TAG = "NewsFeedAdaptor";
    List<NewsFeed> fulllist;
    NewsFeedClicked listener;
    List<Posts> posts;
    public NewsFeedAdaptor(NewsFeedClicked listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ab = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.news_feed_tab,parent, false);

        return new NewsFeedHolder(ab, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedHolder holder, int position) {

        int fullListSize = (fulllist==null?  0: fulllist.size());
        if(position < fullListSize) {
            NewsFeed news = fulllist.get(position);
            holder.setArticle(news.getArticle());
            holder.setTitle(news.getTitle());
        }else if(position >= fullListSize){
                Posts post = posts.get(position - fullListSize);
                holder.setTitle(post.getTitle());
                holder.setArticle(post.getBody());

        }
    }

    @Override
    public int getItemCount() {

        return (fulllist==null? 0 :fulllist.size()) + (posts==null? 0: posts.size());
    }

    public void setFulllist(List<NewsFeed> fulllist) {

        if(fulllist== null || fulllist.size() ==0)
            throw  new RuntimeException("Passing a null to fulllist");

        this.fulllist = fulllist;
        notifyDataSetChanged();
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    class NewsFeedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title, article;
        View v;
        NewsFeedClicked listener;
        public NewsFeedHolder(@NonNull View itemView, NewsFeedClicked listener) {
            super(itemView);
            v = itemView;
            title = itemView.findViewById(R.id.newsfeed_article_title);
            article = itemView.findViewById(R.id.newsfeed_brief_article);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        public void setArticle(String article) {
            this.article.setText(article);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        @Override
        public void onClick(View v) {
            listener.onNewsFeedClicked(getAdapterPosition());
        }
    }

    public interface NewsFeedClicked{
        void onNewsFeedClicked(int position);
    }
}
