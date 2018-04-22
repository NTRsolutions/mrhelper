package com.apitechnosoft.mrhelper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.RepairServiceAdapter;
import com.apitechnosoft.mrhelper.models.DetailListDashboarddata;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;

public class RepairServiceAddToCardActivity extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_service_add_to_card);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }
    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(RepairServiceAddToCardActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }
    private void initView(){
        TextView totalAmountText = (TextView) findViewById(R.id.totalAmount);
        TextView count = (TextView) findViewById(R.id.count);
        final String ServiceName = getIntent().getStringExtra("ServiceName");
        String MenuDetail = getIntent().getStringExtra("MenuDetail");
        TextView wheredo = (TextView) toolbar.findViewById(R.id.wheredo);
        wheredo.setText("Best "+ServiceName+" in This Area");

        ArrayList<DetailListDashboarddata> detaiArraylList = new Gson().fromJson(MenuDetail, new TypeToken<ArrayList<DetailListDashboarddata>>() {
        }.getType());
        ArrayList<DetailListDashboarddata> detailList=new  ArrayList<DetailListDashboarddata>();
        for(DetailListDashboarddata dashboarddata:detaiArraylList){
            if(dashboarddata.getName().equals(ServiceName)){
                detailList.add(dashboarddata);
            }
        }
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.repair_recycler_view);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            RepairServiceAdapter mAdapter = new RepairServiceAdapter(RepairServiceAddToCardActivity.this,detailList,ServiceName,totalAmountText);
            recyclerView.setAdapter(mAdapter);

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");

        TextView arrowicon = (TextView) findViewById(R.id.arrowicon);
        arrowicon.setTypeface(materialdesignicons_font);
        arrowicon.setText(Html.fromHtml("&#xf054;"));
        LinearLayout checkoutLayout= (LinearLayout) findViewById(R.id.checkoutLayout);
        checkoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("AmountPre", MODE_PRIVATE);
                float totalAmount = prefs.getFloat("totalAmount",0);
                Intent intent = new Intent(RepairServiceAddToCardActivity.this, AddressActivity.class);
                intent.putExtra("ServiceName", ServiceName);
                intent.putExtra("totalAmount", totalAmount);
                intent.putExtra("NavigationFlag", 2);
                startActivity(intent);

              /*  if(totalAmount>599){

                }else{
                    Utility.alertForErrorMessage("Min Checkout Amount - Rs.599",RepairServiceAddToCardActivity.this);
                }*/
            }
        });

    }
    private void addValue(){

    }

}
