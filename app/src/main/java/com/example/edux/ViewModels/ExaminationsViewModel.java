package com.example.edux.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edux.Repositories.AppRepository;
import com.example.edux.recyclerView.DataClasses.ExaminationQuestions;
import com.example.edux.recyclerView.DataClasses.Examinations;

import java.util.ArrayList;
import java.util.List;

public class ExaminationsViewModel extends AndroidViewModel {

    MutableLiveData<List<Examinations>> exams;
    List<ExaminationQuestions>  qnlist;
    int adaptorPosition;

    public ExaminationsViewModel(@NonNull Application application) {
        super(application);

        qnlist = new ArrayList<>();
        initExamsQuestions();
    }


    public LiveData<List<Examinations>> getExams() {
        return exams;
    }

    public void init(){
        if(exams == null){
            AppRepository repo = AppRepository.getInstance();
            exams = new MutableLiveData<>();

            if(repo.getExam().size() ==0){
                throw new RuntimeException("The array size is zero");
            }


            exams.setValue(repo.getExam());

        }
    }

    private List<ExaminationQuestions> initExamsQuestions(){

        ExaminationQuestions qn = new ExaminationQuestions(1, "What is your name?", "a", null);
        ExaminationQuestions qn2 = new ExaminationQuestions(2, "What are your parents' name?", "a", null);
        ExaminationQuestions qn3 = new ExaminationQuestions(1, "Where do you come from?", "b", null);
        ExaminationQuestions qn4 = new ExaminationQuestions(3, "Which year were you born?", "a", null);
        ExaminationQuestions qn5 = new ExaminationQuestions(5, "Where do you live now?", "a", null);

        qnlist.add(qn);
        qnlist.add(qn3);
        qnlist.add(qn2);
        qnlist.add(qn4);
        qnlist.add(qn5);

        return qnlist;
    }

    public int getAdaptorPosition() {
        return adaptorPosition;
    }

    public void setAdaptorPosition(int adaptorPosition) {
        this.adaptorPosition = adaptorPosition;
    }

    public List<ExaminationQuestions> getQuestionList() {
        return qnlist;
    }
}
