package com.apitechnosoft.mrhelper.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.OnGoingBookingAdapter;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.models.Bookservicelist;

import java.util.List;


public class OldBookingFragment extends Fragment {


    public OldBookingFragment() {
        // Required empty public constructor
    }

    private Context context;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_old_booking, container, false);
        init();
        return view;
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DbHelper dbHelper = new DbHelper(context);
        List<Bookservicelist> bookservicelists= dbHelper.getAllMyBookingData();
        if(bookservicelists!=null) {
            OnGoingBookingAdapter mAdapter = new OnGoingBookingAdapter(context, bookservicelists);
            recyclerView.setAdapter(mAdapter);
        }
    }


}
