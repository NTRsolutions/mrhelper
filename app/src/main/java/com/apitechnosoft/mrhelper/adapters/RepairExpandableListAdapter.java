package com.apitechnosoft.mrhelper.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.models.MenuheadingtData;
import com.apitechnosoft.mrhelper.models.RepairItemsListParentModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RepairExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    // child data in format of header title, child title
    //ArrayList<FavoriteItemsListParentClass> arrayParents;
    List<RepairItemsListParentModel> arrayParents;

    public RepairExpandableListAdapter(Context context,  List<RepairItemsListParentModel> arrayParents) {
        this.context = context;
        this.arrayParents = arrayParents;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.arrayParents.get(groupPosition).getSubmenuList().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.repair_child_row, null);
            //convertView.setPadding(25, 0, 0, 0);
        }

        final TextView menuname = (TextView) convertView.findViewById(R.id.menuname);

        String childData = arrayParents.get(groupPosition).getSubmenuList().get(childPosition).getServiceName();
        menuname.setText(childData);

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        return convertView;
    }

    // counts the number of children items so the list knows how many times
    // calls getChildView() method
    @Override
    public int getChildrenCount(int groupPosition) {
        int size = 0;
        if (arrayParents.get(groupPosition).getSubmenuList() != null) {
            size = arrayParents.get(groupPosition).getSubmenuList().size();
        }
        return size;
    }

    // gets the title of each parent/group
    @Override
    public Object getGroup(int groupPosition) {
        return this.arrayParents.get(groupPosition);
    }

    // counts the number of group/parent items so the list knows how many
    // times calls getGroupView() method
    @Override
    public int getGroupCount() {
        return this.arrayParents.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.repair_parent_row, null);
        }
        //for always open child
       // ExpandableListView mExpandableListView = (ExpandableListView) parent;
        //mExpandableListView.expandGroup(groupPosition);

        final TextView parentmenuname = (TextView) convertView.findViewById(R.id.parentmenuname);

        final String menu = arrayParents.get(groupPosition).getServiceName();
        parentmenuname.setText(menu);



      /*  convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });*/

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
