package com.apitechnosoft.mrhelper.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.Searchreportdata;

import java.util.ArrayList;
import java.util.List;

public class SearchForServiceAdapter extends RecyclerView.Adapter<SearchForServiceAdapter.MyViewHolder> {

    private ArrayList<Searchreportdata> serviceList;
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView service;
        LinearLayout locationLayout;
        public MyViewHolder(View view) {
            super(view);
            service = (TextView) view.findViewById(R.id.service);
            locationLayout = (LinearLayout) view.findViewById(R.id.locationLayout);
        }
    }


    public SearchForServiceAdapter(Context mContext, ArrayList<Searchreportdata> list) {
        this.serviceList = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_for_service_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         holder.service.setText(serviceList.get(position).getEnteredat());
        holder.locationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }


}
