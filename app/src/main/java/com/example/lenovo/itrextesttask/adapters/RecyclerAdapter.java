package com.example.lenovo.itrextesttask.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.itrextesttask.models.Film;
import com.example.lenovo.itrextesttask.OnFilmClickListener;
import com.example.lenovo.itrextesttask.R;
import com.example.lenovo.itrextesttask.viewHolder.FilmViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<FilmViewHolder>{
    private ArrayList<Film> mDataset;
    private OnFilmClickListener filmClickListener;

    public RecyclerAdapter(ArrayList<Film> dataset, OnFilmClickListener filmClickListener) {
        mDataset = dataset;
        this.filmClickListener = filmClickListener;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_parent, parent, false);

        return new FilmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, final int position) {

        holder.getTvFilmName().setText(mDataset.get(position).getName());
        holder.getTvFilmNameEng().setText(mDataset.get(position).getName_eng());
        Picasso.get().load(Uri.parse(mDataset.get(position).getImage())).into(holder.getIvFilmImage());
        holder.getLlContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filmClickListener.onFilmClick(mDataset.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
