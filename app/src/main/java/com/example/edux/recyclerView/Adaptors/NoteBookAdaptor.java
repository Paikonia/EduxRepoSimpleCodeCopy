package com.example.edux.recyclerView.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.R;
import com.example.edux.recyclerView.DataClasses.NotesAndReferences;

import java.util.ArrayList;
import java.util.List;

public class NoteBookAdaptor extends RecyclerView.Adapter<NoteBookAdaptor.NotesAndBooksHolder> {

    List<NotesAndReferences> dataList = new ArrayList<>();
    NotesClicked listener;
    public NoteBookAdaptor(NotesClicked listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesAndBooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ab = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_tab, parent, false);
        return new NotesAndBooksHolder(ab, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAndBooksHolder holder, int position) {
        NotesAndReferences r = dataList.get(position);
        holder.setNotesTitlee(r.getTitle());
        holder.setNotesData(r.getData());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setDataList(List<NotesAndReferences> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    class NotesAndBooksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        NotesClicked listener;
        private TextView title, subject,  NotesData;
        public NotesAndBooksHolder(@NonNull View itemView, NotesClicked listener) {
            super(itemView);
            title = itemView.findViewById(R.id.notes_title);
            NotesData = itemView.findViewById(R.id.notes_data);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        public void setNotesData(String data) {
            this.NotesData.setText(data);
        }

        public void setNotesTitlee(String title) {
            this.title.setText(title);
        }

        @Override
        public void onClick(View v) {
            listener.onNotesClicked(getAdapterPosition());
        }
    }

    public interface NotesClicked{
        void onNotesClicked(int position);
    }

}
