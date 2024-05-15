package com.example.edux.OtherActivities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;
import com.example.edux.OtherActivities.ViewModels.PersonalNotesSharedViewModel;
import com.example.edux.R;

public class NotesOpenNewNoteFragment extends Fragment {

    View v;
    Toolbar toolbar;
    AppCompatActivity aca;
    PersonalNotesSharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notes_open_new_note_fragment, container, false);

        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(PersonalNotesSharedViewModel.class);

        if(viewModel.getPersonalNote().getValue()==null){
            Toast.makeText(getContext(), "The notes are null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "The notes are note null", Toast.LENGTH_SHORT).show();
        }

        aca = (AppCompatActivity) getActivity();
        toolbar = v.findViewById(R.id.pna_toolbar);
        toolbar.setTitle("New Personal note");

        viewModel.insertNote(new PersonalNote("Something", "Personal", "Something of a trial."));
        viewModel.insertNote(new PersonalNote("Something", "Personal", "Something of a trial."));

        aca.setSupportActionBar(toolbar);
        return v;
    }

}
