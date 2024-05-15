package com.example.edux.fragments.mainpagefragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.GetLocalData;
import com.example.edux.OtherActivities.NotesOpenActivity;
import com.example.edux.R;
import com.example.edux.ViewModels.NotesAndReferencesViewModel;
import com.example.edux.recyclerView.Adaptors.NoteBookAdaptor;
import com.example.edux.recyclerView.DataClasses.NotesAndReferences;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteAndBooksFragment extends Fragment implements NoteBookAdaptor.NotesClicked {

    boolean expandState = false;

    NotesAndReferencesViewModel viewModel;
    View v;
    RecyclerView rv;
    NoteBookAdaptor adaptor;

    Toolbar tb;
    AppCompatActivity aca;

    ExtendedFloatingActionButton expand;
    FloatingActionButton ascending, descending;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.main_page_books_notes_fragment_view, container, false);
        rv = v.findViewById(R.id.notes_fragament_recycler);

        aca = (AppCompatActivity)getActivity();
        tb = v.findViewById(R.id.notes_toolbar);
        aca.setSupportActionBar(tb);
        tb.setTitle("Notes");
        tb.setTitleTextColor(getResources().getColor(R.color.white));
        viewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(NotesAndReferencesViewModel.class);
        viewModel.init();

        expand = v.findViewById(R.id.filterActivator_notes);
        ascending = v.findViewById(R.id.date_ascending_notes);
        descending = v.findViewById(R.id.date_descending_notes);

        viewModel.getNotesAndReferences()
                .observe(getViewLifecycleOwner(), NotesAndReferences -> adaptor.notifyDataSetChanged());
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adaptor = new NoteBookAdaptor(this);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptor.setDataList(viewModel.getNotesAndReferences().getValue());
        rv.setAdapter(adaptor);

        expand.setOnClickListener(v->{
            expandClicked();
        });

        ascending.setOnClickListener(v->{
            sortAscending();
        });

        descending.setOnClickListener(v->{
            sortDescending();
        });


    }


    @Override
    public void onNotesClicked(int position) {
        Intent intent = new Intent(aca, NotesOpenActivity.class);
        intent.putExtra("Note", viewModel.getNotesAndReferences().getValue().get(position));
        aca.startActivity(intent);
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

}
