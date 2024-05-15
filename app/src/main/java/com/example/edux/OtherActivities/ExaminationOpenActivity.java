package com.example.edux.OtherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.OtherActivities.ViewModels.PersonalNotesSharedViewModel;
import com.example.edux.R;
import com.example.edux.ViewModels.ExaminationsViewModel;
import com.example.edux.recyclerView.Adaptors.ExaminationQuestionAdaptor;
import com.example.edux.recyclerView.DataClasses.ExaminationQuestions;
import com.example.edux.recyclerView.DataClasses.Examinations;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ExaminationOpenActivity extends AppCompatActivity implements ExaminationQuestionAdaptor.ExaminationListener {

    Toolbar toolbar;
    Examinations exam;

    ExaminationsViewModel viewModel;

    RecyclerView rv;
    ExaminationQuestionAdaptor adaptor;
    List<ExaminationQuestions> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examinations_open_activity);


        toolbar = findViewById(R.id.open_exam_toolbar);
        Intent intent = getIntent();

        exam = intent.getParcelableExtra("Exam");

        toolbar.setTitle(exam.getTitle());
        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(ExaminationsViewModel.class);
        list = viewModel.getQuestionList();

        if(list==null){
            Toast.makeText(this, "The list did not get send across", Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(this, list.get(0).getQuestion(), Toast.LENGTH_SHORT).show();

            rv = findViewById(R.id.question_attempt_recycler);

            adaptor = new ExaminationQuestionAdaptor(this);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        adaptor.setList(list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adaptor);
    }

    @Override
    public void OnExaminationClick(int position) {
        Toast.makeText(this, "Clicked on an examination", Toast.LENGTH_SHORT).show();

    }
}
