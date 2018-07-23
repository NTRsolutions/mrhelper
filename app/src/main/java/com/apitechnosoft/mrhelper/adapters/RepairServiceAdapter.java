package com.apitechnosoft.mrhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.DetailListDashboarddata;
import com.apitechnosoft.mrhelper.models.Locationreportdata;

import java.util.ArrayList;

public class RepairServiceAdapter extends RecyclerView.Adapter<RepairServiceAdapter.MyViewHolder> {

    private ArrayList<DetailListDashboarddata> locationList;
    Context mContext;
    String ServiceName;
    Typeface materialdesignicons_font;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subHeading, oldPrice, newPrice, option2, option1, free, starting, tickIcon2;
        public TextView addCheckBox;
        LinearLayout repairservicelayout, layoutDecripton2,addLayout;

        public MyViewHolder(View view) {
            super(view);
            subHeading = (TextView) view.findViewById(R.id.subHeading);
            addCheckBox = (TextView) view.findViewById(R.id.addCheckBox);
            oldPrice = (TextView) view.findViewById(R.id.oldPrice);
            newPrice = (TextView) view.findViewById(R.id.newPrice);
            option2 = (TextView) view.findViewById(R.id.option2);
            option1 = (TextView) view.findViewById(R.id.option1);
            free = (TextView) view.findViewById(R.id.free);
            starting = (TextView) view.findViewById(R.id.starting);
            layoutDecripton2 = (LinearLayout) view.findViewById(R.id.layoutDecripton2);
            repairservicelayout = (LinearLayout) view.findViewById(R.id.repairservicelayout);
            addLayout = (LinearLayout) view.findViewById(R.id.addLayout);
            addCheckBox.setTypeface(materialdesignicons_font);
        }
    }


    public RepairServiceAdapter(Context mContext, ArrayList<DetailListDashboarddata> list, String ServiceName) {
        this.locationList = list;
        this.mContext = mContext;
        this.ServiceName = ServiceName;
        materialdesignicons_font = Typeface.createFromAsset(mContext.getAssets(), "fonts/materialdesignicons-webfont.otf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repair_service_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.subHeading.setText(locationList.get(position).getSubHeading());
        holder.oldPrice.setText("Rs." + locationList.get(position).getRate2());
        holder.newPrice.setText("Rs." + locationList.get(position).getRate1());
        holder.oldPrice.setPaintFlags(  holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.option2.setText(locationList.get(position).getOption2());
        holder.option1.setText(locationList.get(position).getOption());
        holder.free.setText(locationList.get(position).getFree());
        holder.starting.setText(locationList.get(position).getHeading());
        if (!locationList.get(position).getOption2().equals("")) {
            holder.layoutDecripton2.setVisibility(View.VISIBLE);
        } else {
            holder.layoutDecripton2.setVisibility(View.GONE);
        }
        //holder.addCheckBox.setSelected(locationList.get(position).isSelected());
        if (locationList.get(position).isSelected()) {
            holder.addCheckBox.setText(Html.fromHtml("&#xf134;"));
        } else {
            holder.addCheckBox.setText(Html.fromHtml("&#xf130;"));
        }
        holder.addLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean flage=false;
                if (locationList.get(position).isSelected()) {
                    locationList.get(position).setSelected(false);
                    flage=false;
                } else {
                    locationList.get(position).setSelected(true);
                    flage=true;//to check item seleeted or not
                }

                Intent intent = new Intent("AddItem");
                intent.putExtra("ItemSon", locationList.get(position).getSno());
                intent.putExtra("Price", locationList.get(position).getRate1());
                intent.putExtra("AddRemove", flage);
                mContext.sendBroadcast(intent);

                notifyItemChanged(position);
            }
        });

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

