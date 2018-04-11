package com.apitechnosoft.mrhelper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.RecommendedServiceAdapter;
import com.apitechnosoft.mrhelper.adapters.ServicesListAdapter;

public class ServicesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.service_recycler_view);

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    //ServicesListAdapter mAdapter = new ServicesListAdapter(movieList);
        //recyclerView.setAdapter(mAdapter);
    }
}
