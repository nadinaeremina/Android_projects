package com.example.recyclerviewhomework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    interface OnStateClickListener {
        void onStateClick (Animal animal, int position);
    }

    OnStateClickListener onClickListener;
    LayoutInflater inflater;
    List<Animal> animals;

    public AnimalAdapter(Context context, List<Animal> animals, OnStateClickListener onClickListener) {
        this.animals = animals;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal animal = animals.get(position);
        holder.flagView.setImageResource(animal.getFlagResource());
        holder.speciesView.setText(animal.getSpecies());
        holder.nicknameView.setText(animal.getNickname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onStateClick(animal, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flagView;
        TextView speciesView, nicknameView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flag);
            speciesView = itemView.findViewById(R.id.species);
            nicknameView = itemView.findViewById(R.id.nickname);
        }
    }
}
