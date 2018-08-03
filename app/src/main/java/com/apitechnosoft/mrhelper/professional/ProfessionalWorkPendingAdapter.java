package com.apitechnosoft.mrhelper.professional;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.Sal1ServiceActivity;
import com.apitechnosoft.mrhelper.adapters.OnGoingBookingAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.ContentMybooking;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.models.OrderBookedListEngineerWise;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalWorkPendingAdapter extends RecyclerView.Adapter<ProfessionalWorkPendingAdapter.MyViewHolder> {

    private ArrayList<OrderBookedListEngineerWise> bookservicelists;
    Context mContext;
    Typeface materialdesignicons_font;
    private boolean completeFlag;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sewrvicename, serviceamount, totalamount, customerphone, address, complete, customerName, servicedate, bookingdate, jobId, email;

        public MyViewHolder(View view) {
            super(view);
            sewrvicename = (TextView) view.findViewById(R.id.sewrvicename);
            jobId = (TextView) view.findViewById(R.id.jobId);
            serviceamount = (TextView) view.findViewById(R.id.serviceamount);
            totalamount = (TextView) view.findViewById(R.id.totalamount);
            customerphone = (TextView) view.findViewById(R.id.customerphone);
            address = (TextView) view.findViewById(R.id.address);
            complete = (TextView) view.findViewById(R.id.complete);
            customerName = (TextView) view.findViewById(R.id.customerName);
            servicedate = (TextView) view.findViewById(R.id.servicedate);
            bookingdate = (TextView) view.findViewById(R.id.bookingdate);
            jobId = (TextView) view.findViewById(R.id.jobId);
            email = (TextView) view.findViewById(R.id.email);
        }
    }


    public ProfessionalWorkPendingAdapter(Context mContext, ArrayList<OrderBookedListEngineerWise> list, boolean completeFlag) {
        this.bookservicelists = list;
        this.mContext = mContext;
        this.completeFlag = completeFlag;
        materialdesignicons_font = Typeface.createFromAsset(mContext.getAssets(), "fonts/materialdesignicons-webfont.otf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.professional_pending_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.sewrvicename.setText(bookservicelists.get(position).getServiceName());
        holder.serviceamount.setText("Rs." + bookservicelists.get(position).getServiceAmount());
        holder.totalamount.setText("Rs." + bookservicelists.get(position).getTotalAmount());
        holder.jobId.setText("Job ID :" + bookservicelists.get(position).getJobid());
        holder.bookingdate.setText(bookservicelists.get(position).getEnterdat());
        holder.servicedate.setText(bookservicelists.get(position).getServiceDate() + " ," + bookservicelists.get(position).getServiceTime());
        holder.customerName.setText("Name : " + bookservicelists.get(position).getName());
        holder.customerphone.setText("Phone : " + bookservicelists.get(position).getMobile());
        holder.email.setText("Email Id : " + bookservicelists.get(position).getEmailId());
        holder.address.setText("Address : " + bookservicelists.get(position).getHouseno() + " " + bookservicelists.get(position).getLocation() + "," + bookservicelists.get(position).getLandmark());
        if (completeFlag) {
            holder.complete.setVisibility(View.GONE);
        } else {
            holder.complete.setVisibility(View.VISIBLE);
        }
        holder.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateBookingActivity.class);
                intent.putExtra("sNumber", bookservicelists.get(position).getSno());
                intent.putExtra("JobId", bookservicelists.get(position).getJobid());
                intent.putExtra("PhooneNo", bookservicelists.get(position).getMobile());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookservicelists.size();
    }

}
