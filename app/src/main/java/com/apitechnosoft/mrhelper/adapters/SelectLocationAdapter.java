package com.apitechnosoft.mrhelper.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Locationreportdata;

import java.util.ArrayList;
import java.util.List;

public class SelectLocationAdapter extends RecyclerView.Adapter<SelectLocationAdapter.MyViewHolder> {

    private ArrayList<Locationreportdata> locationList;
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView location;
        LinearLayout locationLayout;
        public MyViewHolder(View view) {
            super(view);
            location = (TextView) view.findViewById(R.id.location);
            locationLayout = (LinearLayout) view.findViewById(R.id.locationLayout);
        }
    }


    public SelectLocationAdapter(Context mContext, ArrayList<Locationreportdata> list) {
        this.locationList = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         holder.location.setText(locationList.get(position).getPinCode());
holder.locationLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ((Activity)mContext).finish();
    }
});
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }


}
