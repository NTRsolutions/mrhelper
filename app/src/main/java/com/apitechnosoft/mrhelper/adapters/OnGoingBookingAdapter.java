package com.apitechnosoft.mrhelper.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.HomeActivity;
import com.apitechnosoft.mrhelper.activity.LoginActivity;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.models.DetailListDashboarddata;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class OnGoingBookingAdapter extends RecyclerView.Adapter<OnGoingBookingAdapter.MyViewHolder> {

    private List<Bookservicelist> bookservicelists;
    Context mContext;
    Typeface materialdesignicons_font;
    private boolean onGoingFlag;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sewrvicename, serviceamount, aftertaxamount, totalservice, entrydate, address, cancel;

        public MyViewHolder(View view) {
            super(view);
            sewrvicename = (TextView) view.findViewById(R.id.sewrvicename);
            serviceamount = (TextView) view.findViewById(R.id.serviceamount);
            aftertaxamount = (TextView) view.findViewById(R.id.aftertaxamount);
            totalservice = (TextView) view.findViewById(R.id.totalservice);
            entrydate = (TextView) view.findViewById(R.id.entrydate);
            address = (TextView) view.findViewById(R.id.address);
            cancel = (TextView) view.findViewById(R.id.cancel);
        }
    }


    public OnGoingBookingAdapter(Context mContext, List<Bookservicelist> list, boolean onGoingFlag) {
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
        holder.sewrvicename.setText(bookservicelists.get(position).getServiceName());
        holder.serviceamount.setText("Rs." + bookservicelists.get(position).getServiceamount());
        holder.aftertaxamount.setText("Rs." + bookservicelists.get(position).getAftertaxamount());
        holder.totalservice.setText(bookservicelists.get(position).getTotalservice());
        holder.entrydate.setText(bookservicelists.get(position).getEntrydate());
        holder.address.setText(bookservicelists.get(position).getHouseno() + " " + bookservicelists.get(position).getLoc() + "," + bookservicelists.get(position).getLandmark());
        if (onGoingFlag) {
            holder.cancel.setVisibility(View.VISIBLE);
        } else {
            holder.cancel.setVisibility(View.GONE);
        }
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertNative(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return bookservicelists.size();
    }


    public void alertNative(final int pos) {
        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(mContext, R.style.AlertDialogTheme);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        //dialog.getWindow().getAttributes().windowAnimations = R.style.alertAnimation;
        dialog.setMessage("Are you sure you want to delete your service!");
        dialog.setTitle("Request Delete Alert");
        dialog.setButton(Dialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelRequest(bookservicelists.get(pos).getSno(), pos);
                dialog.dismiss();
            }
        });
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#000000"));
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#000000"));
    }

    private void cancelRequest(final int sno, final int pos) {
        if (Utility.isOnline(mContext)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(mContext);
            dotDialog.show();

            ServiceCaller serviceCaller = new ServiceCaller(mContext);
            serviceCaller.callCancelRquestSrvice(sno, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                        if (data != null) {
                            if (data.isStatus()) {
                                Toast.makeText(mContext, "Request cancel Successfully!", Toast.LENGTH_LONG).show();
                                DbHelper dbHelper = new DbHelper(mContext);
                                dbHelper.deleteMyBookingDataWithSno(sno);
                                bookservicelists.remove(pos);
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(mContext, "Request cancel not Successfully!", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, mContext);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, mContext);//off line msg....
        }

    }
}

