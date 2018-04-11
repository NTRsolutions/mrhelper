package com.apitechnosoft.mrhelper.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.SelectLocationAdapter;
import com.apitechnosoft.mrhelper.adapters.SlaServiceAdapter;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Sal1ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sal1_service);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        //chech Portait And LandSacpe Orientation
    }
        public void chechPortaitAndLandSacpe() {
            if (CompatibilityUtility.isTablet(Sal1ServiceActivity.this)) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
        private void initView(){
            Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");

            String locationstr="";
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                locationstr= extras.getString("Location");

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sla_recycler_view);
                recyclerView.setHasFixedSize(false);
                StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                ArrayList<Locationreportdata> locationList = new Gson().fromJson(locationstr, new TypeToken<ArrayList<Locationreportdata>>() {
                }.getType());

                SlaServiceAdapter mAdapter = new SlaServiceAdapter(Sal1ServiceActivity.this,locationList);
                recyclerView.setAdapter(mAdapter);
            }


        }
    }