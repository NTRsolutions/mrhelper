package com.apitechnosoft.mrhelper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Locationreportdata;

import java.util.ArrayList;

public class RepairServiceAdapter extends RecyclerView.Adapter<RepairServiceAdapter.MyViewHolder> {

    private ArrayList<Locationreportdata> locationList;
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView repairservice;
        LinearLayout repairservicelayout;
        public MyViewHolder(View view) {
            super(view);
            repairservice = (TextView) view.findViewById(R.id.repairservice);
            repairservicelayout = (LinearLayout) view.findViewById(R.id.repairservicelayout);
        }
    }


    public RepairServiceAdapter(Context mContext, ArrayList<Locationreportdata> list) {
        this.locationList = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repair_service_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.repairservice.setText(locationList.get(position).getPinCode());
        holder.repairservicelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ((Activity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }


}

