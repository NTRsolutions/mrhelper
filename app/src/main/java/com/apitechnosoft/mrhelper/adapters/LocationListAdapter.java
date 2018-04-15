package com.apitechnosoft.mrhelper.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Locationreportdata;


public class LocationListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Locationreportdata> locationList;

    private Context mContext;

    public LocationListAdapter(Context mContext,
                               ArrayList<Locationreportdata> list) {
        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.locationList = list;
    }

    @Override
    public int getCount() {

        return locationList.size();
    }

    @Override
    public Object getItem(int arg0) {

        return null;
    }

    @Override
    public long getItemId(int arg0) {

        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        ViewHolder holder;

        if (convertview == null) {
            convertview = mInflater.inflate(R.layout.location_row, null);
            holder = new ViewHolder();
            {
                holder.title = (TextView) convertview.findViewById(R.id.country);
                convertview.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertview.getTag();
        }

        holder.title.setText(locationList.get(position).getPinCode());

        return convertview;
    }

    public static class ViewHolder {
        TextView title, icon;

    }
}
