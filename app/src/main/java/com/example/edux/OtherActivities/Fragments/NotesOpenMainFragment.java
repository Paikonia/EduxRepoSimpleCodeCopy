package com.example.edux.OtherActivities.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;
import com.example.edux.OtherActivities.ViewModels.PersonalNotesSharedViewModel;
import com.example.edux.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesOpenMainFragment extends Fragment {

    /**
     * This is the main fragment that will be opened when a note is clicked
     * It will only be opened whe the personal note is clicked and nothing else
     *
     * It also enables the use to update an existing note
     */

    /**
     *The onclick listener. this is to be changed by the navigational components as is the interface below
     */
    NoteMainFragmentClicked clicked;

    /**
     *This model is shared between
     * 1. NotesOpenMainFragment
     * 2. NotesOpenNewNoteFragment
     */

    /**
     *View to be initialised by find view by id.
     */
    PersonalNotesSharedViewModel viewModel;
    View v;
    EditText editBody;
    TextView viewBody;

    FloatingActionButton actionButton;
    RelativeLayout scrollView;
    Toolbar tb;

    /**
     * Initialise in other ways
     */
    PersonalNote note;
    AppCompatActivity aca;

    public NotesOpenMainFragment(NoteMainFragmentClicked clicked, @NonNull PersonalNote note){
        this.clicked = clicked;
        this.note = note;

        aca = (AppCompatActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notes_open_main_fragment, container, false);
        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(PersonalNotesSharedViewModel.class);


        viewModel.insertNote(new PersonalNote("Something", "Personal", "Something of a trial."));

        LiveData<List<PersonalNote>> li =viewModel.getAllNotes();


        initViews(v);

        viewBody.setText(note.getContent());
        aca = (AppCompatActivity) getActivity();
        tb.setTitle(note.getTitle());
        tb.inflateMenu(R.menu.notes_popup_menu);
        aca.setSupportActionBar(tb);



        return v;
    }

    private void initViews(View v){
        tb = v.findViewById(R.id.pna_toolbar);
        editBody = v.findViewById(R.id.edit_note);
        viewBody = v.findViewById(R.id.show_note);
        actionButton = v.findViewById(R.id.new_notes_open);
        scrollView = v.findViewById(R.id.edit_note_layout);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBody.setOnClickListener(v->{
            editBody.setVisibility(View.VISIBLE);
            editBody.setText(viewBody.getText().toString());
            scrollView.setVisibility(View.VISIBLE);
            viewBody.setVisibility(View.INVISIBLE);
            actionButton.setVisibility(View.GONE);

            editBody.setFocusedByDefault(true);
        });
        actionButton.setOnClickListener((w)->{
            clicked.onClick();
        });

    }

    /*
        This will replaced with the navigation components. The comment is there as a reminder
     */

    public interface NoteMainFragmentClicked{
        void onClick();
    }

}
