package com.example.edux.OtherActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.edux.R;
import com.example.edux.recyclerView.DataClasses.NotesAndReferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesOpenActivity extends AppCompatActivity {

    NotesAndReferences notes;

    Toolbar tb;

    FloatingActionButton fab1;

    LinearLayout temp;
    EditText p_title, p_body;
    TextView p_cancel, p_save;
    TextView notes_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_open_activity);

        Intent intent = getIntent();

        notes = intent.getParcelableExtra("Note");

        tb = findViewById(R.id.main_fragment_toolbar);

        temp = findViewById(R.id.temporary_notes_input);
        tb.setTitle(notes.getTitle());

        setSupportActionBar(tb);

        notes_view = findViewById(R.id.main_fragment_notes);

        fab1 = findViewById(R.id.main_fragment_fab);

        notes_view.setText(notes.getData());
        p_title = findViewById(R.id.p_note_title);
        p_body = findViewById(R.id.p_note_body);
        p_cancel = findViewById(R.id.p_note_cancel_button);
        p_save = findViewById(R.id.p_note_save_button);

    }

    @Override
    protected void onStart() {
        super.onStart();
        fab1.setOnClickListener(v->{
            temp.setVisibility(View.VISIBLE);
            p_save.setClickable(true);
            p_cancel.setClickable(true);
            p_save.setFocusable(true);
            p_cancel.setFocusable(true);
        });

        p_save.setOnClickListener(v->{
            String title = p_title.getText().toString();
            String body = p_body.getText().toString();

            if(title.equals("") || body.equals("")){
                if(title.equals("") ){
                    p_title.setHint("Input note");
                    p_title.setHintTextColor(getResources().getColor(R.color.error));
                }
                if(body.equals("")){
                    p_body.setHint("Please input body");
                    p_body.setHintTextColor(getResources().getColor(R.color.error));

                }
                return;
            }

            AsyncTask.execute(()->{

            });

            temp.setVisibility(View.INVISIBLE);
            p_save.setClickable(false);
            p_cancel.setClickable(false);
            p_save.setFocusable(false);
            p_cancel.setFocusable(false);

        });

        p_cancel.setOnClickListener(v->{

            String title = p_title.getText().toString();
            String body = p_body.getText().toString();

            if(!title.equals("") || !body.equals("")){
                Toast.makeText(this, "This will show an alert that confirms weather the user want to close", Toast.LENGTH_SHORT).show();
            }else{
                temp.setVisibility(View.INVISIBLE);
                p_save.setClickable(false);
                p_cancel.setClickable(false);
                p_save.setFocusable(false);
                p_cancel.setFocusable(false);
            }

        });
    }
}
