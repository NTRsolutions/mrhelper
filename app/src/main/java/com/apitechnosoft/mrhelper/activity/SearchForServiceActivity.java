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
import com.apitechnosoft.mrhelper.adapters.SearchForServiceAdapter;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.Searchreportdata;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchForServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_service);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SearchForServiceActivity.this)) {
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
        String servicestr="";
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            servicestr = extras.getString("Service");

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchforservice_recycler_view);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            ArrayList<Searchreportdata> serviceList = new Gson().fromJson(servicestr, new TypeToken<ArrayList<Searchreportdata>>() {
            }.getType());
             SearchForServiceAdapter mAdapter = new SearchForServiceAdapter(SearchForServiceActivity.this, serviceList);
             recyclerView.setAdapter(mAdapter);
        }
    }
}
