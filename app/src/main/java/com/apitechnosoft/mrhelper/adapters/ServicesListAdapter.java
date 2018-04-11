package com.apitechnosoft.mrhelper.adapters;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;

import java.util.List;

/**
 * Created by neeraj on 6/4/18.
 */

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView recoText;
        ImageView recoImg;

        public MyViewHolder(View view) {
            super(view);
            recoText = (TextView) view.findViewById(R.id.recoText);
            recoImg = (ImageView) view.findViewById(R.id.recoImg);
        }
    }


    public ServicesListAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
       // holder.recoText.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}

