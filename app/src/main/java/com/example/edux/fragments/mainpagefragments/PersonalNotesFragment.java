package com.example.edux.fragments.mainpagefragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;
import com.example.edux.OtherActivities.PersonalNotesActivity;
import com.example.edux.R;
import com.example.edux.ViewModels.PersonalNotesViewModel;
import com.example.edux.recyclerView.Adaptors.PersonalNotesAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PersonalNotesFragment extends Fragment implements PersonalNotesAdaptor.PersonalNoteClicked {

    AppCompatActivity aca;
    View v;

    Toolbar tb;
    PersonalNotesAdaptor adaptor;
    PersonalNotesViewModel viewModel;
    FloatingActionButton open;

    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.person_notes_fragment,container, false);


        rv = v.findViewById(R.id.personal_notes_fragment);
        open = v.findViewById(R.id.opening_fab);
        tb = v.findViewById(R.id.personal_notes_toolbar);


        tb.setTitle("Personal notes");

        adaptor = new PersonalNotesAdaptor(this);
        aca = (AppCompatActivity) getActivity();

        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(aca.getApplication())
                .create(PersonalNotesViewModel.class);
        aca.setSupportActionBar(tb);

        adaptor.setNotes(viewModel.getNotes().getValue());

        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adaptor);

        open.setOnClickListener(v->{
            Intent i = new Intent(aca, PersonalNotesActivity.class);

            startActivity(i);
        });

    }

    @Override
    public void onNoteClicked(int position) {
        Intent i = new Intent(aca, PersonalNotesActivity.class);
        //Here the note is set
        i.putExtra("Note", viewModel.getNotes().getValue().get(position));
        startActivity(i);
    }
}
