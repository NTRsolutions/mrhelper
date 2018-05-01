package com.apitechnosoft.mrhelper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.Servicelist;

import java.util.ArrayList;
import java.util.List;

public class ServiceSpinnerAdapter extends BaseAdapter {

    private ArrayList<Servicelist> serviceList;
    private LayoutInflater mInflater;

    public ServiceSpinnerAdapter(Context context, ArrayList<Servicelist> serviceList) {
        this.serviceList = serviceList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return serviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.spinner_row, null);
            holder = new ViewHolder();
            holder.cust_view = (TextView) convertView.findViewById(R.id.cust_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.cust_view.setText(serviceList.get(position).getService());
        return convertView;
    }


    static class ViewHolder {
        TextView cust_view; //spinner name
    }
}
