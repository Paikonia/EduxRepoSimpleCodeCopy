 package com.example.edux.fragments.mainpagefragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.OtherActivities.ExaminationOpenActivity;
import com.example.edux.R;
import com.example.edux.ViewModels.ExaminationsViewModel;
import com.example.edux.recyclerView.Adaptors.ExaminationAdaptor;
import com.example.edux.recyclerView.DataClasses.ExaminationQuestions;
import com.example.edux.recyclerView.DataClasses.Examinations;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ExaminationFragment extends Fragment implements ExaminationAdaptor.ExaminationListener {

    boolean expandState = false;

    View v;
    Toolbar tb;
    CollapsingToolbarLayout ctbl;
    AppCompatActivity aca;
    RecyclerView rv;
    ExaminationAdaptor adaptor;

    ExaminationsViewModel viewModel;

    FloatingActionButton ascending, descending;
    ExtendedFloatingActionButton expand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.examination_fragment, container, false);
        tb = v.findViewById(R.id.examination_toolbar);
        ctbl = v.findViewById(R.id.examination_toolbar_layout);
        aca = (AppCompatActivity) getActivity();
        rv = v.findViewById(R.id.examination_recycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptor = new ExaminationAdaptor(this);
        aca.setSupportActionBar(tb);
        tb.setTitle("Examination & Revision papers");
        tb.setTitleTextColor(getResources().getColor(R.color.white));


        expand = v.findViewById(R.id.filterActivator_exam);
        ascending = v.findViewById(R.id.date_ascending_exam);
        descending = v.findViewById(R.id.date_descending_exam);

        viewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(ExaminationsViewModel.class);

        viewModel.init();


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*The rest of the work is done here*/

        adaptor.setList(viewModel.getExams().getValue());
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
    public void OnExaminationClick(int position) {

        Intent intent = new Intent(getActivity(), ExaminationOpenActivity.class);
        intent.putExtra("Exam", viewModel.getExams().getValue().get(position));

        aca.startActivity(intent);

    }
}
