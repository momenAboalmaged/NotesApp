package com.example.notesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.models.Note;

import java.util.List;

public class RecyclerNotesAdapter extends RecyclerView.Adapter<RecyclerNotesAdapter.ViewHolder>  {
   List<Note> noteArrayList;

    public RecyclerNotesAdapter(List<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    @NonNull
    @Override
    public RecyclerNotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerNotesAdapter.ViewHolder holder, int position) {
        Note currentNote=noteArrayList.get(position);
        holder.desc.setText(currentNote.getDescription());
        holder.tvCategory.setText(currentNote.getCategory());



        if (currentNote.getIsFavourite() == 1){
            holder.imageViewFav.setVisibility(View.VISIBLE);}
        else{
            currentNote.setIsFavourite(0);
            holder.imageViewFav.setVisibility(View.GONE);}


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNote.getIsFavourite() == 0){
                    currentNote.setIsFavourite(1);}
                else {currentNote.setIsFavourite(0);}
                notifyItemRangeChanged(holder.getAdapterPosition(), noteArrayList.size());


            }
        });
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView tvCategory;
        ImageView imageViewFav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc=itemView.findViewById(R.id.description);
            tvCategory=itemView.findViewById(R.id.category);
            imageViewFav=itemView.findViewById(R.id.fav);


        }
    }

}
