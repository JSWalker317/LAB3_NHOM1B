package com.example.Lab3_Nhom1B.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Lab3_Nhom1B.R;
import com.example.Lab3_Nhom1B.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<Note> noteData;
    private final OnItemClickListener mlistener;

    public NoteAdapter(ArrayList<Note> noteData, OnItemClickListener listener) {
        this.mlistener = listener;
        this.noteData = noteData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, @SuppressLint("RecyclerView") int i) {
        final Note note = noteData.get(i);
        if (note == null) {
            return;
        }
        viewholder.imgAnimal.setImageResource(note.getId());
        viewholder.tvTitle.setText(note.getTitle());
        viewholder.tvDescription.setText(note.getDescription());
        viewholder.tvCreateDate.setText(note.getCreateDate());
        viewholder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onItemClick(note);
            }
        });
        viewholder.img_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onItemupdate(note, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (noteData != null) {
            return noteData.size();
        }

        return 0;

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvCreateDate;
        private TextView tvDescription;
        private ImageView imgAnimal;
        private RelativeLayout layout_item;
        private ImageView img_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreateDate = itemView.findViewById(R.id.tv_create_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgAnimal = itemView.findViewById(R.id.img_animals);
            layout_item = itemView.findViewById(R.id.layout_item);
            img_update =itemView.findViewById(R.id.img_update);

        }
    }
}
