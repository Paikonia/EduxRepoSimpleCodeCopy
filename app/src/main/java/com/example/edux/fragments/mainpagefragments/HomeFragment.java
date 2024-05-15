package com.example.edux.fragments.mainpagefragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.InternetClasses.DataClasses.Posts;
import com.example.edux.OtherActivities.ArticleOpenActivity;
import com.example.edux.R;
import com.example.edux.ViewModels.NewFeedViewModel;
import com.example.edux.recyclerView.Adaptors.NewsFeedAdaptor;
import com.example.edux.recyclerView.DataClasses.NewsFeed;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements NewsFeedAdaptor.NewsFeedClicked {

    boolean expandState = false;

    View v;
    Toolbar toolbar;
    RecyclerView rv;
    NewsFeedAdaptor adaptor;
    AppCompatActivity aca;
    NewFeedViewModel viewModel;
    FloatingActionButton ascending, descending;
    ExtendedFloatingActionButton expand;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment_view, container, false);

        toolbar = v.findViewById(R.id.newsfeed_toolbar);
        toolbar.setTitle("News feed");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        expand = v.findViewById(R.id.filterActivator);
        ascending = v.findViewById(R.id.date_ascending);
        descending = v.findViewById(R.id.date_descending);

        aca = (AppCompatActivity) getActivity();
        aca.setSupportActionBar(toolbar);
        rv = v.findViewById(R.id.newsfeed_recycler_view);
        viewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(aca.getApplication())
                .create(NewFeedViewModel.class);

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adaptor = new NewsFeedAdaptor(this);

        viewModel.getNewsData().observe(getViewLifecycleOwner(), newsFeeds -> adaptor.notifyDataSetChanged());


        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        if(viewModel.getNewsData().getValue() == null)
            throw new RuntimeException("There was an error initing data");
        adaptor.setFulllist(viewModel.getNewsData().getValue());
        adaptor.setPosts(viewModel.getPosts().getValue());

        expand.setOnClickListener(v ->{
            expandClicked();
        });
        ascending.setOnClickListener(v->{
            sortAscending();
        });
        descending.setOnClickListener(v->{
            sortDescending();
        });

        rv.setAdapter(adaptor);
    }

    private void expandClicked(){
        if(!expandState){
            openButtons();
        }else{
            closeButtons();
        }

        expandState = !expandState;
    }

    private void openButtons() {
        ascending.setVisibility(View.VISIBLE);
        ascending.setFocusable(true);
        ascending.setClickable(true);

        descending.setVisibility(View.VISIBLE);
        descending.setFocusable(true);
        descending.setClickable(true);
        expand.setText(R.string.filter);
    }

    private void sortAscending(){
        Toast.makeText(aca, "Sorting in ascending order", Toast.LENGTH_SHORT).show();
        closeButtons();


    }

    private void sortDescending(){
        Toast.makeText(aca, "Sorting in descending order", Toast.LENGTH_SHORT).show();
        closeButtons();


    }

    void closeButtons(){
        ascending.setVisibility(View.INVISIBLE);
        ascending.setFocusable(false);
        ascending.setClickable(false);

        descending.setVisibility(View.INVISIBLE);
        descending.setFocusable(false);
        descending.setClickable(false);
        expand.setText("");
    }


    @Override
    public void onNewsFeedClicked(int position) {

        Intent intent = new Intent(aca, ArticleOpenActivity.class);

        int ab = viewModel.getNewsData().getValue() == null? 0 : viewModel.getNewsData().getValue().size();

        if(position<ab) {
            NewsFeed News = viewModel.getNewsData().getValue().get(position);

            intent.putExtra("Article", News);
            intent.putExtra("Post", (Bundle) null);
        }else{
            Posts pt = viewModel.getPosts().getValue().get(position-ab);
            intent.putExtra("Post", pt);
            intent.putExtra("Article", (Bundle) null);
        }
        aca.startActivity(intent);

    }

}
