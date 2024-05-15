package com.example.edux.OtherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;
import com.example.edux.OtherActivities.Fragments.NotesOpenMainFragment;
import com.example.edux.OtherActivities.Fragments.NotesOpenNewNoteFragment;
import com.example.edux.OtherActivities.ViewModels.PersonalNotesSharedViewModel;
import com.example.edux.R;

public class PersonalNotesActivity extends AppCompatActivity implements NotesOpenMainFragment.NoteMainFragmentClicked {

    PersonalNote note;
    PersonalNotesSharedViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_note_activity);

        Display dis = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        dis.getRealMetrics(dm);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Intent intent = getIntent();
        note = intent.getParcelableExtra("Note");


        if(savedInstanceState == null){
            if(note != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.personal_note_fragment, new NotesOpenMainFragment(this, note))
                        .commit();
            }else{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.personal_note_fragment, new NotesOpenNewNoteFragment())
                        .commit();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.new_note_bottom, menu);
            return true;

    }

    @Override
    public void onClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.personal_note_fragment, new NotesOpenNewNoteFragment())
                .commit();
    }
}
