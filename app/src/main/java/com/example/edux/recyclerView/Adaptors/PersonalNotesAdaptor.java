package com.example.edux.recyclerView.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edux.DatabaseClasses.EntityClasses.PersonalNote;
import com.example.edux.R;

import java.util.ArrayList;
import java.util.List;

public class PersonalNotesAdaptor extends RecyclerView.Adapter<PersonalNotesAdaptor.PersonalNotesViewHolder> {

    List<PersonalNote> notes;
    PersonalNoteClicked listener;

    public PersonalNotesAdaptor(PersonalNoteClicked listener) {
        this.listener = listener;
        notes = new ArrayList<>(); // To avoid the Null pointer exception
    }

    @NonNull
    @Override
    public PersonalNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personal_notes_tab, parent, false);
        return new PersonalNotesViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalNotesViewHolder holder, int position) {
            PersonalNote note = notes.get(position);
            holder.setTitle(note.getTitle());
            holder.setSubject(note.getSubject());
            holder.setOverview(note.getContent());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<PersonalNote> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    static class PersonalNotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        PersonalNoteClicked clicked;
       TextView title, subject, overview;
        public PersonalNotesViewHolder(@NonNull View itemView, @NonNull PersonalNoteClicked clicked) {
            super(itemView);

            title = itemView.findViewById(R.id.personal_note_title);
            subject = itemView.findViewById(R.id.personal_note_subject);
            overview = itemView.findViewById(R.id.personal_note_overview);

            this.clicked = clicked;
            itemView.setOnClickListener(this);
        }

        public void setTitle(String t){
            title.setText(t);
        }

        public void setSubject(String t){
            subject.setText(t);
        }

        public void setOverview(String t){
            overview.setText(t);
        }

        @Override
        public void onClick(View v) {
            clicked.onNoteClicked(getAdapterPosition());
        }
    }

    public interface PersonalNoteClicked{
        void onNoteClicked(int position);
    }


}
