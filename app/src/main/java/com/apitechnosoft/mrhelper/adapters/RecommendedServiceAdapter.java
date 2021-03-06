package com.apitechnosoft.mrhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.LoginActivity;
import com.apitechnosoft.mrhelper.activity.PartyServiceActivity;
import com.apitechnosoft.mrhelper.activity.RepairServiceActivity;
import com.apitechnosoft.mrhelper.activity.Sal1ServiceActivity;
import com.apitechnosoft.mrhelper.activity.SignupActivity;
import com.apitechnosoft.mrhelper.models.HealthandwWllnessdata;
import com.apitechnosoft.mrhelper.models.HelpForBusinesdata;
import com.apitechnosoft.mrhelper.models.HomeCleaningdata;
import com.apitechnosoft.mrhelper.models.HomeDesigndata;
import com.apitechnosoft.mrhelper.models.OtherServiecsdata;
import com.apitechnosoft.mrhelper.models.PartyandEventdata;
import com.apitechnosoft.mrhelper.models.RecommendedServicesdata;
import com.apitechnosoft.mrhelper.models.Repairsdata;
import com.apitechnosoft.mrhelper.models.ShiftingHomesdata;
import com.apitechnosoft.mrhelper.models.WeddingServicesdata;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.squareup.picasso.Picasso;

/**
 * Created by usop1 on 6/4/18.
 */

public class RecommendedServiceAdapter extends RecyclerView.Adapter<RecommendedServiceAdapter.MyViewHolder> {

    private ArrayList<?> relist;
    public Context mContext;
    public int dataId;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView recoText;
        public ImageView recoImg;
        public LinearLayout card_view;

        public MyViewHolder(View view) {
            super(view);
            recoText = (TextView) view.findViewById(R.id.recoText);
            recoImg = (ImageView) view.findViewById(R.id.recoImg);
            card_view = (LinearLayout) view.findViewById(R.id.card_view);
        }
    }


    public RecommendedServiceAdapter(Context mContext, ArrayList<?> relist, int dataId) {
        this.relist = relist;
        this.mContext = mContext;
        this.dataId = dataId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (dataId == 1) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recommended_service_list_row, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.other_home_service_row, parent, false);
        }

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String title = "";
        String image = "";

        if (dataId == 1) {
            RecommendedServicesdata Obj = (RecommendedServicesdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            //String[] parts = filePath.split("webapps");
            //String part2 = parts[1];
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 2) {
            Repairsdata Obj = (Repairsdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 3) {
            HomeCleaningdata Obj = (HomeCleaningdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 4) {
            ShiftingHomesdata Obj = (ShiftingHomesdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 5) {
            HomeDesigndata Obj = (HomeDesigndata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 6) {
            WeddingServicesdata Obj = (WeddingServicesdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 7) {
            PartyandEventdata Obj = (PartyandEventdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 8) {
            HelpForBusinesdata Obj = (HelpForBusinesdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
            }
        }
        if (dataId == 9) {
            OtherServiecsdata Obj = (OtherServiecsdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
                //image = replacedString + Obj.getFileName();
            }
        }
        if (dataId == 10) {
            HealthandwWllnessdata Obj = (HealthandwWllnessdata) relist.get(position);
            title = Obj.getImageDescription();
            String filePath = Obj.getFilePath();
            if (filePath != null && !filePath.equals("")) {
                image = filePath.replace("C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps", "http://mrhelper.in:8084");
                //image = replacedString + Obj.getFileName();
            }
        }
        holder.recoText.setText(title);
        //Contants.ImageUrl+reObj.getFileName();
        String imageWithoutSpaces = image.replaceAll("\\s+", "");
        if (dataId == 1) {
            Picasso.with(mContext).load(imageWithoutSpaces).resize(400, 250).placeholder(R.drawable.logo).into(holder.recoImg);
        } else {
            Picasso.with(mContext).load(imageWithoutSpaces).resize(200, 200).placeholder(R.drawable.logo).into(holder.recoImg);
        }
        /*  Glide.with(mContext).load(Contants.ImageUrl+reObj.getFileName())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.recoImg);*/
        // http://www.mrhelper.in:8084/service/MrHelper_Service_images-1/salone.web

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "";
                int sno = 0;
                if (dataId == 1) {
                    RecommendedServicesdata Obj = (RecommendedServicesdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 2) {
                    Repairsdata Obj = (Repairsdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 3) {
                    HomeCleaningdata Obj = (HomeCleaningdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 4) {
                    ShiftingHomesdata Obj = (ShiftingHomesdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 5) {
                    HomeDesigndata Obj = (HomeDesigndata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 6) {
                    WeddingServicesdata Obj = (WeddingServicesdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 7) {
                    PartyandEventdata Obj = (PartyandEventdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 8) {
                    HelpForBusinesdata Obj = (HelpForBusinesdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 9) {
                    OtherServiecsdata Obj = (OtherServiecsdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
                if (dataId == 10) {
                    HealthandwWllnessdata Obj = (HealthandwWllnessdata) relist.get(position);
                    sno = Obj.getSno();
                    type = Obj.getType();
                }
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
        return relist.size();
    }

}
