package com.example.edux.recyclerView.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.R;
import com.example.edux.recyclerView.DataClasses.Examinations;

import java.util.List;

public class ExaminationAdaptor extends RecyclerView.Adapter<ExaminationAdaptor.ExaminationHolder> {

    List<Examinations> list;
    ExaminationListener listener;
    public ExaminationAdaptor(ExaminationListener listener){
        this.listener = listener;

    }

    @NonNull
    @Override
    public ExaminationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.examination_revision_tab,parent, false);

        return new ExaminationHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExaminationHolder holder, int position) {
        Examinations exam = list.get(position);
        holder.setCode(exam.getCode());
        holder.setE_Title(exam.getTitle());
        holder.setDescription(exam.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Examinations> list) {

        this.list = list;
        notifyDataSetChanged();
    }




    class ExaminationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView Title, Code, Description;
        ExaminationListener listener;


        public ExaminationHolder(@NonNull View itemView, ExaminationListener listener) {
            super(itemView);
            Title = itemView.findViewById(R.id.examination_title);
            Code = itemView.findViewById(R.id.examination_paper_code);
            Description = itemView.findViewById(R.id.examination_description);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void setCode(String code) {
            Code.setText(code);
        }

        public void setE_Title(String title) {
            Title.setText(title);
        }

        public void setDescription(String description) {
            Description.setText(description);
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
