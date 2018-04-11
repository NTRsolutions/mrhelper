package com.apitechnosoft.mrhelper.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.SelectLocationAdapter;
import com.apitechnosoft.mrhelper.models.ContentData;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SelectLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }
    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SelectLocationActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
    private void initView(){
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView searchicon = (TextView) findViewById(R.id.searchicon);
        searchicon.setTypeface(materialdesignicons_font);
        searchicon.setText(Html.fromHtml("&#xf349;"));
        String locationstr="";
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            locationstr= extras.getString("Location");

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.location_recycler_view);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            ArrayList<Locationreportdata> locationList = new Gson().fromJson(locationstr, new TypeToken<ArrayList<Locationreportdata>>() {
            }.getType());

             SelectLocationAdapter mAdapter = new SelectLocationAdapter(SelectLocationActivity.this,locationList);
            recyclerView.setAdapter(mAdapter);
        }


    }
}
