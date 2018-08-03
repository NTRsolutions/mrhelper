package com.apitechnosoft.mrhelper.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.CompleteJobList;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.List;

public class CompletedServiceAdapter extends RecyclerView.Adapter<CompletedServiceAdapter.MyViewHolder> {

    private List<CompleteJobList> bookservicelists;
    Context mContext;
    Typeface materialdesignicons_font;
    private boolean onGoingFlag;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sewrvicename, serviceamount, aftertaxamount, totalservice, entrydate, address, cancel,bookingdate;

        public MyViewHolder(View view) {
            super(view);
            sewrvicename = (TextView) view.findViewById(R.id.sewrvicename);
            serviceamount = (TextView) view.findViewById(R.id.serviceamount);
            aftertaxamount = (TextView) view.findViewById(R.id.aftertaxamount);
            totalservice = (TextView) view.findViewById(R.id.totalservice);
            entrydate = (TextView) view.findViewById(R.id.entrydate);
            address = (TextView) view.findViewById(R.id.address);
            cancel = (TextView) view.findViewById(R.id.cancel);
            bookingdate = (TextView) view.findViewById(R.id.bookingdate);
        }
    }


    public CompletedServiceAdapter(Context mContext, List<CompleteJobList> list, boolean onGoingFlag) {
        this.bookservicelists = list;
        this.mContext = mContext;
        this.onGoingFlag = onGoingFlag;
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
        holder.sewrvicename.setText(bookservicelists.get(position).getServicesno());
        holder.serviceamount.setText("Rs." + bookservicelists.get(position).getServiceamount());
        holder.aftertaxamount.setText("Rs." + bookservicelists.get(position).getAftertaxamount());
        holder.totalservice.setText(bookservicelists.get(position).getTotalservice());
        holder.bookingdate.setText(bookservicelists.get(position).getEntrydate());
        holder.entrydate.setText(bookservicelists.get(position).getTxtdate1()+","+bookservicelists.get(position).getTimepicker());
        holder.address.setText(bookservicelists.get(position).getHouseno() + " " + bookservicelists.get(position).getLoc() + "," + bookservicelists.get(position).getLandmark());
        holder.cancel.setVisibility(View.GONE);
    }


    @Override
    public int getItemCount() {
        return bookservicelists.size();
    }

}



