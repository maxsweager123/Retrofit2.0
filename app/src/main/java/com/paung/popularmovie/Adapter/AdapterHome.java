package com.paung.popularmovie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paung.popularmovie.DetailActivity;
import com.paung.popularmovie.Model.ModelMovie;
import com.paung.popularmovie.R;

import java.util.List;


/**
 * Created by mkddr on 16/12/17.
 */

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {
    private final List<ModelMovie.ResultMovie> modelMovie;
    Context c;

    public AdapterHome(List<ModelMovie.ResultMovie> modelMovie, Context c) {
        this.modelMovie = modelMovie;
        this.c = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textList.setText(modelMovie.get(position).title);
        Glide.with(c).load(modelMovie.get(position).getPoster()).
                into(holder.imageList);
        holder.rvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetail = new Intent(c, DetailActivity.class);
                goToDetail.putExtra("title", modelMovie.get(position).title);
                goToDetail.putExtra("deskripsi", modelMovie.get(position).overview);
                goToDetail.putExtra("gambar", modelMovie.get(position).getPoster());
                view.getContext().startActivity(goToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("SIZE MOVIE", "getItemCount: " + modelMovie.size());
        return modelMovie.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageList;
        TextView textList;
        RelativeLayout rvHome;

        public ViewHolder(View itemView) {
            super(itemView);
            rvHome = itemView.findViewById(R.id.rvHome);
            imageList = itemView.findViewById(R.id.image_list);
            textList = itemView.findViewById(R.id.text_list);
        }
    }
}
