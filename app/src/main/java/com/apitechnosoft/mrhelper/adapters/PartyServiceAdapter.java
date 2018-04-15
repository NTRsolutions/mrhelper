package com.apitechnosoft.mrhelper.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.SalModel;

import java.util.ArrayList;

public class PartyServiceAdapter extends RecyclerView.Adapter<PartyServiceAdapter.MyViewHolder> {

    private ArrayList<SalModel> list;
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView partyservice;
        LinearLayout partservicelayout;
        public MyViewHolder(View view) {
            super(view);
            partyservice = (TextView) view.findViewById(R.id.partyservice);
            partservicelayout = (LinearLayout) view.findViewById(R.id.partservicelayout);
        }
    }


    public PartyServiceAdapter(Context mContext, ArrayList<SalModel> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.party_service_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.partyservice.setText(list.get(position).getTitle());
        holder.partservicelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ((Activity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
