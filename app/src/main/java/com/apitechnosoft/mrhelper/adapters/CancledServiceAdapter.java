package com.apitechnosoft.mrhelper.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Cancleservicelist;
import com.apitechnosoft.mrhelper.models.CompleteJobList;

import java.util.List;

public class CancledServiceAdapter extends RecyclerView.Adapter<CancledServiceAdapter.MyViewHolder> {

    private List<Cancleservicelist> bookservicelists;
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


    public CancledServiceAdapter(Context mContext, List<Cancleservicelist> list, boolean onGoingFlag) {
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
        holder.entrydate.setText(bookservicelists.get(position).getCancilationDate());
        holder.bookingdate.setText(bookservicelists.get(position).getEntrydate());
        holder.address.setText(bookservicelists.get(position).getHouseno() + " " + bookservicelists.get(position).getLoc() + "," + bookservicelists.get(position).getLandmark());
        holder.cancel.setVisibility(View.GONE);
    }


    @Override
    public int getItemCount() {
        return bookservicelists.size();
    }

}

