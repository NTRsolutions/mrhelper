package com.apitechnosoft.mrhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.DetailListDashboarddata;

import java.util.ArrayList;
import java.util.List;

public class OnGoingBookingAdapter extends RecyclerView.Adapter<OnGoingBookingAdapter.MyViewHolder> {

    private List<Bookservicelist> bookservicelists;
    Context mContext;
    Typeface materialdesignicons_font;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sewrvicename, serviceamount, aftertaxamount, totalservice, entrydate, address;

        public MyViewHolder(View view) {
            super(view);
            sewrvicename = (TextView) view.findViewById(R.id.sewrvicename);
            serviceamount = (TextView) view.findViewById(R.id.serviceamount);
            aftertaxamount = (TextView) view.findViewById(R.id.aftertaxamount);
            totalservice = (TextView) view.findViewById(R.id.totalservice);
            entrydate = (TextView) view.findViewById(R.id.entrydate);
            address = (TextView) view.findViewById(R.id.address);
        }
    }


    public OnGoingBookingAdapter(Context mContext, List<Bookservicelist> list) {
        this.bookservicelists = list;
        this.mContext = mContext;
        materialdesignicons_font = Typeface.createFromAsset(mContext.getAssets(), "fonts/materialdesignicons-webfont.otf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.sewrvicename.setText(bookservicelists.get(position).getServiceName());
        holder.serviceamount.setText("Rs." + bookservicelists.get(position).getServiceamount());
        holder.aftertaxamount.setText("Rs." + bookservicelists.get(position).getAftertaxamount());
        holder.totalservice.setText(bookservicelists.get(position).getTotalservice());
        holder.entrydate.setText(bookservicelists.get(position).getEntrydate());
        holder.address.setText(bookservicelists.get(position).getHouseno() + " " + bookservicelists.get(position).getLoc() + "," + bookservicelists.get(position).getLandmark());
    }


    @Override
    public int getItemCount() {
        return bookservicelists.size();
    }


}

