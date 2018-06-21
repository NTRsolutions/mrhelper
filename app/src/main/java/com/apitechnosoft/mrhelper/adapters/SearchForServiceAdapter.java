package com.apitechnosoft.mrhelper.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.PartyServiceActivity;
import com.apitechnosoft.mrhelper.activity.RepairServiceActivity;
import com.apitechnosoft.mrhelper.activity.Sal1ServiceActivity;
import com.apitechnosoft.mrhelper.models.AllloactionData;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.Searchreportdata;

import java.util.ArrayList;
import java.util.List;

public class SearchForServiceAdapter extends RecyclerView.Adapter<SearchForServiceAdapter.MyViewHolder> implements Filterable {

    private ArrayList<AllloactionData> serviceList;
    private ArrayList<AllloactionData> mFilteredList;
    Context mContext;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = serviceList;
                } else {

                    ArrayList<AllloactionData> filteredList = new ArrayList<AllloactionData>();

                    for (AllloactionData data : serviceList) {

                        if (data.getEnteredat().toLowerCase().contains(charString)) {

                            filteredList.add(data);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<AllloactionData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView service;
        LinearLayout locationLayout;

        public MyViewHolder(View view) {
            super(view);
            service = (TextView) view.findViewById(R.id.service);
            locationLayout = (LinearLayout) view.findViewById(R.id.locationLayout);
        }
    }


    public SearchForServiceAdapter(Context mContext, ArrayList<AllloactionData> list) {
        this.serviceList = list;
        this.mFilteredList = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_for_service_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.service.setText(mFilteredList.get(position).getEnteredat());
        holder.locationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = mFilteredList.get(position).getPinCode();
                int sno = mFilteredList.get(position).getSno();
                if (type.equals("Sal1")) {
                    Intent intent = new Intent(mContext, Sal1ServiceActivity.class);
                    intent.putExtra("sNumber", sno);
                    mContext.startActivity(intent);
                } else if (type.equals("PartyM1")) {
                    Intent intent = new Intent(mContext, PartyServiceActivity.class);
                    intent.putExtra("sNumber", sno);
                    mContext.startActivity(intent);

                } else if (type.equals("Repair")) {
                    Intent intent = new Intent(mContext, RepairServiceActivity.class);
                    intent.putExtra("sNumber", sno);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


}
