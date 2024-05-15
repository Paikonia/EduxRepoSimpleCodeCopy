package com.example.edux.recyclerView.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.R;
import com.example.edux.recyclerView.DataClasses.ExaminationQuestions;

import java.util.List;

public class ExaminationQuestionAdaptor extends RecyclerView.Adapter<ExaminationQuestionAdaptor.ExaminationQuestionViewHolder> {

    List<ExaminationQuestions> list;
    ExaminationListener listener;

    public ExaminationQuestionAdaptor(ExaminationListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExaminationQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.question_attempt_tab, parent, false);

        return new ExaminationQuestionViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExaminationQuestionViewHolder holder, int position) {
        holder.setNumber("Question "+list.get(position).getQuestionNumber()+list.get(position).getSubscript());
        holder.setQuestion(list.get(position).getQuestion());

        /*There is still the implementation for rendering image here*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ExaminationQuestions> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    protected class ExaminationQuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView number, question, edit, save, add;
        EditText answer;
        ExaminationListener listener;
        public ExaminationQuestionViewHolder(@NonNull View itemView, ExaminationListener listener) {
            super(itemView);

            this.listener = listener;

            number = itemView.findViewById(R.id.open_exam_question_number);
            question = itemView.findViewById(R.id.open_exam_question);
            edit = itemView.findViewById(R.id.edit_answer_button_button);
            save = itemView.findViewById(R.id.save_answer_button);
            answer = itemView.findViewById(R.id.open_exam_answer);

            itemView.setOnClickListener(this);

        }

        public void setNumber(String s) {
            this.number.setText(s);
        }

        public void setQuestion(String s) {
            this.question.setText(s);
        }



        @Override
        public void onClick(View v) {
            listener.OnExaminationClick(getAdapterPosition());
        }
    }



    public interface ExaminationListener{
        void OnExaminationClick(int position);
    }
}
